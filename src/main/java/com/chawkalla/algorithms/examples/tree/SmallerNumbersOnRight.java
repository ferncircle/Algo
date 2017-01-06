/**
 * 
 */
package com.chawkalla.algorithms.examples.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].


 */
public class SmallerNumbersOnRight {

	public List<Integer> countSmaller(int[] nums) {
		
		List<Integer> list=new ArrayList<Integer>();
		return list;
		
	}
	
	public static void main(String[] args) {
		
		assertThat(new SmallerNumbersOnRight().countSmaller(new int[]{5, 2, 6, 1}), 
				is(Arrays.asList(2, 1, 1, 0)));
		
		System.out.println("All test cases passed");
	}

}
