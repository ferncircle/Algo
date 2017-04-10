/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/interview/bF6Tr6FdRiJSB5qqB
 * 
 * You have array of integers nums and you need to return a new counts array. In the new counts array, counts[i] is 
 * the number of smaller elements to the right of nums[i].

Example

For nums = [3, 8, 4, 1], the output should be
countSmallerToTheRight(nums) = [1, 2, 1, 0].

To the right of 3: there is 1 smaller element (1).
To the right of 8: there are 2 smaller elements (4 and 1).
To the right of 4: there is 1 smaller element (1).
To the right of 1: there are 0 smaller elements.
The resulting array is [1, 2, 1, 0].


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
public class CountSmallerToRight {

	int[] countSmallerToTheRight(int[] nums) {
		int[] smaller=new int[nums.length];
		int[] index=new int[nums.length];
		
		for (int i = 0; i < nums.length; i++)
			index[i]=i;		
		
		mergeUtil(nums, index, new int[nums.length], smaller, 0, nums.length-1);
		
		return smaller;
	}
	
	public void mergeUtil(final int[] nums, int[] index, int[] temp, int[] smaller, int start, int end){
		if(start>=end)
			return;
		
		int mid=(start+end)/2;
		
		mergeUtil(nums, index, temp, smaller, start, mid);
		mergeUtil(nums, index, temp, smaller, mid+1, end);
		
		int i=start;
		int j=mid+1;
		int tempCounter=start;
		while(i<=mid && j<=end){
			if(nums[index[i]]<=nums[index[j]])
				temp[tempCounter++]=index[j++];			
			else {
				smaller[index[i]]+=end-j+1; //ONLY this step needed to be added
				temp[tempCounter++]=index[i++];
			}
		}
		while(i<=mid)
			temp[tempCounter++]=index[i++];
		
		while(j<=end)
			temp[tempCounter++]=index[j++];
		
		//put sorted temp back into original array
		for (int k = start; k <= end; k++) 
			index[k]=temp[k];		
	}
	
	
	public static void main(String[] args) {

		//new CountSmallerToRight().countSmallerToTheRight(new int[]{5, 3, 2, 6});
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{3, 8, 4, 1}),
				is(new int[]{1, 2, 1, 0}));
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{}),
				is(new int[]{}));
		
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{-1}),
				is(new int[]{0}));
		
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{-1, -2}),
				is(new int[]{1, 0}));
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{0, 2, 1}),
				is(new int[]{0, 1, 0}));
		
		
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{1, 0, 2}),
				is(new int[]{1, 0, 0}));
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{1, 0, 2 ,-1}),
				is(new int[]{2, 1, 1, 0}));
		assertThat(new CountSmallerToRight().countSmallerToTheRight(new int[]{5, 3, 2, 4, 4, 35, 1, 14, 38, 35, 2}),
				is(new int[]{6, 3, 1, 2, 2, 3, 0, 1, 2, 1, 0}));
		
		System.out.println("All cases passed");
	}
	
}
