package com.chawkalla.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class PerfectNumberSquare {

	public static int totalNumberInSumRecursion(int n){
		if(n==1) return 1;
		if(n==2) return 2;
		if(n==3) return 3;
		int min=Integer.MAX_VALUE;
		for(int i=1;i*i<=n;i++){
			if(n==(i*i)) return 1;
			if(n-(i*i)>0){
				int fork=totalNumberInSumRecursion(n-(i*i))+1;
				if(fork<min)
					min=fork;
			}
			
		}
		return min;
	}
	
	public static int totalSumInDP(int n){		
		
		ArrayList<Integer> squares=new ArrayList<Integer>();
		for(int i=1;i*i<=n;i++)
			squares.add(i*i);
		
		//note that recursive solution only has one parameter (n), so only one dimensional array will work
		int[] DP =new int[n+1];
		
		for(int sum=0;sum<=n;sum++){
			DP[sum]=sum;
		}
		
		//using sums as columns
		for(int i=0;i<squares.size();i++){	
			for(int sum=0;sum<=n;sum++){
			
				if(sum>=squares.get(i))
					DP[sum]=Math.min(DP[sum], DP[sum-squares.get(i)]+1);
				
			}
		}
		System.out.println(Arrays.toString(DP));
		
		
		for(int sum=0;sum<=n;sum++){
			DP[sum]=sum;
		}
		
		//using sums as row (more like recursive path: for given sum, use one square at a time and find minimum among them)
		for(int sum=0;sum<=n;sum++){
			for(int i=0;i<squares.size();i++){	
				if(sum>=squares.get(i))
					DP[sum]=Math.min(DP[sum], DP[sum-squares.get(i)]+1);
				
			}
		}
		System.out.println(Arrays.toString(DP));
		return DP[n];
	}
	
	public static void main(String[] args) {
		System.out.println(totalNumberInSumRecursion(12));
		System.out.println(totalSumInDP(12));

	}

}
