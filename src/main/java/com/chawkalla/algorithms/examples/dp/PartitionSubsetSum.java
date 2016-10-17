package com.chawkalla.algorithms.examples.dp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PartitionSubsetSum {

	public boolean canPartition(int[] nums) {
		boolean isPossible=false;
		if(nums==null || nums.length==0 )
			return true;
		if(nums.length==1)
			return false;
		int total=0;
		for (int i = 0; i < nums.length; i++) {
			total+=nums[i];
		}
		if(total%2 !=0)
			return false;
		
		boolean[][] a=new boolean[nums.length+1][total/2+1];
		for (int i = 0; i < nums.length; i++) {
			
			for (int j = 0; j <= total/2; j++) {
				if(j==0) //zero sum
					a[i][j]=true;
				else if(i==0) //empty input array
					a[i][j]=false;
				/*else if(i==j)
					a[i][j]=true;*/
				else{
					a[i][j]=a[i-1][j];//don't take current item
					if(j-nums[i]>=0)
						a[i][j]=a[i][j] || a[i-1][j-nums[i]]; //or take current item
				}
				
			}
		}
		
		/*for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}*/
		isPossible=a[nums.length-1][total/2];
		return isPossible;
	}
	public static void main(String[] args) {

		assertTrue(new PartitionSubsetSum().canPartition(new int[]{1, 5, 11, 5}));
		System.out.println();
		assertTrue(new PartitionSubsetSum().canPartition(new int[]{1, 2, 3, 5, 1}));
		assertTrue(new PartitionSubsetSum().canPartition(new int[]{}));
		assertFalse(new PartitionSubsetSum().canPartition(new int[]{2}));
		assertTrue(new PartitionSubsetSum().canPartition(new int[]{0,0}));
		
		System.out.println("All test cases passed");
		
	}

}
