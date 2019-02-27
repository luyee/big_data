package com.caiw.kafkamonitor;

import kafka.javaapi.OffsetResponse;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.common.TopicAndPartition;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.PartitionMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class KafkaMonitor {
    private static Logger log = LoggerFactory.getLogger(KafkaMonitor.class);

    private String topic;
    private int port;
    private String host;
    private int time;

    public KafkaMonitor(String topic,int port,String host,int time) {
        this.topic = topic;
        this.port = port;
        this.host = host;
        this.time = time;
    }

    public Map<String, String> getEveryPartitionMaxOffset() {
        //1.获取topic所有分区  以及每个分区的元数据     => 返回 Map<分区id,分区元数据>
        TreeMap<Integer, PartitionMetadata> partitionIdAndMeta = findTopicEveryPartition();
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<Integer, PartitionMetadata> entry : partitionIdAndMeta.entrySet()) {
            int leaderPartitionId = entry.getKey();
            //2.根据每个分区的元数据信息 ==> 获取leader分区的主机
            String leadBroker = entry.getValue().leader().host();
            String clientName = "Client_" + topic + "_" + leaderPartitionId;
            SimpleConsumer consumer = new SimpleConsumer(leadBroker, port,100000, 64 * 1024, clientName);
            //3.从leader主机获取分区的offset
            long readOffset = getLastOffset(consumer, topic, leaderPartitionId, clientName);
            map.put(String.valueOf(leaderPartitionId), String.valueOf(readOffset));
            if (consumer != null)
                consumer.close();
        }
        return map;
    }

    private TreeMap<Integer, PartitionMetadata> findTopicEveryPartition(){
        TreeMap<Integer, PartitionMetadata> map = new TreeMap<Integer, PartitionMetadata>();
        SimpleConsumer consumer = null;
        try {
            consumer = new SimpleConsumer(host, port, 100000, 64 * 1024,"leaderLookup" + new Date().getTime());
            List<String> topics = Collections.singletonList(topic);
            TopicMetadataRequest req = new TopicMetadataRequest(topics);
            kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);
            List<TopicMetadata> metaData = resp.topicsMetadata();

            if(metaData!=null && !metaData.isEmpty()){
                TopicMetadata item = metaData.get(0);
                for (PartitionMetadata part : item.partitionsMetadata()) {
                    map.put(part.partitionId(), part);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null)
                consumer.close();
        }
        return map;
    }


    private long getLastOffset(SimpleConsumer consumer, String topic,int leaderPartitionId, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic,leaderPartitionId);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(time, 1));
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(),clientName);
        OffsetResponse response = consumer.getOffsetsBefore(request);
        if (response.hasError()) {
            log.error("Error fetching data Offset Data the Broker. Reason: "+ response.errorCode(topic, leaderPartitionId));
            return 0;
        }
        long[] offsets = response.offsets(topic, leaderPartitionId);
        return offsets[0];
    }



    public static void main(String[] args) {
        int port = 6667;
        String topic = "cw_test001";
        int time = -1;
        KafkaMonitor offsetSearch = new KafkaMonitor(topic,port,"192.168.23.220",time);
        Map<String, String> map = offsetSearch.getEveryPartitionMaxOffset();
        for (String key : map.keySet()) {
            System.out.println(key+"---"+map.get(key));
        }
    }

}
