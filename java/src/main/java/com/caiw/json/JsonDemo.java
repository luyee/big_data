package com.caiw.json;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JsonDemo {
    public static void main(String[] args) {
        Map<String,String> jsonMap = new HashMap<>();

        jsonMap.put("id","af21dc28-026a-459d-ace2-9d40f029c479");
        jsonMap.put("category","test_201809");
        jsonMap.put("feed","user_test");
        jsonMap.put("feedts","1537241037000");
        jsonMap.put("metadata.dataTransformation.datasourceIds","56bdd9ac-b2f2-4842-b9ff-0e2b18016fb0");
        jsonMap.put("metadata.dataTransformation.dataTransformScript","import org.apache.spark.sql._\n" +
                "val tbl10 = datasourceProvider.getTableFromDatasource(\"test.user_test\", \"56bdd9ac-b2f2-4842-b9ff-0e2b18016fb0\", sqlContext).alias(\"tbl10\")\n" +
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


        Object json = JSONObject.toJSON(jsonMap);

        System.out.println(json.toString());
//        String jsonStr = "{\n" +
//                "\t\"metadata.table.fieldStructure\": \"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\",\n" +
//                "\t\"metadata.table.fieldPoliciesJson\": \"[{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"registration_dttm\\\",\\\"feedFieldName\\\":\\\"registration_dttm\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"id\\\",\\\"feedFieldName\\\":\\\"id\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"first_name\\\",\\\"feedFieldName\\\":\\\"first_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"last_name\\\",\\\"feedFieldName\\\":\\\"last_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"email\\\",\\\"feedFieldName\\\":\\\"email\\\",\\\"standardization\\\":[],\\\"validation\\\":[{\\\"name\\\":\\\"Email\\\",\\\"displayName\\\":null,\\\"description\\\":null,\\\"shortDescription\\\":null,\\\"properties\\\":[],\\\"objectClassType\\\":\\\"com.thinkbiganalytics.policy.validation.EmailValidator\\\",\\\"objectShortClassType\\\":\\\"EmailValidator\\\",\\\"propertyValuesDisplayString\\\":null,\\\"sequence\\\":0}],\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"gender\\\",\\\"feedFieldName\\\":\\\"gender\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"ip_address\\\",\\\"feedFieldName\\\":\\\"ip_address\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"cc\\\",\\\"feedFieldName\\\":\\\"cc\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"country\\\",\\\"feedFieldName\\\":\\\"country\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"birthdate\\\",\\\"feedFieldName\\\":\\\"birthdate\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"salary\\\",\\\"feedFieldName\\\":\\\"salary\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"title\\\",\\\"feedFieldName\\\":\\\"title\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false}]\",\n" +
//                "\t\"feed\": \"user_test\",\n" +
//                "\t\"metadata.dataTransformation.datasourceIds\": \"61f6ae05-d373-4d7f-9064-ef826d1c95a1\",\n" +
//                "\t\"metadata.table.feedFieldStructure\": \"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\",\n" +
//                "\t\"metadata.dataTransformation.dataTransformScript\": \"import org.apache.spark.sql._\\nval tbl10 = datasourceProvider.getTableFromDatasource(\\\"test.user_test\\\", \\\"4951557f-ef85-4ff8-98af-42b21f84154c\\\", sqlContext).alias(\\\"tbl10\\\")\\nvar df = tbl10.select(tbl10.col(\\\"registration_dttm\\\").as(\\\"registration_dttm\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"id\\\").as(\\\"id\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"first_name\\\").as(\\\"first_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"last_name\\\").as(\\\"last_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"email\\\").as(\\\"email\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"gender\\\").as(\\\"gender\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"ip_address\\\").as(\\\"ip_address\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"cc\\\").as(\\\"cc\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"country\\\").as(\\\"country\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"birthdate\\\").as(\\\"birthdate\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"salary\\\").as(\\\"salary\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"title\\\").as(\\\"title\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()))\\ndf = df\\ndf\",\n" +
//                "\t\"category\": \"test_201809\",\n" +
//                "\t\"id\": \"1\"\n" +
//                "}";
//        JSONObject jsonObject = JSONObject.parseObject(json.toString());
//        System.out.println(jsonObject.get("id"));
    }
}
