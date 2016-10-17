package com.chawkalla.algorithms.examples.dp;

import java.util.HashMap;
import java.util.Map;


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

	public static void main(String[] args) {

		System.out.println(new UniqueBST().numTrees(100));

	}

}
