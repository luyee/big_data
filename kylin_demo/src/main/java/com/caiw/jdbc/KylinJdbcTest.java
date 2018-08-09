package com.caiw.jdbc;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @ClassName KylinJdbcTest
 * @Author caiwe
 * @CreateTime 2018/8/7 14:17
 **/

public class KylinJdbcTest {
    public static void main(String[] args) throws Exception{
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
        Properties info = new Properties();
        info.put("user", "ADMIN");
        info.put("password", "KYLIN");
        Connection conn = driver.connect("jdbc:kylin://192.168.23.125:7070/sh", info);
        PreparedStatement state = conn.prepareStatement("select * from KYLIN_SALES_TEST001");
        ResultSet resultSet = state.executeQuery();

        while (resultSet.next()) {
            System.out.print(resultSet.getString(1)+"\t");
            System.out.print(resultSet.getString(2)+"\t");
            System.out.println(resultSet.getString(3));
        }
    }
}
