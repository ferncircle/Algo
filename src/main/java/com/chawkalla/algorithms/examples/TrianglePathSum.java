package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * 
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 */
public class TrianglePathSum {

	public int minimumTotal(List<List<Integer>> triangle) {

		if(triangle==null || triangle.size()==0)
			return 0;
		
		int maxLength=triangle.get(triangle.size()-1).size();
		if(maxLength==0)
			return 0;
		if(maxLength==1)
			return triangle.get(0).get(0);;
		
		int[] a=new int[maxLength];
		int[] prevPathSum=new int[maxLength];
		a[0]=triangle.get(0).get(0); //get first row element
		
		for (int i = 1; i < triangle.size(); i++) {
			
			//backup previous path sum			
			System.arraycopy(a, 0, prevPathSum, 0, maxLength);
			
			List<Integer> currentRow=triangle.get(i);
			int previousRowElements=currentRow.size()-1;
			
			for (int j = 0; j < currentRow.size(); j++) {
				int current=currentRow.get(j);
				
				//get minimum of TWO parents of previous row but check for bounds			
				int leftParent=(j-1 >=0)? prevPathSum[j-1]:Integer.MAX_VALUE;
				int rightParent=(j<previousRowElements)?prevPathSum[j]:Integer.MAX_VALUE;
				int minParent=Math.min(leftParent, rightParent);
				
				a[j]=current+minParent;
			}
		}
		
		int min=Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if(a[i]<min)
				min=a[i];
			
		}
		return min;
	}
	
	public static void main(String[] args) {

		List<List<Integer>> triangle=new ArrayList<List<Integer>>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3,4));
		triangle.add(Arrays.asList(6,5,7));
		triangle.add(Arrays.asList(4,1,8,3));		
		
		assertThat(new TrianglePathSum().minimumTotal(triangle), is(11));
		
		triangle=new ArrayList<List<Integer>>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3,4));
		triangle.add(Arrays.asList(6,5,-4));
		triangle.add(Arrays.asList(4,1,8,3));		
		assertThat(new TrianglePathSum().minimumTotal(triangle), is(5));
		
		System.out.println("All test cases passed");

	}

}
