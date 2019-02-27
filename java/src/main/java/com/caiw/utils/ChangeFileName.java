package com.caiw.utils;

import java.io.File;

public class ChangeFileName {
    public static void main(String[] args) {
        File file1 = new File("C:\\Users\\caiwe\\Desktop\\data");
        File[] files = file1.listFiles();
        for (File file : files) {
            String oName = file.getName();
            String name = oName.substring(0,6);
            name = name+"股票评论数据.txt";
            String parent = file.getParent();
            name = parent+"\\"+name;
            if(file.renameTo(new File(name))){
                System.out.println(oName+"修改成功！");
            }
//            System.out.println(name);
        }
    }
}
