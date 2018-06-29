package com.caiw;

public class CTest001 {

    static int b;
    static int c;
    static int d = (b+c);

    public static void main(String[] args) {
        String a = "123";
//        Runnable noArguments = () -> System.out.println("Hello World");

        boolean e =(b==c);

    }



    public void test(){
    }

}

class Test {
    public static void main(String[] args) {
        Runnable runnable = runnable(new Person(),3);
        runnable.run(); // Breakpoint here
    }

    private static Runnable runnable(Person person,int y) {

        return  () ->
            System.out.println("Hello\t"+y);

    }
}

class Person{
    private String name;
    private int age;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

