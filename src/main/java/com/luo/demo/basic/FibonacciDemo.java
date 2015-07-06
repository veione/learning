package com.luo.demo.basic;

public class FibonacciDemo {

	public static void main(String[] args) {
		System.out.println(fibonacci1(6)+""+fibonacci2(6));
	}

	/**
	 * 时间复杂度：O(2的k次方)，指数级
	 * 效率低是因为有重复计算，比如fibonacci2(1)计算每次都会计算
	 * @param i
	 * @return
	 */
	private static int fibonacci2(int i) {
		if(i<2){
			return 1;
		}
		return fibonacci2(i-1)+fibonacci2(i-2);
	}

	/**
	 * O(1)*n = O(n)
	 * @param i
	 * @return
	 */
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
