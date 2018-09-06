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
 * @ClassName SecendDo
 * @Author caiwe
 * @CreateTime 2018/9/4  16:00
 **/

public class SecondDo {

    static class SecondMapper extends Mapper<LongWritable,Text,Text,Text>{

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String friend = line.split("\t")[0];
            String[] users = line.split("\t")[1].split(",");
            for(int i = 0 ; i< users.length-1; i++ ){
                for (int j = i+1; j < users.length; j++) {
                    String user2 = users[i]+"+"+users[j];
                    context.write(new Text(user2),new Text(friend));
                }
            }
        }
    }

    static class SecondReducer extends Reducer<Text,Text,Text,Text>{

        @Override
        protected void reduce(Text user2, Iterable<Text> friends, Context context) throws IOException, InterruptedException {
            StringBuilder buffer = new StringBuilder();
            for(Text friend : friends){
                buffer.append(friend).append(",");
            }
            context.write(user2,new Text(buffer.toString()));
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

        job.setJarByClass(SecondDo.class);
        job.setMapperClass(SecondMapper.class);
        job.setReducerClass(SecondReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job, new Path("mr/src/main/resources/firstResult/part-r-00000"));
        //D:\DSG\project\big_data\mr\src\main\resources
        FileOutputFormat.setOutputPath(job, new Path("mr/src/main/resources/secondResult"));

        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }
}
