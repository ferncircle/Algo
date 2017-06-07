/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author sfargose
 *
 */
public class Combination {

	public static long nCk(int n, int k){
		if(k==0 || n==k) return 1;
		
		if(k> n-k)
			k=n-k;
		
		long count=n;
		
		for (int i = 1; i <k; i++) {
			count=count*(n-i);
			count=count/(i+1);
		}
		
		return count;
		
	}
	
	public static long nCkDP(long n, long k){
	    if(n < k)
	        return 0;
	    if(k == 0 || k == n)
	        return 1;	    
	    long[][] dp=new long[(int)n+1][(int)k+1];
	    
	    for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if(j==0 || i==j){
					dp[i][j]=1;
					continue;
				}
				if(i==0 || i<j){
					dp[i][j]=0;
					continue;
				}
				
				dp[i][j]=dp[i-1][j-1];//choose current j, so total items(n) left will be n-1 and k will be k-1
				if((i-j)>=0)
					dp[i][j]+=dp[i-1][j];//don't choose current j
			}
		}
	    
	    return dp[(int)n][(int)k];
	    
	}
	
	public static void main(String[] args) {
		int n=4;
		int k=3;

		System.out.println(nCk(n, k));
		assertThat(nCk(n, k), is(nCkDP(n, k)));
		System.out.println("all cases passed");
	}

}
