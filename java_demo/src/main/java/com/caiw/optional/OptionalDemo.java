package com.caiw.optional;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Integer a = 10;

        Optional<Integer> a1 = Optional.of(a);


        Integer integer = a1.map(s -> s + 1).get();
        System.out.println(integer);

    }


}
