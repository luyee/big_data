package com.caiw.generatedata;



import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;


public class GenerateData {

    private static String[] postfixEmail = {"qq","foxmail","163"};
    private static Random random = new Random();


    private static String getRandomEmail(String name){
        return name + "@" + postfixEmail[random.nextInt(3)] + ".com";

    }

    //生成随机用户名，数字和字母组成,
    public static String getName(int length) {

        StringBuilder val = new StringBuilder();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString();

    }


    private static UserEntity getUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID().toString());
        String name = getName(random.nextInt(6)+4);
        userEntity.setName(name);
        userEntity.setAge(random.nextInt(20)+18);
        userEntity.setEmail(getRandomEmail(name));
        return userEntity;
    }

    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 1; i++) {
            String sqlWithData = getSqlWithData();
//            JdbcInsert.insertData(sqlWithData);
            System.out.println(sqlWithData);
//            String name = getName(5);
//            System.out.println(sqlWithData);
//            System.out.println(UUID.randomUUID().toString()+"\t"+name+"\t"+getRandomEmail(name));
        }
//        System.out.println(intTransformBool(1));
//        System.out.println(intTransformBool(2));
//        for (int i = 0; i < 1000; i++) {
//            if(i%10 == 0){
//                System.out.println(i);
//            }
//            StringBuilder fileData = getFileData();
//            String file = "D:\\logs\\user_test_5e.txt";
//            appendData(file,fileData.toString());
//        }
    }

    private static StringBuilder getFileData(){
        //50W
        int length = 500000;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            UserEntity user = getUser();
            builder.append(user.getId());
            builder.append(",");
            builder.append(user.getName());
            builder.append(",");
            builder.append(user.getAge());
            builder.append(",");
            builder.append(user.getEmail());
            builder.append("\n");
        }
        return builder;
    }


    public static boolean intTransformBool(Integer integer){
        return integer == 1;
    }

    private static void appendData(String file, String content){
        BufferedWriter out = null ;
        try{
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file,true)));
            out.write(content);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                out.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static String getSqlWithData(){
        int length = 10000;
        StringBuilder builder = new StringBuilder();
        builder.append("insert into user_test001 (id,name,age,email) values ");
        for (int i = 0; i < length; i++) {
            UserEntity user = getUser();
            builder.append("(\"");
            builder.append(user.getId());
            builder.append("\",\"");
            builder.append(user.getName());
            builder.append("\",");
            builder.append(user.getAge());
            builder.append(",\"");
            builder.append(user.getEmail());
            if(i != length-1){
                builder.append("\"),\n");
            }else {
                builder.append("\");");
            }
        }
        return builder.toString();
    }
}
