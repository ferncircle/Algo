/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Deque;
import java.util.LinkedList;

/**
 *  * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.

Examples:

Input :
arr[] = {1, 3, 1, 2, 1, 5, 2, 3, 6}
k = 3
Output :
3 3 2 5 5 5 6

Input :
arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
k = 4
Output :
10 10 10 15 15 90 90

Solution:
1) Maintain a queue of size k that is always in descending order
2) At each element, remove all elements at the tail of queue that are less than current one and then insert current
3) The start of queue will be max for that range
 *
 */
public class MaximumOfSubArraySizeK {

	public int[] maxSubArray(int input[], int k) {
		int[] output=new int[input.length-k+1];

		Deque<Integer> queue=new LinkedList<Integer>();

		for (int i = 0; i < k; i++) {
			updateQueue(input, queue, i);
		}
		output[0]=input[queue.peekFirst()];
		int outputIndex=1;
		for (int i = k; i < input.length; i++) {
			if(i-queue.peekFirst()>=k) //remove first element in queue if it's outside the range
				queue.pollFirst();
			updateQueue(input, queue, i);
			output[outputIndex++]=input[queue.peekFirst()];			
		}
		return output;
	}

	private void updateQueue(int input[], Deque<Integer> queue, int index){
		if(!queue.isEmpty()){
			while(!queue.isEmpty() && input[queue.peekLast()]<=input[index])
				queue.pollLast();			 
		}
		queue.offerLast(index);			 

	}
	public static void main(String[] args) {

		assertThat(new MaximumOfSubArraySizeK().maxSubArray(new int[]{1, 3, 1, 2, 1, 5, 2, 3, 6}, 3), 
				is(new int[]{3, 3, 2, 5, 5, 5, 6}));
		assertThat(new MaximumOfSubArraySizeK().maxSubArray(new int[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, 4), 
				is(new int[]{10, 10, 10, 15, 15, 90, 90}));
		System.out.println("all test cases passed");
	}

}
