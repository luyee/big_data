package com.caiw.utils

import java.io.{File, IOException}
import java.util
import java.util.{Random, UUID}

import com.alibaba.fastjson.JSON
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.util.Try
/**
  * package: utils
  * describe: HBase工具类
  * creat_user: Fayson
  * email: htechinfo@163.com
  * creat_date: 2018/5/28
  * creat_time: 上午10:51
  * 公众号：Hadoop实操
  */
object HBaseUtils extends Serializable {
  /**
    * @param zkList Zookeeper列表已逗号隔开
    * @param port ZK端口号
    * @return
    */
  def getHBaseConn: Connection = {
    val conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "192.168.23.54,192.168.23.55,192.168.23.56")
    conf.set("hbase.zookeeper.property.clientPort", "2181")
    conf.set("hbase.master", "192.168.23.54:16000")
    conf.set("zookeeper.znode.parent", "/hbase-unsecure")
    val workaround = new File(".")
    System.getProperties.put("hadoop.home.dir", workaround.getAbsolutePath)
    new File("./bin").mkdirs
    try
      new File("./bin/winutils.exe").createNewFile
    catch {
      case e: IOException =>
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    val connection = ConnectionFactory.createConnection(conf)
    connection
  }

  /**
    * kafka message is json format
    */
  val saveHBase2: (RDD[String], SparkSession,String,String) => Unit =
    (rdd : RDD[String], ss:SparkSession,tableNameStr:String,columnFamily:String) => {
    import ss.implicits._
    val df = ss.read.json(ss.createDataset(rdd))
    val fields = df.schema.fieldNames
    df.rdd.foreachPartition{
      rdd =>
        val connection = HBaseUtils.getHBaseConn
        val tableName = TableName.valueOf(tableNameStr)
        val table = connection.getTable(tableName)
        val putList = new util.ArrayList[Put]
        rdd.foreach{
          row =>
            val put = new Put(Bytes.toBytes(new Random().nextInt(10)+UUID.randomUUID().toString))
            for(i <- fields.indices){
              put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(fields(i)),Bytes.toBytes(row.getString(i)))
            }
            putList.add(put)
        }
        Try(table.put(putList)).getOrElse(table.close())
        println(putList.size())
        connection.close()
    }
  }

  /**
    * kafka message is json format
    *
    */
  val saveHBase:(RDD[String],String,String) => Unit = (rdd : RDD[String],tableNameStr:String,columnFamily:String) => {
    rdd.foreachPartition{
      rddPartition =>
        val connection = HBaseUtils.getHBaseConn
        val tableName = TableName.valueOf(tableNameStr)
        val table = connection.getTable(tableName)
        val putList = new util.ArrayList[Put]
        rddPartition.foreach{
          line =>
            val nObject = JSON.parseObject(line)
            val put = new Put(Bytes.toBytes(new Random().nextInt(10)+UUID.randomUUID().toString))
            nObject.keySet.toArray.foreach{
              key =>
                put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(key.toString),Bytes.toBytes(nObject.getString(key.toString)))
            }
            putList.add(put)
        }
        Try(table.put(putList)).getOrElse(table.close())
        println(putList.size())
        connection.close()
    }
  }
}