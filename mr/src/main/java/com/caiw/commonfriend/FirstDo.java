package com.caiw.commonfriend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ClassName FirstDo
 * @Author caiwe
 * @CreateTime 2018/9/4  15:27
 **/

public class FirstDo {

    static class FirstMapper extends Mapper<LongWritable,Text,Text,Text>{

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String user = line.split(":")[0];
            String[] friends = line.split(":")[1].split(",");

            for (String friend : friends){
                context.write(new Text(friend),new Text(user));
            }
        }
    }

    static class FirstReducer extends Reducer<Text,Text,Text,Text>{

        @Override
        protected void reduce(Text friend, Iterable<Text> users, Context context) throws IOException, InterruptedException {
            StringBuilder buffer = new StringBuilder();
            for (Text text : users){
                buffer.append(text).append(",");
            }
            context.write(friend,new Text(buffer.toString()));
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //本地调试模式
        conf.set("mapreduce.framework.name","local");
        conf.set("fs.defaultFS","file:///");

//        本地提交模式 hdfs在线
//        conf.set("mapreduce.framework.name","local");
//        conf.set("fs.defaultFS","hdfs://master:9000");
        Job job = Job.getInstance(conf);

        //线上
        job.setJarByClass(FirstDo.class);

        //指定运行的map程序
        job.setMapperClass(FirstMapper.class);
        job.setReducerClass(FirstReducer.class);

       //指定map输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //最终输出数据类型kv
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

//


        //指定job的输入原始文件所在目录
//        FileInputFormat.setInputPaths(job,new Path("/wordcount/input"));
        FileInputFormat.setInputPaths(job, new Path("mr/src/main/resources/friend.txt"));
        //D:\DSG\project\big_data\mr\src\main\resources
        FileOutputFormat.setOutputPath(job, new Path("mr/src/main/resources/firstResult"));

        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }

}
