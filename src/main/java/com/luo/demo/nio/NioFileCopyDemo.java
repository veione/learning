package com.luo.demo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;
public class NioFileCopyDemo {
	
	public static void main(String[] args) {
		NioFileCopyDemo demo = new NioFileCopyDemo();
//		demo.fileCopyUsingApacheCommons("C:/1.txt","C:/2.txt");
//		demo.fileCopyUsingFileStreams("C:/1.txt","C:/2.txt");
//		demo.fileCopyUsingNIOFilesClass("C:/1.txt","C:/2.txt");
//		demo.fileCopyUsingNIOChannelClass("C:/1.txt","C:/2.txt");
		demo.fileCopyUsingNIOMappedByteBuffer("C:/1.txt","C:/2.txt");
	}
	
	public void fileCopyUsingApacheCommons(String in,String out){
		File srcFile = new File(in);
		File destFile = new File(out);
		try {
			FileUtils.copyFile(srcFile, destFile);
			//或者
//			IOUtils.copy(new FileReader(srcFile), new FileWriter(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileCopyUsingFileStreams(String in,String out){
		File srcFile = new File(in);
		File destFile = new File(out);
		try {
			FileInputStream is = new FileInputStream(srcFile);
			FileOutputStream os = new FileOutputStream(destFile);
			
			byte[] buf = new byte[1024];
			int byteRead;
			while((byteRead=is.read(buf))>0){
				os.write(buf,0,byteRead);
			}
			
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileCopyUsingNIOFilesClass(String in,String out){
		Path srcFile = Paths.get(in);
		Path destFile = Paths.get(out);
		try {
//			Files.copy(srcFile, destFile);
			Files.copy(srcFile, destFile,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileCopyUsingNIOChannelClass(String in,String out){
		File srcFile = new File(in);
		File destFile = new File(out);
		try {
			FileInputStream is = new FileInputStream(srcFile);
			FileOutputStream os = new FileOutputStream(destFile);
			
			FileChannel inChannel = is.getChannel();
			FileChannel outChannel = os.getChannel();
			
			inChannel.transferTo(0, inChannel.size(), outChannel);
//			inChannel.transferTo(0, srcFile.length(), outChannel);
			
			inChannel.close();
			outChannel.close();
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileCopyUsingNIOMappedByteBuffer(String in,String out){
		File srcFile = new File(in);
		File destFile = new File(out);
		try {
			FileInputStream is = new FileInputStream(srcFile);
			FileOutputStream os = new FileOutputStream(destFile);
			
			FileChannel inChannel = is.getChannel();
			FileChannel outChannel = os.getChannel();
			
			MappedByteBuffer buf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, srcFile.length());
			outChannel.write(buf);
			
			inChannel.close();
			outChannel.close();
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
