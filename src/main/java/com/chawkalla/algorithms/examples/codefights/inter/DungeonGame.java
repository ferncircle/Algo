/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class DungeonGame {

	int dungeonGame(int[][] dungeon) {

	    int minNeeded=1;
	    int[][] dp=new int[dungeon.length][dungeon[0].length];
	    int m=dp.length-1;
	    int n=dp[0].length-1;
	    
	    dp[m][n]=Math.max(minNeeded, minNeeded-dungeon[m][n]);
	    //last row
	    for(int i=n-1;i>=0;i--)
	        dp[m][i]=Math.max(minNeeded, dp[m][i+1]-dungeon[m][i]);
	    
	    //last col
	    for(int i=m-1;i>=0;i--)
	        dp[i][n]=Math.max(minNeeded, dp[i+1][n]-dungeon[i][n]);
	    
	    for(int i=m-1;i>=0;i--){
	        for(int j=n-1;j>=0;j--){
	            
	            dp[i][j]=Math.min(Math.max(dp[i + 1][j] - dungeon[i][j], minNeeded), 
									Math.max(dp[i][j + 1] - dungeon[i][j], minNeeded));
	        }
	    }
	    
	    return dp[0][0];
	}


	public static void main(String[] args) {

		assertThat(new DungeonGame().dungeonGame(new int[][]{
			{-2,-3,4}, 
			{-6,-15,0}, 
			{10,25,-6}}), is(8));
		System.out.println("all cases passed");
	}

}
