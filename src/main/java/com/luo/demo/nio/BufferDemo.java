package com.luo.demo.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用buffer一般遵循如下步骤
 * 1、写入数据到buffer
 * 2、调用flip()
 * 3、从buffer中读取数据
 * 4、调用clear()或者compact()方法[clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据]
 * @author hui.luo
 *	capacity:固定大小，写入满了后需要清空
 *	position:表示当前位置，初始为0，写入一个byte，则position加一
 *	limit:能读到多少数据，写模式下=capacity,读模式下=position
 *
 */
public class BufferDemo {

	public static void main(String[] args) throws Exception{
		RandomAccessFile file = new RandomAccessFile(new File("C:/1.txt"),"rw");
		FileChannel inchannel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		
		//读数据到channel
		int bytes = inchannel.write(buf);
		byte abytes = buf.get();
		
//		buf.rewind();//将position重设为0，所以你可以重读buffer中所有数据
		
		//通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。
		//之后可以通过调用Buffer.reset()方法恢复到这个position
		buf.mark();
		buf.reset();
		
//		buf.equals()与buf.compareTo()方法
	}

}
