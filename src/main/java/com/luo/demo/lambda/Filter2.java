package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luohui on 15/11/6.
 */
public class Filter2 {
    public static void main(String[] args) {
        List<String> G7 = Arrays
            .asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");

        List<String> l = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
        l.forEach(System.out::println);
    }
}
