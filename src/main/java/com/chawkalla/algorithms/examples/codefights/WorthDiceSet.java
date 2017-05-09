/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://codefights.com/challenge/5vrZzsJ6L8Y5CXdKm
 * 
 * A worthyDiceSet is a set of rolled dice for which the sum and the product of the face values are equal in the 
 * given modulo.

Given the number of dice n and the modulo mod, calculate all the combinations of dice that form a worthyDiceSet. 
Note that the order of the dice matters, so (2, 4) is not the same as (4, 2).

Example

For n = 2 and mod = 5, the output should be
worthyDiceSet(n, mod) = 4.

These are all the worthyDiceSet combinations:

(2, 2): (2 + 2) % 5 = 4 = (2 * 2) % 5;
(3, 4): (3 + 4) % 5 = 2 = (3 * 4) % 5;
(4, 3): (4 + 3) % 5 = 4 = (4 * 3) % 5;
(5, 5): (5 + 5) % 5 = 0 = (5 * 5) % 5;
 *
 *1 <= n <= 6
 *2 <= mod <= 700
 *
 *Solution:
 *(2+3+6)%5=(2%5+3%5+6%5)%5=(2+3+1)%5=1
 *(2*3*6)%5=(2%5*3%5*6%5)%5=(2*3*1)%5=1
 */
public class WorthDiceSet {

	int worthyDiceSetByMe(int n, int mod) {
		return helper(n, 0, 1, mod);
	}

	
	int worthyDiceSet(final int n, int mod) {
		int count=0; 
		int diceFaceCounter=0;
		int sum=0;
		int product=1;
		for (; sum < n*6; ){
			product = 1; 
			sum=n;
			int diceFace =diceFaceCounter;
			while(diceFace > 0){ 
				sum += diceFace%6;
				product *= diceFace%6 + 1;
				diceFace=diceFace/6;
			}
			count += sum%mod==product%mod?1:0;
			diceFaceCounter++;
		}
		return count;
	}

	public int helper(int cur, int sumSoFar, int productSoFar, int mod){
		if(cur==0){
			if((sumSoFar%mod)==(productSoFar%mod))
				return 1;
			else
				return 0;
		}
		int count=0;
		for (int i = 1; i <= 6; i++) {
			int d=i%mod;
			count+=helper(cur-1, (sumSoFar+d)%mod, (productSoFar*d)%mod, mod);
		}	

		return count;
	}

	public static void main(String[] args) {

		long before=System.currentTimeMillis();
		
		System.out.println(new WorthDiceSet().worthyDiceSet(11, 5));
		System.out.println((System.currentTimeMillis()-before));
		before=System.currentTimeMillis();
		System.out.println(new WorthDiceSet().worthyDiceSetByMe(11, 5));
		System.out.println((System.currentTimeMillis()-before));
		
		assertThat(new WorthDiceSet().worthyDiceSet(2, 5), is(4));
		assertThat(new WorthDiceSet().worthyDiceSet(1, 2), is(6));
		assertThat(new WorthDiceSet().worthyDiceSet(1, 4), is(6));
		assertThat(new WorthDiceSet().worthyDiceSet(2, 5), is(4));
		assertThat(new WorthDiceSet().worthyDiceSet(2, 2), is(9));
		assertThat(new WorthDiceSet().worthyDiceSet(2, 4), is(5));
		assertThat(new WorthDiceSet().worthyDiceSet(2, 36), is(1));
		assertThat(new WorthDiceSet().worthyDiceSet(2, 3), is(8));
		assertThat(new WorthDiceSet().worthyDiceSet(3, 2), is(135));

		System.out.println("all cases passed");

	}

}
