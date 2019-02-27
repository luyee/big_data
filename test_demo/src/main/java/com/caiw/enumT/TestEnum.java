package com.caiw.enumT;

public class TestEnum {
    public static void main(String[] args) {
        Test test = Enum.valueOf(Test.class,"TEST");

        System.out.println(test.toString());
    }
}
