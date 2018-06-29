package com.caiw;

public class StringTest001 {
    public static void main(String[] args) {
        String str = "/tmp/cwTest/test001/userdata3.csv._COPYING_";
//        int num = str.lastIndexOf(".");
//        str = str.substring(0,num);
//        System.out.println(str);
        String s = str.toLowerCase();
        System.out.println(s);
    }
}
