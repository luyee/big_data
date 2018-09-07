package com.caiw.sparkcore

import java.util.Properties

import org.apache.spark.sql.{Row, SparkSession}

import scala.collection._
import scala.util.Properties

object SparkDemoWithMysql {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("SparkDemoWithMysql").master("local[5]").getOrCreate()

    val properties:Properties =  new Properties()

    properties.setProperty("user","root")
    properties.setProperty("password","hadoop")

//    sparkSession.sql("")

    val sql = "(select cbrxm,sum(jfjs*jfny) from t_jf_details3 group by cbrxm) as a"
    val sql2 = "(select cbrxm,jfjs,jfny from t_jf_details3) as a"


    val mysqlRDD = sparkSession.read
      .jdbc("jdbc:mysql://192.168.2.72:3306/weibo_db",sql2,properties)
      .rdd

//    mysqlRDD.foreach(x => println(x.get(0)+",mon = "+x.get(1).toString))
//
//
    val hashMap = new mutable.HashMap[String,Int]

    mysqlRDD.map(row => row.get(0).toString->Integer.parseInt(row.get(1).toString)*Integer.parseInt(row.get(2).toString))
      .reduceByKey{
      (row1,row2) =>
          row1+row2
    }.foreach(x => println("name = "+x._1+",money = "+x._2))


//    mysqlRDD.groupBy(row => row.get(0)).map{
//      row =>
//        val name = row._1.toString
//        var totalMoney = 0
//        row._2.foreach{
//          row1 =>
//            totalMoney = totalMoney + Integer.parseInt(row1.get(1).toString)*Integer.parseInt(row1.get(2).toString)
//        }
//        name -> totalMoney
//    }.foreach(x => println("name = "+x._1+",money = "+x._2))






  }
}
