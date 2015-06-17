package com.luo.demo.netty;

import java.sql.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import static org.jboss.netty.buffer.ChannelBuffers.*;

/**
 * 为了不让buf不在多个channel共享产生安全问题，需要创建一个handler工厂ChannelPipelineFactory，每来一个channel就分配一个handler
 * @author hui.luo
 *
 */
public class TimeClientHandler extends SimpleChannelHandler {
	private final ChannelBuffer buf = dynamicBuffer();
	
	/**
	 * 
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer b = (ChannelBuffer) e.getMessage();
		buf.writeBytes(b);
		if(buf.readableBytes()>=4){
			long current = buf.readInt() * 1000L;
			System.out.println(new Date(current));
			e.getChannel().close();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
