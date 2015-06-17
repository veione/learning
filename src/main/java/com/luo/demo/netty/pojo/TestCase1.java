package com.luo.demo.netty.pojo;

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

import static org.jboss.netty.buffer.ChannelBuffers.*;

public class TestCase1 {
	class Person{
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
	class TimeDecoder extends FrameDecoder{
		private final ChannelBuffer buf = dynamicBuffer();
		@Override
		protected Object decode(ChannelHandlerContext ctx, Channel channel,
				ChannelBuffer buffer) throws Exception {
			if(buffer.readableBytes()<4){
				return null;
			}
			if(buffer.readable()){
				//读到，并写入buf
				buffer.readBytes(buf, buffer.readableBytes());
			}
			
			int nameLength = buf.readInt();
			String name = new String(buf.readBytes(nameLength).array(),"gbk");
			int age = buf.readInt();
			
			return new Person(name, age);
		}
	}
	class TimeEncoder extends SimpleChannelHandler{
		private final ChannelBuffer buf = dynamicBuffer();
		@Override
		public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			Person p = (Person) e.getMessage();
			buf.writeInt(p.getName().length());
			buf.writeBytes(p.getName().getBytes());
			buf.writeInt(p.getAge());
			//先写到buffer，然后由buffer写到channel
			Channels.write(ctx, e.getFuture(), buf);
			
		}
	}
	public void runServer(){
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encoder", new TimeEncoder());
				pipeline.addLast("handler", new TimeServerHandler());
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        
        bootstrap.bind(new InetSocketAddress(8888));
	}
	public void runClient(){
		ChannelFactory channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new TimeDecoder());
				pipeline.addLast("handler", new TimeClientHandler());
				return pipeline;
			}
		});
//		bootstrap.setOption("child.tcpNoDelay", true);
//        bootstrap.setOption("child.keepAlive", true);
        
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost",8888));
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        bootstrap.releaseExternalResources();
	}
	public static void main(String[] args) {
		TestCase1 tc = new TestCase1();
		tc.runServer();
		tc.runClient();
	}
}
