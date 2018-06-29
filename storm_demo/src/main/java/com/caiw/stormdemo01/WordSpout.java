package com.caiw.stormdemo01;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordSpout extends BaseRichSpout {

    String[] INIT_DATA=new String[]{"hello","java","php","c++"};

    private SpoutOutputCollector collector;
    /**
     * 全局的一个方法，一般情况下用于定义一些值的初始值
     * @param conf
     * @param context
     * @param collector
     */
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector=collector;
    }

    /**
     * 定义向bolt里面发送的数据定义（值)
     */
    @Override
    public void nextTuple() {
        System.out.println("----------------------------------------");
        //拿到数据
        String data=INIT_DATA[new Random().nextInt(INIT_DATA.length)];
        //发送
        collector.emit(new Values(data));
    }

    /**
     * 输出字段，名称
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
