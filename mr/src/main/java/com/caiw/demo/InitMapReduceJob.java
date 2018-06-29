package com.caiw.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 */
public class InitMapReduceJob {

    public static Job initWordCountJob(Configuration conf) {
        Job wcJob = null;
        try {
            conf.setBoolean("mapreduce.app-submission.cross-platform", true);  //设置跨平台提交作业
            //设置job所使用的jar包,使用Configuration对象调用set()方法，设置mapreduce.job.jar wcount.jar
            conf.set("mapred.jar", System.getProperty("user.dir") + "/lib/mr-demo-1.0-SNAPSHOT.jar");
            //创建job对象需要conf对象，conf对象包含的信息是：所用的jar包
            wcJob = Job.getInstance(conf);
            wcJob.setJobName("MyWordCount");

            //设置wcJob中的资源所在的jar包
            //调用job对象的setJarByClass()方法，参数是WordCountRunner.class,设置job作业中的资源所在的jar包
            wcJob.setJarByClass(InitMapReduceJob.class);

            //wcJob要使用哪个mapper类，job对象调用setMapperClass()方法，参数是WordCountMapper.class
            wcJob.setMapperClass(WordCountMapper.class);
            //wcJob要使用哪个reducer类,job对象调用setReducerClass()方法，参数为WordCountReducer.class
            wcJob.setReducerClass(WordCountReducer.class);

            //wcJob的mapper类输出的kv数据类型
            //job对象调用setMapperOutputKeyClass();设置Mapper类输出的key值的类型--Text
            //job对象调用setMapperOutputValueClass();设置Mapper类输出value值的类型--LongWritable
            wcJob.setMapOutputKeyClass(Text.class);
            wcJob.setMapOutputValueClass(LongWritable.class);

            //wcjob的reducer类输出的kv数据类型
            //job对象调用setOutputKey
            wcJob.setOutputKeyClass(Text.class);
            wcJob.setOutputValueClass(LongWritable.class);

            //指定要处理的原始数据所存放的路径
            //调用FileInputFormat对象的setInputPath()方法，参数的文件路径，是设置的源数据路径，当此处为集群的路径是就是跑在集群上的程序，
            //如果设置在当前机器的路径，就是本地模式
            FileInputFormat.setInputPaths(wcJob, "/fayson");

            //指定处理之后的结果输出到哪个路径，注意此时应当在路径应当是差不多的
            FileOutputFormat.setOutputPath(wcJob, new Path("/wc/output"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wcJob;
    }
}
