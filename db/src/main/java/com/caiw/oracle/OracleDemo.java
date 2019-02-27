package com.caiw.oracle;

import java.sql.*;     //导入java.sql包
public class OracleDemo {//创建test类，保证文件名与类名相同
    Connection con;//声明Connection对象
    Statement sql;
    ResultSet res;

    public Connection getConnection() {  //建立返回值为Connection的方法
        try {        //加载数据库驱动类
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("数据库驱动加载成功");  //返回加载驱动成功信息
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.23.210:1521:DB112A","u1","u1");//通过访问数据库的URL获取数据库连接对象 ，这里后两个参数分别是数据库的用户名及密码
            System.out.println("数据库连接成功");  //返回连接成功信息
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return con;//按方法要求返回一个Connection对象
    }
    public static void main(String[] args) throws SQLException {   //主方法
        OracleDemo oracleDemo = new OracleDemo();
        Connection con = oracleDemo.getConnection();
        PreparedStatement ps = con.prepareStatement("select DEPARTNAME from SZGA_GPXX_TEST");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        ps.close();
        con.close();
    }
}
