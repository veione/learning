package com.luo.demo.structure;

import java.util.Arrays;

public class BasicDemo {
	public static void main(String[] args) {
		String str = "helloword";
		char[] chArr = str.toCharArray();
		
		Arrays.sort(chArr);
		System.out.println(Arrays.toString(chArr));
	}
}
