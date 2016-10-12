package com.chawkalla.algorithms.examples;

import java.util.Arrays;

public class NumMatrix {

	private int[][] sumMatrix;
	
	public NumMatrix(int[][] matrix) {
		if(matrix==null || matrix.length==0)
			return;
		int rows=matrix.length;
		int cols=matrix[0].length;
		
		sumMatrix=new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			sumMatrix[i][0]=matrix[i][0];
			for (int j = 1; j < cols; j++) {
				sumMatrix[i][j]=matrix[i][j]+sumMatrix[i][j-1];
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(sumMatrix[i]));
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum=0;
		
		for (int i = row1; i <= row2; i++) {
			int preSum=0;
			if(col1>0)
				preSum=sumMatrix[i][col1-1];
			sum+=(sumMatrix[i][col2]-preSum);
		}
			
		
		return sum;
	}
	public static void main(String[] args) {
		NumMatrix test=new NumMatrix(new int[][]{
				{3, 0, 1, 4, 2},
				{5, 6, 3, 2, 1},
				{1, 2, 0, 1, 5},
				{4, 1, 0, 1, 7},
				{1, 0, 3, 0, 5}
		});


		System.out.println(test.sumRegion(2, 1, 4, 3));
		System.out.println(test.sumRegion(1, 1, 2, 2));
		System.out.println(test.sumRegion(1, 2, 2, 4));
		System.out.println(test.sumRegion(1, 0, 2, 0));

	}

}
