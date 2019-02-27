package com.caiw.etl

import java.io.FileInputStream
import java.util.{Properties, UUID}

import org.apache.spark.sql.{SaveMode, SparkSession}

object Hive2Oracle {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("hive2oracle").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()

    //params
    val latestDate = args(0)

    //read properties  (local)
        val props = new Properties()
        val loader = getClass.getClassLoader
        props.load(new FileInputStream(loader.getResource("hive2oracle.properties").getFile))
        props.setProperty("date",latestDate)

    //    read properties  (cluster)  --file /tmp/hive2oracle.properties
//    val props = new Properties()
//    props.load(new FileInputStream("hive2oracle.properties"))
//    props.setProperty("date",latestDate)

    val startTime = System.currentTimeMillis()
    //execute(hive data to oracle)
    val sql2 = "SELECT * FROM "+props.getProperty("hiveTableName")+" where "+props.getProperty("hiveDateFieldName") +
      " = '"+props.getProperty("date")+"'"
    ss.sql(sql2).show()
//      .write
//      .mode(SaveMode.Append)
//      .jdbc(props.getProperty("oracleDriverUrl"),props.getProperty("oracleDataTable"),props)
//    val endTime = System.currentTimeMillis()
//    //统计执行信息
//    val useTime = (endTime - startTime)/1000.0
//    val sql3 = "SELECT count(1) FROM "+props.getProperty("hiveTableName")+" where "+props.getProperty("hiveDateFieldName") +
//      " = '"+props.getProperty("date")+"'"
//    val count:Double = Integer.parseInt(ss.sql(sql3).rdd.first().get(0).toString)
//    val speed:Double = count/useTime
//    ////存储统计信息
//    import ss.sqlContext.implicits._
//    val executeInfo = Seq(
//      (UUID.randomUUID().toString,"hive2oracle",props.getProperty("hiveTableName"),props.getProperty("oracleDataTable"),useTime,
//        count,speed,startTime,endTime)
//    ).toDF("ID","EXECUTE_NAME","SOURCE_TABLE","TARGET_TABLE","EXECUTE_TIME","EXECUTE_COUNT","EXECUTE_SPEED","START_TIME","END_TIME")
//    executeInfo
//      .write
//      .mode(SaveMode.Append)
//      .jdbc(props.getProperty("oracleDriverUrl"),props.getProperty("oracleInfoTable"),props)

    ss.close()
  }

}
