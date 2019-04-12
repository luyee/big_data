package com.caiw.etl

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkSqlWriteHiveExternal {
  def main(args: Array[String]): Unit = {

    val ss = SparkSession.builder().appName("hiveTest").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()



    ss.sql("select * from test.cw_test_10_20190403_01")
        .write
      .mode(SaveMode.Append)
      .partitionBy("age")
      .insertInto("test.user_test_20190408")
//      .format("Hive")
//        .saveAsTable("test.user_test_20190408")


    ss.close()
  }
}
