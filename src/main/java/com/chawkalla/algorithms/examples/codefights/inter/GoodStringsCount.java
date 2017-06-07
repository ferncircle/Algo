/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 
 * https://codefights.com/interview/vzzkv5NHFMELWSwAS/description
 * 
 * 
 * Given an integer len, count the number of different good strings that have a length of exactly len. A good string is
 *  a string for which the following conditions are true:

A good string contains only lowercase English letters.
Each character in a good string is unique.
Exactly one character in a good string is lexicographically greater than the character that precedes it.
Example

For len = 2, the output should be
goodStringsCount(len) = 325.

If the first symbol is 'a', there are 25 possible good strings: "ab", "ac", ...
If the first symbol is 'b', there are 24 possible good strings: "bc", "bd", ...
...
If the first symbol is 'z', there are 0 possible good strings because there is no character that is lexicographically greater.
There are 25 + 24 + 23 + ... + 0 = 325 good strings that have a length of 2.

For len = 1, the output should be
goodStringsCount(len) = 0.

The 3rd rule for good strings can't be true if there is only one character in the string.

Solution: http://blog.codefights.com/goodstringscount-solution/

 *
 */
public class GoodStringsCount {


	int CHARS=26;
	
	int goodStringsCount(int len) {
		//choose len chars out of 26		
		int a=choose(26, len);
		
		int m=0;
		for(int i=1;i<=len;i++){
			//System.out.println("choosing "+i+" out of "+len);
			long c=chooseDP(len, i);//choose i out of len
			m+=(c-1);
		}
		
		int total=a*m;
		
		return total;
	}
	
	int goodStringsCountRec(int len) {

		int total=goodStringUtil(len, 0, -1, false, new boolean[26]);
		return total;
	}

	
	public int goodStringUtil(final int len, int pos, int prevChar, boolean greaterUsed, 
			boolean[] used){
		
		if(pos==len){
			if(greaterUsed){
				return 1;
			}
			return 0;
		}	
		int total=0;
		if(prevChar==-1){ //for first position
			for (int i = 0; i < CHARS; i++) {
				used[i]=true;
				total+=goodStringUtil(len, pos+1, i, false, used);
				used[i]=false;
			}
		}else{

			if(!greaterUsed){
				for(int i=prevChar+1;i<CHARS;i++){
					if(!used[i]){
						used[i]=true;
						total+=goodStringUtil(len, pos+1, i, true, used);
						used[i]=false;
					}

				}
				
				for (int i = 0; i <= prevChar; i++) {
					if(!used[i]){
						used[i]=true;
						total+=goodStringUtil(len, pos+1, i, false, used);
						used[i]=false;
					}
				}
			}else{
				for (int i = 0; i <= prevChar; i++) {						
					if(!used[i]){
						used[i]=true;
						total+=goodStringUtil(len, pos+1, i, true, used);
						used[i]=false;
					}
					
				}
			}
			
		}
		return total;
	}
	
	public static long choose(long total, long choose){
	    if(total < choose)
	        return 0;
	    if(choose == 0 || choose == total)
	        return 1;
	    return choose(total-1,choose-1)+choose(total-1,choose);
	}
	

	public static int choose(int a, int b) {
	    if (a < b)
	        return 0;
	    int res = 1;
	    for (int i = 0; i < b; ++i){
	        res *= a-i;
	        res /= i+1;
	    }
	    return res;
	}
	
	public static long chooseDP(long n, long k){
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
		
		int len=5;
		System.out.println(new GoodStringsCount().goodStringsCountRec(len));
		/*System.out.println(new GoodStringsCount().goodStringsCount(3)/chooseDP(23, 3));		
		System.out.println(new GoodStringsCount().goodStringsCount(4)/chooseDP(26, 3));
		System.out.println(new GoodStringsCount().goodStringsCount(5)/chooseDP(26, 4));
		*/
		//System.out.println(new GoodStringsCount().goodStringsCount1(len));
		
		assertThat(new GoodStringsCount().goodStringsCount(3), is(10400));
		assertThat(new GoodStringsCount().goodStringsCount(4), is(164450));
		assertThat(new GoodStringsCount().goodStringsCount(5), is(1710280));
		
		assertThat(new GoodStringsCount().goodStringsCount(6), is(13123110));
		
		assertThat(new GoodStringsCount().goodStringsCount(7), is(78936000));
		assertThat(new GoodStringsCount().goodStringsCount(8), is(385881925));
		assertThat(new GoodStringsCount().goodStringsCount(9), is(1568524100));
		
		
		//int[] val=new int[]{0, 0, 325, 10400, 164450, 1710280, 13123110, 78936000, 385881925, 1568524100};
		
		
		System.out.println("All cases passed");

	}
	


	
}
