package com.caiw.sql

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext

object SparkSqlWithHive {
  def main(args: Array[String]): Unit = {

    val conf = new Configuration
    conf.set("hadoop.security.authentication", "Kerberos")
    System.setProperty("java.security.krb5.conf", "C:\\Users\\caiwei\\Desktop\\krb5.conf")

    //System.setProperty("sun.security.krb5.realm", "/etc/krb5.ini");
    System.setProperty("sun.security.krb5.debug", "true")
    UserGroupInformation.setConfiguration(conf)
    UserGroupInformation.loginUserFromKeytab("hive/slave.hdp193.com@EXAMPLE.COM", "D:\\tmp\\hive.service.keytab")
    val ss = SparkSession.builder().appName("hiveTest").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()




//    ss.sql("create table tb_partition(id string, name string)" +
//      " PARTITIONED BY (month string)" +
//      " row format delimited fields terminated by '\\t';")
//        .show()
//    new HiveContext()
//    ss.read
//      .option("header","true")
//      .csv("file:///C:\\Users\\caiwe\\Desktop\\partition.csv")
//        .createTempView("test")
    //1535621681000
//    ss.sql("insert into test_201809.trans_test003_feed partition(processing_dttm='1535621681000') select * from test").show()
//    val frame = ss.sql("select * from wylt")
//    val schema = frame.schema
//    val rdd = frame.rdd
//      .map {
//        row =>
//          val age = row.getString(3)
//          age -> row
//      }.reduceByKey((x, y) => x)
//      .map(_._2)
//    ss.createDataFrame(rdd,schema).show()

    ss.sql("show tables").show()

    ss.close()
  }
}
