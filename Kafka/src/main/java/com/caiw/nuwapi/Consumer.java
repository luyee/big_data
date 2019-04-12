package com.caiw.nuwapi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

public class Consumer {
    public static void main(String[] args) {

        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.23.221:6667,192.168.23.222:6667,192.168.23.220:6667");
//        props.put("bootstrap.servers", "cw02:9092");
        props.put("bootstrap.servers", "192.168.23.54:6667,192.168.23.55:6667,192.168.23.56:6667");
        props.put("group.id", "consumer-test2");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset","earliest");
        //offsets.storage=kafka and dual.commit.enabled=true in your consumer config
//        props.put("offsets.storage","zookeeper");
        props.put("enable.auto.commit","false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("cw_test_2019041101"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.offset() + ": " + record.value());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }
}
