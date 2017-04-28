package com.chawkalla.algorithms.examples.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * https://leetcode.com/problems/minimum-height-trees/
 *
 */
public class MinimumHeightTreesFloydWarshall {

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> middles=new ArrayList<Integer>();
		if(edges==null || edges.length==0){
			middles.add(0);
			return middles;
		}

		int minHeight=Integer.MAX_VALUE;
		HashSet<Integer> minHeightNodes=new HashSet<Integer>();
		
		int[][] M=createAdjMatrix(n, n, edges);
		
		int[][] F=Graph.floydWarshallDistance(n, M);
		for (int i = 0; i < F.length; i++) {
			//System.out.println(Arrays.toString(F[i]));
			int maxDist=Integer.MIN_VALUE;
			for (int j = 0; j < n; j++) {
				if(i!=j && F[i][j]>maxDist)
					maxDist=F[i][j];
				
			}			
			if(maxDist<minHeight){
				minHeight=maxDist;
				minHeightNodes.clear();
				minHeightNodes.add(i);
			}
			else if(maxDist==minHeight){
				minHeightNodes.add(i);
			}
			
		}
		
		middles.addAll(minHeightNodes);

		return middles;
	}

	
	public static int[][] createAdjMatrix(int m, int n, int[][] edges){
		int[][] M=new int[m][n];
		if(edges==null || edges.length==0)
			return null;
		for(int[] edge:edges){
			if(edge!=null){
				M[edge[0]][edge[1]]=1;
				M[edge[1]][edge[0]]=1;
			}
		}
		return M;
	}

	public static void main(String[] args) {
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(7, new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}}));
		System.out.println();
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(3, new int[][]{{0,1},{0,2}}));
		System.out.println();		
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(1, new int[][]{}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(8, new int[][]{{0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}}));
		System.out.println();
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
		System.out.println();
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
		
		int n=1000;
		int[][] a=new int[n][2];
		for(int i=0;i<n;i++){
			a[i]=new int[]{i,i+1};
		}
		System.out.println(new MinimumHeightTreesFloydWarshall().findMinHeightTrees(n+1, a));
		
		
		
	}

}
