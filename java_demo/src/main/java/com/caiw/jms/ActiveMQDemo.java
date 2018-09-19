package com.caiw.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQDemo {
    public static void main(String[] args) {
        try {
            // 连接工厂
            // 路径 tcp://host:61616
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.6.253:61616");
            // 获取一个连接
            Connection connection = connectionFactory.createConnection();
            // 建立会话
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建队列或者话题对象
            Queue queue = session.createQueue("analyse");
            // 创建生产者 或者 消费者
            MessageProducer producer = session.createProducer(queue);
            //json信息
            String json = "{\"metadata.table.targetFormat\":\"STORED AS ORC\"," +
                    "\"spark.input_folder\":\"/tmp\"," +
                    "\"metadata.table.fieldStructure\":\"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\"," +
//                    "\"nifi.home\":\"/opt/nifi\"," +
                    "\"metadata.table.fieldPoliciesJson\":\"[{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"registration_dttm\\\",\\\"feedFieldName\\\":\\\"registration_dttm\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"id\\\",\\\"feedFieldName\\\":\\\"id\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"first_name\\\",\\\"feedFieldName\\\":\\\"first_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"last_name\\\",\\\"feedFieldName\\\":\\\"last_name\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"email\\\",\\\"feedFieldName\\\":\\\"email\\\",\\\"standardization\\\":[],\\\"validation\\\":[{\\\"name\\\":\\\"Email\\\",\\\"displayName\\\":null,\\\"description\\\":null,\\\"shortDescription\\\":null,\\\"properties\\\":[],\\\"objectClassType\\\":\\\"com.thinkbiganalytics.policy.validation.EmailValidator\\\",\\\"objectShortClassType\\\":\\\"EmailValidator\\\",\\\"propertyValuesDisplayString\\\":null,\\\"sequence\\\":0}],\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"gender\\\",\\\"feedFieldName\\\":\\\"gender\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"ip_address\\\",\\\"feedFieldName\\\":\\\"ip_address\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"cc\\\",\\\"feedFieldName\\\":\\\"cc\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"country\\\",\\\"feedFieldName\\\":\\\"country\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"birthdate\\\",\\\"feedFieldName\\\":\\\"birthdate\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"salary\\\",\\\"feedFieldName\\\":\\\"salary\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false},{\\\"profile\\\":true,\\\"index\\\":false,\\\"fieldName\\\":\\\"title\\\",\\\"feedFieldName\\\":\\\"title\\\",\\\"standardization\\\":null,\\\"validation\\\":null,\\\"domainTypeId\\\":null,\\\"partitionColumn\\\":false}]\"," +
//                    "\"feedts\":\"1537241037000\"," +
                    "\"feed\":\"user_test\"," +
//                    "\"hdfs.ingest.root\":\"/etl\"," +
                    "\"metadata.dataTransformation.datasourceIds\":\"61f6ae05-d373-4d7f-9064-ef826d1c95a1\"," +
//                    "\"spark.version\":\"2\"," +
                    "\"metadata.table.feedFieldStructure\":\"registration_dttm|string||0|0|0|registration_dttm\\nid|string||1|0|0|id\\nfirst_name|string||0|0|0|first_name\\nlast_name|string||0|0|0|last_name\\nemail|string||0|0|0|email\\ngender|string||0|0|0|gender\\nip_address|string||0|0|0|ip_address\\ncc|string||0|0|0|cc\\ncountry|string||0|0|0|country\\nbirthdate|string||0|0|0|birthdate\\nsalary|string||0|0|0|salary\\ntitle|string||0|0|0|title\"," +
                    "\"metadata.dataTransformation.dataTransformScript\":\"import org.apache.spark.sql._\\nval tbl10 = datasourceProvider.getTableFromDatasource(\\\"test.user_test\\\", \\\"4951557f-ef85-4ff8-98af-42b21f84154c\\\", sqlContext).alias(\\\"tbl10\\\")\\nvar df = tbl10.select(tbl10.col(\\\"registration_dttm\\\").as(\\\"registration_dttm\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"id\\\").as(\\\"id\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"first_name\\\").as(\\\"first_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"last_name\\\").as(\\\"last_name\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"email\\\").as(\\\"email\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"gender\\\").as(\\\"gender\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"ip_address\\\").as(\\\"ip_address\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"cc\\\").as(\\\"cc\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"country\\\").as(\\\"country\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"birthdate\\\").as(\\\"birthdate\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"salary\\\").as(\\\"salary\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()), tbl10.col(\\\"title\\\").as(\\\"title\\\", new org.apache.spark.sql.types.MetadataBuilder().putString(\\\"comment\\\", \\\"\\\").build()))\\ndf = df\\ndf\"," +
                    "\"category\":\"test_201809\"" +
//                    "\"hive.ingest.root\":\"/model.db\"," +
//                    "\"hive.master.root\":\"/app/warehouse\"," +
//                    "\"metadata.table.targetMergeStrategy\":\"SYNC\"" +
                    "}";
            // 发送消息
            producer.send(session.createTextMessage(json));
            // 提交操作
            session.commit();
            //关闭资源
            session.close();
            connection.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
