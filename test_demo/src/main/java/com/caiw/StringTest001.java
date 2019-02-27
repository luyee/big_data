package com.caiw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class StringTest001 {
    public static void main(String[] args) {
//        String str = "/tmp/cwTest/test001/userdata3.csv._COPYING_";
////        int num = str.lastIndexOf(".");
////        str = str.substring(0,num);
////        System.out.println(str);
//        String s = str.toLowerCase();
//        System.out.println(s);


//        String json = "{" +
//                "\"spark.input_folder\":\"/tmp\"," +
//                "\"metadata.table.fieldStructure\":\"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\"," +
//                "\"metadata.table.fieldPoliciesJson\":\"[{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"registration_dttm\\\",\\\"feedFieldName\\\":\\\"registration_dttm\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"id\\\",\\\"feedFieldName\\\":\\\"id\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"first_name\\\",\\\"feedFieldName\\\":\\\"first_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"last_name\\\",\\\"feedFieldName\\\":\\\"last_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"email\\\",\\\"feedFieldName\\\":\\\"email\\\",\\\"standardization\\\":[],\\\"validation\\\":[{\\\"name\\\":\\\"Email\\\",\\\"displayName\\\":null,\\\"description\\\":null,\\\"shortDescription\\\":null,\\\"properties\\\":[],\\\"objectClassType\\\":\\\"com.thinkbiganalytics.policy.validation.EmailValidator\\\",\\\"objectShortClassType\\\":\\\"EmailValidator\\\",\\\"propertyValuesDisplayString\\\":null,\\\"sequence\\\":0}],\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"gender\\\",\\\"feedFieldName\\\":\\\"gender\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"ip_address\\\",\\\"feedFieldName\\\":\\\"ip_address\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"cc\\\",\\\"feedFieldName\\\":\\\"cc\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"country\\\",\\\"feedFieldName\\\":\\\"country\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"birthdate\\\",\\\"feedFieldName\\\":\\\"birthdate\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"salary\\\",\\\"feedFieldName\\\":\\\"salary\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"title\\\",\\\"feedFieldName\\\":\\\"title\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false}]\"," +
//                "\"feed\":\"user_test\"," +
//                "\"metadata.dataTransformation.datasourceIds\":\"61f6ae05-d373-4d7f-9064-ef826d1c95a1\"," +
//                "\"metadata.table.feedFieldStructure\":\"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\"," +
//                "\"metadata.dataTransformation.dataTransformScript\":\"import org.apache.spark.sql._\\nval tbl10 = datasourceProvider.getTableFromDatasource(\\\"test.user_test\\\", \\\"4951557f-ef85-4ff8-98af-42b21f84154c\\\", sqlContext).alias(\\\"tbl10\\\")\\nvar df = tbl10.select(tbl10.col(\\\"registration_dttm\\\").as(\\\"registration_dttm\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"id\\\").as(\\\"id\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"first_name\\\").as(\\\"first_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"last_name\\\").as(\\\"last_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"email\\\").as(\\\"email\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"gender\\\").as(\\\"gender\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"ip_address\\\").as(\\\"ip_address\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"cc\\\").as(\\\"cc\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"country\\\").as(\\\"country\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"birthdate\\\").as(\\\"birthdate\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"salary\\\").as(\\\"salary\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"title\\\").as(\\\"title\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()))\\ndf = df\\ndf\"," +
//                "\"category\":\"test_201809\"" +
//                "}";
//
//        Serializable serializable = json;
//
//        String id= "1111111";
//        String baseType = "USER";
//        StringBuilder sb = new StringBuilder();
//        sb.append("{\"uuid\":\"");
//        sb.append(id);
//        sb.append("\",\"type\":\"");
//        sb.append(baseType);
//        sb.append("\",\"opt\":\"DELRELATIONSHIPBYTYPE\"}");
//        System.out.println(sb.toString());
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        switch (s){
//            case "111":
//                System.out.println("1111");
//                break;
//            case "222":
//                System.out.println("2222");
//                break;
//            case "3":
//                System.out.println("3");
//                break;
//        }
//        String json = "[{\"id\":2073,\"name\":\"DBP\"},{\"id\":2074,\"name\":\"SuperSync\"},{\"id\":2075,\"name\":\"BDMP\"},{\"id\":2076,\"name\":\"ETL\"},{\"id\":2077,\"name\":\"一体机\"},{\"id\":2078,\"name\":\"DMP\"},{\"id\":2079,\"name\":\"Audit\"},{\"id\":2080,\"name\":\"Dsync\"},{\"id\":2081,\"name\":\"Archiving\"},{\"id\":2082,\"name\":\"SnapAssure\"}]";
//        String json = "{\"id\":2073,\"name\":\"DBP\"}";
//        String json = "[{\"id\":1,\"name\":\"test\"}]";
//        if(!json.startsWith("[")){
//            System.err.println("Data format error. example [{\"id\":1,\"name\":\"test\"}]");
//            System.exit(1);
//        }
//        List<JSONObject> objects = JSON.parseArray(json,JSONObject.class);
////        JSONObject jsonObject = JSON.parseObject(json);
//
//
//        JSONObject o = objects.get(0);
////        List list = (List) o;
//
//        Set<String> keySet = o.keySet();
//        keySet.forEach(System.out::println);
//        objects.forEach(obj -> {
//
//        });

//        String a = "1.2.3";
//        String substring1 = a.substring(a.indexOf(".") + 1);
//        String substring2 = a.substring(a.lastIndexOf(".") + 1);


        List<Person1> person1s = new ArrayList<>();
        Person1 person1 = new Person1();
        person1.setAge("10");
        person1.setName("cw1");
        person1s.add(person1);
        Person1 person2 = new Person1();
        person2.setAge("20");
        person2.setName("cw2");
        person1s.add(person2);
        String s = JSONObject.toJSONString(person1s);

        System.out.println(s);

//        System.out.println(substring1);
//        System.out.println(substring2);
    }

}

class Person1{
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
