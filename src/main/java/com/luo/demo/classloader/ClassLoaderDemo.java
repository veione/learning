package com.luo.demo.classloader;

public class ClassLoaderDemo {
	
	public void say(){
		System.out.println("tom");
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(ClassLoader.getSystemClassLoader());
		Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.luo.demo.classloader.ClassLoaderDemo");
		ClassLoaderDemo demo = (ClassLoaderDemo) clazz.newInstance();
		demo.say();
	}
}
