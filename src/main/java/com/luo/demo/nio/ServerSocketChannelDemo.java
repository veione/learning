package com.luo.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8888));
//		serverSocketChannel.configureBlocking(false);
		
		while (true) {
			//accept()方法会一直【阻塞】到有新连接到达
			SocketChannel socketChannel = serverSocketChannel.accept();
			//do something with socketChannel...

			//设置非阻塞模式后,需要判断
			if(socketChannel!=null){
				
			}
		}
		
	}

}
