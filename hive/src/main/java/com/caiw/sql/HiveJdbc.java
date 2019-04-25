package com.caiw.sql;


import com.caiw.utils.HiveUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: caiwei
 * @Description:
 * @Date: create in 2019/4/15 16:45
 */
public class HiveJdbc {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "Kerberos");
        System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
        System.setProperty("sun.security.krb5.debug", "true");
        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation.loginUserFromKeytab("hive/slave.hdp192.com@EXAMPLE.COM", "/etc/security/keytabs/hive.service.keytab");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        String className = "org.apache.hive.jdbc.HiveDriver";
        String url = "jdbc:hive2://192.168.23.192:10000/default;principal=hive/slave.hdp192.com@EXAMPLE.COM";
        String user = "hdfs";
        String password = "";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "select * from test.employee limit 10";
        Connection conn = null;
        ResultSet set = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            set = ps.executeQuery();
            while (set.next()){
                set.getString("money");
            }
            set.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
