package com.luo.demo.basic;

public class ThreadLocalDemo {
	
	static class SeqNummer{
		private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
			protected Integer initialValue() {
				return 0;
			};
		};
		public Integer getNextSeq(){
			seqNum.set(seqNum.get()+1);
			return seqNum.get();
		}		
	}
	

	static class SeqClient implements Runnable{
		private SeqNummer seq;
		 
		public SeqClient(SeqNummer seq) {
			super();
			this.seq = seq;
		}
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName()+":"+seq.getNextSeq());
			}
		}
	}
	
	public static void main(String[] args) {
		//SeqNummer的对象实例，将被多线程共享
		SeqNummer seqNummer = new SeqNummer();
		new Thread( new SeqClient(seqNummer),"client1" ).start();
		new Thread( new SeqClient(seqNummer),"client2" ).start();
		new Thread( new SeqClient(seqNummer),"client3" ).start();
	}

}
