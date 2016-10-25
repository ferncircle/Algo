package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/challenge/Qjts7cukDvYpDW4Bc
 * 
 * The difference between two sequences of the same length a1, a2, a3,..., an and b1, b2, b3,..., bn can be defined as the sum of absolute differences between their respective elements:

diff(a, b) = |a1 - b1| + |a2 - b2| + ... + |an - bn|.

For the given sequences a and b (not necessarily having the same lengths) find a subsequence b' of b such that diff(a, b') is minimal. Return this difference.

Example

For a = [1, 2, 6] and b = [0, 1, 3, 4, 5], the output should be
closestSequence2(a, b) = 2.

The best subsequence will be b' = [1, 3, 5] which has a difference of 2 with a.
 *
 */
public class ClosestSequence {

	public int closestSequence2(int[] a, int[] b){
		
		if(a==null || b==null)
			return 0;
		
		int min=0;
		int max=Integer.MAX_VALUE;
		int[][] dp=new int[a.length+1][b.length+1];
		
		for (int i = 0; i < a.length+1; i++) {			
			for (int j = 0; j < b.length+1; j++) {
				if(i==0 || j==0){
					dp[i][j]=0;
				}
				else if(j<i){
					dp[i][j]=max;
				}
				else{
					dp[i][j]=max;
					for (int k = i; k <= j; k++) {
						int candidate=dp[i-1][k-1]+Math.abs(a[i-1]-b[k-1]);
						dp[i][j]=Math.min(dp[i][j], candidate);
					}
				}
					
			}
		}
		min=dp[a.length][b.length];
				
		return min;
		
	}
	public static void main(String[] args) {
		
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{1, 4, 6, 3}, 
				new int[]{2, 5, 9, 6, 4, 5, 1}), 
				is(3));
		
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{1, 2, 6}, 
				new int[]{0, 1, 3, 4, 5}), 
				is(2));
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{1, 2, 1, 2, 1, 2}, 
				new int[]{3, 0, 0, 3, 0, 3, 3, 0, 0}), 
				is(7));
		
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{1, 1, 1, 1, 1, 1}, 
				new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}), 
				is(0));
		
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{13, 5, 3, -1, -9, 20, 5, -17, 20, -11, -6, 1, 17, 18, 20, -6, 11, 12, 3, -8}, 
				new int[]{1, 1, -18, -3, -9, 16, 5, 13, -2, 4, -9, -16, -20, 13, -3, 10, 20, -5, -20, 2}), 
				is(270));
		
		assertThat(new ClosestSequence().closestSequence2(
				new int[]{-26, -35, 44, 23, 7, -40, -14, 18, 39, -12, -22, -5, 4, 10, 0, -11, 45, -16, 2, 46, -45, 2, -3, -50, -17, 49, 47, -15, 49, -15, 16, 43, 33, 22, -34, 48, -41, 12, 19, -17, 31, -46, 38, -21, 16, 3, -43, -50, 4, 7}, 
				new int[]{18, 16, -22, 4, -5, -46, -43, 28, 50, -47, 31, -41, 35, -6, -20, -33, 10, 34, -7, -46, 0, 35, 29, 22, 19, -48, -4, 10, -41, 26, -33, 45, -2, 24, 4, 39, -2, -42, 41, 18, -28, 28, -44, 19, 34, 41, 33, -27, -26, 41}), 
				is(1928));
		

		System.out.println("All test cases passed");
	}

}
