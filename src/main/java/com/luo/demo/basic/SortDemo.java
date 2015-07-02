package com.luo.demo.basic;

import java.util.Arrays;

public class SortDemo {
	/**
	 * 快速排序一般基于递归实现。其思路是这样的：
		1.选定一个合适的值（理想情况中值最好，但实现中一般使用数组第一个值）,称为“枢轴”(pivot)。
		2.基于这个值，将数组分为两部分，较小的分在左边，较大的分在右边。
		3.可以肯定，如此一轮下来，这个枢轴的位置一定在最终位置上。
		4.对两个子数组分别重复上述过程，直到每个数组只有一个元素。
		5.排序完成
	 * @param args
	 */
	public static void quickSort(int[] arr,int low,int high){
		if(low<high){
			int p = partition(arr,low,high);
			quickSort(arr,low, p-1);
			quickSort(arr,p+1, high);
		}
	}
	
	private static int partition(int[] arr,int low, int high) {
		int v = arr[low];//默认是一个数组的第一个元素为锚点
		while(low<high){
			while(low<high && arr[high]>=v){
				high--;
			}
			arr[low] = arr[high];//如果右边元素小于v，则把右边元素放到锚点
			while(low<high && arr[low]<=v){
				low++;
			}
			arr[high]=arr[low];
		}
		arr[low] = v;
		return low;
	}
	
	private static void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	
	/**
	 * ⒈ 从第一个元素开始，该元素可以认为已经被排序
		⒉ 取出下一个元素，在已经排序的元素序列中从后向前扫描
		⒊ 如果该元素（已排序）大于新元素，将该元素移到下一位置
		⒋ 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
		⒌ 将新元素插入到下一位置中
		⒍ 重复步骤2~5
	 * @param arr
	 */
	private static void insertSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if(arr[j]<arr[j-1]){
					swap(arr, j, j-1);
				}else{
					break;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[] arr = {9,122,3,6,22,77,5,89,5,34};
//		quickSort(arr,0,arr.length-1);
		insertSort(arr);
		System.out.println( Arrays.toString(arr));
	}

}
