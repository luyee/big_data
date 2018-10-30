package com.caiw.questions;

import java.util.Scanner;

/**
 * 计算字符串最后一个单词的长度，单词以空格隔开。
 */
public class DemoFour {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String[] s = word.split(" ");
        System.out.println(s[s.length-1].length());
    }
}
