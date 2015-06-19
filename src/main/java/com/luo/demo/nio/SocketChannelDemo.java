package com.luo.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannel是一个连接到TCP网络套接字的通道。可以通过以下2种方式创建SocketChannel：
 * 1.打开一个SocketChannel并连接到互联网上的某台服务器。
 * 2.一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
 * @author hui.luo
 *
 */
public class SocketChannelDemo {

	public static void main(String[] args) throws IOException {
		//打开 SocketChannel
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress(8888));
		
		//从SocketChannel中读取数据
		ByteBuffer buf = ByteBuffer.allocate(48);
		int readBytes = socketChannel.read(buf);
		
		//写入数据
		String data = "asdfasdf asdfkasdlf";
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf2.clear();
		buf2.put(data.getBytes());
		buf2.flip();
		while(buf2.hasRemaining()){
			socketChannel.write(buf2);
		}
		
		//非阻塞模式
		//可以设置socketChannel为非阻塞模式，之后就可以在异步模式下调用connect(), read() 和write()了。
		
		//1.connect  如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法。像这样：
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(8888));
		while (!socketChannel.finishConnect()) {
			System.out.println("connected ...");
		}
		//2.write() 非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()
		
		//3.read() 非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。所以需要关注它的int返回值，它会告诉你读取了多少字节。
		
		//4.非阻塞模式与选择器  非阻塞模式与选择器搭配会工作的更好，通过将一或多个SocketChannel注册到Selector，可以询问选择器哪个通道已经准备好了读取，写入等
		
		socketChannel.close();
	}

}
