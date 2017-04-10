/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

/**
 *https://codefights.com/interview/ccHxGiEhZmbyycSNu
 *
 *For coins = [10, 50, 100] and quantity = [1, 2, 1], the output should be
possibleSums(coins, quantity) = 9.

Here are all the possible sums:

50 = 50;
10 + 50 = 60;
50 + 100 = 150;
10 + 50 + 100 = 160;
50 + 50 = 100;
10 + 50 + 50 = 110;
50 + 50 + 100 = 200;
10 + 50 + 50 + 100 = 210;
10 = 10;
100 = 100;
10 + 100 = 110.
As you can see, there are 9 distinct sums that can be created from non-empty groupings of your coins.
 *
 */
public class DistinctPossibleSums {
	int count=0;
	int possibleSumsRec(int[] coins, int[] quantity) {
		int sum=0;
		HashSet<Integer> set= new HashSet<Integer>();
		
		sumUtil(coins, quantity, coins.length-1, set, 0);
		sum=set.size()-1;
		System.out.println("count="+count);
		return sum;
	}
	
	public void sumUtil(int[] coins, int[] quantity, int pos, HashSet<Integer> set, int sum){
		set.add(sum);
		if(pos<0){
			count++;
			return;
		}
		
		for (int i = 0; i <= quantity[pos]; i++) {
			sumUtil(coins, quantity, pos-1, set, sum+(i*coins[pos]));
		}		
	}

	int possibleSumsDP(int[] coins, int[] quantity) {

	    int totalSum=0;
	    
	    HashSet<Integer> set=new HashSet<Integer>();
	    for(int i=0;i<coins.length;i++)
	        totalSum+=coins[i]*quantity[i];
	    
	    boolean[][] dp=new boolean[coins.length+1][totalSum+1];
	    
	    for(int i=0;i<dp.length;i++){
	        
	        for(int j=0;j<dp[0].length;j++){
	            if(i==0 && j==0){
	                dp[i][j]=true;
	                continue;
	            }
	            if(i==0){
	                dp[i][j]=false;
	                continue;
	            }
	            if(j==0){
	                dp[i][j]=true;
	                continue;
	            }              
	            int curCoin=coins[i-1];
	            int curQuantity=quantity[i-1];
	            int curSum=j;
	            
	            for(int k=0;k<=curQuantity;k++){
	                if(curSum-k*curCoin >=0)
	                    dp[i][j]=dp[i-1][curSum-k*curCoin];
	            }
	            if(dp[i][j])
	                set.add(j);
	        }
	    }
	    
	    return set.size();
	}

	
	public static void main(String[] args) {
		
		assertThat(new DistinctPossibleSums().possibleSumsRec(new int[]{10, 50, 100}, new int[]{1, 2, 1}), is(9));		
		assertThat(new DistinctPossibleSums().possibleSumsDP(new int[]{10, 50, 100, 500}, new int[]{5, 3, 2, 2}), is(122));
		
		long before=System.currentTimeMillis();
		assertThat(new DistinctPossibleSums().possibleSumsRec(new int[]{1, 2}, new int[]{50000, 2}), is(50004));
		
		
		System.out.println("Time="+(System.currentTimeMillis()-before));
		System.out.println("All cases passed");

	}

}
