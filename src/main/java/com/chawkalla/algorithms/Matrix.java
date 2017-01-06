package com.chawkalla.algorithms;

import java.math.BigInteger;

public class Matrix {

	public static long[][] matrixMultiply(long[][] m, long[][] n){
		long[][] r=null;
		int mRowSize = m.length;
		int mColumnSize = m[0].length;
		int nColumnSize = n[0].length;

		r = new long[mRowSize][nColumnSize];

		for (int i = 0; i < mRowSize; i++) { 
			for (int j = 0; j < nColumnSize; j++) {
				for (int k = 0; k < mColumnSize; k++) {
					r[i][j] += m[i][k] * n[k][j];

				}
			}
		}		
		return r;
	}
	
	public static BigInteger[][] matrixMultiply(BigInteger[][] m, BigInteger[][] n){
		BigInteger[][] r=null;
		int mRowSize = m.length;
		int mColumnSize = m[0].length;
		int nColumnSize = n[0].length;

		r = new BigInteger[mRowSize][nColumnSize];

		for (int i = 0; i < mRowSize; i++) { 
			for (int j = 0; j < nColumnSize; j++) {
				if(r[i][j]==null)
					r[i][j]=new BigInteger("0");
				for (int k = 0; k < mColumnSize; k++) {
					r[i][j]=r[i][j].add(m[i][k].multiply(n[k][j]));

				}
			}
		}		
		return r;
	}


	public static long[][] matrixPower(long[][] a, int power){
		long[][] m=null;
		if(power<=1)
			return a;
		long[][] r=matrixPower(a, power/2);
		m=matrixMultiply(r, r);
		if(power%2!=0){ 
			m=matrixMultiply(m, a);
		}
		return m;
	}	
	
	public static BigInteger[][] matrixPower(BigInteger[][] a, long power){
		BigInteger[][] m=null;
		if(power<=1)
			return a;
		BigInteger[][] r=matrixPower(a, power/2);
		m=matrixMultiply(r, r);
		if(power%2!=0){ 
			m=matrixMultiply(m, a);
		}
		return m;
	}	
}
