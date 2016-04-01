package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luohui on 15/11/6.
 */
public class Reduce1 {
    public static void main(String[] args) {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = costBeforeTax.stream().reduce((sum,cost)->sum+cost).get();
//        double total = costBeforeTax.stream().map((cost)->cost*1.1).reduce((sum,cost)->sum+cost).get();
        System.out.println(total);
    }
}
