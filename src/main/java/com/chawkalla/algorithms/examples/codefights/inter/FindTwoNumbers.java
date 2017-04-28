/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 *
https://codefights.com/interview/ypWMG9KExz8FbGkYB/description

You have an array a containing 2 * n + 2 positive numbers, in which n numbers occur twice and two other numbers occur only 
once and are distinct. Find the two distinct numbers and return them in ascending order.

Example

For a = [1, 3, 5, 6, 1, 4, 3, 6], the output should be
findTheNumbers(a) = [4, 5];
For a = [4, 5, 6, 5, 3, 4], the output should be
findTheNumbers(a) = [3, 6].
 *
 */
public class FindTwoNumbers {

	int[] findTheNumbers(int[] a) {

		//get xor of all elements to get xor of just two elements
		int twoElementsXor=0;
		for (int i = 0; i < a.length; i++) {
			twoElementsXor=twoElementsXor ^ a[i];
		}
		
		//get rightmost set bit(or any set bit)
		int oneSetBit=twoElementsXor & ~(twoElementsXor-1);
		
		//divide into two sets
		int set1=0;
		int set2=0;
		
		for (int i = 0; i < a.length; i++) 
			if((a[i] & oneSetBit)==0)
				set1=set1 ^ a[i];
			else
				set2=set2 ^ a[i];
		
		int[] res=new int[]{set1, set2};
		Arrays.sort(res);
		return res;
	}
	
	
	public static void main(String[] args) {
		
		assertThat(new FindTwoNumbers().findTheNumbers(new int[]{1, 3, 5, 6, 1, 4, 3, 6}), is(new int[]{4,5}));
		assertThat(new FindTwoNumbers().findTheNumbers(new int[]{4, 5, 6, 5, 3, 4}), is(new int[]{3,6}));

		System.out.println("all cases passed");
	}

}
