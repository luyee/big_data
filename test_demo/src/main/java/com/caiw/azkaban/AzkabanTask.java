package com.caiw.azkaban;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;

public class AzkabanTask {

    private static final Logger log = LoggerFactory.getLogger(AzkabanTask.class);

    private static String API;

    private static String SESSION_ID;

    private static String PROJECT;

    private static String FLOW_NAME ;

    private static RestTemplate restTemplate;

    public static Properties prop;

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);
        restTemplate = new RestTemplate(requestFactory);
    }

    public static void main(String[] args) throws Exception {

        String userName = "azkaban";
        String password = "azkaban";
        API = "http://192.168.23.57:8081";
        loginGetSession(userName,password); //登录获取session
        //TODO:获取数据
        String execId = startFlowHive2Kafka();
            //hive2kafka
        log.info("execId = " + execId);
    }

    /**
     * 登录测试 登录调度系统
     */
    private static void loginGetSession(String userName, String password) throws Exception {
        SSLUtil.turnOffSslChecking();
        HttpHeaders hs = new HttpHeaders();
        hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        hs.add("X-Requested-With", "XMLHttpRequest");
        MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("action", "login");
        linkedMultiValueMap.add("username", userName);
        linkedMultiValueMap.add("password", password);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(linkedMultiValueMap, hs);
        String responseStr = restTemplate.postForObject(API, httpEntity, String.class);
        JSONObject jsonObject = JSON.parseObject(responseStr);
        SESSION_ID = jsonObject.get("session.id").toString();
        log.info("sessionId = " + SESSION_ID);
    }


    /**
     * 调度hive2jdbc
     * @param taskInfo
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static String startFlow(TaskInfo taskInfo) throws KeyManagementException, NoSuchAlgorithmException {
        SSLUtil.turnOffSslChecking();
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("session.id", AzkabanTask.SESSION_ID);
        linkedMultiValueMap.add("ajax", "executeFlow");
        linkedMultiValueMap.add("project", taskInfo.getProject());
        linkedMultiValueMap.add("flow", taskInfo.getFlowName());
        linkedMultiValueMap.add("flowOverride[executeDate]", taskInfo.getExecuteDate());
        linkedMultiValueMap.add("flowOverride[sourceTable]", taskInfo.getSourceTable());
        //daily为日表，monthly为月表
        linkedMultiValueMap.add("flowOverride[sourceDataField]", taskInfo.getSourceDateField());
        linkedMultiValueMap.add("flowOverride[isFullSync]", String.valueOf(taskInfo.isFullSync()));
        linkedMultiValueMap.add("flowOverride[selectClause]", taskInfo.getSelectClause());
        linkedMultiValueMap.add("flowOverride[whereClause]", taskInfo.getWhereClause());
        linkedMultiValueMap.add("flowOverride[isDayTable]", String.valueOf(taskInfo.isDayTable()));
        linkedMultiValueMap.add("flowOverride[targetTable]", taskInfo.getTargetTable());
        Map postForObject = restTemplate.postForObject(API + "/executor", linkedMultiValueMap, Map.class);
        log.info("azkaban start flow:{}", postForObject);
        return postForObject.get("execid").toString();
    }

    private static String startFlow3() throws KeyManagementException, NoSuchAlgorithmException {
        SSLUtil.turnOffSslChecking();
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("session.id", AzkabanTask.SESSION_ID);
        linkedMultiValueMap.add("ajax", "executeFlow");
        linkedMultiValueMap.add("project", "file2hive");
        linkedMultiValueMap.add("flow", "HDFSToHive");
        linkedMultiValueMap.add("flowOverride[sourcePath]", "/opt/app/user_test_10.txt");
        linkedMultiValueMap.add("flowOverride[hdfsUrl]", "hdfs://192.168.23.54:8020");
        //daily为日表，monthly为月表
        linkedMultiValueMap.add("flowOverride[hdfsPath]", "/tmp/");
        linkedMultiValueMap.add("flowOverride[master]", "local[4]");
        linkedMultiValueMap.add("flowOverride[fileName]", "user_test_10.txt");
        linkedMultiValueMap.add("flowOverride[strSchema]", "[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]");
        linkedMultiValueMap.add("flowOverride[dbName]", "test");
        linkedMultiValueMap.add("flowOverride[tableName]", "cw_test_10_20190403_01");
        Map postForObject = restTemplate.postForObject(API + "/executor", linkedMultiValueMap, Map.class);
        log.info("azkaban start flow:{}", postForObject);
        return postForObject.get("execid").toString();
    }

    /**
     * 调度 Azkaban transform
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static String startFlowAzkaban() throws KeyManagementException, NoSuchAlgorithmException {
        SSLUtil.turnOffSslChecking();
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("session.id", AzkabanTask.SESSION_ID);
        linkedMultiValueMap.add("ajax", "executeFlow");
        linkedMultiValueMap.add("project", "dataTransform");
        linkedMultiValueMap.add("flow", "Transform");
        linkedMultiValueMap.add("flowOverride[folder]", "/tmp/cw_test");
        linkedMultiValueMap.add("flowOverride[dbName]", "test2");
        linkedMultiValueMap.add("flowOverride[tableName]", "user_test_2019041002");
        linkedMultiValueMap.add("flowOverride[dataTransformScript]", "import org.apache.spark.sql._\\nvar df = sqlContext.sql(\"select * from test.cw_test_10_20190403_01\")\\ndf");
        linkedMultiValueMap.add("flowOverride[scriptFileName]", "transform.scala");
        linkedMultiValueMap.add("flowOverride[fieldSchema]", "[{\"name\":\"id\",\"type\":\"string\",\"rowkey\":\"true\"},{\"name\":\"name\",\"type\":\"string\",\"rowkey\":\"false\"},{\"name\":\"email\",\"type\":\"string\",\"rowkey\":\"false\"}]");
        String responseStr = restTemplate.postForObject(API + "/executor", linkedMultiValueMap, String.class);
        log.info("azkaban start flow:{}", responseStr);
        return JSON.parseObject(responseStr).get("execid").toString();
    }

    /**
     * start flow hive2Kafka
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static String startFlowHive2Kafka() throws KeyManagementException, NoSuchAlgorithmException {
        SSLUtil.turnOffSslChecking();
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("session.id", AzkabanTask.SESSION_ID);
        linkedMultiValueMap.add("ajax", "executeFlow");
        linkedMultiValueMap.add("project", "hive2kafka");
        linkedMultiValueMap.add("flow", "hive2kafka");
        linkedMultiValueMap.add("flowOverride[folder]", "/tmp/cw_test");
        linkedMultiValueMap.add("flowOverride[executeDate]", "201902");
        linkedMultiValueMap.add("flowOverride[sourceTable]", "test.cw_test_10_20190403_01");
        //daily为日表，monthly为月表
        linkedMultiValueMap.add("flowOverride[sourceDataField]", "date");
        linkedMultiValueMap.add("flowOverride[isFullSync]", String.valueOf(true));
        linkedMultiValueMap.add("flowOverride[selectClause]", "*");
        linkedMultiValueMap.add("flowOverride[whereClause]", "");
        linkedMultiValueMap.add("flowOverride[isDayTable]", String.valueOf(false));
        linkedMultiValueMap.add("flowOverride[kafkaTopic]", "cw_test_2019041101");
        String responseStr = restTemplate.postForObject(API + "/executor", linkedMultiValueMap, String.class);
        log.info("azkaban start flow:{}", responseStr);
        return JSON.parseObject(responseStr).get("execid").toString();
    }

    /**
     * 调度hive2local
     * @param taskInfo
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static String startFlow2(TaskInfo taskInfo) throws KeyManagementException, NoSuchAlgorithmException {
        SSLUtil.turnOffSslChecking();
        LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.add("session.id", AzkabanTask.SESSION_ID);
        linkedMultiValueMap.add("ajax", "executeFlow");
        linkedMultiValueMap.add("project", taskInfo.getProject());
        linkedMultiValueMap.add("flow", taskInfo.getFlowName());
        linkedMultiValueMap.add("flowOverride[executeDate]", taskInfo.getExecuteDate());
        linkedMultiValueMap.add("flowOverride[sourceTable]", taskInfo.getSourceTable());
        //daily为日表，monthly为月表
        linkedMultiValueMap.add("flowOverride[sourceDataField]", taskInfo.getSourceDateField());
        linkedMultiValueMap.add("flowOverride[isFullSync]", String.valueOf(taskInfo.isFullSync()));
        linkedMultiValueMap.add("flowOverride[selectClause]", taskInfo.getSelectClause());
        linkedMultiValueMap.add("flowOverride[whereClause]", taskInfo.getWhereClause());
        linkedMultiValueMap.add("flowOverride[isDayTable]", String.valueOf(taskInfo.isDayTable()));
        linkedMultiValueMap.add("flowOverride[ftpFilePath]", taskInfo.getFtpFilePath());
        Map postForObject = restTemplate.postForObject(API + "/executor", linkedMultiValueMap, Map.class);
        log.info("azkaban start flow:{}", postForObject);
        return postForObject.get("execid").toString();
    }

}