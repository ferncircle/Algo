package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 *
 */
public class KthElementTwoSortedArrays {

	public int kthElement(int[] a, int[] b, int k){

		return findUtil(a, b, 0, a.length-1, 0, b.length-1, k-1);
	}

	public int findUtil(int[] a, int[] b, int s1, int e1, int s2, int e2, int k){
		if(s1==e1)
			return b[k-s1];
		if(s2==e2)
			return a[k-s2];		

		int m1=(s1+e1)/2;
		int m2=(s2+e2)/2;

		if(m1+m2<k){ 
			//number is on right halfs, so remove one of left halfs
			if(a[m1]<b[m2]){
				return findUtil(a, b, m1+1, e1, s2, e2, k); //remove first left half
			}else{
				return findUtil(a, b, s1, e1, m2+1, e2, k); //remove second left half
			}
		}else{
			//number is on left halfs, so remove one of right halfs
			if(a[m1]<b[m2]){
				return findUtil(a, b, s1, e1, s2, m2, k); //remove second right half
			}else{
				return findUtil(a, b, s1, m1, s2, e2, k); //remove first right half
			}
		}

	}

	public static void main(String[] args){

		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5), is(6));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 1), is(1));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 8), is(10));
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{100, 112, 256, 349, 770}, 
				new int[]{72, 86, 113, 119, 265, 445, 892}, 7), is(256));
		
		System.out.println("all cases passed");
	}
}
