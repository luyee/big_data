package com.caiw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 * @author 蔡维
 */
public class PropertiesUtil {

    public static void configureSasl() {
        // 如果用 -D 或者其它方式设置过，这里不再设置
        if (null == System.getProperty("java.security.auth.login.config")) {
            // 请注意将 XXX 修改为自己的路径
            // 这个路径必须是一个文件系统可读的路径，不能被打包到 jar 中
            System.setProperty("java.security.auth.login.config", getKafkaProperties().getProperty("java.security.auth.login.config"));
        }
    }

    /**
     * 获取kafka的properties
     * @return
     */
    public static Properties getKafkaProperties(){
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/kafka.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
