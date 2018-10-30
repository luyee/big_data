package com.caiw.questions;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。例如：
 * 输入
 * abcd
 *
 * 输出
 * dcba
 */
public class Demo011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        char[] c = next.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int a = c.length-1 ; a>=0 ;a--){
            sb.append(c[a]);
        }
        System.out.println(sb.toString());
    }
}
