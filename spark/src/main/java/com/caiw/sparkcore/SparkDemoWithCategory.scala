package com.caiw.sparkcore

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.ml.feature.Tokenizer

object SparkDemoWithCategory {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("SparkDemoWithCategory").master("local[2]").getOrCreate()

    import ss.sqlContext.implicits._
    val data = ss.sparkContext.textFile("D:\\tmp\\sougou-train").map {
      x =>
        val strs = x.split(",")
        RawDataRecord(strs(0), strs(1))
    }

    val splitData = data.randomSplit(Array(0.7,0.3))
    var train = splitData(0).toDF()
    var test = splitData(1).toDF()

    var tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words")
    var wordsData = tokenizer.transform(train)
    wordsData.select($"category",$"text",$"words").take(1)






    //    ss.read.text("D:\\tmp\\sougou-train").map{
//      x =>
//        x.get(0).toString.split(",")
//
//    }



  }
}

case class RawDataRecord(category: String, text: String)
