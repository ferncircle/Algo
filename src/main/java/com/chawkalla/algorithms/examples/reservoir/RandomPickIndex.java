package com.chawkalla.algorithms.examples.reservoir;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-index/
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. 
 * You can assume that the given target number must exist in the array.
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.
 */
public class RandomPickIndex {

	int[] nums=null;
	public RandomPickIndex(int[] nums) {
		this.nums=nums;
	}

	public int pick(int target) {
		
		if(nums.length==0)
			return -1;
		int[] sol=reservoir(nums, 1, target);
		return sol[0];
	}
	
	public int[] reservoir(int[] nums, int k, int target){
		int[] samples=new int[k];
		int samplesCounter=-1;
		Random rand=new Random();
		for (int i = 0; i < nums.length; i++) {
			if(target!=nums[i])
				continue;
			
			samplesCounter++;
			if(samplesCounter>0 && samplesCounter<k){
				samples[samplesCounter]=nums[i];
			}else{
				//generate a random number from 0 to counter
				//rand.nextInt((max - min) + 1) + min;
				int randomPos=rand.nextInt((samplesCounter-0)+1)+0;
				if(randomPos<k)
					samples[randomPos]=i;
			}
			
		}
		return samples;
	}
	public static void main(String[] args) {

		assertThat(Arrays.asList(2,3,4),hasItem(new RandomPickIndex(new int[]{1,2,3,3,3}).pick(3)));
		assertThat(Arrays.asList(0),hasItem(new RandomPickIndex(new int[]{1,2,3,3,3}).pick(1)));
		assertThat(Arrays.asList(1),hasItem(new RandomPickIndex(new int[]{1,2,3,3,3}).pick(2)));
		assertThat(Arrays.asList(5,7),hasItem(new RandomPickIndex(new int[]{1,2,3,3,3,4,5,4}).pick(4)));
		
		
		System.out.println("all test cases passed");
	}

}
