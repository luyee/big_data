package com.caiw.sql

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlWithHive {
  def main(args: Array[String]): Unit = {
//    val conf = new Configuration
//    conf.set("hadoop.security.authentication", "Kerberos")
//    System.setProperty("java.security.krb5.conf", "C:\\Users\\caiwei\\Desktop\\krb5.conf")
//    //System.setProperty("sun.security.krb5.realm", "/etc/krb5.ini");
//    System.setProperty("sun.security.krb5.debug", "true")
//    UserGroupInformation.setConfiguration(conf)
//    UserGroupInformation.loginUserFromKeytab("hive/slave.hdp193.com@EXAMPLE.COM", "D:\\tmp\\hive.service.keytab")

    val ss = SparkSession.builder().appName("hiveTest").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()


    import ss.sqlContext.implicits._

    val df:DataFrame = ss.sparkContext
      .parallelize(Array(("abc",2,"cw1"),("efg",4,"cw2"),("hij",5,"cw3")))
      .toDF("id","age","name")

    df.repartition(10)
    df.select("id","age").show()

//    val frame = ss.sql("select * from test.cw_test_10_20190403_01")
//    df.toJSON.foreach(println(_))






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
