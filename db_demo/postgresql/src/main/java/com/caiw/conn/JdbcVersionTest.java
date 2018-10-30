package com.caiw.conn;

import java.sql.*;

public class JdbcVersionTest {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        //gsql -h 192.168.23.130 -p 20051 -d hivemeta -U hive -W HiveUser@
        //192.168.6.253 5432 kylo_db kylo kylo123
        Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.23.124:5432/kylo","kylo","kylo123");
//        Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.6.253:5432/kylo_db","kylo","kylo123");


        PreparedStatement preparedStatement = con.prepareStatement("UPDATE dsg_analyse SET status = ? WHERE ID = ?");
        preparedStatement.setInt(1,0);

        preparedStatement.setString(2,"af21dc28-026a-459d-ace2-9d40f029c479");
        int i = preparedStatement.executeUpdate();

        System.out.println(i);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1));
//        }

//        System.out.println(1);
//        con.prepareStatement("")
    }
}
