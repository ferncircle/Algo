/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/
 * Given an array of size n where all elements are in range from 0 to n-1, change contents of arr[] so that arr[i] = j is changed to arr[j] = i.

Examples:

Example 1:
Input: arr[]  = {1, 3, 0, 2};
Output: arr[] = {2, 0, 3, 1};
Explanation for the above output.
Since arr[0] is 1, arr[1] is changed to 0
Since arr[1] is 3, arr[3] is changed to 1
Since arr[2] is 0, arr[0] is changed to 2
Since arr[3] is 2, arr[2] is changed to 3

Example 2:
Input: arr[]  = {2, 0, 1, 4, 5, 3};
Output: arr[] = {1, 2, 0, 5, 3, 4};

Example 3:
Input: arr[]  = {0, 1, 2, 3};
Output: arr[] = {0, 1, 2, 3};

Example 4:
Input: arr[]  = {3, 2, 1, 0};
Output: arr[] = {3, 2, 1, 0};

Solution: 
1) e.g. {1, 3, 0, 2} think of the chain for index: 0->1->3->2-0. Along the way update the values
2) It fails if there are cycles {2, 0, 1, 4, 5, 3}: chain is 0->2->1->0 and 3->4->5->4
3) change the sign to negative for already processed indexes (increment all items by 1 for handling zero)
4) For each element, if positive, follow the chain and update values
 *
 */
public class RearrangeArrayByIndex {

	public int[] rearrangeWithoutCycles(int input[]) {
		
		int prevIndex=0;
		int currentIndex=input[prevIndex]; 
		int oldVal=input[currentIndex];
		
		while(currentIndex!=0){
			input[currentIndex]=prevIndex;
			
			prevIndex=currentIndex;
			currentIndex=oldVal;
			oldVal=input[currentIndex];
		}
		input[0]=prevIndex;
		
		return input;
	}
	
	public int[] rearrange(int input[]) {
		
		
		
		return input;
	}
	
	public void rearrangeUtil(int input[], int i){
		
		
	}


	public static void main(String[] args) {
		
		assertThat(new RearrangeArrayByIndex().rearrangeWithoutCycles(new int[]{1, 3, 0, 2}), 
				is(new int[]{2, 0, 3, 1}));
		assertThat(new RearrangeArrayByIndex().rearrange(new int[]{2, 0, 1, 4, 5, 3}), 
				is(new int[]{1, 2, 0, 5, 3, 4}));
		
		System.out.println("All test cases passed");

	}

}
