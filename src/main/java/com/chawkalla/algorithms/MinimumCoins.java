package com.chawkalla.algorithms;

public class MinimumCoins {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] coins={1,2, 3,4, 5};
		
		System.out.println(minimumCoins(coins, 10));

	}

	public static int minimumCoins(int[] coins, int sum){
		
		int[] min=new int[sum+1];
		for(int j=1;j<=sum;j++){
			min[j]=1000000;
		}
		min[0]=0;
		for(int i=0;i<coins.length;i++){
			for(int j=1;j<=sum;j++){				
				if((coins[i]<=j)){
					int earlierCoinMin=min[j-coins[i]];
					int newMin=earlierCoinMin+1;
					int curMin=min[j];
					if(newMin<curMin){
						min[j]=newMin;	
					}
				}
					
				
			}
		}
		
		return min[sum];
	}
}
