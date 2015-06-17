package com.luo.demo.netty;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class NettyDemo {
	public static void main(String[] args) {
		Server server = new Server();
		server.start(8888);
	}
}
class Server{
	ServerBootstrap bootstrap;
	MyChannelHandler channelHandler = new MyChannelHandler();
	Channel parantChannel;
	
	public Server() {
		/**
		 * NioServerSocketChannelFactory创建channel
		 * 2个线程池用于接受连接和消息
		 */
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		bootstrap = new ServerBootstrap(channelFactory);
		
		bootstrap.setOption("reuseAddress", true);
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.soLinger", 2);
		
		//channelHandler用于处理消息和连接
		bootstrap.getPipeline().addLast("servercnfactory", channelHandler);
	}
	
	public void start(int port){
		SocketAddress localAddress = new InetSocketAddress(port);
		parantChannel = bootstrap.bind(localAddress);
	}
	
	class MyChannelHandler extends SimpleChannelHandler{

		/**
		 * ChannelHandlerContext为channel上下文
		 * event中有消息和连接的信息
		 */
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			System.out.println("messageReceived " + e.toString() + "from "+ ctx.getChannel());
			Channel channel = e.getChannel();
			channel.write(e.getMessage());
			super.messageReceived(ctx, e);
		}

		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			System.out.println("Channel connected " + e);
			super.channelConnected(ctx, e);
		}

		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
				throws Exception {
			System.out.println("Channel closed " + e);
			super.channelClosed(ctx, e);
		}
	}
}
