package com.luo.demo.basic;

public class BasicTest {

	public static void main(String[] args) {
		Integer i1 = new Integer(100);
		Integer i2 = new Integer(100);
		System.out.println(i1==i2);
		System.out.println(i1>i2);
		System.out.println(i1<i2);
		
		System.out.println("string" instanceof Object);
		System.out.println(new String("string") instanceof Object);
//		System.out.println('A' instanceof Character);
	}
}
