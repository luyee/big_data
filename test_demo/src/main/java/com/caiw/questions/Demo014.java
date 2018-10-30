package com.caiw.questions;

import java.util.Scanner;

/**
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 *
 * 输入描述:
 *  输入一个整数（int类型）
 *
 * 输出描述:
 *  这个数转换成2进制后，输出1的个数
 *
 * 示例1
 * 输入
 * 5
 *
 * 输出
 * 2
 */
public class Demo014 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int num = 0;
        while(i>=1){
            int i1 = i % 2;
            if(i1 == 1){
                num ++ ;
            }
            i = i/2;
        }
        System.out.println(num);


    }
}
