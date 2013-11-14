package com.luo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
	
	private static Singleton instance=null;
	
	public static Singleton getInstance(){
//		synchronized (instance) {
//			if(instance==null){
//				instance=new Singleton();
//			}
//		}
		if(instance==null){
			synchronized (Singleton.class) {
				instance=new Singleton();
			}
		}
		return instance;
	}
}

class Singleton1 {
	private Singleton1 instance=null;
	public Singleton1 getInstance(){
		if(instance==null){
			synchronized(instance){
				instance=new Singleton1();
			}
		}
		return instance;
	}
}
class Singleton2 {
	private Singleton2 instance=null;
	public synchronized Singleton2 getInstance(){
		if(instance==null){
			instance=new Singleton2();
		}
		return instance;
	}
}
class Singleton3 {
	private Singleton3 instance=null;
	private Lock lock = new ReentrantLock();;
	public Singleton3 getInstance(){
		try {
			lock.lock();
			if(instance==null){
				instance=new Singleton3();
				return instance;
			}else{
				return instance;
			}
		} finally{
			lock.unlock();
		}
	}
}
