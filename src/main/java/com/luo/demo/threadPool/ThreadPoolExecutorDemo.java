package com.luo.demo.threadPool;
/**
 *     
public ThreadPoolExecutor(int corePoolSize,
		                  int maximumPoolSize,
		                  long keepAliveTime,
		                  TimeUnit unit,
		                  BlockingQueue<Runnable> workQueue,
		                  ThreadFactory threadFactory,
		                  RejectedExecutionHandler handler) {
ThreadPoolExecutor.AbortPolicy() 抛出java.util.concurrent.RejectedExecutionException异常
ThreadPoolExecutor.CallerRunsPolicy()只用调用者所在线程来运行任务
ThreadPoolExecutor.DiscardOldestPolicy()抛弃旧的任务
ThreadPoolExecutor.DiscardPolicy()抛弃当前的任务           
 * @author hui.luo
 */
public class ThreadPoolExecutorDemo {

	public static void main(String[] args) {
		
	}

}
