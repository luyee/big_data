package com.caiw.alitest;


import com.caiw.utils.PropertiesUtil;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ALiKafkaTest {

    @Test
    public void kafkaProduceTest(){
        // 设置 sasl 文件的路径
        PropertiesUtil.configureSasl();
        // 加载 kafka.properties
        Properties kafkaProperties =  PropertiesUtil.getKafkaProperties();
        Properties props = new Properties();
        // 设置接入点，请通过控制台获取对应 Topic 的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));
        // 设置 SSL 根证书的路径，请记得将 XXX 修改为自己的路径
        //与 sasl 路径类似，该文件也不能被打包到 jar 中
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getProperty("ssl.truststore.location"));
       //根证书store的密码，保持不变
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
        //接入协议，目前支持使用 SASL_SSL 协议接入
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        // SASL 鉴权方式，保持不变
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        // Kafka 消息的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);

        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<String, String>("alikafka-cw_topic001","this is a test2"));
        producer.flush();
        producer.close();
    }


    @Test
    public void kafkaConsumerTest(){
        // 设置 sasl 文件的路径
        PropertiesUtil.configureSasl();
        // 加载 kafka.properties
        Properties kafkaProperties =  PropertiesUtil.getKafkaProperties();
        Properties props = new Properties();
        // 设置接入点，请通过控制台获取对应 Topic 的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));
        // 设置 SSL 根证书的路径，请记得将 XXX 修改为自己的路径
        // 与 sasl 路径类似，该文件也不能被打包到 jar 中
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getProperty("ssl.truststore.location"));
        // 根证书 store 的密码，保持不变
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
        // 接入协议，目前支持使用 SASL_SSL 协议接入
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        // SASL 鉴权方式，保持不变
        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        // 两次 poll 之间的最大允许间隔
        // 请不要改得太大，服务器会掐掉空闲连接，不要超过 30000
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);
        // 每次 poll 的最大数量
        // 注意该值不要改得太大，如果 poll 太多数据，而不能在下次 poll 之前消费完，则会触发一次负载均衡，产生卡顿
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        // 消息的反序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 当前消费实例所属的消费组，请在控制台申请之后填写
        // 属于同一个组的消费实例，会负载消费消息
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getProperty("group.id"));
        // 构造消息对象，也即生成一个消费实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 设置消费组订阅的 Topic，可以订阅多个
        // 如果 GROUP_ID_CONFIG 是一样，则订阅的 Topic 也建议设置成一样
        List<String> subscribedTopics =  new ArrayList<String>();
        // 如果需要订阅多个 Topic，则在这里 add 进去即可
        // 每个 Topic 需要先在控制台进行创建
        subscribedTopics.add(kafkaProperties.getProperty("topic"));
        consumer.subscribe(subscribedTopics);
        // 循环消费消息
        while (true){
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                // 必须在下次 poll 之前消费完这些数据, 且总耗时不得超过 SESSION_TIMEOUT_MS_CONFIG
                // 建议开一个单独的线程池来消费消息，然后异步返回结果
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Throwable ignore) {
                }
                e.printStackTrace();
            }
        }
    }
}
