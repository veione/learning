package com.luo.demo.netty.pojo;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import static org.jboss.netty.buffer.ChannelBuffers.*;

public class TimeClientHandler extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		UnixTime time = (UnixTime) e.getMessage();
		System.out.println(time);
		e.getChannel().close();
	}
	
	/**
	 * 对写操作的拦截
	 */
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		UnixTime time = (UnixTime) e.getMessage();
		ChannelBuffer buf = buffer(4);
		buf.writeInt(time.getValue());
//		Channels.write(e.getChannel(), buf);
		Channels.write(ctx, e.getFuture(), buf);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
