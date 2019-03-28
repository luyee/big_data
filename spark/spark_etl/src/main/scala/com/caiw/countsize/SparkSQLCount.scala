package com.caiw.countsize

import java.io.{FileInputStream, IOException}
import java.net.URI
import java.util.Properties

import org.apache.hadoop.hbase.{HBaseConfiguration, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

object SparkSQLCount {
  def main(args: Array[String]): Unit = {


    //read properties  (local)
//    val props = new Properties()
//    val loader = getClass.getClassLoader
//    props.load(new FileInputStream(loader.getResource("statistical.properties").getFile))

//    read properties  (cluster)  --file statistical.properties
//    val props = new Properties()
//    props.load(new FileInputStream("statistical.properties"))
//    props.setProperty("date",latestDate)

    val tableName = "hbase_test"
    val namespace = "default"
    val ss = SparkSession.builder().master("local[4]").appName("SparkSQLCount")
//      .enableHiveSupport()
      .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()


    val count = getTableCount(ss,namespace,tableName)
    val size = getTableSize("/apps/hbase/data/data/" + namespace + "/" + tableName)

    import ss.sqlContext.implicits._
    val df2 = Seq((namespace,tableName,count,size)).toDF("namespace","tableName","dataCount","dataSize")
    df2.show()
    ss.close()

  }

  private def getTableCount(ss: SparkSession , namespace:String ,tableName :String) : Long = {
    val hbaseConf: Configuration = HBaseConfiguration.create()
    hbaseConf.set(TableInputFormat.INPUT_TABLE, namespace+":"+tableName)//指定hbase的表名，根据自己需要统计的hbase表格修改
    val hBaseRDD = ss.sparkContext.newAPIHadoopRDD(hbaseConf, classOf[TableInputFormat], classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable], classOf[org.apache.hadoop.hbase.client.Result])
    hBaseRDD.count
  }


  private def getTableSize(path : String) : Long = {
    var hdfs:FileSystem = null
    try
      hdfs = FileSystem.get(new URI("hdfs://192.168.23.54:8020"),new Configuration, "hdfs")
    catch {
      case e: Exception =>
        e.printStackTrace()
    }
    val fileNamePath = new Path(path)
    try {
      hdfs.getContentSummary(fileNamePath).getLength
    } catch {
      case e: IOException => println("The data path error : "+ e.toString )
        0
    }
  }
}
