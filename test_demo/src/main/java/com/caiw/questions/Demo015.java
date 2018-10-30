package com.caiw.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 处理：
 * 1、 记录最多8条错误记录，循环记录，对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 * 3、 输入的文件可能带路径，记录文件名称不能带路径。
 *
 * 输入描述:
 * 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
 *
 * 输出描述:
 * 将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开，如：
 *
 * 示例1
 * 输入
 * E:\V1R2\product\fpgadrive.c 1325
 * 输出
 * fpgadrive.c 1325 1
 */
public class Demo015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        Map<String,Integer> map = new HashMap<>();
        while ((str = reader.readLine()) != null){
            String[] split = str.split("\\\\");
            str = split[split.length-1];
            if(map.get(str) == null){
                map.put(str,1);
            }else {
                map.put(str,map.get(str)+1);
            }
        }
        int num = 0;
        Set<String> strings = map.keySet();
        for (String key:strings) {
            if (num < 9) {
                System.out.println(key + " " + map.get(key));
            }
            num++;
        }
    }
}
