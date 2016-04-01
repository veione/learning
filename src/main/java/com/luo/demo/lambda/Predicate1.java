package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by luohui on 15/11/6.
 */
public class Predicate1 {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//        filter(languages,(str)->str.startsWith("j"));


    }
    private static void filter(List<String> names,Predicate condition){
        for (Object name : names) {
            if(condition.test(name)){
                System.out.println(name);
            }
        }
    }
}
