package com.caiw.stormwithkafka;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class KafkaTopology {
    public static void main(String[] args) {
        //用于Kafka与storm的整合
        BrokerHosts brokerHosts = new ZkHosts("192.168.23.220:2181,192.168.23.221:2181,192.168.23.222:2181");
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "cw_test001", "/hadoop/zookeeper", "kafkaspout");
        Config config = new Config();
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        //设置拿去数据的偏移量,取出的时现在生产的数据
        spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
        spoutConfig.zkServers = Arrays.asList("192.168.23.220","192.168.23.221","192.168.23.222");
        spoutConfig.zkPort = 2181;

        RecordFormat format = new DelimitedRecordFormat().withFieldDelimiter("|");
        SyncPolicy syncPolicy = new CountSyncPolicy(1000);
        FileRotationPolicy rotationPolicy = new FileSizeRotationPolicy(100.0f, FileSizeRotationPolicy.Units.MB);
        FileNameFormat fileNameFormat = new DefaultFileNameFormat().withPath("/tmp");

        HdfsBolt hdfsBolt = new HdfsBolt()
                .withFsUrl("hdfs://192.168.23.220:8020")
                .withFileNameFormat(fileNameFormat)
                .withRecordFormat(format)
                .withRotationPolicy(rotationPolicy)
                .withSyncPolicy(syncPolicy);


        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new KafkaSpout(spoutConfig));
        builder.setBolt("ChangNameBolt",new ChangNameBolt()).shuffleGrouping("spout");
        builder.setBolt("printBolt",new PrintBolt()).shuffleGrouping("ChangNameBolt");
//        builder.setBolt("hdfsBolt",hdfsBolt).shuffleGrouping("ChangNameBolt");
        config.setNumWorkers(3);
        //判断是否有参数，有参数就集群运行
//        if (args != null && args.length > 0) {
//            try {
//                StormSubmitter.submitTopologyWithProgressBar(KafkaTopology.class.getSimpleName(), new Config(), builder.createTopology());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("KafkaTopology", new Config(), builder.createTopology());
           // cluster.shutdown();
//        }
    }
}
