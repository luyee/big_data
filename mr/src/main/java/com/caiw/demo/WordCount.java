package com.caiw.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class WordCount {

    private static Logger logger = LoggerFactory.getLogger(WordCount.class);

    public static void main(String[] args) {

        args = new String[]{"/caiw/test.txt","/caiw/out"};

        logger.info(args[0] + "-----" + args[1]);
        try {
            Configuration conf = new Configuration();
            Job wcJob = Job.getInstance(conf);
            wcJob.setJobName("MyWordCount");
            wcJob.setJarByClass(WordCount.class);

            wcJob.setJarByClass(InitMapReduceJob.class);

            wcJob.setMapperClass(WordCountMapper.class);
            wcJob.setReducerClass(WordCountReducer.class);
            wcJob.setMapOutputKeyClass(Text.class);
            wcJob.setMapOutputValueClass(LongWritable.class);
            wcJob.setOutputKeyClass(Text.class);
            wcJob.setOutputValueClass(LongWritable.class);
            FileInputFormat.setInputPaths(wcJob, args[0]);

            FileOutputFormat.setOutputPath(wcJob, new Path(args[1]));
            //调用job对象的waitForCompletion()方法，提交作业。
            boolean res = wcJob.waitForCompletion(true);
            System.exit(res ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
