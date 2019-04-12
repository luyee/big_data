package com.caiw.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.sql

/**
  * @Author: caiwei
  * @Description:
  * @Date: create in 2019/4/11 10:28 
  */
object Hive2Kafka {
  def main(args: Array[String]): Unit = {

  }


  def saveTable2KafKa(df:sql.DataFrame,bootstrapServers:String,topic:String,keyIndex:String,fieldArr:Seq[String]): Unit ={
    val kafkaProps = new Properties()
    kafkaProps.put("bootstrap.servers", bootstrapServers)
    kafkaProps.put("key.serializer", classOf[StringSerializer])
    kafkaProps.put("value.serializer", classOf[StringSerializer])
    df.rdd.foreach(row=>{
      val producer = new KafkaProducer[String,String](kafkaProps)
      val message = if(keyIndex.equals("nokey")){
        new ProducerRecord[String, String](topic,null, row.toString)
      }
      else {
        val strr = new StringBuffer()
        for(i <- fieldArr.indices){
          if (i!=keyIndex.toInt)
            strr.append(row.get(i)+",")
        }
        new ProducerRecord[String, String](topic,fieldArr(keyIndex.toInt),strr.deleteCharAt(strr.length()-1).toString)
      }
      producer.send(message)
    })
  }

}
