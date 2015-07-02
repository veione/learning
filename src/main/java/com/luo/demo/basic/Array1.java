package com.luo.demo.basic;

import java.util.Arrays;

public class Array1 {
	public static void main(String[] args) {
		int[] arr = {1,1,2,3,3,4,5};
		int index=0;
		for (int i = 1; i < arr.length; i++) {
			if(arr[index]!=arr[i]){
				arr[++index]=arr[i];
			}
		}
		System.out.println(Arrays.toString(arr));
	}
}
