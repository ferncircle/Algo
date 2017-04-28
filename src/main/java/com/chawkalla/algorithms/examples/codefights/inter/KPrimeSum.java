/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * https://codefights.com/tournaments/NfsMqkaNkZDWhAajY/B
 * 
 * A number is called a k-primeSum if it can be represented as a sum of k (not necessarily distinct) prime numbers. 
 * Implement a function that checks if the given number is k-primeSum or not.

Example

For n = 9 and k = 3, the output should be
primeSum(n, k) = true.

We can represent 9 as 2 + 2 + 5.

Solution: Check out how to get prime numbers from 1 to n
 *
 */
public class KPrimeSum {

	boolean primeSum(int n, int k) {

		List<Integer> primes =getAllPrimes(n);

		// dp[A][B] is true if number A can be represented
		// as a sum of B prime numbers
		// and false otherwise
		boolean[][] dp = new boolean[n + 1][k + 1];
		dp[0][0] = true;
		for (int b = 1; b <= k; b++) {
			for (int a = 2; a <= n; a++) {
				for (Integer p : primes) {
					if (a - p >= 0 && dp[a - p][b - 1]) {
						dp[a][b]=dp[a][b] || dp[a - p][b - 1];
					}
				}
			}
		}

		return dp[n][k];
	}

	public List<Integer> getAllPrimes(int n){
		List<Integer> primes=new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			boolean ok = true;
			for (Integer p : primes) {
				if (i % p == 0) {
					ok = false;
					break;
				}
			}
			if (ok) {
				primes.add(i);
			}
		}
		return primes;
	}

	public static void main(String[] args) {
		assertThat(new KPrimeSum().primeSum(9, 3), is(true));

		assertThat(new KPrimeSum().primeSum(9, 4), is(true));

		assertThat(new KPrimeSum().primeSum(9, 5), is(false));
		System.out.println("all cases passed");

	}

}
