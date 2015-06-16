package com.luo.demo.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
/**
 * channel/buffer/selector
 * channer-->buffer/buffer-->channel
 * @author hui.luo
 * 如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel传输到另外一个channel。
 */
public class ChannelDemo {
	public static void main(String[] args) throws Exception {
		transferFrom();
		transferTo();
	}

	private static void transferTo() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;//从position处开始向目标文件写入数据
		long count = fromChannel.size();//表示最多传输的字节数
		
		fromChannel.transferTo(position, count, toChannel);
	}

	private static void transferFrom() throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;//从position处开始向目标文件写入数据
		long count = fromChannel.size();//表示最多传输的字节数
		
		toChannel.transferFrom(fromChannel, position, count);
		//此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。
		//因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
	}
}
