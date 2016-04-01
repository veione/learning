package com.luo.demo.lambda;

/**
 * Created by luohui on 15/11/6.
 * 匿名类可以使用lambda表达式来代替
 */
public class lamdba2 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("aa");
            }
        }).start();


        new Thread(()-> System.out.println("bb")).start();
    }
}
