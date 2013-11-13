package com.luo.patern;

/**
 * 策略模式和线程类thread和runnable一样
 * @author 罗辉, @date:Nov 5, 2013
 *
 */
public class StrategyPatern {
	public static void main(String[] args) {
		new MyThread(new MyRunnable(){
			@Override
			public void run() {
				System.out.println("hehe....");
			}
		}).start();
	}
}

class MyThread{
	private MyRunnable runnable;
	
	public MyThread(MyRunnable runnable) {
		super();
		this.runnable = runnable;
	}

	public void start(){
		this.runnable.run();
	}
}

interface MyRunnable{
	void run();
}