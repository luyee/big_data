package com.caiw.dsg.hive2hbase

import java.io.FileInputStream
import java.util.Properties
import java.util.zip.CRC32

import com.beust.jcommander.{JCommander, ParameterException}
import org.apache.spark.sql.jdbc.{JdbcDialect, JdbcDialects}
import org.apache.spark.sql.SparkSession

object SparkKafkaHbase {
  def main(args: Array[String]): Unit = {

    val begin = System.currentTimeMillis()
    val dataValidationInfo = new DataValidationInfo()
    val jCommander = JCommander.newBuilder().addObject(dataValidationInfo).build()
    try{
      jCommander.parse(args:_*)
    }catch{
      case e:ParameterException =>
        println("Parameters invalid: "+e.getMessage)
        jCommander.usage()
        return
    }
    if(dataValidationInfo.isHelp){
      jCommander.usage()
    }

    println(dataValidationInfo.toString)
    val keyName = dataValidationInfo.getKeyName

    //read properties  (local)
    val props = new Properties()
    val loader = getClass.getClassLoader
    props.load(new FileInputStream(loader.getResource("hive.properties").getFile))

    //read properties  (cluster)  --file hive.properties
//    val props = new Properties()
//    props.load(new FileInputStream("hive.properties"))

    val hiveJdbcUrl = props.getProperty("url")

    println("begin : ["+begin+"]")
    //spark session
    val sparkSession = SparkSession.builder().appName("SparkMysql").master("local[8]").enableHiveSupport().getOrCreate()
    //执行语句
    println("Begin get rows : ["+begin+"]")
    val beginGetRows = System.currentTimeMillis()
    //table 1
    val sessionDF = sparkSession.sql("select * from "+dataValidationInfo.getSourceDbName+"."+dataValidationInfo.getSourceTableName)
    val endGetRows = System.currentTimeMillis()
    println("end get rows : ["+endGetRows+"] used["+(endGetRows-beginGetRows)+"]")
    import sparkSession.sqlContext.implicits._
    val index = sessionDF.schema.fieldNames.indexOf(keyName)
    sessionDF.rdd.mapPartitions {
      rows =>
        rows.map{
          row=>
            val crc32 = new CRC32()
            crc32.update(row.toString().getBytes())
            val crchex = LongUtils.LongToHex(crc32.getValue)
            (row.getString(index),crchex)
        }
    }.toDF(keyName,"crchex").createTempView("table1")

    //table 2
    //register dialect
    val dialect = JdbcDialects
    JdbcDialects.unregisterDialect(dialect.get(hiveJdbcUrl))
    JdbcDialects.registerDialect(HiveDialect)
    val jdbcDf = sparkSession.read
      .option("fetchsize","2000")
      .jdbc(hiveJdbcUrl,dataValidationInfo.getTargetDbName+"."+dataValidationInfo.getTargetTableName,props)
    jdbcDf.rdd.mapPartitions{
      rows =>
        rows.map{
          row =>
          val crc32 = new CRC32()
          crc32.update(row.toString().getBytes())
          val crchex = LongUtils.LongToHex(crc32.getValue)
          (row.getString(index),crchex)
        }
    }.toDF(keyName,"crchex").createTempView("table2")

    //获取不相等的数据
    //不等于<>
    sparkSession.sql("select t1."+keyName+" from table1 t1 left join table2 t2 " +
      "on t1."+keyName+"=t2."+keyName+" where t1.crchex <> t2.crchex or t2."+keyName+" is null")
      .show(false)

    sparkSession.close()
    val end = System.currentTimeMillis()
    println("End : ["+end+"] used ["+(end-begin)/1000.0+"] second")
  }
}

case object HiveDialect extends JdbcDialect {
  override def canHandle(url : String): Boolean = url.startsWith("jdbc:hive2")
  override def quoteIdentifier(colName: String): String = {
    colName.split('.').map(part => s"`$part`").mkString(".")
  }
}



