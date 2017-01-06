/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://www.youtube.com/watch?v=S9oUiVYEq7E
 * http://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/
 * 
 * 1) Maintain a sorted numbers(indexes)
 * 2) At each number, 
 * 		a) If higher than last number in sorted array, then just append it by incrementing length
 * 		b) else, find the position of it among the sorted numbers(binary search) and 
 * 			remove the next higher number
 */
public class LongestIncreasingSubSequenceNLogN {

	public int getLongest(int[] num){
		int len=0;
		
		int[] T=new int[num.length];  //will contain sorted numbers
		int[] R=new int[num.length];  //will contain path
		
		for (int i = 0; i < R.length; i++) {
			R[i]=-1;
		}
		
		for (int i = 1; i < num.length; i++) {
			int cur=num[i];
			if(cur<num[T[0]]){ //if cur less than first element in T, then replace the first one
				T[0]=i;
			}
			else if(cur>num[T[len]]){ //if cur greater than last one, then just append it
				len++; //increment the size of subsequence
				T[len]=i;
				R[i]=T[len-1];
			}
			else{ //cur is somewhere in between T, find its position
				
				int pos=findCeiling(num, T, 0, len, cur);
				T[pos]=i; //replace it
				R[i]=T[pos-1];
			}
		}
		
		return len+1;
	}
	
	public int findCeiling(int[] num, int[] T, int l, int h, int number){
		int pos=-1;
		
		while(l<=h){
			int mid=(l+h)/2;
			if(num[T[mid]] < number && number <= num[T[mid+1]]){
				pos=mid+1;
				break;
			}
			else if(num[T[mid]] > number)
				h=mid-1;
			else
				l=mid+1;
		}
		return pos;
	}
	
	public static void main(String[] args) {
		
		assertThat(new LongestIncreasingSubSequenceNLogN().getLongest(new int[]{3, 4, 1, 5, 8, 2, 3, 12, 7, 9, 10}), 
				is(6));
		System.out.println("All test cases passed");
	}

}
