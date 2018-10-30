package com.caiw.questions;

import java.util.Scanner;

/**
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 * 最后一个数后面也要有空格
 *
 * 详细描述：
 * 函数接口说明：
 * public String getResult(long ulDataInput)
 * 输入参数：
 * long ulDataInput：输入的正整数
 * 返回值：
 * String
 */
public class DemoFive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ulDataInput = sc.nextInt();
        String result = getResult(ulDataInput);
        System.out.println(result);
    }

    public static String getResult(long ulDataInput){
        StringBuilder sb = new StringBuilder();
        for(int i=2 ; i<= ulDataInput ; ){
            if(ulDataInput % i == 0 && isPrimeNumber(i)){
                ulDataInput = ulDataInput/i;
                sb.append(i).append(" ");
            }else {
                i++;
            }
        }
        return sb.toString();
    }

    public static boolean isPrimeNumber(int factor){
        boolean flag = true;
        for (int i = 2; i <= factor/2; i++) {
            if(factor % i == 0){
                flag = false;
            }
        }
        return flag;
    }
}
