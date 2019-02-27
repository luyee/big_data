package com.caiw.test;

import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SparkDemo {
    public static void main(String[] args) {
        SparkSession ss = SparkSession.builder().appName("hiveT").master("local[4]")
                .enableHiveSupport()
                .getOrCreate();

        String category = "c2";
        String feed = "f2";
        String feedts = "123455";

                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://192.168.23.184:5432/kylo_db184";
                String username = "kylo";
                String password = "kylo123";
        String dbName = "d2";
        String tableName = "t2";
        String tableType = "type2";
        String feedId = "feeedId2";
        String sql = "select 'c2' as category, 'f2' as feed, processing_dttm as processingdttm, columnname,  metrictype, metricvalue  from cw_test001.ingest_test020_profile where processing_dttm = '1546491732587'";
        Dataset<Row> df = ss.sql(sql);

        //dsg_tab_resources
        String validCount = "0";
//        List<Row> rows2 = new ArrayList<>();
//        RDD<Row> rdd = df.rdd();
        List<Row> collect = df.collectAsList();
//        df.rdd().foreach(row -> {
//            rows2.add(row);
//            return null;
//        });
        for (Row row : collect) {
            if(row.getAs("metrictype").equals("VALID_COUNT")) {
                validCount = row.getAs("metricvalue");
                break;
            }
        }

        List<Row> rows = new ArrayList<>();
        Row row = RowFactory.create(category, feed, feedts, feedId, tableType, dbName, tableName, new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), 0, Integer.parseInt(validCount));
        rows.add(row);

        List<StructField> structFields = new ArrayList<>();
        structFields.add(DataTypes.createStructField("category", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("feed", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("processingdttm", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("feed_id", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("table_type", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("db_name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("table_name", DataTypes.StringType, true));
        structFields.add(DataTypes.createStructField("create_time", DataTypes.DateType, true));
        structFields.add(DataTypes.createStructField("update_time", DataTypes.DateType, true));
        structFields.add(DataTypes.createStructField("size", DataTypes.IntegerType, true));
        structFields.add(DataTypes.createStructField("cnt", DataTypes.IntegerType, true));
        StructType structType = DataTypes.createStructType(structFields);

        Dataset<Row> dataFrame = ss.sqlContext().createDataFrame(rows, structType);

        Properties prop = new Properties();

        prop.put("driver", driver);
        // prop.put("url", url);
        prop.put("user", username);
        prop.put("password", password);

        dataFrame.write().mode(SaveMode.Append).jdbc(url,"dsg_tab_resources",prop);
    }
}
