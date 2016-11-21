package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/majority-element/
 * Given an array of size n, find the majority element. The majority element is the element that appears more than  n/2 times.

You may assume that the array is non-empty and the majority element always exist in the array.


 *
 */
public class MajorityElement {

	public int majorityElement(int[] nums) {
		if(nums.length==0)
			return 0;
		
        int majority=nums[0];
        int count=1;
        
        for (int i = 1; i < nums.length; i++) {
        	if(count==0){
        		majority=nums[i];
        		count=1;
        		continue;
        	}
			if(majority!=nums[i]){
				count--;
			}else{
				count++;
			}
		}
        return majority;
    }

	public static void main(String[] args) {

		assertThat(new MajorityElement().majorityElement(new int[]{1,3,2,1,3,2,1,1,1}), is(1));
		assertThat(new MajorityElement().majorityElement(new int[]{10,9,9,9,10}), is(9));
		
		System.out.println("All test cases passed");
	}

}
