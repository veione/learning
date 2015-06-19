package com.luo.demo.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 能够检测一到多个channel，并能够知晓channel是否为诸如读写事件做好准备的组件
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * 注意：不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式
 * @author hui.luo
 */
public class SelectorDemo {

	public static void demo() throws IOException {
		Selector selector = Selector.open();
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		while(true){
			int readyChannels = selector.select();
			if(readyChannels==0)continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectedKeys) {
				if(selectionKey.isAcceptable()){
					
				}else if(selectionKey.isConnectable()){
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		
		//1、Selector的创建
		Selector selector = Selector.open();
		
		//2、向Selector注册通道
		socketChannel.configureBlocking(false);
		/**
		 * 在通过Selector监听Channel时对什么事件感兴趣
		 * 1.Connect:某个channel成功连接到另一个服务器称为“连接就绪”
		 * 2.Accept:一个server socket channel准备好接收新进入的连接称为“接收就绪”
		 * 3.Read:一个有数据可读的通道可以说是“读就绪”
		 * 4.Write:等待写数据的通道可以说是“写就绪”
		 */
		SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		
		//用“位与”操作interest 集合和给定的SelectionKey常量，可以确定某个确定的事件是否在interest 集合中
		int interestSet  = key.interestOps();
		boolean isInterestedInAccept = (interestSet & SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT;
		
		//可以这样访问ready集合：
		int readySet = key.readyOps();
		
		//检测channel中什么事件或操作已经就绪
		key.isConnectable();
		key.isAcceptable();
		key.isReadable();
		key.isWritable();
		
		//从SelectionKey访问Channel和Selector很简单
		Channel channel = key.channel();
		Selector selector2 = key.selector();
	}

}
