/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * https://www.interviewbit.com/problems/sub-matrices-with-sum-zero/?ref=similar_problems
 *Given a 2D matrix, find the number non-empty sub matrices, such that the sum of the elements inside the sub matrix is equal to 0. (note: elements might be negative).

Example:

Input

-8 5  7
3  7 -8
5 -8  9
Output
2


 */
public class SubMatricesWithSumZero {

	public int solve1(int[][] A) {
		int[][] rowSum=new int[A.length+1][A[0].length+1];
		print(A);
		for (int i = 1; i < rowSum.length; i++) {
			for (int j = 1; j < rowSum[0].length; j++) {				
				rowSum[i][j]=A[i-1][j-1]+rowSum[i-1][j]+rowSum[i][j-1]-rowSum[i-1][j-1];
			
			}
		}
		
		print(rowSum);
		int count=0;
		for (int i = 1; i < rowSum.length; i++) {
			for (int j = i; j < rowSum.length; j++) {

				for (int j2 = i; j2 < rowSum.length; j2++) {
					for (int k = j; k < rowSum.length; k++) {
						
					}
				}
			}
		}
		
		return count;
    }
	
	private void print(int[][] a){
		for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		
		System.out.println("\n");
	}
	
	
	public static void main(String[] args) {
		
		assertThat(new SubMatricesWithSumZero().solve1(new int[][]{
			{0, 0},
			{0, 0}
		}), is(9));
		
		assertThat(new SubMatricesWithSumZero().solve1(new int[][]{
			{-8, 5,  7},
			{3,  7, -8},
			{5, -8,  9}
		}), is(2));
		
		System.out.println("all cases passed");
	}

}
