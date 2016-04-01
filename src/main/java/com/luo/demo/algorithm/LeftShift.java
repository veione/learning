package com.luo.demo.algorithm;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午7:32
 */
public class LeftShift {
    public static void main(String[] args) {
        System.out.println(leftShift("abcdefg",2));
    }

    private static String leftShift(String str, int n) {
        char[] arr = str.toCharArray();
        int len = arr.length;
        int begin = 0;
        int end = len-1;
        reverse(arr,begin,n-1);
        reverse(arr,n,end);
        reverse(arr,begin,end);
        return new String(arr);
    }

    private static void reverse(char[] arr, int begin, int end) {
        while (begin<end){
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end]=temp;
            begin++;
            end--;
        }
    }
}
