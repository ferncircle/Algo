package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.chawkalla.algorithms.Matrix;

public class FibonaciiNth {
	
	public int fibMatrix(int n){
		long before=System.currentTimeMillis();
		if(n<=2)
			return 1;	
		int nth=0;
		
		int[][] A=new int[][]{ //Q matrix
				{0,	1},
				{1,	1}
		};
		
		int[][] initialMatrix=new int[][]{
				{1},
				{1}
		};
		int[][] nthPower=Matrix.matrixPower(A, n-1); //Take n-1 power of Q matrix
		
		int[][] solution=Matrix.matrixMultiply(nthPower, initialMatrix);
		nth=solution[0][0];
		System.out.println("fib matrix="+(System.currentTimeMillis()-before));
		return nth;
	}
	

	public int fib1(int n){
		long before=System.currentTimeMillis();
		if(n<=2)
			return 1;		
		int nth=0;
		int a=1,b=1;
		int count=2;
		while(count!=n){
			nth=a+b;
			a=b;
			b=nth;
			count++;
			
		}		
		System.out.println("fib1="+(System.currentTimeMillis()-before));
		return nth;
	}
	
	public static void main(String[] args) {
		
		int n=4;
		assertThat(new FibonaciiNth().fib1(n), is(new FibonaciiNth().fibMatrix(n)));
		
		
		System.out.println("All test cases passed");
	}

}
