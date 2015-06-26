package com.luo.demo.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java中2种同步机制：同步块和volatile
 * volatile具有synchronizde的可见性，但没原子性
 * @author hui.luo
 */
public class VolatileDemo {
	private AtomicInteger i = new AtomicInteger(0);
	private volatile boolean shutdownRequested;
	public void shutdown(){
		shutdownRequested=true;
	}
	public void dowork(){
		while(!shutdownRequested){
			System.out.println(i.incrementAndGet());
		}
	}
	public static void main(String[] args) {
		final VolatileDemo demo = new VolatileDemo();
		new Thread(new Runnable() {
			public void run() {
				demo.dowork();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				demo.shutdown();
			}
		}).start();
	}
}
