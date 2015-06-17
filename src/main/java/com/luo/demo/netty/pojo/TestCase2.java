package com.luo.demo.netty.pojo;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
public class TestCase2 {
	public static void main(String[] args) throws Exception {
		new TestCase2().new ObjectEchoClient().start();
	}
	
	final class ObjectEchoClient{
		final String HOST = System.getProperty("host", "localhost");
		final Integer PORT = Integer.parseInt(System.getProperty("port", "8888"));
		final Integer SIZE = Integer.parseInt(System.getProperty("size", "256"));
		
		public void start() throws Exception{
			ChannelFactory channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
			ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
			bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				
				@Override
				public ChannelPipeline getPipeline() throws Exception {
					ChannelPipeline channelPipeline = Channels.pipeline();
//					channelPipeline.addLast("", handler);
					return null;
				}
			});
		}
	}
}
