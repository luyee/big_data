package com.caiw.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 数据输出n行，输出结果为按照字典序排列的字符串。
 */
public class Demo013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while(sc.hasNext()){
            int num = Integer.parseInt(sc.next());
            for (int i = 0; i <= num -1; i++) {
                list.add(sc.next());
            }
            break;
        }
        Object[] objects = list.toArray();
        Arrays.sort(objects);
        for (Object obj : objects ) {
            System.out.println(obj);
        }
    }
}
