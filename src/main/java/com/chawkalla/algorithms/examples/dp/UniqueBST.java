package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;


/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 *
 *
 *Solution:
 *1) At each root i, int totalBefore=i;
			int totalAfter=n-1-i;
			total=totalBefore*totalAfter
			
  2) See DP solution, solve it in chunks, starting from beginning.
 *
 */
public class UniqueBST {

	Map<Integer, Integer> cache=new HashMap<Integer, Integer>();
	
	public int numTrees(int n) {
		if(n<0)
			return 0;
		if(n==0 || n==1)
			return 1;
		
		if(n==2)
			return 2;
		
		if(cache.containsKey(n))
			return cache.get(n);		
		
		int total=0;
		for (int i = 0; i < n; i++) {
			int totalBefore=i;
			int totalAfter=n-1-i;
			total+=numTrees(totalBefore)*numTrees(totalAfter);
		}
		
		cache.put(n, total);
		
		return total;

	}
	
	public int numTreesDP(int n) {
		if(n<0)
			return 0;
		int total=0;
		
		int[] Cat=new int[n+1];
		Cat[0]=1;
		Cat[1]=1;
		
		//solve in chunks
		for (int i = 2; i <= n; i++) {
			int sum=0;
			for (int j = 0; j < i; j++) {
				int totalBefore=Cat[j];
				int totalAfter=Cat[i-1-j];
				sum+=totalBefore*totalAfter;
			}
			Cat[i]=sum;
		}
		
		total=Cat[n];
		return total;
	}
	

	public static void main(String[] args) {

		assertThat(new UniqueBST().numTrees(3), is(5));
		assertThat(new UniqueBST().numTrees(10), is(16796));
		assertThat(new UniqueBST().numTreesDP(3), is(5));
		assertThat(new UniqueBST().numTreesDP(10), is(16796));
		
		System.out.println("All test cases passed");

	}

}
