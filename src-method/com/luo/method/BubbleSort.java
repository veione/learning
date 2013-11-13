package com.luo.method;

public class BubbleSort {

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
		for (int i = 0; i < data.length-1; i++) {
			for (int j = 0; j < data.length-1-i; j++) {
				if(data[j+1]<data[j]){
					temp=data[j+1];
					data[j+1]=data[j];
					data[j]=temp;
				}
			}
		}
	}
}
