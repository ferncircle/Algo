/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * https://codefights.com/challenge/mphk8gHzaN5KQAJij
 * 
 * Given a non-negative integer k, to count the number of way to represent it as a sum of no more than 2 
 * squares of positive integers. In other words, your task is to calculate the number of representations of k in
 *  the formats k = a2 + b2 and k = c2.

Example

For k = 1, the output should be
sumSquares(k) = 1.

There's only one way to represent 1 as described above: 1 = 12.

For k = 25, the output should be
sumSquares(k) = 3.

Here are all the possible ways to represent 25 as a sum of no more than 2 squares:

25 = 52;
25 = 32 + 42;
25 = 42 + 32.
 *
 */
public class SumSquares {

	int sumSquares(long k) {

		int count=0;
		
		HashSet<Long> squares=new HashSet<Long>();
		
		for (int i = 1; i*i <=k; i++) {
			squares.add((long)i*i);
		}
		
		System.out.println(squares.size());
		/*for (long a:squares) {
			if(a==k || squares.contains(k-a))
				count++;
		}		*/
		return count;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(new SumSquares().sumSquares((long)Math.pow(2, 50)));
		assertThat(new SumSquares().sumSquares(25), is(3));
		assertThat(new SumSquares().sumSquares(1), is(1));
		assertThat(new SumSquares().sumSquares(101), is(2));
		System.out.println("all cases passed");
	}

}
