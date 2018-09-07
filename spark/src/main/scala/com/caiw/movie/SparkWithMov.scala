package com.caiw.movie

import org.apache.spark._
import org.apache.spark.sql.SparkSession

/**
  * 看过“Lord of the Rings, The (1978)”用户和年龄性别分布
  */

object SparkWithMov {


    def main(args: Array[String]) {
      val ss = SparkSession.builder().appName("movieAnalysis").master("local").getOrCreate()
      val sc = ss.sparkContext
      //1.加载数据
      val MOVIE_ID = "999"
      val usersRdd = sc.textFile("spark/src/main/resources/ml-1m/users.dat")
      val ratingsRdd = sc.textFile("spark/src/main/resources/ml-1m/ratings.dat")

      //2.获取用户ID，sex,age
      val users = usersRdd.map(_.split("::")).map { x =>
        (x(0), (x(1), x(2)))
      }

      //3.拿到指定movie的用户，并对用户的age和sex进行聚合
      val userMov = ratingsRdd
        .map(_.split("::"))
        .map { x =>(x(0), x(1))}
        .filter(_._2.equals(MOVIE_ID))
        .join(users)
        .map { x =>(x._2._2, 1)}
        .reduceByKey(_ + _)

      //5.输出结果
      userMov
        .collect
        .foreach(x => println("性别："+x._1._1+",年龄："+x._1._2+",观看人数："+x._2))

      ss.stop()
    }
}
