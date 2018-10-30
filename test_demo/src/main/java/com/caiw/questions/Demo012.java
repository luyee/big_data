package com.caiw.questions;

import java.util.Scanner;

/**
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 *
 * 接口说明
 *  反转句子
 *   //@param sentence 原句子
 *  *@return 反转后的句子
 *
 * public String reverse(String sentence);
 *
 *
 */
public class Demo012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        String reverse = reverse(next);
        System.out.println(reverse);
    }

    public static String reverse(String sentence){
        String[] s = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s.length-1 ;i>=0 ; i--){
            sb.append(s[i]).append(" ");
        }
        return sb.toString();
    }
}
