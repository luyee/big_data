package com.caiw.dsg.hive2hbase

import java.io.IOException

import org.apache.hadoop.hbase._
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

object HbaseUtil {
  private var connection: Connection = null
  private var admin: Admin = null


  // 初始化链接
  def init(): Unit = {
    val configuration = HBaseConfiguration.create
    connection = ConnectionFactory.createConnection(configuration)
    admin = connection.getAdmin

  }


  // 关闭连接
  def close(): Unit = {
    try {
      if (null != admin) admin.close()
      if (null != connection) connection.close()
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  def getAdmin: Admin = {
    init()
    admin
  }

  def getConnection: Connection = {
    init()
    connection
  }


  // 建表
  def createTable(nameSpace: String, srcTableName: String, cols: Array[String]): Unit = {
    init()
    val tableName = TableName.valueOf(nameSpace + ":" + srcTableName)
    if (admin.tableExists(tableName)) System.out.println("table is exists!")
    else {
      try
        admin.getNamespaceDescriptor(nameSpace)
      catch {
        case e: NamespaceNotFoundException =>
          //若发生特定的异常，即找不到命名空间，则创建命名空间
          val namespaceDescriptor = NamespaceDescriptor.create(nameSpace).build
          admin.createNamespace(namespaceDescriptor)
      }
      val hTableDescriptor = new HTableDescriptor(tableName)
      for (col <- cols) {
        val hColumnDescriptor = new HColumnDescriptor(col)
        hTableDescriptor.addFamily(hColumnDescriptor)
      }
      admin.createTable(hTableDescriptor)
    }
    close()
  }

  // 删表
  def deleteTable(tableName: String): Unit = {
    init()
    val tn = TableName.valueOf(tableName)
    if (admin.tableExists(tn)) {
      admin.disableTable(tn)
      admin.deleteTable(tn)
    }
    close()
  }

  // 插入数据
  def insertRow(tableName: String, rowkey: String, colFamily: String, col: String, value: String): Unit = {
    init()
    val table = connection.getTable(TableName.valueOf(tableName))
    val put = new Put(Bytes.toBytes(rowkey))
    put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(value))
    //    put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes("age"), Bytes.toBytes("20"))
    table.put(put)
    // 批量插入
    /*
             * List<Put> putList = new ArrayList<Put>(); puts.add(put);
             * table.put(putList);*/

    table.close()
    close()
  }

  // 插入数据
  def insertRows(tableName:String,rows:ArrayBuffer[Put]): Unit = {
    init()
    val table = connection.getTable(TableName.valueOf(tableName))
    table.put(rows)
    table.close()
    close()
  }

}
