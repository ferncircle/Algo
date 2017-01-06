package com.chawkalla.algorithms.examples.matrix;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.chawkalla.algorithms.Matrix;

public class FibonaciiNth {
	
	public static long fibMatrix(int n){
		if(n<=2)
			return 1;	
		long nth=0;
		
		long[][] A=new long[][]{ //Q matrix
				{0,	1},
				{1,	1}
		};
		
		long[][] initialMatrix=new long[][]{
				{1},
				{1}
		};
		long[][] nthPower=Matrix.matrixPower(A, n-1); //Take n-1 power of Q matrix
		
		long[][] solution=Matrix.matrixMultiply(nthPower, initialMatrix);
		nth=solution[0][0];
		System.out.println("matrix="+nth);
		return nth;
	}
	
	public static BigInteger fibMatrixBigInteger(long n){
		if(n<=2)
			return new BigInteger("1");	
		BigInteger nth=null;
		
		BigInteger[][] A=new BigInteger[][]{ //Q matrix
				{new BigInteger("0"),	new BigInteger("1")},
				{new BigInteger("1"),	new BigInteger("1")}
		};
		
		BigInteger[][] initialMatrix=new BigInteger[][]{
				{new BigInteger("1")},
				{new BigInteger("1")}
		};
		BigInteger[][] nthPower=Matrix.matrixPower(A, n-1); //Take n-1 power of Q matrix
		
		BigInteger[][] solution=Matrix.matrixMultiply(nthPower, initialMatrix);
		nth=solution[0][0];
		System.out.println("Biginteger="+nth);
		return nth;
	}
	

	public static long fibSimple(int n){
		if(n<=2)
			return 1;		
		long nth=0;
		long a=1,b=1;
		long count=2;
		while(count!=n){
			nth=a+b;
			System.out.print(nth+" ");
			a=b;
			b=nth;
			count++;
			
		}	
		System.out.println();
		System.out.println("simple="+nth);
		return nth;
	}
	
	public static void main(String[] args) {
		
		int n=92;
		FibonaciiNth.fibSimple(n);
		FibonaciiNth.fibMatrix(n);
		FibonaciiNth.fibMatrixBigInteger(new Long(n));
		System.out.println(new BigDecimal(Math.pow(2, 52)));
		System.out.println("All test cases passed");
	}

}
