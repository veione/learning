package com.luo.demo.test;

import java.lang.reflect.Field;

public class StringDemo {

	public static void main(String[] args) throws Exception, SecurityException {
		String s = "abc";
		System.out.println(s.hashCode());
		Field f = s.getClass().getDeclaredField("value");
		f.setAccessible(true);
		f.set(s, new char[]{'a','d'});
		System.out.println(s.hashCode());
	}

}
