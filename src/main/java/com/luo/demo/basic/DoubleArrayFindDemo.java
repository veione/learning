package com.luo.demo.basic;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上往下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该函数。
 * @author hui.luo
 *
 */
public class DoubleArrayFindDemo {

	public static void main(String[] args) {
		int[][] arr = {
				{1,2,3,4,5},
				{6,7,8,9,10}
		};
		int a = 6;
		boolean result = find(arr,a);
		System.out.println(result);
	}

	private static boolean find(int[][] arr, int a) {
		boolean result = false;
		int row = arr.length;
		int col = arr[0].length;
		System.out.println(row+","+col);
		
		int r = 0;
		int c = col;
		while(r<row && c>=0){
			int temp = arr[r][c-1];
			if(temp==a){
				result = true;
				break;
			}
			if(temp>a){
				c--;
			}else{
				r++;
			}
		}
		return result;
	}

}
