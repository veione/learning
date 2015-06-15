package com.luo.demo.generics;

public class Demo2<T> {
	private T x;
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	
	public static void main(String[] args) {
		Demo2<Integer> demo =new Demo2<Integer>();
		demo.setX(1);
		int a = demo.getX();
		
		//通配符
		say(new Demo2<Integer>());
		say(new Demo2<String>());
		
	}
	public static void say(Demo2<?> demo){
		
	}
}
