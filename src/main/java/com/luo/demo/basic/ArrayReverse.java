package com.luo.demo.basic;

import java.util.Arrays;

public class ArrayReverse {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8};
		
		reverse0(arr,0,arr.length-1);
		
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 尾递归，可以简单改写成循环调用
	 * 尾递归：算法的最后一步就是调用递归的情况
	 * 本例复杂度：O(1)*n = O(n)
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void reverse(int[] arr, int i, int j) {
		if(i<j){
			swap(arr,i,j);//O(1)
			reverse(arr, i+1, j-1);//最后一步又是递归，属于尾递归
		}
	}
	
	/**
	 * 复杂度：O(1)*n/2 = O(n)
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void reverse0(int[] arr, int i, int j) {
		while(i<j){
			swap(arr,i,j);
			i++;
			j--;
		}
	}
	
	private static void reverse1(int[] arr, int i, int j) {
		java.util.Stack<Integer> stack = new java.util.Stack();
		for (int k = 0; k < arr.length; k++) {
			stack.push(arr[i]);
		}
		
		int k=0;
		int[] arr0 = new int[arr.length];
		while(!stack.isEmpty()){
			arr0[k]=stack.pop();
			k++;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
