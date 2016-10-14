package com.chawkalla.algorithms.examples.graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * https://leetcode.com/problems/minimum-height-trees/
 *
 */
public class MinimumHeightTreesPlay {

	private HashSet<Integer> nthNodes=new HashSet<Integer>();
	private BitSet visited;
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> middles=new ArrayList<Integer>();
		if(edges==null || edges.length==0){
			middles.add(0);
			return middles;
		}

		//build adjacency list for graph
		HashMap<Integer, List<Integer>> graph=createAdjList(edges);

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
		int maxHeight=getMaxHeight(leaf, graph);
		
		//get furthest node from random leaf node
		//HashSet<Integer> furthestNodes=new HashSet<Integer>();
		nthNodes.clear();
		visited.clear();
		getNthNodes(leaf, graph, maxHeight);
		
		leaf=nthNodes.iterator().next();
		
		//now measure max height from that node
		visited.clear();
		maxHeight=getMaxHeight(leaf, graph);
		
		//get the furthest node path
		visited.clear();
		List<LinkedList<Integer>> paths=getNthNodesPath(leaf, graph,
				maxHeight);
		
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

	private void getNthNodes(int node, HashMap<Integer, List<Integer>> graph,
			int level){
		if(level==0){
			nthNodes.add(node);
			return;
		}

		List<Integer> neighbors=graph.get(node);
		
		/*if(visited.get(node))
			return;*/
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
			getNthNodes(node, graph, level);
		}else{
			for(int neighbor:neighbors){
				if(!visited.get(neighbor))
					getNthNodes(neighbor, graph, level-1);
				
			}
		}
		
		
		
	}
	
	private List<LinkedList<Integer>> getNthNodesPath(int node, HashMap<Integer, List<Integer>> graph,
			int level){
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
			List<LinkedList<Integer>> lists=getNthNodesPath(node, graph, level);
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
					List<LinkedList<Integer>> lists=getNthNodesPath(neighbor, graph, level-1);
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

	private int getMaxHeight(int node, final HashMap<Integer, List<Integer>> graph){
		int h=0;

		List<Integer> neighbors=graph.get(node);
		if(visited.get(node))
			return 0;
		if(neighbors==null || neighbors.size()==0)
			return 0;
		visited.set(node);

		for(int neighbor:neighbors){
			if(!visited.get(neighbor)){
				int val=1+getMaxHeight(neighbor, graph);
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
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(7, new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(2, new int[][]{{0,1},{0,2}}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(1, new int[][]{}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(2, new int[][]{{0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
		
		int n=5000;
		int[][] a=new int[n][2];
		for(int i=0;i<n;i++){
			a[i]=new int[]{i,i+1};
		}
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(n+1, a));
		
		
		
	}

}
