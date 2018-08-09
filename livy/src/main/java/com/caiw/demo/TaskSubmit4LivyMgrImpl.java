package com.caiw.demo;

import org.quartz.JobExecutionContext;

import java.util.logging.Logger;

/**
 * 向yarn 上提交Spark 任务
 */
public class TaskSubmit4LivyMgrImpl{

//   private static Logger log = Logger.getLogger(TaskSubmit4LivyMgrImpl.class);
    //通过Livy 提交spark 任务到yarn 集群
//    public void invoke(JobExecutionContext context) {
////        log.info("~~ invoke task");
//        String url = "http://172.17.11.11:8998/batches";
//        String postData = "{\"kind\": \"spark\"}";
//        String pData = "{\"file\":\"D://Pi.jar\",\"className\":\"neu.edu.cn.PiJob\" }";
//        String reqResult = ReqEngine.sendPostReq(url, pData);
//        System.out.println(reqResult);
//    }
    public static void main(String[] args){

        String url = "http://192.168.23.222:9001/sessions";

        String postData = "{\"kind\": \"spark\"}";
        String pData = "{\"file\":\"hdfs://192.168.23.220:8020/test/spark/spark-examples_2.11-2.1.1.2.6.2.14-5.jar\",\"className\":\"org.apache.spark.examples.SparkPi\"}";
        String reqResult = ReqEngine.sendPostReq(url, postData);
//        String reqResult = ReqEngine.sendPostReq(url,postData);
        System.out.println(reqResult);
    }
}
