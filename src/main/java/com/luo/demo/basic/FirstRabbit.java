package com.luo.demo.basic;

/**
 * 题目一：古典问题：有一对兔子，从出生后每三个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总共有多少对？
 * @author hui.luo
 * 1 1 2 3 5 7
 */
public class FirstRabbit {

	public static void main(String[] args) {
		int month = 5;
		int a = find(month);
		System.out.println(a);
	}

	private static int find(int month) {
		if(month==1 || month==2){
			return 1;
		}
		return find(month-2)+find(month-1);
	}

}
