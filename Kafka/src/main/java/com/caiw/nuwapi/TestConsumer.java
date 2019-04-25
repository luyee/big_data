package com.caiw.nuwapi;



import org.apache.commons.collections.map.HashedMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/12 14:54
 */
public class TestConsumer {
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashedMap();
    int count = 0;

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config","D:\\tmp\\161hdp\\kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf","D:\\tmp\\161hdp\\krb5.conf");
        Properties props = new Properties();
        props.put("group.id", "test_2019042301");// 指定消费者组
        props.put("enable.auto.commit", "true");// 关闭自动提交
        //props.put("auto.commit.interval.ms", "1000");// 自动提交的时间间隔
        // 反序列化消息主键
        props.put("auto.offset.reset", "earliest"); // 缓冲大小
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 反序列化消费记录
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("log4j.appender.kafkaAppender.Threshold", "ERROR");

//        props.put("bootstrap.servers", "yamb2:6667,yamb3:6667,yamb4:6667");
        props.put("bootstrap.servers", "192.168.23.162:6667,192.168.23.163:6667,192.168.23.164:6667");
//        props.put("request.required.acks", "1");
        props.put("security.protocol", "PLAINTEXTSASL");
        props.put("sasl.kerberos.service.name","kafka");

        // 创建一个消费者实例对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅消费主题集合
        consumer.subscribe(Collections.singletonList("cw_test2019042301"));
        // 实时消费标识
        boolean flag = true;
        while (flag) {
            // 获取主题消息数据
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records){
                // 循环打印消息记录
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
                //处理消息
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                //解析消息将消息存储到Hbase上的表中;
                // consumer.commitSync(currentOffsets);
                //手动提交偏移量
            }


        }
        // 出现异常关闭消费者对象
//		consumer.commitAsync();
//		consumer.commitSync();
        consumer.close();
    }
}
