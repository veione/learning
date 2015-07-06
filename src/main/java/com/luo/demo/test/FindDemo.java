package com.luo.demo.test;

import java.util.Arrays;

public class FindDemo {
	public static void main(String[] args) {
		int[] arr = {4,7,2,6,3,5,1,9,0};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int index = binaryFind(arr,5,0,arr.length-1);
//		int index = find(arr,5);
		System.out.println(index);
	}

	private static Integer binaryFind(int[] arr, int d, int low, int high) {
		while(true){
			if(d<arr[low] || d>arr[high] || low>high){
				return null;
			}
			int mid = (low+high)/2;
			if(d<arr[mid]){
				high = mid -1;
			}else if(d>arr[mid]){
				low = mid+1;
			}else{
				return mid;
			}
		}
	}

	private static Integer binaryFind0(int[] arr, int d, int low, int high) {
		if(low<=high){
			int mid = (low+high)/2;
			if(arr[mid]>d){
				return binaryFind(arr, d, low, mid-1);//尾递归
			}else if(arr[mid]<d){
				return binaryFind(arr, d, mid+1, high);
			}
			return mid;
		}
		return null;
	}
	
	private static Integer find(int[] arr, int d) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==d){
				return i;
			}
		}
		return null;
	}
}
