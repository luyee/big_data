package com.caiw.sparkcore

import org.apache.spark.sql.SparkSession

import scala.collection.immutable.HashMap
import scala.collection.mutable

object ShareFriend {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("shareFriend").master("local[1]").getOrCreate()

    ss.sparkContext
      .textFile("spark/src/main/resources/friend.txt")
      .map(_.split(":"))
      .flatMap{
        strs =>
          val user = strs(0)
          val friends = strs(1).split(",")
          var map = Map[String,(String, String)]()
          friends.foreach(friend => map += (friend -> Tuple2(friend, user)))
          map
      }
      .reduceByKey{
      (t1,t2) =>
        (t1._1,t1._2+","+t2._2)
      }
      .map{
      x =>
        val users = x._2._2.split(",")
        x._1 -> users
      }
      .flatMap{
      xx =>
        val users = xx._2
        val a = users.size
        var b = 0
        var map = Map[String,String]()
        while(b < a-1){
          var c = b+1
          while(c < a) {
            map += (users(b)+"+"+users(c) -> xx._1)
            c += 1
          }
          b += 1
        }
        map
      }
      .reduceByKey{
      (f1,f2) =>
        f1+","+f2
      }
      .foreach{
        result =>
          println(result._1 + "\t" +result._2)
      }



  }
}
