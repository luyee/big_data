package com.caiw.questions;

import java.util.*;

/**
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 输入
 * 9876673
 *
 * 输出
 * 37689
 */
public class Demo008 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        while (no%10 >= 1){
            int i = no % 10;
            if(!list.contains(i)){
                list.add(i);
            }
            no = no/10;
        }
        list.forEach(System.out::print);
    }
}
