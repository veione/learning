package com.luo.demo.structure;

public class ReferenceTest {
	static class Demo{
		private int i = 10;
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.i = 22;
		change(demo);
		System.out.println(demo.i);
	}
	
	private static void change(Demo demo) {
		Demo d = new Demo();
		d.i = 30;
		demo = d;
		System.out.println(demo.i);
	}
}