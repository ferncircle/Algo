/**
 * 
 */
package com.chawkalla.algorithms.sort;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * 
 *
 */
public class QuickSort {

	public void sort(int[] a){
		sortUtil(a, 0, a.length-1);
	}
	
	private void sortUtil(int[] a, int low, int high){
		if(low >= high)
			return;
		
		int pos=partition(a, low, high); //place one element at correct position
		
		sortUtil(a, low, pos-1); //repeat the same for left half
		sortUtil(a, pos+1, high); //repeat the same for right half
	}
	
	
	/**
	 * Always move j pointer, if value at j is less than pivot, swap with current i pointer and then move i
	 */
	public static int partition(int[] a, final int low, final int high){
		int i=low+1;
		int j=low+1;
		int pivot=low;
		
		while(j<=high){
			if(a[j]<=a[pivot]){
				swap(a, i, j);
				i++;
			}
			j++;
		}
		swap(a,pivot, i-1);
		return i-1;
	}
	
	/**
	 * start pointer i, end pointer j and swap when needed
	 */
	public static int split(int[] a, final int low, final int high){
		int i=low+1;
		int j=high;
		int pivot=low;
		
		while(i <= j){
			if(a[i] <= a[pivot]){
				i++;
				continue;
			}
			if(a[j] > a[pivot]){
				j--;
				continue;
			}
			swap(a, i, j);
			i++;j--;
		}
		int pos=j;
		
		if(a[pivot] > a[pos]){
			swap(a, pivot, pos);
		}
		
		return pos;
	}
	
	public static int findMedian(int[] a){
		int median=Integer.MAX_VALUE;
		if(a.length%2!=0){
			median=findKthElement(a, a.length/2+1);
		}else{
			int x=findKthElement(a, a.length/2-1);
			int y=findKthElement(a, a.length/2);
			
			median=(x+y)/2;
		}
		
		return median;
	}
	
	public static int findKthElement(int[] a, int k){
		int kth=Integer.MAX_VALUE;
		
		if(k>a.length)
			return kth;
		
		kth=0;
		int low=0;
		int high=a.length-1;
		
		while(kth!=k-1){
			kth=split(a, low, high);
			if(kth>k)
				high=kth-1;
			if(kth<k)
				low=kth+1;			
		}		
		
		return a[kth];
	}
	
	
	
	public static void swap(int[] a, int i, int j){
		int temp=a[i];	
		a[i]=a[j];
		a[j]=temp;
	}
	public static void main(String[] args) {
		//int[] a=new int[]{3,4,1,5,2,5,6,3,1,4,2};
		int[] a=new int[]{3,2,4,6,1,-1,2,5,-3,2};
		new QuickSort().sort(a);
		
		System.out.println(Arrays.toString(a));
		
		assertThat(QuickSort.findMedian(new int[]{4,2,1,6,3}), is(3));
		assertThat(QuickSort.findKthElement(new int[]{4,2,1,6,3}, 5), is(6));
		
		System.out.println("all test cases passed");

	}

}
