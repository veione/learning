package com.luo.demo.redis;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

public class RedisClient {
	private Jedis jedis;
	
	private void connect(){
		jedis = new Jedis("localhost", 6379);
	}
	
	/**
	 * 普通调用要释放jedis
	 */
	private void disConnect(){
		jedis.disconnect();
	}
	
	private void operateSet(){
		jedis.set("nickName".getBytes(), "tom".getBytes());
		System.out.println( jedis.get("nickName") );
		jedis.set("nickName".getBytes(), "jack".getBytes());
		System.out.println( jedis.get("nickName") );
	}
	
	private void operateList(){
//		jedis.del("nameList".getBytes());
//		System.out.println(jedis.lrange("nameList".getBytes(), 0, -1));
//		jedis.lpush("nameList".getBytes(), "tom".getBytes());
//		jedis.lpush("nameList".getBytes(), "jack".getBytes());
//		jedis.lpush("nameList".getBytes(), "Lucy".getBytes());
//		System.out.println(jedis.lrange("nameList".getBytes(), 0, -1));
		
		/**
		 * List的FIFO
		 */
//		jedis.del("nameList");
//		System.out.println(jedis.lrange("nameList".getBytes(), 0, -1));
//		jedis.rpush("nameList", "Nick");
//		jedis.lpush("nameList", "tom");
//		jedis.lpush("nameList", "jack");
//		jedis.lpush("nameList", "Lucy");
//		System.out.println(jedis.lrange("nameList", 0, -1));
		
		/**
		 * 最新的20条
		 */
		String key = "commentList";
//		jedis.del(key);
//		for (int i = 0; i < 100; i++) {
//			jedis.lpush(key, "v"+i);
//		}
//		jedis.ltrim(key, 0, 20);
//		System.out.println(jedis.lrange(key, 0, -1));
		
		/**
		 * 阻塞5s
		 */
//		System.out.println("blocking here");
//		jedis.blpop(5, "emptyKey");
//		System.out.println("blocking here");
		
//		System.out.println(jedis.lindex(key, 0));
		
	}
	
	private void operateHash(){
		String key = "human";
		jedis.del(key);
		jedis.hset(key, "name", "jack");
		jedis.hset(key, "age", "11");
		jedis.hset(key, "add", "shanghai");
		jedis.hsetnx(key, "add", "beijing");
		
		jedis.hincrBy(key, "age", 12);
		
		System.out.println(jedis.hgetAll(key));
	}
	
	private void operateString(){
//		jedis.set("nickName".getBytes(), "tom".getBytes());
//		System.out.println( jedis.get("nickName") );
//		jedis.set("nickName".getBytes(), "jack".getBytes());
//		System.out.println( jedis.get("nickName") );
//		jedis.del("nickName".getBytes());
//		jedis.mset("nickName".getBytes(), "tom".getBytes(),"nick".getBytes(), "张三".getBytes());
//		System.out.println( jedis.get("nickName") + jedis.get("name"));
		for (int i = 0; i < 10; i++) {
			System.out.println(jedis.incr("pv"));
		}
	}

	private void operatePubSub(){
		String channel = "music";
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("received : "+ message);
			}
		}, channel);
		
		jedis.publish(channel, "hello");
	}
	
	private void operateTranscation(){
		String key = "Nick";
		jedis.watch(key);
		Transaction t = jedis.multi();
		t.set(key, "tom1");
		t.set(key, "tom2");
		t.set(key, "tom3");
		System.out.println("====="+t.get(key));
		t.exec();
		System.out.println("====="+jedis.get(key));
	}
	
	private void operatePipeline(){
		String key = "Nick";
		long start = System.currentTimeMillis();
		Pipeline pipeline = jedis.pipelined();
//		pipeline.multi();
		for (int i = 0; i < 10000; i++) {
			jedis.get(key+i);
		}
//		pipeline.exec();
		List<Object> list = pipeline.syncAndReturnAll();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			if(i>9900){
				System.out.println(list.get(i));
			}
		}
		System.out.println( System.currentTimeMillis()-start );
		disConnect();
	}
	
	private void operateDistribute(){
		List<JedisShardInfo> list = Arrays.asList(new JedisShardInfo("localhost"),new JedisShardInfo("localhost"));
		ShardedJedis shardedJedis = new ShardedJedis(list);
		for (int i = 0; i < 10000; i++) {
			shardedJedis.set("key"+i, "value"+i);
		}
		System.out.println(jedis.get("key2000"));
		System.out.println("aaaaaaaaaaaa");
		shardedJedis.disconnect();
	}
	
	public static void main(String[] args) {
		RedisClient client = new RedisClient();
		client.connect();
		client.operateDistribute();
	}
}
