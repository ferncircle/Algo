package com.google.challenges;

import java.util.HashMap;

public class Answer31 {

	public static HashMap<String, Integer> cache=new HashMap<String, Integer>();
	public static int answer(int n){
		return sumToNum(n, 0);
	}

	public static int sumToNum(int n,int start ){
		int count=0;

		if(start>=n/2)
			return 0;
		if(n==2 || n==3 || n==4)
			return 1;
		String key=""+start+":"+n;
		if(cache.containsKey(key))
			return cache.get(key);
		for(int j=start+1;j<=n/2;j++){
			if(j<(n-j)){
				count=count+(1+sumToNum(n-j,j));
			}

		}	
		cache.put(key, count);
		return count;
	}

	public static int answerDP(int n){
		int count=0;

		if(n==2 || n==3 || n==4)
			return 1;
		
		int[][] a=new int[n+1][n+1];
		
		for(int i=0;i<(n+1);i++){
			
			for(int j=0;j<(n+1);j++){
				if(j>=i/2){
					a[i][j]=0;
					continue;
				}
				if(i==2 || i==3 || i==4){
					a[i][j]=1;
					continue;
				}
				for(int k=j+1;k<=i/2;k++){
					if(k<(i-k)){
						a[i][j]=a[i][j]+1+a[i-k][k];
					}
				}
				
					
			}
		}
		count=a[n][0];
		return count;
	}
	public static void main(String[] args) {

		long time=System.currentTimeMillis();
		System.out.println(answer(600)+" & time taken="+(System.currentTimeMillis()-time));
		time=System.currentTimeMillis();
		System.out.println(answerDP(600)+" & time taken="+(System.currentTimeMillis()-time));

	}

}
