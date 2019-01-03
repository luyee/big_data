package com.caiw.generatedata;

import com.caiw.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcInsert {
    public static void insertData(String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
//            ps.setString(1,userEntity.getId());
//            ps.setString(2,userEntity.getName());
//            ps.setInt(3,userEntity.getAge());
//            ps.setString(4,userEntity.getEmail());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection,ps,null);
        }
    }
}
