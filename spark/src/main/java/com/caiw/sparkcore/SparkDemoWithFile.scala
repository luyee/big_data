package com.caiw.sparkcore

import org.apache.spark.sql.SparkSession

object SparkDemoWithFile {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("sougoLog").master("local[2]").getOrCreate()


    val readData = ss.read.text("E:\\迅雷下载\\SogouQ.reduced").rdd

    readData.map(row => row.get(0).toString.split("\t")(1) -> 1)
      .reduceByKey((r1,r2) => r1+r2)
      .foreach(x => println("user = "+x._1+",count = "+x._2))
  }
}
