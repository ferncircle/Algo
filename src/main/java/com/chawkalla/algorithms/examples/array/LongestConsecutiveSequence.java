/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

/**
 * 
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 *
 *Goal: Maintain a map that contains each element as key and value is the length of it's sequence.
 *At each element, check if left sequence exists (ending at cur-1) and check if right sequence exists(starting with cur+1).
 * This element will join those two sequences and update the length of this merged sequence's ends.
 *update the extreme left end and extreme right end length in map. 
 */
public class LongestConsecutiveSequence {

	public int longestConsecutive(int[] nums) {
		if(nums.length<2)
			return nums.length;
		
        int longest=0;
        
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
        	
			int cur=nums[i];
			if(map.containsKey(cur))
        		continue;
			//check if left sequence exists
			int totalOnLeft=map.containsKey(cur-1)?map.get(cur-1):0;
			
			//check if right sequence exists
			int totalOnRight=map.containsKey(cur+1)?map.get(cur+1):0;
			
			int totalCurSequenceLength=1+totalOnLeft+totalOnRight;
			
			//we only care about updating left and right ends. But this is only required to handle duplicate elements
			map.put(cur, totalCurSequenceLength); 
			
			//update extreme left element length
			map.put(cur-totalOnLeft, totalCurSequenceLength);
			
			//update extreme right element length
			map.put(cur+totalOnRight, totalCurSequenceLength);
			
			//update longest
			if(totalCurSequenceLength>longest)
				longest=totalCurSequenceLength;
			
		}
        
        return longest;
    }
	
	public static void main(String[] args) {
		
		assertThat(new LongestConsecutiveSequence().longestConsecutive(new int[]{1,2,0,1}), is(3));
		assertThat(new LongestConsecutiveSequence().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}), is(4));
		
		System.out.println("All test cases passed");
	}

}
