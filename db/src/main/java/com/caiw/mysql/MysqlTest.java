package com.caiw.oracle.mysql;

import java.sql.*;

/**
 * @ClassName MysqlTest
 * @Author caiwe
 * @CreateTime 2018/8/16  11:08
 **/

public class MysqlTest {
    public static void main(String[] args) throws SQLException
    {
        try
        {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        // Hive2 JDBC URL with LDAP
        String jdbcURL = "jdbc:mysql://192.168.23.120:3306/test";
        // 访问HiveServer2的用户的用户名
        String user = "root";
        // 访问HiveServer2的用户的密码
        String password = "mysql123";
        Connection conn = DriverManager.getConnection(jdbcURL, user, password);
//        Statement stmt = conn.createStatement();
//        ResultSet showTables = stmt.executeQuery("sELECT * FROM system.database_privileges_v LIMIT 50");
//        while (showTables.next()){
//            System.out.println(showTables.getString(1));
//        }
//        stmt.close();
        conn.close();
    }
}
