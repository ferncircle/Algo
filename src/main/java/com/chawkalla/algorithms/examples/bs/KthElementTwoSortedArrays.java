package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 *
 */
public class KthElementTwoSortedArrays {

	public int kthElement(int[] a, int[] b, int k){

		//return findUtil(a, b, 0, a.length-1, 0, b.length-1, k-1);
		
		return findKthIterative(a, b, k-1);
	}
	
	public int findKthIterative(int[] a, int[] b, int k){
		int s1=0, e1=a.length-1,  s2=0,  e2=b.length-1;
		
		while(true){
			if(s1==e1 && s2==e2)
				return k==0?Math.min(a[s1], b[s2]):Math.max(a[s1], b[s2]);
			if(s1>e1)
				return b[k-s1];						
			if(s2>e2)
				return a[k-s2];	
			
			int m1=(s1+e1)/2;
			int m2=(s2+e2)/2;

			if(m1+m2<k){ 
				//number is on right halfs, so remove one of left halfs
				if(a[m1]<b[m2]){
					s1=m1+1; //remove first left half
				}else{
					s2=m2+1; //remove second left half
				}
			}else{
				//number is on left halfs, so remove one of right halfs
				if(a[m1]<b[m2]){
					e2= m2-1; //remove second right half
				}else{
					e1= m1-1; //remove first right half
				}
			}
		}
		
	}


	public static void main(String[] args){
		
		int[] a=new int[]{-50, -41, -40, -19, 5, 21, 28, -50, -21, -10};
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{-50, -41, -40, -19, 5, 21, 28}, 
				new int[]{-50, -21, -10}, 6), is(-19));
		
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 3, 3, 6, 7, 9}, 
				new int[]{1, 4, 8, 10}, 5), is(3));
		
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{1, 2}, new int[]{3}, 3), is(3));
		
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{1}, new int[]{2}, 1), is(1));
		
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5), is(6));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 1), is(1));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 8), is(10));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{100, 112, 256, 349, 770}, 
				new int[]{72, 86, 113, 119, 265, 445, 892}, 7), is(256));
		
		System.out.println("all cases passed");
	}
	

}
