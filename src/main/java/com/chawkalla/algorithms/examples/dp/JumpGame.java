package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/jump-game/
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 *
 *Goal: at every element, check if it can update max Range. Now if current
 *element goes beyond max Reachable then break, cause it's not possible to 
 *reach beyond that.
 */
public class JumpGame {
	public boolean canJump(int[] nums) {
        boolean possible=false;
        
        int maxIndexReachable=0;
        int i=0;
        for ( i = 0; i < nums.length; i++) {
        	if(i>maxIndexReachable) //reached beyond max possible 
				break;
        	//update max reachable
			maxIndexReachable=Math.max(i+nums[i], maxIndexReachable);			
		}
        possible=(i==nums.length);
        return possible;
        
    }
	
	public static void main(String[] args) {
		
		assertThat(new JumpGame().canJump(new int[]{2,3,1,1,4}), is(true));
		assertThat(new JumpGame().canJump(new int[]{3,2,1,0,4}), is(false));
		System.out.println("All test cases passed");
	}
}
