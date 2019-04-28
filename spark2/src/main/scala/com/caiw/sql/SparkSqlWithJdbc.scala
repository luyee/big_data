package com.caiw.sql

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import java.util
import java.util.Properties

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.jdbc.{JdbcDialect, JdbcDialects}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable.ArrayBuffer

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/26 10:30 
  */
object SparkSqlWithJdbc {
  var conn:Connection = _
  var statement:PreparedStatement = _

  def main(args: Array[String]): Unit = {

    val prop = new Properties()
    prop.put("url","jdbc:hive2://192.168.23.55:10000/default")
    prop.put("driver","org.apache.hive.jdbc.HiveDriver")
    prop.put("user","hive")
    prop.put("password","")

    val ss = SparkSession.builder().appName("SparkSqlWithJdbc").master("local[4]").enableHiveSupport().getOrCreate()

    val dialect = JdbcDialects

    val jdbcUrl = "jdbc:hive2://192.168.23.55:10000/default"
    JdbcDialects.unregisterDialect(dialect.get(jdbcUrl))
    JdbcDialects.registerDialect(HiveDialect)

    val jdbcDF2 = ss.read
      .option("fetchsize","20")
      .jdbc(jdbcUrl,"default.user_test_100",prop)

//    val jdbcDF2 = ss.read.format("jdbc")
//      .options(Map(
//        "url" -> "jdbc:mysql://192.168.23.132:3306/test",
//        "dbtable" -> "cw_test_201902",
//        "driver" -> "com.mysql.jdbc.Driver",
//        "user" -> "root",
//        "password" -> "mysql123"))
//      .option("fetchsize","20")
//      .load()


    jdbcDF2.show()

//    val resultSet = hiveSelect("select * from cwk_test_20190329.e431d36c6be56a8013c80442bcedd8dc limit 10")
//    val rsmd = resultSet.getMetaData
//
//
//    val columnTypeList = new util.ArrayList[String]
//    val rowSchemaList = new util.ArrayList[StructField]
//    for(i <- 1 to rsmd.getColumnCount){
//      var temp = rsmd.getColumnClassName(i)
//      temp=temp.substring(temp.lastIndexOf(".")+1)
//      if("Integer".equals(temp)){
//        temp="Int"
//      }
//      columnTypeList.add(temp)
//      rowSchemaList.add(createStructField(rsmd.getColumnName(i),rsmd.getColumnClassName(i)))
//    }
//    val rowSchema = StructType(rowSchemaList)
//    //ResultSet反射类对象
//    val rsClass =  resultSet.getClass
//
//
//    while (resultSet.next()){
//      val arr  = new ArrayBuffer[Any]()
//      for(i <- 1 to rsmd.getColumnCount){
//        val method = rsClass.getMethod("get"+columnTypeList.get(i - 1),"aa".getClass)
//        arr += method.invoke(resultSet, rsmd.getColumnName(i))
//      }
//      val row = Row(arr:_*)
//      println(row.get(0).toString)
//    }





//    resultSet.close()
//    statement.close()
//    conn.close()
    ss.close()
  }

//  def createStructField(name:String,colType:String):StructField={
//    colType match {
//      case "java.lang.String" => StructField(name,StringType,nullable = true)
//      case "java.lang.Integer" =>  StructField(name,IntegerType,nullable = true)
//      case "java.lang.Long" => StructField(name,LongType,nullable = true)
//      case "java.lang.Boolean" => StructField(name,BooleanType,nullable = true)
//      case "java.lang.Double" => StructField(name,DoubleType,nullable = true)
//      case "java.lang.Float" => StructField(name,FloatType,nullable = true)
//      case "java.sql.Date" => StructField(name,DateType,nullable = true)
//      case "java.sql.Time" => StructField(name,TimestampType,nullable = true)
//      case "java.sql.Timestamp" => StructField(name,TimestampType,nullable = true)
//      case "java.math.BigDecimal" => StructField(name,DecimalType(10,0),nullable = true)
//    }
//  }
//
//  def hiveSelect(sql:String): ResultSet ={
//    Class.forName("org.apache.hive.jdbc.HiveDriver").newInstance()
//    conn = DriverManager.getConnection("jdbc:hive2://192.168.23.55:10000/default")
//    statement = conn.prepareStatement(sql)
//    statement.executeQuery()
//  }
}

case object HiveDialect extends JdbcDialect {
  override def canHandle(url : String): Boolean = url.startsWith("jdbc:hive2")
  override def quoteIdentifier(colName: String): String = {
    colName.split('.').map(part => s"`$part`").mkString(".")
  }
}
