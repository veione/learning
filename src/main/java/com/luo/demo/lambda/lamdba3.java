package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by luohui on 15/11/6.
 * Comparator 类被用来排序集合
 */
public class lamdba3 {
    public static void main(String[] args) {
        String[] players = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka", "David Ferrer",
            "Roger Federer", "Andy Murray",
            "Tomas Berdych", "Juan Martin Del Potro",
            "Richard Gasquet", "John Isner"};

//        Arrays.sort(players, new Comparator<String>() {
//            @Override public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });

//        Arrays.sort(players,(s1,s2)->s1.compareTo(s2));
          Arrays.sort(players,(String s1,String s2)->s1.compareTo(s2));

        System.out.println(Arrays.toString(players));

    }
}
