package com.luo.demo.netty.http;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpChunk;
import org.jboss.netty.handler.codec.http.HttpClientCodec;
import org.jboss.netty.handler.codec.http.HttpContentCompressor;
import org.jboss.netty.handler.codec.http.HttpContentDecompressor;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.util.CharsetUtil;

import static org.jboss.netty.buffer.ChannelBuffers.*;
public class TestCase1 {
	static class HttpSnoopClientHandler extends SimpleChannelUpstreamHandler{
		private boolean readingChunk = false;
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			if(!readingChunk){
				HttpResponse response = (HttpResponse) e.getMessage();
				if(response.isChunked()){
					readingChunk=true;
					System.err.println("CHUNKED CONTENT {");
				}else{
					ChannelBuffer buffer = response.getContent();
					if(buffer.readable()){
						System.err.println("CONTENT {");
						System.out.println(buffer.toString(Charset.defaultCharset()));
						System.err.println("} END OF CONTENT");
					}
				}
			}else{
				HttpChunk chunk = (HttpChunk) e.getMessage();
				if(chunk.isLast()){
					readingChunk = false;
					System.err.println("} END OF CHUNKED CONTENT");
				}else{
					System.out.println(chunk.getContent().toString(Charset.defaultCharset()));
					System.out.flush();
				}
			}
		}
	}
	static class HttpSnoopClient{
		static final String URL = System.getProperty("url", "http://127.0.0.1:8080/");
		public static void start() throws Exception{
			ChannelFactory channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
			ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
			
			try{
				bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
					@Override
					public ChannelPipeline getPipeline() throws Exception {
						ChannelPipeline pipeline = Channels.pipeline();
						pipeline.addLast("codec", new HttpClientCodec());
						pipeline.addLast("inflater", new HttpContentDecompressor());
						pipeline.addLast("handler", new HttpSnoopClientHandler());
						return pipeline;
					}
				});
		        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost",8080));
		        //Wait until the connection attempt succeeds or fails.
		        Channel channel = future.sync().getChannel();
		        
		        HttpRequest httpRequest = new DefaultHttpRequest(
		        		HttpVersion.HTTP_1_1, HttpMethod.GET, "http://localhost:8080/nick");
		        
		        channel.write(httpRequest);
		        channel.getCloseFuture().sync();
		        
//		        future.getChannel().getCloseFuture().awaitUninterruptibly();
			}finally{
		        bootstrap.releaseExternalResources();				
			}
		}
	}
	static class HttpSnoopServerHandler extends SimpleChannelUpstreamHandler{
		private boolean readingChunks;
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			if(!readingChunks){
				HttpRequest request = (HttpRequest) e.getMessage();
				System.out.println(request.getProtocolVersion());
				System.out.println(request.getUri());
				
				if(request.isChunked()){
					readingChunks=true;
				}else{
					writeResponse(e,"hello");
				}
			}else{
				HttpChunk chunk = (HttpChunk) e.getMessage();
				if(chunk.isLast()){
					readingChunks = false;
					System.err.println("} END OF CHUNKED CONTENT");
				}else{
					System.out.println(chunk.getContent().toString(Charset.defaultCharset()));
					System.out.flush();
				}
				writeResponse(e,"chunk");
			}
		}
		
		private void writeResponse(MessageEvent e,String str) {
			HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            ChannelBuffer buffer=dynamicBuffer();
            buffer.writeBytes(str.getBytes());
            res.setContent(buffer);
            res.setHeader("Content-Type", "text/html; charset=UTF-8");
            res.setHeader("Content-Length", res.getContent().writerIndex());
            Channel ch = e.getChannel();
            ch.write(res);
            ch.disconnect();
            ch.close();
		}
	}
	static class HttpSnoopServer{
		public static void start(){
			ChannelFactory channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
			ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
			bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				
				@Override
				public ChannelPipeline getPipeline() throws Exception {
					ChannelPipeline pipeline = Channels.pipeline();
					pipeline.addLast("decoder", new HttpRequestDecoder());
					pipeline.addLast("encoder", new HttpResponseEncoder());
					pipeline.addLast("deflater", new HttpContentCompressor());
					pipeline.addLast("handler", new HttpSnoopServerHandler());
					return pipeline;
				}
			});
			bootstrap.setOption("child.tcpNoDelay", true);
//	        bootstrap.setOption("child.keepAlive", true);
	        
	        bootstrap.bind(new InetSocketAddress(8080));
		}
	}
	public static void main(String[] args) throws Exception {
		HttpSnoopServer.start();
		HttpSnoopClient.start();
	}
}
