package com.luo.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioFileDownloadDemo {

	public static void main(String[] args) throws IOException {
		NioFileDownloadDemo demo = new NioFileDownloadDemo();
		demo.startServer();
		demo.startClient();
	}

	private void startClient() {
		
	}

	private void startServer() throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		server.socket().bind(new InetSocketAddress(8888));
		server.configureBlocking(false);
		
		server.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Listernint on " + 8888);  
		
		while(true){
			try{
				serverListen(selector);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void serverListen(Selector selector) throws IOException {
		while(true){
			selector.select();
			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while(iter.hasNext()){
				SelectionKey key = iter.next();
				iter.remove();
				//处理事件
				handleKey(key,selector);
			}
		}
	}

	private void handleKey(SelectionKey key,Selector selector) throws IOException {
		if(key.isAcceptable()){//接收请求
			ServerSocketChannel server = (ServerSocketChannel)key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			
			//网络管道准备处理 读事件
            channel.register(selector, SelectionKey.OP_READ);  
		}else if(key.isReadable()){//读信息  
			SocketChannel channel = (SocketChannel) key.channel();
		}
	}

}
