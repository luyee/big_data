package com.caiw.etl

import java.sql.Date
import java.util
import java.util.{ArrayList, List}

import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{Dataset, Row, RowFactory, SparkSession}

object SparkSqlProfile {

  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().appName("hiveT").master("local[4]")
      .enableHiveSupport()
      .getOrCreate()

    val category = "c2"
    val feed = "f2"
    val feedts = "123455"

    //        String driver = args[3];
    //        String url = args[4];
    //        String username = args[5];
    //        String password = args[6];
    val dbName = "d2"
    val tableName = "t2"
    val tableType = "type21"
    val feedId = "feeedId2"
    val sql = "select 'c1' as category, 'f1' as feed, processing_dttm as processingdttm, columnname,  metrictype, metricvalue  from cw_test001.ingest_test020_profile where processing_dttm = '1546491732587'"
    val df = ss.sql(sql)


    val rows = df.rdd.collect()

    for(row <- rows){
      if(row.getAs("metrictype").equals("VALID_COUNT")) {
        println(row.getAs("metricvalue"))
      }
    }

    var validCount: String = "0"
    val collect: Array[Row] = df.rdd.collect
    for (row <- collect) {
      if (row.getAs("metrictype") == "VALID_COUNT") {
        validCount = row.getAs("metricvalue")
      }
    }

//    val size = 1512631111415L

    val rows1: util.List[Row] = new util.ArrayList[Row]
    val row: Row = RowFactory.create(category, feed, feedts, feedId, tableType, dbName, tableName, new Date(System.currentTimeMillis), new Date(System.currentTimeMillis), Integer.valueOf(0), Integer.valueOf(validCount))
    rows1.add(row)

    val structFields: util.List[StructField] = new util.ArrayList[StructField]
    structFields.add(DataTypes.createStructField("category", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("feed", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("processingdttm", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("feed_id", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("table_type", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("db_name", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("table_name", DataTypes.StringType, true))
    structFields.add(DataTypes.createStructField("create_time", DataTypes.DateType, true))
    structFields.add(DataTypes.createStructField("update_time", DataTypes.DateType, true))
    structFields.add(DataTypes.createStructField("size", DataTypes.IntegerType, true))
    structFields.add(DataTypes.createStructField("cnt", DataTypes.IntegerType, true))
    val structType: StructType = DataTypes.createStructType(structFields)

    val dataFrame: Dataset[Row] = ss.sqlContext.createDataFrame(rows1, structType)

    dataFrame.show()

//    df.rdd.foreach({
//      row =>
//
//        }
//    })



    ss.close()
  }
}
