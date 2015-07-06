package com.luo.demo.test;

import java.util.Arrays;

public class SortDemo {

	public static void main(String[] args) {
		int[] arr = {2,6,3,5,1,9,0};
//		quickSort(arr,0,arr.length-1);
		insertSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 时间复杂度：O(N2),最坏的情况就是由大到小
	 * @param arr
	 */
	private static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[j]>arr[i]){
					swap(arr,i,j);
				}
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

	private static void quickSort(int[] arr, int low, int high) {
		if(low<high){
			int m = partition(arr,low,high);
			quickSort(arr, low, m-1);
			quickSort(arr, m+1, high);
		}
	}

	/**
	 * 返回下标，而不是值
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(int[] arr, int low, int high) {
		int mid = arr[low];
		while(low<high){
			while(low<high && arr[high]>mid){
				high--;
			}
			arr[low] = arr[high];
			while(low<high && arr[low]<mid){
				low++;
			}
			arr[high] = arr[low];
		}
		arr[low]=mid;
		return low;
	}
	
}
