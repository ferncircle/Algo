package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * 
 * Given an integer array of size n, find all elements that appear more than  n/3 times. 
 * The algorithm should run in linear time and in O(1) space.
 *
 * Solution1: There can be at most 2 such elements. Extend the algo of finding element appearing more than n/2 times
 */
public class MajorityElementN3 {

	public List<Integer> majorityElement(int[] nums) {		
		List<Integer> list=new ArrayList<Integer>();
		if(nums.length<3){
			for (int i = 0; i < nums.length; i++) 
				if(!list.contains(nums[i]))
					list.add(nums[i]);				
			return list;
		}
			
		int c1=0;
		int c2=0;
		
		Integer n1=null;
		Integer n2=null;
						
		for (int i = 0; i < nums.length; i++) {
			int cur=nums[i];
			if(n1!=null && cur==n1){
				c1++;
			}
			else if(n2!=null && cur==n2){
				c2++;
			}
			else if(c1==0){
				n1=cur;
				c1=1;
			}
			else if(c2==0){
				n2=cur;
				c2=1;
			}
			else{
				c1--;
				c2--;
			}	
		
		}
		
		c1=c2=0;
		
		for (int i = 0; i < nums.length; i++) {
			if(n1==nums[i])
				c1++;
			else if(n2==nums[i])
				c2++;
		}
		
		if(c1>nums.length/3)
			list.add(n1);
		if(c2>nums.length/3)
			list.add(n2);
		
		return list;
		
	}
	public static void main(String[] args) {
		
		assertThat(new MajorityElementN3().majorityElement(new int[]{0,0,0}), is(Arrays.asList(0)));		
		
		assertThat(new MajorityElementN3().majorityElement(new int[]{1,1,1,3,3,2,2,2}), is(Arrays.asList(1,2)));		
		assertThat(new MajorityElementN3().majorityElement(new int[]{1, 4, 1, 4, 5, 4, 5, 5}), is(Arrays.asList(5,4)));
		assertThat(new MajorityElementN3().majorityElement(new int[]{2,2}), is(Arrays.asList(2)));
		
		System.out.println("All test cases passed");

	}

}
