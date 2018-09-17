package com.caiw.conn;

import java.sql.*;

public class JdbcVersionTest {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        //gsql -h 192.168.23.130 -p 20051 -d hivemeta -U hive -W HiveUser@
        //192.168.6.253 5432 kylo_db kylo kylo123
        Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.23.130:20051/kylo_db","kylo","kylo123@");
//        Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.6.253:5432/kylo_db","kylo","kylo123");


        PreparedStatement preparedStatement = con.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

//        System.out.println(1);
//        con.prepareStatement("")
    }
}
