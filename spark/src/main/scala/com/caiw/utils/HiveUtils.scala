package com.caiw.utils

import java.sql.{DriverManager, ResultSet}
import java.util.Properties

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/9 15:42 
  */
object HiveUtils {

  def hiveExec(sql: String,properties: Properties): Unit = try {
    val className = properties.getProperty("hive.driver")
    val url = properties.getProperty("hive.url")
    val user = properties.getProperty("hive.user")
    val password = properties.getProperty("hive.password")
    Class.forName(className).newInstance
    val conn = DriverManager.getConnection(url, user, password)
    val ps = conn.prepareStatement(sql)
    ps.executeUpdate()
    ps.close()
    conn.close()
  } catch {
    case e: Exception =>
      println("执行失败！" + e.toString)
  }


  def hiveQuery(sql: String,properties: Properties): ResultSet = try {
    val className = properties.getProperty("hive.driver")
    val url = properties.getProperty("hive.url")
    val user = properties.getProperty("hive.user")
    val password = properties.getProperty("hive.password")
    Class.forName(className).newInstance
    val conn = DriverManager.getConnection(url, user, password)
    val ps = conn.prepareStatement(sql)
    val set = ps.executeQuery()
    ps.close()
    conn.close()
    set
  } catch {
    case e: Exception =>
      e.printStackTrace()
      null
  }
}
