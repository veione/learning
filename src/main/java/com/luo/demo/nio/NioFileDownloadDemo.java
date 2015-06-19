package com.luo.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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

		serverListen(selector);
	}

	private void serverListen(Selector selector) throws IOException {
		while(true){
			try{
				int num = selector.select();//num表示发生的事件个数
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while(iter.hasNext()){
					SelectionKey key = iter.next();
					//删除处理过的 SelectionKey
					iter.remove();
					//处理事件
					handleKey(key,selector);
					/**
					 * 在现实的应用程序中，您需要通过将通道从 Selector 中删除来处理关闭的通道。
					 * 而且您可能要使用多个线程。这个程序可以仅使用一个线程，因为它只是一个演示，
					 * 但是在现实场景中，创建一个线程池来负责 I/O 事件处理中的耗时部分会更有意义。 
					 */
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void handleKey(SelectionKey key,Selector selector) throws IOException {
		if(key.isAcceptable()){//接收请求
			ServerSocketChannel server = (ServerSocketChannel)key.channel();
			SocketChannel channel = server.accept();//不用担心 accept() 操作会阻塞
			channel.configureBlocking(false);
			
			/**
			 * 下一步是将新连接的 SocketChannel 配置为非阻塞的。
			 * 而且由于接受这个连接的目的是为了读取来自套接字的数据，
			 * 所以我们还必须将 SocketChannel 注册到 Selector上
			 */
			//注意我们使用 register() 的 OP_READ 参数，将 SocketChannel 注册用于 读取 而不是 接受 新连接。 
            channel.register(selector, SelectionKey.OP_READ);  
		}else if(key.isReadable()){//读信息  
			SocketChannel channel = (SocketChannel) key.channel();
			echoData(channel);
		}
	}
	
	private ByteBuffer echoBuffer = ByteBuffer.allocate(48);
	private void echoData(SocketChannel channel) throws IOException {
		echoBuffer.clear();
		int byteEchoed = 0;
		while(true){
			int r = channel.read(echoBuffer);
			if(r<=0){
				break;
			}
			echoBuffer.flip();
			channel.write(echoBuffer);
			byteEchoed += r;
		}
		System.out.println("echo "+byteEchoed+" from "+channel);
	}

}
