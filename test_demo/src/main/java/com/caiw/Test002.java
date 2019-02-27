package com.caiw;


import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class Test002 {
    public static void main(String[] args) {
//        long fileLineNumber = getFileLineNumber(new File("D:\\tmp\\test\\tab_json001\\part-00002-46314ac9-60b7-4bbc-a2b7-7c7dc7c86e7e-c000.txt"));
//        System.out.println("fileLineNumber = " + fileLineNumber);
//        String property = System.getProperty("oozie.action.conf.xml");
//        System.out.println(property);
        List list = new ArrayList();
        People people = new People();
        people.setName("cw");
        people.setAge("10");

        list.add(people);


        Object o = list.get(0);

        if (o instanceof People){
            People people1 = (People) o;
            System.out.println(people1.getName());
        }


    }


    private static long getFileLineNumber(File file) {
        LineNumberReader lnr = null;
        int lineNo = 0;
        try {
            lnr = new LineNumberReader(new FileReader(file));
            lnr.skip(Long.MAX_VALUE);
            lineNo = lnr.getLineNumber();
            String s = lnr.readLine();
            System.out.println(s);
            if(s != null && s.trim().equals("")){
                lineNo--;
            }
            lnr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineNo;
    }
}

class People{

    private String name;
    private String age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
