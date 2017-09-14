package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.chawkalla.algorithms.utils.ArrayUtils;

/**
 * Given array of positive numbers and number of dividers. Partition the array using all dividers to minimize the maximum
 *  sum in any partition.
 * 
 * Note that naive approach of (sumOfNumbers)/dividers won't work for the case, ({1,1,1,1,1,1,1,8,1}, 3)
 * 
 * O(kn^2)
 * 
 * 
 *
 */
public class NumberPartition {

	public int partition(int[] s, int k){

		int maxCost=0;

		int n=s.length;

		int[][] m=new int[n+1][k+1]; //DP for cost of [s][k]
		int[][] d=new int[n+1][k+1]; //DP for divider position for k in array

		int[] prefixSum=new int[n+1]; //prefix sum

		for (int i = 1; i <= n; i++) 
			prefixSum[i]=prefixSum[i-1]+s[i-1];
		

		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				if(j==1){ //only one partition
					m[i][j]=prefixSum[i];
					continue;
				}
				if(i==1){ //only one element
					m[i][j]=s[0];
					continue;
				}
				m[i][j]=Integer.MAX_VALUE; //max cost for m[i][j], upto only i and using only j dividers
				for (int x = 1; x <= i-1; x++) {

					m[i][j]=Math.min(m[i][j], Math.max(
							m[x][j-1], //one less divider for sub problem
							prefixSum[i]-prefixSum[x]
							));
				}
			}
		}

		maxCost=m[n][k];

		System.out.println("cost matrix:");
		ArrayUtils.print(m);
		
		System.out.println("Divider matrix:");
		ArrayUtils.print(d);

		reconstruct_partition(s, d, n, k);
		
		return maxCost;
	}

	public void reconstruct_partition(int[] s,int[][] d, int n,  int k)
	{
		if (k==1)
			print_books(s,1,n);
		else {
			reconstruct_partition(s,d,d[n][k],k-1);
			print_books(s,d[n][k]+1,n);
		}
	}
	public void print_books(int s[], int start, int end)
	{
		System.out.print("("+start+"-"+end+"):");
		for (int i=start; i<=end; i++) 
			System.out.print(s[i-1]+" ");
		System.out.println();
	}

	public static void main(String[] args) {

		assertThat(new NumberPartition().partition(new int[]{1,1,1,1,1,1,1,1,1}, 3), is(3));
		System.out.println("All test cases passed");

	}

}
