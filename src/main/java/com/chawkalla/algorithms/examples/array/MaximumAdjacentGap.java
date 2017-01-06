/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/maximum-gap/
 * 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
e.g. {5, 2, 8, 20, 11, 15, 36, 100, 30, 3} --> 64
 *
 *{4, 3, 13, 2, 9, 5, 6, 7, 20, 22} --> 7
 *
 *Difficulty: Hard
 *
 *Solution:
 * 1) Create n buckets of ranges, each bucket will ONLY have start and end element of that range.
 * 		The range of each bucket is (max-min)/n-1
 * 2) for each element, find out the bucket of that range (cur-min)/bucketSize
 * 3) check if cur element can become begin or end of that bucket
 * 4) In the end, Go through each bucket and calculate (curBegin-prevEnd) and check if it's maximum so far
 */
public class MaximumAdjacentGap {

	class Range{
		int begin;
		int end;
		boolean empty=true;
		
		public void update(int a){
			if(empty){
				begin=a;
				end=a;
				empty=false;
			}else{
				begin=Math.min(begin, a);
				end=Math.max(end, a);
			}
		}

		@Override
		public String toString() {
			return "Range [begin=" + begin + ", end=" + end + "]";
		}
	}
	public int maximumGap(int[] nums) {
		int maxDiff=0;
		if(nums.length<2)
			return 0;
		
		Range[] ranges=new Range[nums.length];
		for (int i = 0; i < ranges.length; i++)
			ranges[i]=new Range();
		
		int maxElement=Integer.MIN_VALUE;
		int minElement=Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			maxElement=Math.max(nums[i], maxElement);
			minElement=Math.min(nums[i], minElement);
		}
		int rangeSize=(int)Math.ceil((double)(maxElement-minElement)/(nums.length-1));
		
		for (int i = 0; i < nums.length; i++) {
			int curElement=nums[i];
			int rangeBucket=0;
			if(curElement-minElement > 0)
				rangeBucket=(curElement-minElement)/rangeSize;
			
			ranges[rangeBucket].update(curElement);
		}
		
		int prevEnd=ranges[0].begin;
		
		for (int i = 0; i < ranges.length; i++) {
			Range curRange=ranges[i];
			if(curRange.empty)
				continue;
			
			int curDiff=curRange.begin-prevEnd;
			maxDiff=Math.max(maxDiff, curDiff);
			prevEnd=curRange.end;
		}
		return maxDiff;
	}

	public static void main(String[] args) {
		
		assertThat(new MaximumAdjacentGap().maximumGap(new int[]{1,1,1,1}), is(0));
		
		assertThat(new MaximumAdjacentGap().maximumGap(new int[]{1,1000}), is(999));
		
		assertThat(new MaximumAdjacentGap().maximumGap(new int[]{5, 2, 8, 20, 11, 15, 36, 100, 30, 3}), is(64));
		assertThat(new MaximumAdjacentGap().maximumGap(new int[]{4, 3, 13, 2, 9, 5, 6, 7, 20, 22}), is(7));
		
		System.out.println("All test cases passed");

	}

}
