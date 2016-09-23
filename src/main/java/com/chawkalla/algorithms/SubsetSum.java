package com.chawkalla.algorithms;

import java.util.Arrays;

public class SubsetSum {

	public static void main(String[] args) {
		int[] A={4,2,7,1};

		System.out.println("Recursion="+isSum(A, 7, A.length-1)+" and DP="+isSumDP(A, 7));
		System.out.println("Recursion="+isSum(A, 8, A.length-1)+" and DP="+isSumDP(A, 8));
		System.out.println("Recursion="+isSum(A, 18, A.length-1)+" and DP="+isSumDP(A, 18));
		System.out.println("Recursion="+isSum(A, 13, A.length-1)+" and DP="+isSumDP(A, 13));
		
		System.out.println();
		System.out.println();
		System.out.println(isSumWithOnly(A, 3, A.length-1, 1));
		
	}

	public static boolean isSum(int[] A, int sum, int n){

		boolean isPossible=false;
		if(sum==0) return true;
		if(n<0) return false;

		//include current element
		boolean route1=isSum(A, sum-A[n], n-1);
		//don't include current
		boolean route2=isSum(A, sum, n-1);
		isPossible=route1 || route2;

		return isPossible;
	}
	
	//you need two dimentional array since you can use one element at most once and you need to keep track if the element is already used or not
	public static boolean isSumDP(int[] A, int sum){
		boolean isPossible=false;		
		boolean[][] dp=new boolean[sum+1][A.length+1];
		
		//notice how it matches with recursion termination condition
		
		//sum 0, answer is true
		for(int i=0;i<A.length;i++){	
			dp[0][i]=true;
		}
		
		//emtpy set
		for(int i=1;i<sum;i++){	
			dp[i][0]=false;
		}
		
		for(int i=1;i<=sum;i++){
			
			for(int j=1;j<=A.length;j++){
				if(i-A[j-1]>=0)
					dp[i][j]=dp[i][j-1] || dp[i-A[j-1]][j-1];
			}
		}
		
		PrimeSubsets.printBooleanMatrix(dp, sum+1, A.length+1);
		
		isPossible=dp[sum][A.length];
		return isPossible;
	}
	
	
	//This algorithm fails because it only uses one dimentional array, that doesn't keep track of if the element is already used or not.
	//use one dimentional array if you are allowed to use same element again and again
	public static boolean isSumDPOneDimentional(int[] A, int sum){
		boolean isPossible=false;
		
		boolean[] dp=new boolean[sum+1];
		
		for(int i=0;i<A.length;i++){	
			dp[A[i]]=true;
		}
		System.out.println(Arrays.toString(dp));
		for(int i=0;i<=sum;i++){
			System.out.println("processing sum "+i);
			for(int j=0;j<A.length;j++){
				if(i-A[j]>=0)
					dp[i]=dp[i] || dp[i-A[j]];
			}
		}
		System.out.println(Arrays.toString(dp));
		
		isPossible=dp[sum];
		return isPossible;
	}

	public static boolean isSumWithOnly(int[] A, int sum, int current, int remaining){

		boolean isPossible=false;

		if(sum==0) return true;
		if(remaining<=0) return false;
		if(current<0) return false;

		//include current element
		boolean route1=isSumWithOnly(A, sum-A[current], current-1, remaining-1);

		//don't include current
		boolean route2=isSumWithOnly(A, sum, current-1, remaining);

		isPossible=route1 || route2;


		return isPossible;
	}

}
