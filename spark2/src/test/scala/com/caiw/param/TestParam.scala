package com.caiw.param

import com.beust.jcommander.{JCommander, ParameterException}
import com.caiw.dsg.hive2hbase.DataValidationInfo

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/28 10:26 
  */
object TestParam {
  def main(args: Array[String]): Unit = {
    val dataValidationInfo = new DataValidationInfo()
    val jCommander = JCommander.newBuilder().addObject(dataValidationInfo).build()
    try{
      jCommander.parse(args:_*)
    }catch{
      case e:ParameterException =>
        println("Parameters invalid: "+e.getMessage)
        jCommander.usage()
        return
    }
    if(dataValidationInfo.isHelp){
      jCommander.usage()
    }
    println(dataValidationInfo.toString)
  }

}
