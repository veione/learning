package com.luo.demo.netty;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DiscardServer {

	public static void main(String[] args) {
		ChannelFactory factory = new NioServerSocketChannelFactory();
		
	}

}
