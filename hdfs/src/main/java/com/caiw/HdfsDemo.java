package com.caiw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class HdfsDemo {
    public static void main(String[] args) {
        FileSystem hdfs = null;

        Configuration conf = new Configuration();

        try {
            hdfs = FileSystem.get(new URI("hdfs://192.168.23.54:8020"),conf,"hdfs");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Path filenamePath = new Path("/apps/hbase/data/data/default/hbase_test");
        try {
            //会根据集群的配置输出，例如我这里输出3G
            System.out.println("SIZE OF THE HDFS DIRECTORY : " + hdfs.getContentSummary(filenamePath).getSpaceConsumed());
            // 显示实际的输出，例如这里显示 1G
            System.out.println("SIZE OF THE HDFS DIRECTORY : " + hdfs.getContentSummary(filenamePath).getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
