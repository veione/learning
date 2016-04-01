package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luohui on 15/11/6.
 */
public class Map1 {
    public static void main(String[] args) {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost)->cost+1).forEach(System.out::println);


    }
}
