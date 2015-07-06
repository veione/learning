package com.luo.demo.test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	private int core_num;
	private int max_num;
	private BlockingQueue<Runnable> taskQueue;
	private Worker[] workers;
	private int i = -1;
	private static final int MAX_QUEUE_SIZE = 8;
	
	public ThreadPool(){
		this(3,5);
	}
	
	public ThreadPool(int core_num,int max_num){
		this.core_num = core_num;
		this.max_num = max_num;
		this.taskQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
		workers = new Worker[core_num];
		for (int j = 0; j < core_num; j++) {
			workers[++i] = new Worker();
			workers[i].start();
		}
	}
	
	public void execute(Runnable task) throws InterruptedException{
		if(workers.length<core_num){
		}else if(workers.length==core_num && this.taskQueue.size()<MAX_QUEUE_SIZE){
			this.taskQueue.put(task);
			System.out.println("big than core_num worker size "+i+" queue size "+taskQueue.size());
		}else if(workers.length==MAX_QUEUE_SIZE && workers.length<max_num){
			workers = Arrays.copyOf(workers, max_num);
			workers[++i] = new Worker();
			workers[i].start();
			System.out.println("queue is full worker size "+i+" queue size "+taskQueue.size());
		}else if(workers.length==max_num){
			throw new RuntimeException("thread pool full");
		}
	}
	
	class Worker extends Thread{
		@Override
		public void run() {
			Runnable t;
			try {
				while((t=taskQueue.take())!=null){
					t.run();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		ThreadPool pool = new ThreadPool();
		for (int i = 0; i < 12; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					Random r = new Random();
					try {
						TimeUnit.SECONDS.sleep(50000000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
