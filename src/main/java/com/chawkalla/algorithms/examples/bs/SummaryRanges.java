package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/summary-ranges/
 * Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 *
 */
public class SummaryRanges {

	public List<String> summaryRanges(int[] nums) {
		List<String> ranges=new ArrayList<String>();
		if(nums==null || nums.length==0)
			return ranges;
		
		boolean done=false;
		int i=0;
		while(!done){
			int beginRange=i;			
			int endRange=findRangeEnd(nums, beginRange, nums.length-1);
			
			if(beginRange==endRange)
				ranges.add(""+nums[beginRange]);
			else
				ranges.add(""+nums[beginRange]+"->"+nums[endRange]);
			
			i=endRange+1;
			if(i>=nums.length)
				done=true;
		}
		
		return ranges;
	}
	
	public int findRangeEnd(int[] a, int start, int end){
		//end condition
		if(end-start==1 && a[end]-a[start]==1)
			return end;	
		if(end-1<=start)
			return start;
		
		int mid=(start+end)/2;
				
		if(a[mid]-a[start]>mid-start){//not in sequence, go left
			return findRangeEnd(a, start, mid);
		}else{//in sequence, go right to find end
			return findRangeEnd(a, mid, end);
		}	
		
	}


	public static void main(String[] args) {
		
		assertThat(new SummaryRanges().summaryRanges(new int[]{0,1,2,4,5,7}), is(Arrays.asList("0->2","4->5","7")));
		assertThat(new SummaryRanges().summaryRanges(new int[]{5}), is(Arrays.asList("5")));
		assertThat(new SummaryRanges().summaryRanges(new int[]{}), is(Arrays.asList()));		
		assertThat(new SummaryRanges().summaryRanges(new int[]{4,5,6}), is(Arrays.asList("4->6")));
		assertThat(new SummaryRanges().summaryRanges(new int[]{1,2,105,106}), is(Arrays.asList("1->2","105->106")));
		
		
		
		System.out.println("All test cases passed");

	}

}
