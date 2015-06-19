package com.luo.demo.design;

public class AdapterDemo {
	interface Target{
		void say();
	}
	class Adaptee{
		public void drink(){
			System.out.println("drink");
		}
	}
	class Adapter extends Adaptee implements Target{
		@Override
		public void say() {
			drink();
		}
	}
	public static void main(String[] args) {
		Target target = new AdapterDemo().new Adapter();
		target.say();
	}
}
