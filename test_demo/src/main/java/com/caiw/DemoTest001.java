package com.caiw;

public class DemoTest001 {

    public static void main(String[] args) {
        Level level = Level.CONNECTIONS;
        int i = level.compareTo(Level.ADMIN);
        System.out.println(i);
    }
}

enum Level{
    /**
     * Include sensitive fields in result
     */
    ADMIN,

    /**
     * Include everything except sensitive fields in result
     */
    FULL,

    /**
     * Include basic field and connections in result
     */
    CONNECTIONS,

    /**
     * Include only basic fields in result
     */
    BASIC
}
