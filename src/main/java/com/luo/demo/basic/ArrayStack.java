package com.luo.demo.basic;

import java.util.Vector;

public class ArrayStack extends Vector implements Stack {
	@Override
	public int getSize() {
		return super.size();
	}

	@Override
	public boolean isEmpty() {
		return super.size()==0;
	}

	@Override
	public void push(Object obj) {
		addElement(obj);
	}

	@Override
	public Object pop() {
		Object obj = get(super.size()-1);
		remove(super.size()-1);
		return obj;
	}

	@Override
	public Object peek() {
		return get(super.size()-1);
	}
	
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
