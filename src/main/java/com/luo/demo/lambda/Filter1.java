package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luohui on 15/11/6.
 */
public class Filter1 {
    public static void main(String[] args) {
        List<String> str = Arrays.asList("abc","bcd","defg", "jk");
        List<String> filtered = str.stream().filter(x->x.length()>2).collect(Collectors.toList());
        System.out.println(filtered);
    }
}
