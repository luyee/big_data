package com.caiw.sparkcore

import java.util.Properties

import org.apache.spark.sql.{SparkSession, functions}


object FunOrderBy {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("testOrder").master("local").getOrCreate()



    val properties:Properties =  new Properties()

    properties.setProperty("user","root")
    properties.setProperty("password","mysql")

    //    sparkSession.sql("")

//    val sql = "(select cbrxm,sum(jfjs*jfny) from t_jf_details3 group by cbrxm) as a"
//    val sql2 = "(select cbrxm,jfjs,jfny from t_jf_details3) as a"
    val sql3 = "(select * from sc) as s"

    val mysqlRDD = ss.read
      .jdbc("jdbc:mysql://192.168.23.140:3306/glustermanager",sql3,properties)

    mysqlRDD.toDF().select(mysqlRDD("*"), functions.sum(mysqlRDD("score")).over(org.apache.spark.sql.expressions.Window.orderBy(mysqlRDD("S"))))
        .show(100)

    ss.close()
  }
}
