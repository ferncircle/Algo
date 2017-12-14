/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/can-i-win/description/
 * 
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first 
 * causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
 *
 */
public class CanIWin {

	public boolean canIWin(int n, int desiredTotal) {
		if(n*(n+1)/2 < desiredTotal)
			return false;
		return util(desiredTotal, new boolean[n+1]);
	}
	
	private boolean util(int desiredTotal, boolean[] pool){
		if(won(desiredTotal, pool))
			return true;

		for (int i = 1; i < pool.length; i++) {
			if(!pool[i]){
				pool[i]=true;
				boolean canwin=util(desiredTotal-i, pool);
				pool[i]=false;
				if(!canwin)
					return true;				
			}
			
		}
		
		return false;
		
	}
	
	private boolean won(int total, boolean[] pool){
		for (int i = 1; i < pool.length; i++) {
			if(!pool[i] && i>=total)
				return true;
		}
		return false;
	}
	

	public static void main(String[] args) {
		
		
		assertThat(new CanIWin().canIWin(10, 11), is(false));
		assertThat(new CanIWin().canIWin(10, 12), is(true));
		assertThat(new CanIWin().canIWin(15, 100), is(true));
		
		
		
		System.out.println("all cases passed");

	}

}
