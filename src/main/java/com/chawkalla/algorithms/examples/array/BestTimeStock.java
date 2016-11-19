package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 */
public class BestTimeStock {

	public int maxProfit(int[] prices) {
		int profit=0;
		if(prices.length<2)
			return profit;
		
		int[] maxProfitFromRight=new int[prices.length];
		int maxPrice=prices[prices.length-1];
		int maxProfitOnRight=0;
		
		for (int i = prices.length-2; i>=0; i--) {
			maxProfitFromRight[i]=maxProfitOnRight;
			
			int cur=prices[i];			
			if(cur>maxPrice){
				maxPrice=cur;
			}else{
				int localProfit=maxPrice-cur;
				if(localProfit>maxProfitOnRight)
					maxProfitOnRight=localProfit;				
			}	
			
		}
		
		int minPrice=prices[0];		
		for (int i = 1; i < prices.length; i++) {
			int cur=prices[i];			
			
			if(cur<minPrice){
				minPrice=cur;
			}else{
				int localProfit=cur-minPrice;
				int rightProfit=maxProfitFromRight[i];
				int totalCurrentProfit=localProfit+rightProfit;
				if(totalCurrentProfit>profit)
					profit=totalCurrentProfit;
			}
		}
		
		return profit;
		
	}
	
	
	public static void main(String[] args) {

		assertThat(new BestTimeStock().maxProfit(new int[]{2, 5, 4, 1, 5, 2, 3}), is(7));
		assertThat(new BestTimeStock().maxProfit(new int[]{2, 5}), is(3));
		assertThat(new BestTimeStock().maxProfit(new int[]{1, 2, 5}), is(4));
		
		System.out.println("All test cases passed");

	}

}
