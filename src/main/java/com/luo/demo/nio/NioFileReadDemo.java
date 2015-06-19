package com.luo.demo.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/**
 * FileChannel提供了map方法来把文件影射为内存映像文件
 * MappedByteBuffer的确快，但也存在一些问题，主要就是内存占用和文件关闭等不确定问题。
 * 被MappedByteBuffer打开的文件只有在垃圾收集时才会被关闭，而这个点是不确定的
 * @author hui.luo
 *
 */
public class NioFileReadDemo {
	
	public static void main(String[] args) {
		NioFileReadDemo demo = new NioFileReadDemo();
//		demo.OIORead("C:/1.txt");
//		demo.NioReadSmallFile("C:/1.txt");
//		demo.NioReadLargeFile("C:/1.txt");
		demo.NioReadFasterFile("C:/1.txt");
	}
	
	public void OIORead(String path){
		BufferedReader br = null;
        String sCurrentLine = null;
        try{
            br = new BufferedReader(new FileReader(path));
            while ((sCurrentLine = br.readLine()) != null){
                System.out.println(sCurrentLine);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (br != null){
                    br.close();                	
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
	}
	
	public void NioReadSmallFile(String path){
		try{
			RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
			FileChannel channel = file.getChannel();
			long fileSize = channel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int)fileSize);
			channel.read(buffer);
			buffer.flip();
			for (int i = 0; i < fileSize; i++) {
//				System.out.println(buffer.getChar());//这样会报错的BufferUnderflowException
				System.out.println((char)buffer.get());
			}
			channel.close();
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void NioReadLargeFile(String path){
		try{
			RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
			FileChannel channel = file.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(channel.read(buffer)>0){
				buffer.flip();
				for (int i = 0; i < buffer.limit(); i++) {
//					System.out.println(buffer.getChar());//这样会报错的BufferUnderflowException
					System.out.println((char)buffer.get());
				}
				//或者
//				while (buffer.hasRemaining()) {
				buffer.clear();//别忘记了
			}
			channel.close();
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void NioReadFasterFile(String path){
		try{
			RandomAccessFile file = new RandomAccessFile(new File(path), "rw");
			FileChannel channel = file.getChannel();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			buffer.load();
			for (int i = 0; i < buffer.limit(); i++) {
				System.out.println((char)buffer.get());
			}
			buffer.clear();//别忘记了
			channel.close();
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
