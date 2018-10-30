package com.caiw.questions;

import java.util.*;

/**
 * 题目描述
 * 数据表记录包含表索引和数值，请对表索引相同的记录进行合并，
 * 即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 */
public class DemoSeven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        Map<Integer,Integer> keyMap = new HashMap<>();
        while(scanner.hasNextInt()){
            num = Integer.parseInt(scanner.next());
            for (int i= 0; i< num ; i++){
                Integer key = Integer.parseInt(scanner.next());
                Integer data = keyMap.get(key);
                Integer data1 = Integer.parseInt(scanner.next());
                if(data == null){
                    data = data1;
                }else {
                    data = data + data1;
                }
                keyMap.put(key,data);
            }
            break;
        }
        TreeSet<Integer> treeSet = new TreeSet<>(keyMap.keySet());
        Object[] objects = treeSet.toArray();
        for (int a =0; a<= objects.length-1 ; a++) {
            System.out.println(objects[a] + " " + keyMap.get(Integer.parseInt(objects[a].toString())));
        }
    }
}
