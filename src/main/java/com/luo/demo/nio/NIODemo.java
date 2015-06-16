package com.luo.demo.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {
	public static void main(String[] args) throws Exception{
		RandomAccessFile file = new RandomAccessFile(new File("C:/1.txt"),"rw");
		FileChannel inchannel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inchannel.read(buf);//1、首先读取数据到Buffer
		while(bytesRead != -1){
			System.out.println("read "+ bytesRead);
			buf.flip();//2、然后反转Buffer(从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据)
			
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());//3、接着再从Buffer中读取数
			}
			buf.clear();//4、清空buffer
			bytesRead = inchannel.read(buf);
		}
		inchannel.close();
		file.close();
	}
}
