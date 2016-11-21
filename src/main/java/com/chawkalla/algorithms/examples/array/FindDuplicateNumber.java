package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

 * 
 * Solution: Think of array as linkedlist where index is node and value is pointer to next node. In case of duplicate number, it'll create a cycle
 * in the list. Then problem reduces to finding loop in linkedlist
 *Note this works because all values are within the size of array.
 */
public class FindDuplicateNumber {

	public int findDuplicate(int[] nums) {
		if(nums.length<3)
			return nums[0];
		int dup=0;
		
		int slowPtr=nums[0];//node.next
		int fastPtr=nums[nums[0]];//node.next.next
		while(slowPtr!=fastPtr){
			slowPtr=nums[slowPtr]; //slow.next
			fastPtr=nums[nums[fastPtr]]; //fast.next.next
		}
		
		fastPtr=0;
		while(slowPtr!=fastPtr){
			slowPtr=nums[slowPtr]; //slow.next
			fastPtr=nums[fastPtr]; //fast.next
		}
		
		dup=slowPtr;
		
		return dup;
	}

	public static void main(String[] args) {
		
		assertThat(new FindDuplicateNumber().findDuplicate(new int[]{3, 1, 2, 4, 3, 5}), is(3));
		assertThat(new FindDuplicateNumber().findDuplicate(new int[]{1, 2, 4, 2, 2}), is(2));
		
		System.out.println("all test cases passed");

	}

}
