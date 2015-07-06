package com.luo.demo.basic;

public class PowerDemo {

	public static void main(String[] args) {
		int n = 5;
		int m = power(n);
		System.out.println(m);
	}

	private static int power(int n) {
		if(n==0){
			return 1;
		}
		if(n%2==0){
			return power(n/2) * power(n/2);
		}
		return 2 * power((n-1)/2) * power((n-1)/2);
	}

}
