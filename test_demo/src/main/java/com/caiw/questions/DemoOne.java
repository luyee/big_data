package com.caiw.questions;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 */
public class DemoOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine().toLowerCase();
        char c = sc.next().toLowerCase().toCharArray()[0];
        char[] chars = s.toCharArray();
        int a = 0;
        for(int i=0 ; i<chars.length-1; i++ ) {
            if(chars[i] == c){
                a++;
            }
        }
        System.out.println(a);
    }
}
