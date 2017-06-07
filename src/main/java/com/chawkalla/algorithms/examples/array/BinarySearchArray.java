/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * @author SFargose
 *
 */
public class BinarySearchArray {

	int search(int[] a, int start, int end, int val){	
		if(start==end && a[start]==val) return start;
		if(start>end) return -(start+1);
		
		int mid=(start+end) >> 1;
		if(a[mid]==val) return mid;
		if(a[mid]>val)
			return search(a, start, mid-1, val);
		else
			return search(a, mid+1, end, val);
		
		
	}
	
	public static void main(String[] args) {
		
		int[] a=new int[]{3,5,6,7,9,22,25,25,25,27,27,37,37};
		
		System.out.println(Arrays.binarySearch(a, 4));
		assertThat(new BinarySearchArray().search(a, 0, a.length-1,22), is(Arrays.binarySearch(a, 22)));

		assertThat(new BinarySearchArray().search(a, 0, a.length-1,4), is(Arrays.binarySearch(a, 4)));

		assertThat(new BinarySearchArray().search(a, 0, a.length-1,29), is(Arrays.binarySearch(a, 29)));

		assertThat(new BinarySearchArray().search(a, 0, a.length-1,0), is(Arrays.binarySearch(a, 0)));
		

		assertThat(new BinarySearchArray().search(a, 0, a.length-1,110), is(Arrays.binarySearch(a, 110)));
		
		System.out.println("all cases passed");

	}

}
