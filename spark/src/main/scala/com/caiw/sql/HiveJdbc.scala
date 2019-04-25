package com.caiw.sql

import java.io.FileInputStream
import java.sql.DriverManager
import java.util.Properties

import com.caiw.utils.HiveUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/12 11:49 
  */
object HiveJdbc {
  def main(args: Array[String]): Unit = {
//    val conf = new Configuration
//    conf.set("hadoop.security.authentication", "Kerberos")
//    System.setProperty("java.security.krb5.conf", "/etc/krb5.conf")
//    System.setProperty("sun.security.krb5.debug", "true")
//    UserGroupInformation.setConfiguration(conf)
//    UserGroupInformation.loginUserFromKeytab("hive/slave.hdp193.com@EXAMPLE.COM", "/etc/security/keytabs/hive.service.keytab")
    val properties = new Properties()
    val loader = getClass.getClassLoader
    properties.load(new FileInputStream(loader.getResource("hive.properties").getFile))
//
//    val set = HiveUtils.hiveQuery("show tables",props)
//    if(set != null){
//      while (set.next()){
//        println(set.getString(0))
//      }
//    }

    val className = properties.getProperty("hive.driver")
    val url = properties.getProperty("hive.url")
    val user = properties.getProperty("hive.user")
    val password = properties.getProperty("hive.password")
    Class.forName(className).newInstance
    val conn = DriverManager.getConnection(url, user, password)
    val ps = conn.prepareStatement("select * from wylt limit 10")
    val set = ps.executeQuery()
    while (set.next()){
      println(set.getString(1))
    }
    set.close()
    ps.close()
    conn.close()


  }
}
