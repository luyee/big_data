package com.caiw.utils;

import java.sql.*;
import java.util.Properties;

/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/15 16:35
 */
public class HiveUtils {

    public void hiveExec(String sql, Properties properties){
        String className = properties.getProperty("hive.driver");
        String url = properties.getProperty("hive.url");
        String user = properties.getProperty("hive.user");
        String password = properties.getProperty("hive.password");
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ResultSet hiveQuery(String sql, Properties properties) {

        return null;
    }

}
