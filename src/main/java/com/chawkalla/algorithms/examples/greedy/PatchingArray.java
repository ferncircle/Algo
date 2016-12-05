/**
 * 
 */
package com.chawkalla.algorithms.examples.greedy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/patching-array/
 *Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Solution: http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
1) go through each number, check if current number value is less than or equal to sum so far. 
If not, then add the sum so far to the array (that's the number to be inserted).

 */
public class PatchingArray {

	public int minPatches(int[] nums, int n) {
		int totalItemsInserted=0;

		long sum=1; //add 1 to sum at start
		int i=0;
		while(sum<=n){
			if(i==nums.length || nums[i]>sum){				
				long newValueToBeInserted=sum;
				sum+=newValueToBeInserted;
				//System.out.println("inserting item "+newValueToBeInserted);
				totalItemsInserted++;											
			}else{
				sum+=nums[i];
				i++;
			}
		}

		return totalItemsInserted;
	}


	public static void main(String[] args) {

		assertThat(new PatchingArray().minPatches(new int[]{1,2,31,33}, 2147483647), is(28));
		assertThat(new PatchingArray().minPatches(new int[]{}, 8), is(4));
		assertThat(new PatchingArray().minPatches(new int[]{}, 10), is(4));
		assertThat(new PatchingArray().minPatches(new int[]{5}, 10), is(3));
		assertThat(new PatchingArray().minPatches(new int[]{1, 5, 10}, 20), is(2));
		assertThat(new PatchingArray().minPatches(new int[]{1, 3}, 6), is(1));
		assertThat(new PatchingArray().minPatches(new int[]{1, 2, 2}, 5), is(0));
		assertThat(new PatchingArray().minPatches(new int[]{}, 0), is(0));

		System.out.println("All test cases passed");
	}

}
