package com.chawkalla.algorithms.examples.numbers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/next-permutation/
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 -> 1,3,2
3,2,1 -> 1,2,3
1,1,5 ->s 1,5,1 
 e.g 1 7 9 8 4 will transform to 1 8 4 7 9
 * or  4 2 6 4 3 will transform to 4 3 2 4 6
 *
 */
public class NextBiggerPermutation {

	public int[] nextPermutation(int[] nums) {
		if(nums==null || nums.length<2)
			return nums;
		
		//traverse from end 
		//find longest suffix that's in descending order
		int i=nums.length-1;
		while(i>0 && nums[i-1]>=nums[i]){
			i--;
		}
		
		//now reverse that suffix
		int start=i;
		int end=nums.length-1;
		while(start<end){
			int temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}
		
		if(i>0){
			int indexToBeReplaced=i-1;
			//find out first element greater than i-1
			
			while(nums[i]<=nums[indexToBeReplaced])
				i++;			
			
			//swap that element 
			int temp=nums[i];
			nums[i]=nums[indexToBeReplaced];
			nums[indexToBeReplaced]=temp;
		}
		
		return nums;
	}
	
	public static void main(String[] args) {
		assertThat(new NextBiggerPermutation().nextPermutation(new int[]{1,3,2,1,1,1}), 
				is(new int[]{2,1,1,1,1,3}));
		assertThat(new NextBiggerPermutation().nextPermutation(new int[]{1,1,5}), is(new int[]{1,5,1}));
		assertThat(new NextBiggerPermutation().nextPermutation(new int[]{3,2,1}), is(new int[]{1,2,3}));
		assertThat(new NextBiggerPermutation().nextPermutation(new int[]{1, 7, 9, 8, 4,}), 
				is(new int[]{1, 8, 4, 7, 9 }));
		assertThat(new NextBiggerPermutation().nextPermutation(new int[]{4, 2, 6, 4, 3}), 
				is(new int[]{4, 3, 2, 4, 6 }));
		System.out.println("All test cases passed");

	}

}
