/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class RegularExpressionMatching {

	boolean regularExpressionMatching(String s, String p) {

		boolean[][] dp=new boolean[s.length()+1][p.length()+1];

		for(int i=0;i<dp.length;i++){

			for(int j=0;j<dp[0].length;j++){
				if(i==0 && j==0){
					dp[i][j]=true;
					continue;
				}
				if(i>0 && j==0){
					dp[i][j]= false;
					continue;
				}
				if(i==0 && j>0){
					if(j>1 && p.charAt(1)=='*')
						dp[i][j]=true;
					continue;
				}
				char curChar=p.charAt(j-1);
				char prevChar=(j>=2)?p.charAt(j-2):0;
				char curTextChar=s.charAt(i-1);
				switch(curChar){
				case '.':
					//return matchUtil(s,p, i-1, j-1);
					dp[i][j]=dp[i-1][j-1];
					break;
				case '*':
					boolean option=false;
					char prev=prevChar;
					int k=i;
					//zero occurance
					//option=option || matchUtil(s,p,i,j-2);
					option=option || dp[i][j-2];
					//one or more occurance while match
					while(k>0 && (s.charAt(k-1)==prev || prev=='.')){
						//option=option || matchUtil(s,p,k-1, j-2);
						option=option || dp[k-1][j-2];
						k--;
					}
					dp[i][j]=option;
					break;
				default:                        
					if(i>0 && curTextChar==curChar &&
					dp[i-1][j-1]) {
						dp[i][j]=true;
					}

				}

			}
		}

		return dp[s.length()][p.length()];
	}


	public static void main(String[] args) {

		assertThat(new RegularExpressionMatching().regularExpressionMatching("aaa", ".*"), is(true));
		assertThat(new RegularExpressionMatching().regularExpressionMatching("bb", "b"), is(false));
		assertThat(new RegularExpressionMatching().regularExpressionMatching("caab", "d*c*x*a*b"), is(true));
		assertThat(new RegularExpressionMatching().regularExpressionMatching("zab", "z.*"), is(true));
		
	}

}
