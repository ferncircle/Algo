/**
 * 
 */
package com.chawkalla.algorithms.examples.matrix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.chawkalla.algorithms.Matrix;

/**
 * http://fusharblog.com/solving-linear-recurrence-for-programming-contest-part-2/
 *
 *Sum of first n elements of fibonacii series.
 *
 * Series: 1 1 2 3 5 8 
 * 
 */
public class FibonaciiSum {

	long fibonaciiSum(int n){
		long sum=0;
		
		long[][] T={
				{1, 1, 0},
				{0, 0, 1},
				{0, 1, 1}
		};
		long[][] F={
				{0},
				{1},
				{1}
		};
		
		long[][] nth=Matrix.matrixPower(T, n);
		long[][] res=Matrix.matrixMultiply(nth, F);
		
		sum=res[0][0];
		
		return sum;
	}
	
	public static void main(String[] args) {
		
		assertThat(new FibonaciiSum().fibonaciiSum(4), is(7L));
		assertThat(new FibonaciiSum().fibonaciiSum(6), is(20L));
		assertThat(new FibonaciiSum().fibonaciiSum(7), is(33L));
		System.out.println("all cases passed");
	}

}
