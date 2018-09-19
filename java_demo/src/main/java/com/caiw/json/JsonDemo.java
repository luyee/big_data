package com.caiw.json;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonDemo {
    public static void main(String[] args) {
        Map<String,String> jsonMap = new HashMap<>();

        jsonMap.put("category","test_201809");
        jsonMap.put("feed","user_test");
        jsonMap.put("feedts","1537241037000");
        jsonMap.put("hdfs.ingest.root","/etl");
        jsonMap.put("hive.ingest.root","/model.db");
        jsonMap.put("hive.master.root","/app/warehouse");
        jsonMap.put("metadata.dataTransformation.datasourceIds","61f6ae05-d373-4d7f-9064-ef826d1c95a1");
        jsonMap.put("metadata.dataTransformation.dataTransformScript","import org.apache.spark.sql._\n" +
                "val tbl10 = datasourceProvider.getTableFromDatasource(\"test.user_test\", \"61f6ae05-d373-4d7f-9064-ef826d1c95a1\", sqlContext).alias(\"tbl10\")\n" +
                "var df = tbl10.select(tbl10.col(\"registration_dttm\").as(\"registration_dttm\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"id\").as(\"id\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"first_name\").as(\"first_name\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"last_name\").as(\"last_name\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"email\").as(\"email\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"gender\").as(\"gender\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"ip_address\").as(\"ip_address\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"cc\").as(\"cc\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"country\").as(\"country\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"birthdate\").as(\"birthdate\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"salary\").as(\"salary\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()), tbl10.col(\"title\").as(\"title\", new org.apache.spark.sql.types.MetadataBuilder().putString(\"comment\", \"\").build()))\n" +
                "df = df\n" +
                "df");
        jsonMap.put("metadata.table.feedFieldStructure","registration_dttm|string||0|0|0|registration_dttm\n" +
                "id|string||1|0|0|id\n" +
                "first_name|string||0|0|0|first_name\n" +
                "last_name|string||0|0|0|last_name\n" +
                "email|string||0|0|0|email\n" +
                "gender|string||0|0|0|gender\n" +
                "ip_address|string||0|0|0|ip_address\n" +
                "cc|string||0|0|0|cc\n" +
                "country|string||0|0|0|country\n" +
                "birthdate|string||0|0|0|birthdate\n" +
                "salary|string||0|0|0|salary\n" +
                "title|string||0|0|0|title");
        jsonMap.put("metadata.table.fieldPoliciesJson","[{\"profile\":true,\"index\":false,\"fieldName\":\"registration_dttm\",\"feedFieldName\":\"registration_dttm\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"id\",\"feedFieldName\":\"id\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"first_name\",\"feedFieldName\":\"first_name\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"last_name\",\"feedFieldName\":\"last_name\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"email\",\"feedFieldName\":\"email\",\"standardization\":[],\"validation\":[{\"name\":\"Email\",\"displayName\":null,\"description\":null,\"shortDescription\":null,\"properties\":[],\"objectClassType\":\"com.thinkbiganalytics.policy.validation.EmailValidator\",\"objectShortClassType\":\"EmailValidator\",\"propertyValuesDisplayString\":null,\"sequence\":0}],\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"gender\",\"feedFieldName\":\"gender\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"ip_address\",\"feedFieldName\":\"ip_address\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"cc\",\"feedFieldName\":\"cc\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"country\",\"feedFieldName\":\"country\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"birthdate\",\"feedFieldName\":\"birthdate\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"salary\",\"feedFieldName\":\"salary\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false},{\"profile\":true,\"index\":false,\"fieldName\":\"title\",\"feedFieldName\":\"title\",\"standardization\":null,\"validation\":null,\"domainTypeId\":null,\"partitionColumn\":false}]");
        jsonMap.put("metadata.table.fieldStructure","registration_dttm|string||0|0|0|registration_dttm\n" +
                "id|string||1|0|0|id\n" +
                "first_name|string||0|0|0|first_name\n" +
                "last_name|string||0|0|0|last_name\n" +
                "email|string||0|0|0|email\n" +
                "gender|string||0|0|0|gender\n" +
                "ip_address|string||0|0|0|ip_address\n" +
                "cc|string||0|0|0|cc\n" +
                "country|string||0|0|0|country\n" +
                "birthdate|string||0|0|0|birthdate\n" +
                "salary|string||0|0|0|salary\n" +
                "title|string||0|0|0|title");
        jsonMap.put("metadata.table.targetFormat","STORED AS ORC");
        jsonMap.put("metadata.table.targetMergeStrategy","SYNC");
        jsonMap.put("nifi.home","/opt/nifi");
        jsonMap.put("spark.input_folder","/tmp");
        jsonMap.put("spark.version","2");


        Object json = JSONObject.toJSON(jsonMap);

        System.out.println(json.toString());
//        JSONObject jsonObject = JSONObject.parseObject(json.toString());
//        System.out.println(jsonObject.get("metadata.table.fieldPoliciesJson"));
    }
}
