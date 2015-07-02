package com.luo.demo.basic;

import java.util.Stack;

public class BinarySystem {

	public static void main(String[] args) {
		change1(2007,8);
	}

	/**
	 * 十进制转二进制 、八进制
	 * @param n
	 * @return
	 */
	public static void change1(int n,int mod){
		java.util.Stack<String> stack = new Stack<>();
		while(n>0){
			int i = n % mod;
			n = n / mod;
			stack.push(String.valueOf(i));
		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop());
		}
	}
	
}
