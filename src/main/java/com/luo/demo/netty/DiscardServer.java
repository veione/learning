package com.luo.demo.netty;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DiscardServer {
	static boolean s;

	public static void main(String[] args) {
		ChannelFactory factory = new NioServerSocketChannelFactory();
		System.out.println(s);
	}

}
