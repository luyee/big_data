package com.caiw.questions;


import java.util.Scanner;

/**
 * 描述：
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 *
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 */
public class Demo010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while((no*10 / 10) >= 1){
            sb.append(String.valueOf(no % 10));
            no = no / 10;
        }
        System.out.println(sb.toString());
    }
}
