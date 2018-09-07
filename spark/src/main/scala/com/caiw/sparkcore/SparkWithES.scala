package com.caiw.sparkcore

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.elasticsearch.spark._

object SparkWithES {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[1]").setAppName("sparkWithEs")
    /**
      * 根据es官网的描述，集成需要设置：
      * es.index.auto.create--->true
      * 我们要去连接es集群，要设置es集群的位置(host, port)
      */
    conf.set("es.index.auto.create", "true")
    conf.set("es.nodes", "localhost")
    //---->如果是连接的远程es节点，该项必须要设置
    conf.set("es.port", "9200")
    val sparkSession = SparkSession.builder().master("local[2]").appName("sparkWithEs").config(conf).getOrCreate()
//    val sc = new SparkContext(conf)
//    val sqlContext = sparkSession.sqlContext

    writeJson2Es(sparkSession)

//    writeTxt2ES(sparkSession)

//    readFromES(sparkSession.sparkContext)

//    sqlReadEs(sparkSession)

//    sc.stop()
  }

  def readFromES(sc:SparkContext): Unit = {
//    val resource = "spark/people"
    val resource = "index20180529_06"

    /** 从ES中读取出来的数据的Tuple2
      * Tuple2._1--->es中该条记录的标识id
      * Tuple2._2--->es中该条记录的内容
      */
    val accountRDD = sc.esJsonRDD(resource)
    accountRDD.foreach(t => println(t._1 + "=== " + t._2))
  }

  def sqlReadEs(ss:SparkSession):Unit ={
//    val resource = "cw/json_test"
    val resource = "index20180530_02"
    val options = Map("pushdown" -> "true")
    val df = ss.sqlContext.read.format("org.elasticsearch.spark.sql").options(options).load(resource)
//    df.select("name","age").collect().foreach(println(_))

    df.createTempView("json_test")
    ss.sqlContext.sql("select * from json_test").show(1000)


  }

  def writeTxt2ES(ss:SparkSession): Unit = {
    //--->使用一个普通sparkContext来进行操作
    /** resource
      * 指的是数据在rs中的存放的位置，由index/type(格式) 共同组成
      */
    val resource = "index_test/sh"
    //定义Person　case class
    case class Person(name: String, surname: String, age: Int)

    //创建DataFrame
    val people = ss.sparkContext.textFile("file:///C:\\Users\\caiwe\\Desktop\\test.txt").map(_.split(",")).map(p => Person(p(0), p(1), p(2).trim.toInt))

    people.saveToEs(resource)
  }

  def writeJson2Es(sparkSession: SparkSession): Unit = {
    val resource = "index_test001/sh001"
    val strings = sparkSession.sparkContext.textFile("file:///C:\\Users\\caiwe\\Desktop\\details01.json").collect()
    sparkSession.sparkContext.makeRDD(strings).saveJsonToEs(resource)
  }
}
