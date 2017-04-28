/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import java.util.Arrays;

/**
 * 
Solution: 
Easy answer is , if we assign 6 bits (log(52)) for each card then it'll take 52*6=312 bits total.
But the theoretical minimum is log(52!)=226 total bits needed

Note that if we send first 50 cards, then for last two cards we need only 1 bit(instead of 6*2 bits). 
We need to place divider between cards 1 to the 52 to optimize it. We send all cards(k) before the divider 
using permutation(n,k) i.e. choose k cards from n, and then recurse for rest of n-k cards. 
Find the optimum divider using recursion or DP
 *
 */
public class CardSequence {

	public int cardSequenceCount(int n){
		
		int[] dp=new int[n+1];
		int[] bt=new int[n+1];
		for (int i = 2; i < dp.length; i++) {
			dp[i]=Integer.MAX_VALUE;
			for (int j = 1; j <= i; j++) {
				int bitsForFirstJCards=j*bitsNeededNPK(i, j)+dp[i-j];//first j cards will require j*bitsNeededPerCard
				if(bitsForFirstJCards<dp[i]){
					dp[i]=bitsForFirstJCards;
					bt[i]=j;
				}

			}
		}
		
		printSequenceOfCards(n, bt);
		return dp[n];
	}

	public void printSequenceOfCards(int n, int[] bt){
		System.out.println("BackTrack array"+Arrays.toString(bt));
		int total=0;
		while(n>1){
			if (n<=1) continue;
			System.out.println("Send next "+bt[n]+" cards, needs "+
					bitsNeededNPK(n,  bt[n])+
					" bits for P("+n+","+bt[n]+") permutations, remains "+(n-bt[n])+" cards.");
			total+=bt[n]*bitsNeededNPK(n, bt[n]);
			n -= bt[n];
			
		};

		System.out.println("Send last card, needs 0 bits (no information needed)");
		System.out.println("Total="+total);
	};

	//choose k cards from n cards (n!/(n-k)!)
	public static int bitsNeededNPK(int n, int k){
		return logOfFactorial(n)-logOfFactorial(n-k);
	}

	public static int logOfFactorial(int n){
		double count=0;
		for (int i = 2; i <=n; i++) 
			count+=(Math.log(i)/Math.log(2));
		
		return (int)Math.ceil(count);
	}
	public static void main(String[] args) {
		System.out.println(logOfFactorial(52));
		System.out.println(new CardSequence().cardSequenceCount(52));

	}

}
