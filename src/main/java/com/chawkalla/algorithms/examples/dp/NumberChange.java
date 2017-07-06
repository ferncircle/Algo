/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

/**
 * @author sfargose
 *
 */
public class NumberChange {

	int fewestNumberChange(int[] change, int[] count, int target){
		
		//flatten 2-d {change,coin} to 1-d array coins
		ArrayList<Integer> coins=new ArrayList<Integer>();
		for (int i = 0; i < change.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				coins.add(change[i]);
			}
		}
		
		int[][] dp=new int[coins.size()+1][target+1];
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if(j==0)//sum is zero, min=0
					continue;
				if(i==0){ //no coins, assign to max number
					dp[i][j]=100000000;
					continue;
				}
				int curCoin=coins.get(i-1);
				dp[i][j]=dp[i-1][j];
				if(j>=curCoin)
					dp[i][j]=Math.min(dp[i][j], 1+dp[i-1][j-curCoin]);				
			}
			
		}
		return dp[coins.size()][target];
	}
	
	public static void main(String[] args) {
		
		assertThat(new NumberChange().fewestNumberChange(new int[]{25,10,1}, new int[]{3,10,20}, 40), is(4));
		assertThat(new NumberChange().fewestNumberChange(new int[]{25,10,1}, new int[]{3,10,20}, 23), is(5));
		assertThat(new NumberChange().fewestNumberChange(new int[]{25,10,1}, new int[]{3,10,20}, 20), is(2));
		assertThat(new NumberChange().fewestNumberChange(new int[]{25,10,1}, new int[]{3,10,20}, 51), is(3));
		assertThat(new NumberChange().fewestNumberChange(new int[]{25,10,1}, new int[]{3,10,20}, 100), is(7));
		System.out.println("all cases passed");
	}

}
