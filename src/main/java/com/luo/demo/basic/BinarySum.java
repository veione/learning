package com.luo.demo.basic;

/**
 * 二分递归
 * 每递归一次，n减半，那么算法复杂度就是O(logn)
 * @author hui.luo
 *
 */
public class BinarySum {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		int m = binarySum(arr,0,arr.length-1);
		System.out.println(m);
	}

	/**
	 * 1 0-1 m=0
	 * 2 0-2 m=1
	 * 3 0-3 m=1 0-1 2-3
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	private static int binarySum(int[] arr, int i, int j) {
		if(i==j){
			return arr[i];
		}
		int m = (i+j)/2;
		return binarySum(arr, i, m) + binarySum(arr, m+1, j);
	}

}
