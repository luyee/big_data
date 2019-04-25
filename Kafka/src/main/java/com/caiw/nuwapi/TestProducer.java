package com.caiw.nuwapi;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/12 14:54
 */
public class TestProducer {

    public static void main(String... args) throws InterruptedException {
        String topic = "cw_test2019042301";

        System.setProperty("java.security.auth.login.config","D:\\tmp\\161hdp\\kafka_client_jaas.conf");
        System.setProperty("java.security.krb5.conf","D:\\tmp\\161hdp\\krb5.conf");
//        System.setProperty("security.auth.useSubjectCredsOnly","false");

        Properties props = new Properties();
        props.put("bootstrap.servers", "yamb2:6667,yamb3:6667,yamb4:6667");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("request.required.acks", "1");
        props.put("security.protocol", "PLAINTEXTSASL");
        props.put("sasl.kerberos.service.name","kafka");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10000; i++){
            String s = UUID.randomUUID().toString() +" " + i + " Test Date: " + new Date();
            System.out.println(s);
            producer.send(new ProducerRecord<>(topic,s ));
            Thread.sleep(1000);
        }
    }
}
