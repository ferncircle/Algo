package com.chawkalla.algorithms.examples.bits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaxXorTwoNumbers {

	public int findMaximumXOR(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return 0;
		int max=0;
		
		for (int i = 0; i < nums.length; i++) {
			
			for (int j = i+1; j < nums.length; j++) {
				int val=nums[i] ^ nums[j];
				if(val>max)
					max=val;
			}
		}
		
		return max;
	}

	public static void main(String[] args) {

		assertThat(new MaxXorTwoNumbers().findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}), is(28));

		System.out.println("All test cases passed");
	}

}
