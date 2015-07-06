package com.luo.demo.test;

import java.util.HashSet;

public class HashCodeDemo {

	public static void main(String[] args) {
		A a = new A(1);
		A b = new A(1);
		HashSet<A> set = new HashSet<>();
		set.add(a);
		set.add(b);
		System.out.println(set.size());
	}
	
	static class A {
		private int x;

		public A(int x) {
			super();
			this.x = x;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		@Override
		public boolean equals(Object obj) {
			A aa = (A) obj;
			return aa.getX()==this.x;
		}
		
	}
}
