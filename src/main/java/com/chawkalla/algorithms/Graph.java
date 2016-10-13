package com.chawkalla.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

	public static int[][] floydWarshallDistance(int n, int[][] matrix){
		int[][] dist=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(matrix[i][j]==0)
					dist[i][j]=100000;
				else
					dist[i][j]=matrix[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if((dist[i][k]+dist[k][j])<dist[i][j])
						dist[i][j]=dist[i][k]+dist[k][j];
				}
			}
		}
		
		return dist;
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
	
	
	public static HashMap<Integer, List<Integer>> createAdjList(int[][] edges){
		HashMap<Integer, List<Integer>> adj=new HashMap<Integer, List<Integer>>();
		if(edges==null || edges.length==0)
			return null;
		for(int[] edge:edges){
			if(edge!=null){
				addKeyValue(adj, edge[0], edge[1]);
				addKeyValue(adj, edge[1], edge[0]);
			}
		}

		return adj;
	}
	

	public static void addKeyValue(HashMap<Integer, List<Integer>> map, int key, int value){
		if(map.containsKey(key))
			map.get(key).add(value);
		else{
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(value);
			map.put(key, list);
		}

	}
}
