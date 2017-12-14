/**
 * 
 */
package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.interviewbit.com/problems/search-for-a-range/
 *
 *Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm’s runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example:

Given [5, 7, 7, 8, 8, 10]

and target value 8,

return [3, 4].
 */
public class SearchRangeForDuplicate {

	public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
		int lower=-1;
		int higher=-1;
		
		//lower
		int s=0,e=a.size()-1;
		//5, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8
		while(s<=e){
			int mid=(s+e)/2;
			if(a.get(mid)==b){
				lower=mid;
				e=mid-1;
			}
			else if(a.get(mid)<b){
				s=mid+1;
			}else{
				e=mid-1;
			}
		}
		
		//higher
		s=0;e=a.size()-1;
		while(s<=e){
			int mid=(s+e)/2;
			if(a.get(mid)==b){
				higher=mid;
				s=mid+1;
			}
			else if(a.get(mid)<b){
				s=mid+1;
			}else{
				e=mid-1;
			}
		}
		
		return new ArrayList<Integer>(Arrays.asList(new Integer[]{lower,higher}));
	}
	
	public ArrayList<Integer> searchRange1(final List<Integer> a, int b) {
		int lower=-1;
		int higher=-1;
		ArrayList<Integer> res=new ArrayList<Integer>();
		res.add(lower);
		res.add(higher);
		
		//e.g. 5, 7, 7, 8, 8, 10
		int ls=0,le=0,hs=0,he=a.size()-1, s=0, e=a.size()-1;
		
		while(s<=e){			
			int mid=(s+e)/2;
			if(a.get(mid)==b){
				le=mid;
				hs=mid;
				break;
			}else if(a.get(mid)>b){
				e=mid-1;
			}else{
				s=mid+1;
			}
		}
		if(s>e)
			return res;	
		
		while(ls<=le){
			
			int mid=(ls+le)/2;
			if(a.get(mid)==b){
				le=mid-1;
			}else if(a.get(mid)<b){
				ls=mid+1;
			}
		}
		lower=ls;

		while(hs<=he){	
			int mid=(hs+he)/2;
			if(a.get(mid)==b){
				hs=mid+1;
			}else if(a.get(mid)>b){
				he=mid-1;
			}
		}
		higher=he;
		
		res.set(0, lower);
		res.set(1, higher);
		return res;

	}
	
	


	public static void main(String[] args) {
		assertThat(new SearchRangeForDuplicate().searchRange1 (Arrays.asList(new Integer[]{5, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8}),8), is(new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 11}))));
		
		
		assertThat(new SearchRangeForDuplicate().searchRange1(
				IntStream.of(new int[]{5, 7, 7, 8, 8, 10}).boxed().collect(Collectors.toList()),
				8), is(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,4}))));

		
		assertThat(new SearchRangeForDuplicate().searchRange1(
				IntStream.of(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10}).boxed().collect(Collectors.toList()),
				7), is(new ArrayList<Integer>(Arrays.asList(new Integer[]{25,25}))));


		assertThat(new SearchRangeForDuplicate().searchRange1 (Arrays.asList(new Integer[]{5, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8}),8), is(new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 11}))));
		
		System.out.println("all cases passed");


	}

}
