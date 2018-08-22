package com.caiw.dbservice;

import java.sql.*;

/**
 * @ClassName DbServerTest
 * @Author caiwe
 * @CreateTime 2018/8/13  17:10
 **/

public class DbServerTest {

    public static void main(String[] args) throws SQLException
    {
        try
        {
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        // Hive2 JDBC URL with LDAP
        String jdbcURL = "jdbc:postgresql://192.168.23.130:20051/hivemeta";
        // 访问HiveServer2的用户的用户名
        String user = "hive";
        // 访问HiveServer2的用户的密码
        String password = "d2NjX2NyeXB0ATQxNDU1MzVGNDM0MjQzOzQ1MzQ0MzM1MzQ0NDQzMzAzOTQ0Mzc0MjQyNDYzNzM0Mzc0MjM4NDYzMzQzMzU0NjMwMzEzMDQ1MzYzODM5NDI7OzMyMzUzMDMwOzg1QTE5OUY0Rjk2Q0I0ODJGN0M4RjE3MTIwRUU0RDVBOzc2RUNDRjgyRTM4Q0EyNTk7MzEzNjYzNjQzNzM1MzE2NjJEMzEzNTYyMzUyRDM0MzM2NTM1MkQzOTM0MzkzMzJEMzEzODM2MzAzNzM1MzgzMjMyNjY2MTYxOw";
        Connection conn = DriverManager.getConnection(jdbcURL, user, password);
        Statement stmt = conn.createStatement();
        ResultSet showTables = stmt.executeQuery("show databases");
        while (showTables.next()){
            System.out.println(showTables.getString(1));
        }
        stmt.close();
        conn.close();
    }

}
