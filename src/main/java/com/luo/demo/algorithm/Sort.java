package com.luo.demo.algorithm;

import java.util.Arrays;

/**
 * Created by luohui on 15/11/5.
 * http://blog.csdn.net/shan9liang/article/details/7533466
 * 插入排序（直接插入排序、希尔排序）
 * 选择排序（简单选择排序、堆排序）
 * 交换排序（冒泡排序、快速排序）
 * 归并排序
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {5,8,9,0,2,6,1,4};
        insertSort(arr);
        shellSort(arr);

        selectSort(arr);
        stackSort(arr);

        bubbleSort(arr);
        quickSort(arr);

        print(arr);
    }

    private static void stackSort(int[] arr) {

    }

    /**
     * 快速排序一般基于递归实现。其思路是这样的：
     1.选定一个合适的值（理想情况中值最好，但实现中一般使用数组第一个值）,称为“枢轴”(pivot)。
     2.基于这个值，将数组分为两部分，较小的分在左边，较大的分在右边。
     3.可以肯定，如此一轮下来，这个枢轴的位置一定在最终位置上。
     4.对两个子数组分别重复上述过程，直到每个数组只有一个元素。
     5.排序完成
     * @param arr
     */
    private static void quickSort(int[] arr) {

    }

    /**
     * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     * @param arr
     */
    private static void bubbleSort(int[] arr) {

    }

    /**
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     * @param arr
     */
    private static void selectSort(int[] arr) {

    }

    /**
     * 该方法的基本思想是：先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，再对全体元素进行一次直接插入排序。因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，因此希尔排序在时间效率上比前两种方法有较大提高
     * @param arr
     */
    private static void shellSort(int[] arr) {

    }

    /**
     * 基本思想：在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {//第一层for从第2个元素开始比较，总共有arr.length-1个元素
            for (int j = i; j > 0; j--) {//第二次for为，和前面的几个元素比较
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }else {
                    break;
                }
            }
        }
    }


    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    private static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}
