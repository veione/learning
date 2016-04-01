package com.luo.demo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午6:59
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] array = new int[]{8,9,7,6,5};
        int[] arr = match(array,11);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] match(int[] array, int total) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            map.put(array[i],i);
        }

        for (int i = 0; i < len; i++) {
            int left = total - array[i];
            if(map.containsKey(left) && map.get(left)!=i){
                int index = map.get(left);
                if(index<i){
                    return new int[]{index,i};
                }else{
                    return new int[]{i,index};
                }
            }
        }
        return null;
    }
}
