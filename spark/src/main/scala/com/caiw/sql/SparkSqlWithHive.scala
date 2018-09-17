package com.caiw.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext

object SparkSqlWithHive {
  def main(args: Array[String]): Unit = {

    val ss = SparkSession.builder().appName("hiveTest").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()


//    ss.sql("create table tb_partition(id string, name string)" +
//      " PARTITIONED BY (month string)" +
//      " row format delimited fields terminated by '\\t';")
//        .show()
    new HiveContext()
    ss.read
      .option("header","true")
      .csv("file:///C:\\Users\\caiwe\\Desktop\\partition.csv")
        .createTempView("test")
    //1535621681000
//    ss.sql("insert into test_201809.trans_test003_feed partition(processing_dttm='1535621681000') select * from test").show()
    ss.sql("insert into default.tb_partition partition(month='6') select * from test").show()

    ss.close()
  }
}
