package com.caiw.stormdemo01;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class WordBolt extends BaseRichBolt {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector=collector;
    }


    @Override
    public void execute(Tuple input) {
        //拿到spout发送过来的数据
       //String data=(String)input.getValueByField("word");
       // String data=input.getString(0);
        String data=input.getStringByField("word");
        //先判断传递过来的值，在map中是否存在

        Integer count=counts.get(data);
        if(count==null)
            count=0;
        counts.put(data,++count);
        collector.emit(new Values(data,count));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","count"));
    }
}
