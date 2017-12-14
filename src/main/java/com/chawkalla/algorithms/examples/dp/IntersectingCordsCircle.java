/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://www.interviewbit.com/problems/intersecting-chords-in-a-circle/
 * 
 * Given a number N, return number of ways you can draw N chords in a circle with 2*N points such that no 2 chords intersect.
Two ways are different if there exists a chord which is present in one way and not in other.

For example,

N=2
If points are numbered 1 to 4 in clockwise direction, then different ways to draw chords are:
{(1-2), (3-4)} and {(1-4), (2-3)}

So, we return 2.

Solution: This is also similar to ways of counting # of balanced parantheses which is a catalan number. 
 *
 */
public class IntersectingCordsCircle {

	long MOD=(long)1e9+7;

	public int chordCnt(int N) {

		long[] dp=new long[N+1];
		dp[0]=1; dp[1]=1;
		for(int i=2;i<dp.length;i++){
			for(int j=0;j<i;j++){
				long v=(dp[j]*dp[i-1-j])%MOD;
				dp[i]=((dp[i]+v)%MOD);
			}

		}

		return (int)dp[N];
	}

	public static void main(String[] args) {
		assertThat(new IntersectingCordsCircle().chordCnt(36), is(141865378));

		assertThat(new IntersectingCordsCircle().chordCnt(2), is(2));
		assertThat(new IntersectingCordsCircle().chordCnt(8), is(1430));

		System.out.println("all test cases passed");

	}

}
