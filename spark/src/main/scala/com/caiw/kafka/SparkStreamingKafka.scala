package com.caiw.kafka

import java.sql.Date
import java.util
import java.util.{ArrayList, List, Random, UUID}

import com.alibaba.fastjson.JSON
import com.caiw.utils.HBaseUtils
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.util.Bytes
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

/**
  *
  * Created by 蔡维 in 14:54 2017/12/15
  */
object SparkStreamingKafka {
  def main(args: Array[String]): Unit = {

    val kafkaProperties=Map[String,Object](
      "bootstrap.servers"-> "192.168.23.54:6667,192.168.23.55:6667,192.168.23.56:6667",
      //      "bootstrap.servers"-> "cw02:9092",
      "key.deserializer"->classOf[StringDeserializer],
      "value.deserializer"->classOf[StringDeserializer],
      "group.id"->"cw_test",
      "auto.offset.reset"->"earliest",
      "enable.auto.commit"->(true:java.lang.Boolean)
    )
//    val conf = new SparkConf().setMaster("local[4]").setAppName("SparkStreamingKafka")
    val ss = SparkSession.builder().master("local[4]").appName("test").getOrCreate()
    val sc = ss.sparkContext
    sc.setCheckpointDir("D:\\tmp\\checkpoint")
    sc.setLogLevel("WARN")
    val ssc = new StreamingContext(sc,Seconds(5))

    val topics = Array("cw_test_20190327_01")
    val hbaseTableName = "user_info"
    val hbaseColumnFamily = "cf1"
    val kafkaRDD = KafkaUtils.createDirectStream(ssc,PreferConsistent,Subscribe[String,String](topics,kafkaProperties))

//    kafkaRDD.map(_.value).foreachRDD(HBaseUtils.saveHBase2(_,ss,hbaseTableName,hbaseColumnFamily))

    kafkaRDD.map(_.value).foreachRDD(HBaseUtils.saveHBase(_,hbaseTableName,hbaseColumnFamily))

    // kafka message count
    kafkaRDD
      .map(_ => ("count",1))
      .updateStateByKey(updateFunc,new HashPartitioner(sc.defaultParallelism), rememberPartitioner = true)
      .print()

    ssc.start()
    ssc.awaitTermination()
  }

  private val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.map{ case(word, current_count, history_count) => (word, current_count.sum + history_count.getOrElse(0)) }
  }

}
