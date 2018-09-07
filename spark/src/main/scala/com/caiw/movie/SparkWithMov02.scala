package com.caiw.movie

import org.apache.spark.sql.SparkSession

object SparkWithMov02 {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().master("local[4]").appName("top10").getOrCreate()

    //获取年龄在18-24之间的观众
    val users = ss.read
      .text("spark/src/main/resources/ml-1m/users.dat")
      .rdd
      .map {
        row =>
          val strs = row.get(0).toString.split("::")
          strs(0) -> strs(2)
      }
      .filter(18 <= _._2.toInt)
      .filter(_._2.toInt <= 27)

    //通过观众ID 找到观众看过的movieID
    val movieId = ss.read
      .text("spark/src/main/resources/ml-1m/ratings.dat")
      .rdd
      .map{
        row =>
          val strs = row.get(0).toString.split("::")
          strs(0)->strs(1)
      }
      .join(users)
      .map(x => x._2)

    //通过movie拿到电影名字，根据电影名聚合拿到观看次数并取出前10
    ss.read
      .text("spark/src/main/resources/ml-1m/movies.dat")
      .rdd
      .map{
        row =>
          val strs = row.get(0).toString.split("::")
          strs(0) -> strs(1)
      }
      .join(movieId)
      .map(xx => (xx._1,1))
      .reduceByKey(_+_)
      .sortBy(_._2,ascending = false)
      .take(10)
      .foreach(cc => println(cc._1+"\t"+cc._2))

    ss.stop()
  }
}
