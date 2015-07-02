package com.luo.demo.encode;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESDemo extends Coder{
	
	private static Key toKey(byte[] key) throws Exception{
		DESKeySpec des = new DESKeySpec(key);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = factory.generateSecret(des);
		return secretKey;
	}
	
	public static byte[] encrypt(byte[] data,String key) throws Exception{
		Key k = toKey(base64Decrypt(key));
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
}
