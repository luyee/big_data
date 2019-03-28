package com.caiw.nuwapi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.Random;

/**
 * @ClassName Producer
 * @Author caiwe
 * @CreateTime 2018/8/8  14:17
 **/

public class Producer {
    private final static String TOPIC = "cw_test_20190327_01";

    private static String[] sourceId = {"/var/log/test.json","/var/csv/123.csv"};

    private static String[] eventType = {"insert","update","delete"};

    private static void produce(int i,KafkaProducer<String, String> producer){
        String key = String.valueOf(i);
        String meg = getJsonMessage(i);
        producer.send(new ProducerRecord<>(TOPIC,key,meg));
//        System.out.println(meg);

    }

    /*
     *
     {
     "id":1,
     "source_id":"/var/csv/test.csv",
     "event_type":"insert",
     "program_name":"testApp",
     "ip_addr":"192.168.23.120",
     "read_data_count":20000,
     "read_bytes":1000000,
     "write_data_count":20000,
     "write_byte":1000000,
     "begin_time":1533598833000,
     "end_time":1533698833000,
     "status":0
     }
     */
    private static String getJsonMessage(int i){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer("{");
//        buffer.append("\"id\":").append(i).append(",");
//        buffer.append("\"source_id\":\"").append(sourceId[random.nextInt(2)]).append("\",");
//        buffer.append("\"event_type\":\"").append(eventType[random.nextInt(3)]).append("\",");
//        buffer.append("\"program_name\":\"testApp").append(i).append("\",");
//        buffer.append("\"ip_addr\":\"192.168.23.").append(random.nextInt(200)).append("\",");
//        buffer.append("\"read_data_count\":").append(random.nextInt(5000)).append(",");
//        buffer.append("\"read_bytes\":").append(random.nextInt(1000000)).append(",");
//        buffer.append("\"write_data_count\":").append(random.nextInt(5000)).append(",");
//        buffer.append("\"write_byte\":").append(random.nextInt(1000000)).append(",");
//        buffer.append("\"begin_time\":1532").append(random.nextInt(89)+10).append("8833000,");
//        buffer.append("\"end_time\":1533").append(random.nextInt(89)+10).append("8833000,");
//        buffer.append("\"status\":0");
        buffer.append("\"name\":\"testApp").append(i).append("\",");
        buffer.append("\"age\":\"").append(random.nextInt(59)+10).append("\"");
        buffer.append("}");
        return buffer.toString();
    }


    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.23.54:6667,192.168.23.55:6667,192.168.23.56:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置分区类,根据key进行数据分区
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);


        for (int i = 2000003;i<= 2000004;i++){
            produce(i,producer);
            if(i % 100 == 0){
                System.out.println(i);
            }
        }
        producer.close();
//        System.out.println(getJsonMessage(1));
    }






}