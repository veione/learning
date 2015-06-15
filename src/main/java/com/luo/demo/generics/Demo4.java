package com.luo.demo.generics;

/**
 * 泛型方法不一定是泛型类
 * @author hui.luo
 *
 */
public class Demo4 {
	public <T> void say(T t){
		System.out.println(t);
	}
	public static void main(String[] args) {
		Demo4 demo = new Demo4();
		demo.say(111);
		demo.say("aaa");
	}
}
