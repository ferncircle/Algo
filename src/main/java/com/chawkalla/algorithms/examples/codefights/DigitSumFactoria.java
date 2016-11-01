package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DigitSumFactoria {

	int digitSumFactorial(int n) {
	    return (n<6)?new int[]{0,1,2,6,6,3}[n]:0;
	}
	
	public static void main(String[] args) {

		assertThat(new DigitSumFactoria().digitSumFactorial(25), is(0));
		assertThat(new DigitSumFactoria().digitSumFactorial(4), is(6));
		assertThat(new DigitSumFactoria().digitSumFactorial(3), is(6));
		assertThat(new DigitSumFactoria().digitSumFactorial(5), is(3));
		long before=System.currentTimeMillis();
		assertThat(new DigitSumFactoria().digitSumFactorial(10000), is(0));
		System.out.println("Excution time="+(System.currentTimeMillis()-before));
		
		before=System.currentTimeMillis();
		assertThat(new DigitSumFactoria().digitSumFactorial((int)Math.pow(10, 7)+554), is(0));
		System.out.println("Excution time="+(System.currentTimeMillis()-before));
		
		System.out.println("All test cases passed");
	}
}
