package com.luo.method;

/**
 * 选择排序
 * @author 罗辉, @date:Nov 6, 2013
 *
 */
public class SelectSort {

	static int data[] = { 9, 2, 7, 19, 100, 97, 63, 208, 55, 78 };
	
	public static void main(String[] args) {
		sort();
		print();
	}

	private static void print() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+",");
		}
	}
	private static void sort() {
		int temp=0;
		int low=0;
		for (int i = 0; i < data.length; i++) {
			low=i;
			//从剩下的数中选择【最小的】,所以不加break
			for (int j = i+1; j < data.length; j++) {
				if(data[j]<data[low]){
					low=j;
				}
			}
			if(i!=low){
				temp = data[i];
				data[i]=data[low];
				data[low]=temp;
			}
		}
	}
}
