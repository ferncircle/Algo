/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://codefights.com/interview/7pg6yZR4eZH8HAHCD
 * 
 * 


Solution:

1) Do merge sort, during merge step count smaller on right
e.g. Index: 1 2 3		4 5 6
	 		8 5 3		6 4 2
While comparing 8 and 6, since 6 is smaller than 8, we can assume 3 elements(6-4+1) are smaller from right subarray
2) Just ONE step needed to add in it, see below
3) Also, notice the temp array being passed around instead of creating in each function
4) Notice mid=(end+start)/2 instead of (end-start)/2
 *
 */
public class CountInversions {

	int countInversions(int[] nums) {		
		
		int count=mergeUtil(nums, new int[nums.length], 0, nums.length-1);
		
		return count;
	}
	
	public int mergeUtil(final int[] nums, int[] temp, int start, int end){
		if(start>=end)
			return 0;
		
		int mid=start+(end-start)/2;
		int count=0;
		count+=mergeUtil(nums, temp, start, mid);
		count+=mergeUtil(nums, temp, mid+1, end);
		
		int i=start;
		int j=mid+1;
		int tempCounter=start;
		while(i<=mid && j<=end){
			if(nums[i]<nums[j])
				temp[tempCounter++]=nums[j++];			
			else {
				//count+=end-j+1; //ONLY this step needed to be added
				int greaterThan2IIndex=indexGreaterThan2I(nums, mid+1, end, nums[i]);
				if(greaterThan2IIndex>=0)
					count+=end-greaterThan2IIndex+1;
				temp[tempCounter++]=nums[i++];
			}
		}
		while(i<=mid)
			temp[tempCounter++]=nums[i++];
		
		while(j<=end)
			temp[tempCounter++]=nums[j++];
		
		//put sorted temp back into original array
		for (int k = start; k <= end; k++) 
			nums[k]=temp[k];		
		
		return count;
	}
	
	public static int indexGreaterThan2I(int[] a, int start, int end, long value){
		long endValue=a[end];
		if(start==end)
			if(value>2*endValue)
				return end;
			else 
				return -1;
	
		int mid=(start+end)/2;
		long midValue=a[mid];
		if(value<=2*midValue)
			return indexGreaterThan2I(a, mid+1, end, value);
		else
			return indexGreaterThan2I(a, start, mid, value);
		
	}
	
	
	public static void main(String[] args) {

		int[] a=new int[]{2};
		System.out.println(indexGreaterThan2I(a, 0, a.length-1, 5));
		System.out.println(indexGreaterThan2I(new int[]{-5, -5}, 0, 1, -5));
		
		assertThat(new CountInversions().countInversions(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}), 
				is(0));
		
		assertThat(new CountInversions().countInversions(new int[]{-5, -5}), is(1));
		
		assertThat(new CountInversions().countInversions(new int[]{1,3,2,3,1}), is(2));
		
		assertThat(new CountInversions().countInversions(new int[]{2,4,3,5,1}), is(3));
		assertThat(new CountInversions().countInversions(new int[]{5,4,3,2,1}), is(4));
		System.out.println("All cases passed");
	}
	
}
