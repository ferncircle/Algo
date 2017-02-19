package com.chawkalla.algorithms.examples.graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * https://leetcode.com/problems/minimum-height-trees/
 * * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, 
 * write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges 
(each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Solution:
One approach:
	1) Take any leaf node and find the farthest node from it. Now find the farthest node from this node and the path. Then take middle(or two) from that pathh
	
Another approach:
1) Find all leaves and delete them
2) Continue this until size <=2
 *
 */
public class MinimumHeightTrees {

	private HashMap<Integer, List<Integer>> graph;
	private BitSet visited;
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> middles=new ArrayList<Integer>();
		if(edges==null || edges.length==0){
			middles.add(0);
			return middles;
		}

		//build adjacency list for graph
		graph=createAdjList(edges);

		//get any leaf node
		int leaf=-1;
		for(int i:graph.keySet()){
			if(graph.get(i).size()==1){
				leaf=i;
				break;
			}
		}
		
		visited=new BitSet(n);
		//get max height from random node
		int maxHeight=getMaxHeight(leaf);
		
		//get furthest node from random leaf node
		HashSet<Integer> nthNodes=new HashSet<Integer>();
		
		nthNodes.clear();
		Graph.getNthNodes(leaf, maxHeight, graph, new HashSet<Integer>(), nthNodes);
		//getNthNodes(leaf,  maxHeight);
		
		leaf=nthNodes.iterator().next();
		
		//now measure max height from that node
		visited.clear();
		maxHeight=getMaxHeight(leaf);
		
		//get the furthest node path
		visited.clear();
		//List<LinkedList<Integer>> paths=getNthNodesPath(leaf, maxHeight);
		List<LinkedList<Integer>> paths=Graph.getNthNodesPath(leaf, maxHeight, graph, new HashSet<Integer>());
		
		//now get middle or two middle elements from the path		
		HashSet<Integer> solution=new HashSet<Integer>();
		for(LinkedList<Integer> ll:paths){					
			solution.add(ll.get(ll.size()/2));
			if(maxHeight % 2!=0){
				solution.add(ll.get(ll.size()/2-1));
			}
		}
		middles.addAll(solution);

		return middles;
	}

	
	

	private int getMaxHeight(int node){
		int h=0;

		List<Integer> neighbors=graph.get(node);
		if(visited.get(node))
			return 0;
		if(neighbors==null || neighbors.size()==0)
			return 0;
		visited.set(node);

		for(int neighbor:neighbors){
			if(!visited.get(neighbor)){
				int val=1+getMaxHeight(neighbor);
				if(val>h)
					h=val;
			}			
		}

		return h;
	}

	public HashMap<Integer, List<Integer>> createAdjList(int[][] edges){
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

	private void addKeyValue(HashMap<Integer, List<Integer>> map, int key, int value){
		if(map.containsKey(key))
			map.get(key).add(value);
		else{
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(value);
			map.put(key, list);
		}

	}
	
	public static void main(String[] args) {
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(7, new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}}));
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(2, new int[][]{{0,1},{0,2}}));
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(1, new int[][]{}));
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(2, new int[][]{{0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}}));
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
		
		int n=4000;
		int[][] a=new int[n][2];
		for(int i=0;i<n;i++){
			a[i]=new int[]{i,i+1};
		}
		System.out.println(new MinimumHeightTrees().findMinHeightTrees(5001, a));
		
		
		
	}

}
