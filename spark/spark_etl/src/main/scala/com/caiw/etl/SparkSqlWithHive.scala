package com.caiw.etl

import java.util.{Date, Properties}

import org.apache.spark.sql.SparkSession

object SparkSqlWithHive {
  def main(args: Array[String]): Unit = {

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

    val prop = new Properties()
    prop.setProperty("driver","com.mysql.jdbc.Driver")
    prop.setProperty("user","root")
    prop.setProperty("password","mysql123")
//    ss.sql("insert into test_201809.trans_test003_feed partition(processing_dttm='1535621681000') select * from test").show()
//    ss.sql("SELECT * from dsgdata.dsgdatatest2 where registration_dttm like '%2016-02%'").show()
    ss.sql("select * from db_201902.tab_ingest_2019022015_yfq01_profile")
//      .show()
      .write
        .jdbc("jdbc:mysql://192.168.23.132:3306/test001","kylo_profile",prop)
//    frame.createTempView("dataFrame")
//    frame.selectExpr("select * from dataFrame limit 3").show()

    ss.close()
   //2月1好
//    import java.text.SimpleDateFormat
//    val time = "2019-01-30"
//    val format = new SimpleDateFormat("yyyy-MM-dd")
//    val newtime :Date = format.parse(time)
//    import java.util.Calendar
//    println(newtime.getDate)
//    val calendar = Calendar.getInstance
//    calendar.setTime(newtime)
//    calendar.add(Calendar.DATE,1)
//    if(calendar.getTime.getDate != 1){
//       calendar.add(Calendar.DATE,-1)
//    }
//    calendar.add(Calendar.MONTH,-1)
//    val str = format.format(calendar.getTime)
//    println(str.substring(0,7))
  }
}
