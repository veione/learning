package com.luo.demo.netty.pojo;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import static org.jboss.netty.buffer.ChannelBuffers.*;

/**
 * 因为Person是TestCase3的内部类，也需要序列化
 * @author hui.luo
 *
 */
public class TestCase3 implements Serializable{
	private static final long serialVersionUID = -4079978237109165981L;
	class Person implements Serializable{
		private static final long serialVersionUID = 7279610151571117702L;
		private String name;
		private int age;
		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		@Override
		public String toString() {
			return "name:"+name+",age:"+age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	class TimeServerHandler extends SimpleChannelHandler{
		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			System.out.println("connected:"+e);
			Person p = new Person("tom", 22);
			ChannelFuture cf = e.getChannel().write(p);
			cf.addListener(ChannelFutureListener.CLOSE);
		}
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			e.getCause().printStackTrace();
			e.getChannel().close();
		}
	}
	class TimeClientHandler extends SimpleChannelHandler{
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			Person p = (Person) e.getMessage();
			System.out.println("received : "+p);
			e.getChannel().close();
		}
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			e.getCause().printStackTrace();
			e.getChannel().close();
		}
	}
	/**
	 * 发送消息
	 */
	public void runServer(){
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encoder", new ObjectEncoder());
				pipeline.addLast("handler", new TimeServerHandler());
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        
        bootstrap.bind(new InetSocketAddress(8888));
	}
	/**
	 * 接受消息
	 */
	public void runClient(){
		ChannelFactory channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())));//接受消息并解码
				pipeline.addLast("handler", new TimeClientHandler());
				return pipeline;
			}
		});
//		bootstrap.setOption("child.tcpNoDelay", true);
//      bootstrap.setOption("child.keepAlive", true);
        
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost",8888));
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        bootstrap.releaseExternalResources();
	}
	public static void main(String[] args) {
		TestCase3 tc = new TestCase3();
		tc.runServer();
		tc.runClient();
	}
}
