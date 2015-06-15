package com.luo.demo.threadPool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceDemo {
	private static ExecutorService es = Executors.newFixedThreadPool(11);
	private static BlockingQueue<Future<Integer>> queue = new ArrayBlockingQueue<>(10);
	private static CompletionService<Integer> cs = new ExecutorCompletionService<>(es, queue);
	
	public static void main(String[] args) {
		/**
		 * 模拟产生并发10个任务
		 */
		for (int i = 0; i < 10; i++) {
			cs.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return (Integer) callDubbo();
				}
			});
		}
		
		for (int i = 0; i < 10; i++) {
			try {
				Future<Integer> future = cs.take();
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private static Object callDubbo() throws Exception {
		Random r = new Random();
		int n = r.nextInt(5);
		TimeUnit.SECONDS.sleep(n);
		System.out.println(Thread.currentThread().getName()  + " 休息了 " + n);
		return n;
//		ApplicationContext applicationContext;
//		applicationContext.getBean("demoService",IDe);
	}
	
}
