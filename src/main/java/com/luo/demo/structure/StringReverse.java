package com.luo.demo.structure;

import java.util.Stack;

public class StringReverse {
	public static void main(String[] args) {
		String str = "abcdrfg";
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
		}
		StringBuffer sb = new StringBuffer();
		Character c = null;
		while(!stack.isEmpty() && (c=stack.pop())!=null){
			sb.append(c);
			System.out.println(sb);
		}
		System.out.println(sb);
	}
}
