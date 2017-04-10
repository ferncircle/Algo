/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/interview/9WK3GJepHfDuuKpAx
 * A string is a k-palindrome if it can be transformed into a palindrome by removing any amount of characters 
 * from 0 to k. Your task is to determine whether the given string s is a k-palindrome.

Example

For s = "abrarbra" and k = 1, the output should be
kpalindrome(s, k) = true.

You can remove one letter, 'r', to obtain the string "abrarba", which is a palindrome.

For s = "adbcdbacdb" and k = 2, the output should be
kpalindrome(s, k) = false.

Solution:
1) Find out longest subsequence palindrome possible
2) check if (stringLength-lcs)<=k
 *
 */
public class KPalindrome {

	boolean kpalindrome(String s, int k) {
	    if(s.length()<2) return true;
	    
	    String r=new StringBuffer(s).reverse().toString();
	    
	    int[][] dp=new int[s.length()+1][s.length()+1];
	    
	    
	    for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if(i==0 || j==0){
					dp[i][j]=0;
					continue;
				}
				
				char curS=s.charAt(i-1);
				char curR=r.charAt(j-1);
				
				if(curS==curR){
					dp[i][j]=1+dp[i-1][j-1];
				}else{
					dp[i][j]=Math.max(dp[i-1][j-1], Math.max(dp[i-1][j],dp[i][j-1]));
				}
			}
		}
	    
	    int lcs=dp[s.length()][s.length()];
	    
	    int deleteNeeded=s.length()-lcs;
	    
	    boolean value=(deleteNeeded<=k)?true:false;
	    
	    return value;
	}

	public static void main(String[] args) {

		assertThat(new KPalindrome().kpalindrome("abad",1), is(true));
		System.out.println("all cases passed");
	}

}
