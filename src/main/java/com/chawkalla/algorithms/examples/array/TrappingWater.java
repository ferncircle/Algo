package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Solution: http://www.geeksforgeeks.org/trapping-rain-water/

Examples:

Input: height[]   = {2, 0, 2}
Output: 2
Structure is like below
| |
|_|
We can trap 2 units of water in the middle gap.

Input: height[]   = {3, 0, 0, 2, 0, 4}
Output: 10
Structure is like below
     |
|    |
|  | |
|__|_| 
We can trap "3*2 units" of water between 3 an 2,
"1 unit" on top of bar 2 and "3 units" between 2 
and 4.  See below diagram also.

Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 */
public class TrappingWater {

	public int trap(int[] height) {
		if(height==null || height.length<3)
			return 0;
		int n=height.length;
		// left[i] contains height of tallest bar to the
	    // left of i'th bar including itself
	    int[] left=new int[n];
	 
	    // Right [i] contains height of tallest bar to
	    // the right of ith bar including itself
	    int[] right=new int[n];
	 
	    // Initialize result
	    int water = 0;
	 
	    // Fill left heightay
	    left[0] = height[0];
	    for (int i = 1; i < n; i++)
	       left[i] = Math.max(left[i-1], height[i]);
	 
	    // Fill right heightay
	    right[n-1] = height[n-1];
	    for (int i = n-2; i >= 0; i--)
	       right[i] = Math.max(right[i+1], height[i]);
	 
	    // Calculate the accumulated water element by element
	    // consider the amount of water on i'th bar, the
	    // amount of water accumulated on this particular
	    // bar will be equal to min(left[i], right[i]) - height[i] .
	    for (int i = 0; i < n; i++)
	       water += Math.min(left[i],right[i]) - height[i];
	 
	    return water;
	}

	public static void main(String[] args) {

		assertThat(new TrappingWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}), is(6));
		assertThat(new TrappingWater().trap(new int[]{3, 0, 0, 2, 0, 4}), is(10));
		assertThat(new TrappingWater().trap(new int[]{2, 0, 2}), is(2));
		
		System.out.println("All test cases passed");

	}

}
