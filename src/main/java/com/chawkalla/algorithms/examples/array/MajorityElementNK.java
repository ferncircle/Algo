package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * 
 * Given an integer array of size n, find all elements that appear more than  n/3 times. 
 * The algorithm should run in linear time and in O(1) space.
 *
 *Solution: There can only be k-1 such elements. Extend MajorityElementN3 solution to use map 
 */
public class MajorityElementNK {

	public List<Integer> majorityElement(int[] nums, int k) {
		List<Integer> list=new ArrayList<Integer>();
		if(nums.length<k){
			for (int i = 0; i < nums.length; i++) 
				if(!list.contains(nums[i]))
					list.add(nums[i]);				
			return list;
		}
		
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		
		for (int i = 0; i < nums.length; i++) {
			int cur=nums[i];
			if(map.containsKey(cur)){
				map.put(cur, map.get(cur)+1);
			}
			else if(map.size()<k-1){
				map.put(cur, 1);	
			}
			else{
				for (Iterator<Map.Entry<Integer, Integer>> iter=map.entrySet().iterator(); iter.hasNext();) {
					Map.Entry<Integer, Integer> e=iter.next();
					if(e.getValue()==1)
						iter.remove();
					else{
						map.put(e.getKey(), e.getValue()-1);
					}
				}
			}	
		}
		
		for (Map.Entry<Integer, Integer> e:map.entrySet()) {
			map.put(e.getKey(), 0);
		}
		
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])){
				int prevCount=map.get(nums[i]);
				int newCount=prevCount+1;
				map.put(nums[i], newCount);
				
				if(newCount>nums.length/k)
					if(!list.contains(nums[i]))
						list.add(nums[i]);
			}
		}
		
		return list;
	}
	
	
	public static void main(String[] args) {

		assertThat(new MajorityElementNK().majorityElement(new int[]{0,0,0},3), is(Arrays.asList(0)));		
		
		assertThat(new MajorityElementNK().majorityElement(new int[]{1,1,1,3,3,2,2,2},3), is(Arrays.asList(1,2)));		
		assertThat(new MajorityElementNK().majorityElement(new int[]{1, 4, 1, 4, 5, 4, 5, 5},3), is(Arrays.asList(4,5)));
		assertThat(new MajorityElementNK().majorityElement(new int[]{2,2},3), is(Arrays.asList(2)));
		
		System.out.println("All test cases passed");

	}

}
