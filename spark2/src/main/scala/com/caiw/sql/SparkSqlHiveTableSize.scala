package com.caiw.sql

import java.io.IOException
import java.util.Properties

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession
;

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/17 11:46
  */
object SparkSqlHiveTableSize {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("hiveTest").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()
//    val integer = getDataSize("default","wylt",ss)
//    println(integer)

    val prop = new Properties
    prop.setProperty("user","root")
    prop.setProperty("password","root")
    prop.setProperty("driver","com.mysql.jdbc.Driver")
    val df = ss.read.jdbc("jdbc:mysql://192.168.2.54:3306/db_dcp2","dsg_asset",prop)
    df.where("table_name is not null")
        .where("schema_name is not null")
      .select("table_name","schema_name")
        .show(200)


    ss.close()
  }

  def getDataSize(db: String, table: String,ss: SparkSession): Integer = {
    val df1 = ss.sql("describe formatted " + db + "." + table)
    //    df1.show()
    var loc = ""
    import scala.collection.JavaConversions._
    var flag = true
    for (row <- df1.collectAsList if flag) {
      if (row.getString(0) == "Location") {
        loc = row.getString(1)
        flag = false
      }
    }
    val path = new Path(loc)
    var totalSize = 0L
    var hdfs:FileSystem = null
    try
      hdfs = FileSystem.get(new Configuration)
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    try
      totalSize = hdfs.getContentSummary(path).getLength
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
    String.valueOf(totalSize).toInt
  }
}
