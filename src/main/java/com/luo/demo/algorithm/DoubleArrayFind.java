package com.luo.demo.algorithm;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午1:52
 *         题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *         请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class DoubleArrayFind {
    public static void main(String[] args) {
        Integer[][] arr = {{1, 2, 3, 4}, {2, 3, 4, 5}};
        boolean match = matchNumber(arr, 9);
        System.out.println(match);
    }

    private static boolean matchNumber(Integer[][] arrs, int n) {
        int rows = arrs.length;
        int cols = arrs[0].length;
        int row = 0;//start row
        int col = cols -1;//start col
        while (row<rows && col>=0){
            if(arrs[row][col]==n){
                return true;
            }
            if(arrs[row][col]>n){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}
