package com.luo.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luohui on 15/11/6.
 */
public class lamdba1 {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer","Roger Federer",
            "Andy Murray","Tomas Berdych",
            "Juan Martin Del Potro"};

        List<String> players = Arrays.asList(atp);

//        players.forEach((player) -> System.out.println(player));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);


    }
}
