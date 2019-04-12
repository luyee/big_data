package com.caiw.sql

import java.util
import java.util.List

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.collection.mutable.ArrayBuffer

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/9 14:48 
  */
object AppendSQLWithHiveExternal {
  /**
    * CREATE EXTERNAL TABLE
    * test.user_test_20190408
    * (
    * id string,
    * name string,
    * age int
    * ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH
    * SERDEPROPERTIES
    * ("hbase.columns.mapping" = ":key,c1:name,c1:age")
    * TBLPROPERTIES("hbase.table.name" = "test:user_test_20190408");
    */
  def main(args: Array[String]): Unit = {
    val json = "[{\"name\":\"id\",\"type\":\"string\",\"rowkey\":\"true\"},{\"name\":\"name\",\"type\":\"string\",\"rowkey\":\"false\"},{\"name\":\"email\",\"type\":\"string\",\"rowkey\":\"false\"}]"
    val dbName = "test1"
    val tableName = "user_test_2019040901"
    val jsonObjects = JSON.parseArray(json, classOf[JSONObject])
    val sb = new StringBuilder
    sb.append("CREATE EXTERNAL TABLE ")
    sb.append(dbName).append(".").append(tableName).append(" (")
    import scala.collection.JavaConversions._
    val fields_1 = new ArrayBuffer[String]
    var rowKeyField = ""
    val fields_2 = new ArrayBuffer[String]
    jsonObjects.foreach{
      jsonObject =>
        fields_1 += (jsonObject.getString("name")+" "+jsonObject.getString("type"))
        if(jsonObject.getBoolean("rowkey")){
          rowKeyField = jsonObject.getString("name")
        }else{
          fields_2 += ("cf1:" + jsonObject.getString("name"))
        }
    }
    sb.append(fields_1.mkString(","))
    sb.append(") ")
    sb.append(" STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' ")
    sb.append(" WITH SERDEPROPERTIES (\"hbase.columns.mapping\" = \":key,")
    sb.append(fields_2.mkString(","))
    sb.append("\") ")
    sb.append(" TBLPROPERTIES(\"hbase.table.name\" = \"")
    sb.append(dbName).append(":").append(tableName)
    sb.append("\")")
    println(sb.toString())
  }
}
