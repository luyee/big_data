package com.caiw.sparkcore

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreamingDemo001 {
    def main(args: Array[String]): Unit = {

      val ss = SparkSession.builder().master("local[2]").appName("sparkStreaming").getOrCreate()
      val ssc = new StreamingContext(ss.sparkContext,Seconds(10))


      ssc.textFileStream("C:\\Users\\caiwe\\Desktop\\test1.json").foreachRDD(rdd => rdd.toString())


      ssc.start()
      ssc.awaitTermination()
    }
}
