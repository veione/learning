package com.luo.demo.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用于描述从channel读或者写到channel的操作
 * Scatter分算：把从channel读到数据分算到多个buffer
 * Gather聚集：把从多个buffer中读取的数据写到同一个channel中
 * 例如：可以把一条消息的消息头和消息体，分开处理
 * 
 * @author hui.luo
 *
 */
public class ScatterGather {

	public static void main(String[] args) throws Exception{
		scatteringReads();
		gattherWrites();
	}

	//与scatter相反，能很好的额处理动态消息
	private static void gattherWrites() {
		
	}

	private static void scatteringReads() throws Exception {
		ByteBuffer headerBuf = ByteBuffer.allocate(48);
		ByteBuffer bodyBuf = ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = {headerBuf,bodyBuf};

		RandomAccessFile file = new RandomAccessFile(new File("C:/1.txt"),"rw");
		FileChannel inchannel = file.getChannel();
		inchannel.read(bufferArray);//channel按照顺序把数据读到buffer中，一个buffer被写满后，channel紧接着向另一个buffer中写
		//前一个buffer填充满了，才能写下一个buffer
	}

}
