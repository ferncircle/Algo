/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/ones-and-zeroes/#/description
 * 
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings 
consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used
 at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 * 
 *Solution:  Note the optimization of space since we only care about previous array i-1, we create new array each time
 * 
 * 
 *
 */
public class OnesAndZeroes {


	public int findMaxForm(String[] strs, int m, int n) {
		int size=strs.length;
		int[][] dp=new int[m+1][n+1];
		
		for (int i = 0; i < size; i++) {
			int[] c=count(strs[i]);
			
			int[][] newDP=new int[m+1][n+1];
			
			for (int j = 0; j <= m; j++) {
				for (int k = 0; k <=n ; k++) {
					if(j>=c[0] && k>=c[1]){
						newDP[j][k]=Math.max(dp[j][k], 1+dp[j-c[0]][k-c[1]]); //use current item or don't use
					}
					else
						newDP[j][k]=dp[j][k];	 //current item contains more 0's or 1's so, skip it
				}
			}
			
			dp=newDP;
		}
		
		return dp[m][n];

	}
	public int findMaxFormRecur(String[] strs, int m, int n) {

		int a= helper(strs, m, n, strs.length-1);
		
		return a<0?0:a;
	}
	
	public int findMaxForm3(String[] strs, int m, int n) {
	    int[][] dp = new int[m+1][n+1];
	    for (String s : strs) {
	        int[] count = count(s);
	        for (int i=m;i>=count[0];i--) 
	            for (int j=n;j>=count[1];j--) 
	                dp[i][j] = Math.max(1 + dp[i-count[0]][j-count[1]], dp[i][j]);
	    }
	    return dp[m][n];
	}
	
	public int findMaxForm2(String[] strs, int m, int n) {
		int size=strs.length;
		int[][][] dp=new int[size+1][m+1][n+1];
		
		for (int i = 1; i < dp.length; i++) {
			int[] c=count(strs[i-1]);
			for (int j = 0; j <= m; j++) {
				for (int k = 0; k <=n ; k++) {
					if(j>=c[0] && k>=c[1]){
						dp[i][j][k]=Math.max(dp[i][j][k], 1+dp[i-1][j-c[0]][k-c[1]]);
					}else
						dp[i][j][k]=dp[i-1][j][k];
				}
			}
		}
		
		return dp[size][m][n];

	}

	public int helper(String[] strs, int m, int n, int cur){
		if(cur<0 && (m>=0 && n>=0)) 
			return 0;
		if(cur<0 || m<0 || n<0) 
			return Integer.MIN_VALUE;
		int[] c=count(strs[cur]);

		int a=helper(strs, m,n, cur-1);
		int b=1+helper(strs,m-c[0],n-c[1], cur-1 );

		return Math.max(a,b);
	}

	private int[] count(String s){
		int[] c=new int[2];
		if(s.length()==0) return c;
		for(char ch:s.toCharArray())
			if(ch=='0') 
				c[0]++;
			else c[1]++;

		return c;
	}



	public static void main(String[] args) {

		assertThat(new OnesAndZeroes().findMaxForm(new String[]{
				"10", "0001", "111001", "1", "0"
		}, 5, 3), is(4));
		assertThat(new OnesAndZeroes().findMaxForm(new String[]{
				"10", "0", "1"
		}, 1, 1), is(2));
		assertThat(new OnesAndZeroes().findMaxForm(new String[]{
				"10","0001","111001","1","0"
		}, 3, 4), is(3));
		
		System.out.println("all cases passed");
	}

}
