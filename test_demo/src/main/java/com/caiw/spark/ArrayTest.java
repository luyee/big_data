package com.caiw.spark;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {

    @Test
    public void arrayTest(){
        List<String> strings = new ArrayList<String>();
        strings.add("--conf");
        strings.add("\"spark.executor.Memory=512m\"");

        //(String[])cmd.toArray(new String[cmd.size()])
        String[] com = (String[])strings.toArray(new String[strings.size()]);
        ProcessBuilder pb = new ProcessBuilder(com);
        for (String str:com) {
            System.out.println(str);
        }
    }
}
