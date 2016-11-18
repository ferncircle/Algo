package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/jump-game-ii/s
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *
 *Optimal Solution: Consider: [5,2,6,1,2,0,1,1,1]
 *Note that first long jump has option of going from index 0 to index 5. So index 5 is end of range.
 *Now go through every element in that range [2,6,1,2,0](consider them as children of index 0). Out of them element 6 will give you next biggest range
 *At the end of range, increment the noOfHops 
 */
public class JumpGame2 {

	/**
	 * DP solution O(n*n)
	 */
	public int jump(int[] nums) {
        int min=0;
        if(nums.length<=1)
        	return 0;
        int[] steps=new int[nums.length];
        
        for (int i = 1; i < nums.length; i++) {
			steps[i]=Integer.MAX_VALUE;
        	for (int j = 0; j < i; j++) {
        		int dist=i-j;
        		int jumpStrength=nums[j];
				if(jumpStrength>=dist && (1+steps[j])<steps[i])
					steps[i]=1+steps[j];
			}
		}
        min=steps[nums.length-1];
        return min;
    }
	
	/**
	 * O(n)
	 */
	public int jump2(int[] nums) {
        int hops=0;
        if(nums.length<=1)
        	return 0;
        
        int previousLongJumpRangeEnd=0;
        int maxJumpSoFar=0;
        
        for (int i = 0; i < nums.length-1; i++) {
			maxJumpSoFar=Math.max(i+nums[i], maxJumpSoFar);
			if(i==previousLongJumpRangeEnd){
				hops++;
				previousLongJumpRangeEnd=maxJumpSoFar;
			}
		}
        
        return hops;
    }
	

	public static void main(String[] args) {
		assertThat(new JumpGame2().jump(new int[]{2,3,1,1,4}), is(2));
		
		
		assertThat(new JumpGame2().jump2(new int[]{2,3,1,1,4}), is(2));
		assertThat(new JumpGame2().jump2(new int[]{5,2,6,1,2,0,1,1,1}), is(2));
		
		System.out.println("All test cases passed");
	}

}
