package com.chawkalla.algorithms;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
	
	public static int getMaxHeight(int node, final HashMap<Integer, List<Integer>> graph, BitSet visited){
		int h=0;

		List<Integer> neighbors=graph.get(node);
		if(visited.get(node))
			return 0;
		if(neighbors==null || neighbors.size()==0)
			return 0;
		visited.set(node);

		for(int neighbor:neighbors){
			if(!visited.get(neighbor)){
				int val=1+getMaxHeight(neighbor, graph, visited);
				if(val>h)
					h=val;
			}			
		}

		return h;
	}
	
	public static void getNthNodes(int node, int level,
			final HashMap<Integer, List<Integer>> graph, BitSet visited, HashSet<Integer> nthNodes){
		if(level==0){
			nthNodes.add(node);
			return;
		}
		List<Integer> neighbors=graph.get(node);		
		
		if(neighbors==null || neighbors.size()==0)
			return;
		visited.set(node);
		
		if(neighbors.size()==2){
			while(neighbors.size()==2)
			{
				for(int neighbor:neighbors){
					if(!visited.get(neighbor)){
						visited.set(neighbor);
						level=level-1;
						neighbors=graph.get(neighbor);
						node=neighbor;
					}
				}
			}
			getNthNodes(node, level, graph, visited, nthNodes);
		}else{
			for(int neighbor:neighbors){
				if(!visited.get(neighbor))
					getNthNodes(neighbor, level-1, graph, visited, nthNodes);
				
			}
		}
		
	}
	
	public static List<LinkedList<Integer>> getNthNodesPath(int node,	int level,
			final HashMap<Integer, List<Integer>> graph, BitSet visited){
			
		if(level==0){
			List<LinkedList<Integer>> lists=new ArrayList<LinkedList<Integer>>();
			LinkedList<Integer> ll=new LinkedList<Integer>();
			ll.add(node);
			lists.add(ll);
			return lists;
		}

		List<Integer> neighbors=graph.get(node);
		/*if(visited.get(node))
			return null;*/
		if(neighbors==null || neighbors.size()==0)
			return null;
		visited.set(node);
		
		List<LinkedList<Integer>> result=new ArrayList<LinkedList<Integer>>();
		if(neighbors.size()==2){
			LinkedList<Integer> li=new LinkedList<Integer>();
			while(neighbors.size()==2)
			{
				for(int neighbor:neighbors){
					if(!visited.get(neighbor)){
						visited.set(neighbor);
						li.add(node);
						level=level-1;
						neighbors=graph.get(neighbor);
						node=neighbor;
					}
				}
			}
			List<LinkedList<Integer>> lists=getNthNodesPath(node, level, graph, visited);
			if(lists!=null && lists.size()>0){
				for(LinkedList<Integer> ll:lists){
					if(ll!=null){
						ll.addAll(0, li);
						result.add(ll);
					}
						
				}
			}
		}else{
			for(int neighbor:neighbors){
				if(!visited.get(neighbor)){
					List<LinkedList<Integer>> lists=getNthNodesPath(neighbor, level-1, graph, visited);
					if(lists!=null && lists.size()>0){
						for(LinkedList<Integer> ll:lists){
							if(ll!=null){
								ll.addFirst(node);
								result.add(ll);
							}
								
						}
					}
				}
					
				
			}
		}
		
		
		return result;
	}
}
