package com.caiw.stormdemo01;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class WordTopology {
    public static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();
        //定义了自己的spout对象
        builder.setSpout("myspout",new WordSpout());
        builder.setBolt("mybout",new WordBolt()).shuffleGrouping("myspout");
        builder.setBolt("myprint",new PrintBolt()).shuffleGrouping("mybout");
        //在storm里面启动时，有两种模式，一种是本地模式和集群模式


        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("myTopology",new Config(),builder.createTopology());
        Thread.sleep(100000);
        cluster.shutdown();
    }
}
