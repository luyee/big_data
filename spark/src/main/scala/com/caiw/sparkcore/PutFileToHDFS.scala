package com.caiw.sparkcore

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import com.databricks.spark.avro._
import org.apache.spark.sql.hive.HiveContext

object PutFileToHDFS {



  def main(args: Array[String]): Unit = {

    val path = "file:///C:\\Users\\caiwe\\Desktop\\userdata2.csv"
//    val path = "file:///C:\\Users\\caiwe\\Desktop\\test1.json"
//    val path = "file:///C:\\Users\\caiwe\\Desktop\\test.avro"
//    val path = "file:///C:\\Users\\caiwe\\Desktop\\test4.orc"
    val path1 = "file:///C:\\Users\\caiwe\\Desktop\\test"

    val paths = Array(path,path1)

    val ss = SparkSession.builder().enableHiveSupport().master("local[3]").appName("putFileToHDFS").config("spark.sql.warehouse.dir", "file:///D:\\tmp\\spark-warehouse").getOrCreate()

//    ssc.sparkContext.textFile(path).saveAsTextFile(path1)
    val hiveContext = new HiveContext(ss.sparkContext)
    cutCSVHeader(hiveContext,paths)

//    readJsonFile(ssc,paths)

//      readAvroFile(ssc,paths)
//    readOrcFile(hiveContext,paths)

//    readOrcFile2(ss,paths)
  }

  def cutCSVHeader (hiveContext:HiveContext,paths:Array[String]):Unit={
    hiveContext.read
//      .option("header", true.toString) //这里如果在csv第一行有属性的话，没有就是"false"
      .csv(paths(0))
      .write
      .avro(paths(1))

  }

  def readJsonFile (ss:SparkSession,paths:Array[String]) : Unit = {
    ss.sqlContext.read.format("json").load(paths(0)).foreach(x => println(x.mkString))
//    ds.write.save(paths(1))
  }

  def readAvroFile (ss:SparkSession,paths:Array[String]) : Unit = {
    ss.sqlContext
      .read
      .avro(paths(0)).show()
  }

  def readOrcFile (hiveContext: HiveContext , paths :Array[String]):Unit ={
    hiveContext.read
      .orc(paths(0)).show()
//      .write
//      .orc(paths(1))

  }

  def readOrcFile2(ss: SparkSession, paths: Array[String]): Unit = {
    ss.read
      .csv(paths(0))
      .write
      .orc(paths(1))
  }
}
