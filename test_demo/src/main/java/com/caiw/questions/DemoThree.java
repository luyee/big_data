package com.caiw.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 */
public class DemoThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder s = new StringBuilder(sc.nextLine());
        StringBuilder s2 = new StringBuilder(sc.nextLine());
        List<String> stringList = new ArrayList<>();
        stringList.addAll(splitStr(s));
        stringList.addAll(splitStr(s2));
        for (String str : stringList) {
            System.out.println(str);
        }
    }

    public static List<String> splitStr(StringBuilder s){
        List<String> stringList = new ArrayList<>();
        if(s.length() != 0){
            if(s.length() % 8 != 0){
                int b = 8 - s.length() % 8;
                for(int a = 0; a <= b - 1 ; a++){
                    s.append("0");
                }
            }
            for(int i = 0 ; i < s.length()-1 ;){
                stringList.add(s.substring(i,i+8));
                i = i+8;
            }
        }
        return stringList;
    }
}
