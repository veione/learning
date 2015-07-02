package com.luo.demo.basic;

/**
 * 判断101~200之间有多少个素数，并输出所有素数。
 *  
 * @author hui.luo
 * 程序分析：判断素数的方法：用一个数分别去除2到sqt（这个数），如果能被整除，则表明此数不是素数。反之则是。
 */
public class PrimeNumberDemo {

	public static void main(String[] args) {
		boolean result = false;
		int count = 0;
		for (int i = 101; i <= 200; i++) {
			for (int j = 2; j <= Math.sqrt(j); j++) {
				if(i%j==0){
					result = true;
					break;
				}
			}
			if(result){
				count++;
			}
		}
		System.out.println(count);
	}

}
