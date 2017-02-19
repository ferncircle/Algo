/**
 * 
 */
package com.chawkalla.algorithms.utils;

import java.util.Arrays;

/**
 * @author sfargose
 *
 */
public class ArrayRecursionUtil {


	public void printArray(int[] a){
		System.out.println(Arrays.toString(a));
		printArrayUtil(a, a.length-1);
	}
	
	public void printArray(int[][] a){
		for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		
		printArrayUtil(a, a.length-1, a[0].length-1, false);
	}
	
	public void printArrayUtil(int[][] a, int i, int j, boolean processingColumns){		
		if(i<0 || j<0)
			return;
		if(!processingColumns){
			printArrayUtil(a, i-1, j, false);
			System.out.println();
		}
		
		printArrayUtil(a, i, j-1, true);
		System.out.print(a[i][j]+" ");
		
	}
	
	public void printArrayUtil(int[] a, int i){		
		if(i<0)
			return;
		printArrayUtil(a, i-1);
		System.out.print(a[i]+" ");
	}
	
	

	public static void main(String[] args) {
		ArrayRecursionUtil test=new ArrayRecursionUtil();
		
		test.printArray(new int[]{4,2,1,5,2,4,23});
		System.out.println();
		test.printArray(new int[][]{
					{4,2,1,5},
					{2,4,1,2},
					{5,4,2,6},
					{4,6,2,4}
				});
		System.out.println();
		test.printArray(new int[][]{
			{4,2},
			{6,9}
		});
	}

}
