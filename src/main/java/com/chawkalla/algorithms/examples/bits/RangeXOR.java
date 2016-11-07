package com.chawkalla.algorithms.examples.bits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/challenge/KEbTTLutFqbTvs4mu
 * 
 * Given two positive integers a and b, return the bitwise XOR of all the integers in range [a, b].
 *
 *http://stackoverflow.com/questions/10670379/find-xor-of-all-numbers-in-a-given-range
 */
public class RangeXOR {

	int rangeXOR(int a, int b) {
		if(a>0)
			return (int)(xorUptoN(a-1) ^ xorUptoN(b));
		else
			return (int)(xorUptoN(b));
	}
	
	/**
	 * xor upto n=
	 * n	if n%4=0
	 * 1	if n%4=1
	 * n+1	if n%4=2
	 * 0	if n%4=3
	 */
	public long xorUptoN(int n){
		int[] sol=new int[]{n, 1, n+1, 0};
		return sol[n%4];
	}
	
	public static void main(String[] args) {
		
		assertThat(new RangeXOR().rangeXOR(2, 4), is(5));
		assertThat(new RangeXOR().rangeXOR(12345, 23456), is(27544));
		assertThat(new RangeXOR().rangeXOR(895, 903), is(895));

		System.out.println("All test cases passed");

	}

}
