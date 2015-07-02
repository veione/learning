package com.luo.demo.basic;

import java.util.Stack;

public class StackDemo21 {
	public static void main(String[] args) {
		String s = "([[]()]})";
		java.util.Stack<String> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			String c = String.valueOf(s.charAt(i));
			if(stack.isEmpty()){
				stack.push(c);
			}else{
				if(String.valueOf(stack.peek()).equals("(") && ")".equals(c))stack.pop();
				else if(String.valueOf(stack.peek()).equals("[") && "]".equals(c))stack.pop();
				else if(String.valueOf(stack.peek()).equals("{") && "}".equals(c))stack.pop();
				else stack.push(c);
			}
		}
		if(stack.isEmpty()){
			System.out.println("匹配");
		}else{
			System.out.println("不匹配");
		}
	}
}
