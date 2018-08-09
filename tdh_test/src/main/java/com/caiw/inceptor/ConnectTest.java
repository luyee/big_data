package com.caiw.inceptor;

import java.sql.*;
/**
 * @ClassName ConnectTest
 * @Author caiwe
 * @CreateTime 2018/7/3017:40
 **/

public class ConnectTest {

    public static void main(String[] args) throws SQLException
        {
            try
            {
                String driverName = "org.apache.hive.jdbc.HiveDriver";
                Class.forName(driverName);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
            // Hive2 JDBC URL with LDAP
            String jdbcURL = "jdbc:hive2://192.168.23.134:10000/system";
            // 访问HiveServer2的用户的用户名
            String user = "inceptoruser";
            // 访问HiveServer2的用户的密码
            String password = "password";
            Connection conn = DriverManager.getConnection(jdbcURL, user, password);
            Statement stmt = conn.createStatement();
            ResultSet showTables = stmt.executeQuery("sELECT * FROM system.database_privileges_v LIMIT 50");
            while (showTables.next()){
                System.out.println(showTables.getString(1));
            }
            stmt.close();
            conn.close();
        }

}
