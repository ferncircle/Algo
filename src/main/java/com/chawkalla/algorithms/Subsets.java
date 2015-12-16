package com.chawkalla.algorithms;

public class Subsets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] coins={1,2,3,4,5};
		
		System.out.println(getSubsets(coins, 10));

	}

	public static int getSubsets(int[] a, int sum){
		int[][] dp=new int[a.length][sum+1];
		
		
		for(int i=1;i<a.length;i++){
			for(int j=1;j<=sum;j++){				
				if((a[i]<=j)){
					int previousSet=j-a[i];
					if(previousSet>=0){
						int x=dp[i-1][j-a[i]];
						
						int y=dp[i-1][j];

						dp[i][j]=x+y;
					}
					
				}
					
				
			}
		}
		
		return dp[a.length][sum];
	}
}
