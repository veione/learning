package com.luo.demo.generics;

public class Demo3<T,Y> {
	private T x;
	private Y y;
	public Demo3(T x,Y y){
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public Y getY() {
		return y;
	}

	public void setY(Y y) {
		this.y = y;
	}

	public static void main(String[] args) {
		Demo3<Integer,String> demo3 = new Demo3<Integer,String>(12,"abc");
		int a = demo3.getX();
		String b = demo3.getY();
		
	}
}
