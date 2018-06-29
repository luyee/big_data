package com.caiw;

import java.sql.*;

public class PhoenixTest {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:phoenix:192.168.23.222");
        PreparedStatement ps = connection.prepareStatement("select * from CW_TEST001");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            System.out.print(resultSet.getInt(1)+"\t");
            System.out.print(resultSet.getString(2)+"\t");
            System.out.print(resultSet.getString(3)+"\t");
            System.out.print(resultSet.getString(3)+"\n");
        }
        resultSet.close();
        ps.close();
        connection.close();
    }
}
