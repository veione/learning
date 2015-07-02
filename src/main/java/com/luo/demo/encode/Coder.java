package com.luo.demo.encode;
import java.io.IOException;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
public class Coder {
	public static final String KEY_SHA = "SHA";  
    public static final String KEY_MD5 = "MD5";
    
    public static String base64Encrypt(byte[] key){
    	return (new BASE64Encoder()).encodeBuffer(key);
    }
    
    public static byte[] base64Decrypt(String key) throws IOException{
    	return (new BASE64Decoder()).decodeBuffer(key);
    }
}
