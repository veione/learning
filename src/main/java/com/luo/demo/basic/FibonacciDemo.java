package com.luo.demo.basic;

public class FibonacciDemo {

	public static void main(String[] args) {
		System.out.println(fibonacci1(6)+""+fibonacci2(6));
	}

	private static int fibonacci2(int i) {
		if(i<2){
			return 1;
		}
		return fibonacci2(i-1)+fibonacci2(i-2);
	}

	private static int fibonacci1(int i) {
		if(i<2){
			return 1;
		}
		int n1 =1,n2=1,sn=0;
		for (int j = 0; j < i-2; j++) {
			sn=n1+n2;
			n1=n2;
			n2=sn;
		}
		return sn;
	}

}
