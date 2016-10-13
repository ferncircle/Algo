package com.chawkalla.algorithms;

public class Matrix {

	public static int[][] matrixMultiply(int[][] m, int[][] n){
		int[][] r=null;
		int mRowSize = m.length;
		int mColumnSize = m[0].length;
		int nColumnSize = n[0].length;

		r = new int[mRowSize][nColumnSize];

		for (int i = 0; i < mRowSize; i++) { 
			for (int j = 0; j < nColumnSize; j++) {
				for (int k = 0; k < mColumnSize; k++) {
					r[i][j] += m[i][k] * n[k][j];

				}
			}
		}		
		return r;
	}


	public static int[][] matrixPower(int[][] a, int power){
		int[][] m=null;
		if(power<=1)
			return a;
		int[][] r=matrixPower(a, power/2);
		m=matrixMultiply(r, r);
		if(power%2!=0){ 
			m=matrixMultiply(m, a);
		}
		return m;
	}	
}
