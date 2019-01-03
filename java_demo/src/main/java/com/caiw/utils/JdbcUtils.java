package com.caiw.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

//    private static String driverName;
    private static String jdbcURL;
    private static String user;
    private static String password;

    static {
        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
//            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            InputStream in = new FileInputStream(new File("D:\\DSG\\project\\big_data\\java_demo\\src\\main\\resources\\jdbc.properties"));
            // 使用properties对象加载输入流
            properties.load(in);
            jdbcURL = properties.getProperty("mysql.jdbcURL");
            user = properties.getProperty("mysql.user");
            password = properties.getProperty("mysql.password");
            Class.forName(properties.getProperty("mysql.driverName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcURL, user, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return conn;
    }


    public static void close(Connection connection, PreparedStatement ps, ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
