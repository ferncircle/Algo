/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i-1][j-2]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
 *
 */
public class RegularExpressionMatching {

	public boolean isMatch(String s, String p) {

		boolean[][] dp=new boolean[s.length()+1][p.length()+1];
		
		for(int i=0;i<dp.length;i++){
			for(int j=0;j<dp[0].length;j++){
				if(i==0 && j==0){
					dp[i][j]=true;
					continue;
				}
				if(j==0)
					continue;
				if(i==0){
					if(p.charAt(j-1)=='*')
						dp[i][j]=dp[i][j-2];
					continue;
				}

				if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.'){
					dp[i][j]=dp[i-1][j-1];
				}else{
					if(p.charAt(j-1)=='*'){
						if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
							dp[i][j]=dp[i-1][j] //a* is multiple of a
									|| dp[i-1][j-2] // take both a and a*
											|| dp[i][j-2]; //a* is empty
						}else{
							dp[i][j]=dp[i][j-2];
						}
					}
				}

			}
		}

		return dp[s.length()][p.length()];
	}



	public static void main(String[] args) {

		assertThat(new RegularExpressionMatching().isMatch("", "a*"), is(true));

		assertThat(new RegularExpressionMatching().isMatch("aaa", ".*"), is(true));
		assertThat(new RegularExpressionMatching().isMatch("bb", "b"), is(false));
		assertThat(new RegularExpressionMatching().isMatch("caab", "d*c*x*a*b"), is(true));
		assertThat(new RegularExpressionMatching().isMatch("zab", "z.*"), is(true));
		System.out.println("all cases passed");
	}

	
}
