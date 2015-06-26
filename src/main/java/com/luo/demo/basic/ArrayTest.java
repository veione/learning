package com.luo.demo.basic;

import java.util.Arrays;

public class ArrayTest {
	private static enum Week{a,b,c,d,e};
	
	public static void main(String[] args) {
		System.out.println(Week.a.ordinal());
		System.out.println(Week.b.ordinal());
		System.out.println(Week.c.ordinal());
		int[] arr1 = {1,2,3,4,5};
		Integer[] arr2 = {1,2,3,4,5};
		
		System.out.println(Arrays.asList(arr1).size());//基本数据类型是不能传给泛型参数的，只会把整个int[]作为一个对象传过去
		System.out.println(Arrays.asList(arr2).size());
	}

}
