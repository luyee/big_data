package com.caiw.questions;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 编写一个函数，计算字符串中含有的不同字符的个数。
 * 字符在ACSII码范围内(0~127)。不在范围内的不作统计。
 */
public class Demo009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] chars = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        for (char c: chars) {
            if( (int) c >= 0 && (int) c <=127){
               set.add((int) c);
            }
        }
        System.out.println(set.size());
    }
}
