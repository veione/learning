package com.luo.demo.structure;

public class BinaryFind {
	private static Integer [] array = {2, 4, 7, 8, 11,19, 22, 25, 34, 99};
	public static void main(String[] args) {
		System.out.println(find(0,array.length-1,11));
//		System.out.println(search(7));
	}
	/**
	 * 推荐的写法
	 * @param a
	 * @return
	 */
	private static int search(int a) {
		int low = 0;
		int upper = array.length-1;
		while (low<=upper) {
			int mid = (low+upper)/2;
			if(array[mid]<a){
				low=mid+1;
			}else if(array[mid]>a){
				upper = mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}

	public static int find( int begin,int end,int a ){
		if(begin<=end){
			int mid = (begin+end)/2;
			int midValue = array[mid];
			if(a==midValue){
				return mid;
			}else{
				if(a<midValue){
					return find(begin, mid-1, a);
				}else{
					return find(mid+1, end, a);
				}
			}
		}
		return -1;
	}
	
	/*
	 * 我自己想的
	 */
	public static void find0( int begin,int end,int a ){
		int middleIndex = 0;
		if(end-begin==1 && begin!=0){
			middleIndex = ((end-begin)/2)+begin+1;
		}else{
			middleIndex = ((end-begin)/2)+begin;
		}
		int middleValue = array[middleIndex];
		if( middleValue==a ){
			System.out.println(middleIndex);
			System.exit(0);
		}else{
			if( middleValue>a ){
				find(0, middleIndex, a);
			}else{
				find(middleIndex, end, a);
			}
		}
	}
}
