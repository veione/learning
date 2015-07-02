package com.luo.demo.basic;

public class StrReplaceBlankDemo {

	public static void main(String[] args) {
		String str = "we are happy";
		System.out.println(replace(str));
	}

	private static String replace(String str) {
		return str.replaceAll(" ", "%20");
	}

}
