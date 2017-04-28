package com.chawkalla.algorithms.examples.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	public static int[][] floydWarshallDistance(int n, int[][] matrix){
		int[][] dist=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(matrix[i][j]==0 || matrix[i][j]==Integer.MAX_VALUE)
					dist[i][j]=100000;
				else
					dist[i][j]=matrix[i][j];
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
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

		return createAdjList(edges,false);
	}

	public static HashMap<Integer, List<Integer>> createAdjList(int[][] edges, boolean directed){
		HashMap<Integer, List<Integer>> adj=new HashMap<Integer, List<Integer>>();
		if(edges==null || edges.length==0)
			return null;
		for(int[] edge:edges){
			if(edge!=null){
				addKeyValue(adj, edge[0], edge[1]);
				if(!directed)
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

	public static int getMaxHeight(int node, final HashMap<Integer, List<Integer>> graph, HashSet<Integer> visited){
		int h=0;

		List<Integer> neighbors=graph.get(node);
		if(visited.contains(node))
			return 0;
		if(neighbors==null || neighbors.size()==0)
			return 0;
		visited.add(node);

		for(int neighbor:neighbors){
			if(!visited.contains(neighbor)){
				int val=1+getMaxHeight(neighbor, graph, visited);
				if(val>h)
					h=val;
			}			
		}

		return h;
	}

	public static int getMaxHeightIterative(int node, final HashMap<Integer, List<Integer>> graph){
		int h=-1;

		int dummy=Integer.MAX_VALUE;
		Queue<Integer> queue=new LinkedList<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();
		queue.add(node);
		queue.add(dummy);
		while(!queue.isEmpty()){
			int current=queue.remove();			

			if(current==dummy && !queue.isEmpty()){//this is IMPORTANT! only add dummy node when current is dummy node and queue is not empty(terminal case)		
				h++;
				queue.add(dummy);
			}else{				
				if(visited.contains(current))
					continue;
				visited.add(current);
				List<Integer> neighbors=graph.get(current);
				if(neighbors!=null)
					queue.addAll(neighbors);
			}
		}	

		return h;
	}

	public static HashSet<Integer>  getNthNodesIterative(int node,int level, final HashMap<Integer, List<Integer>> graph){
		HashSet<Integer> nodes=new HashSet<Integer>();

		int h=-1;

		int dummy=Integer.MAX_VALUE;
		Queue<Integer> queue=new LinkedList<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();
		queue.add(node);
		queue.add(dummy);
		while(!queue.isEmpty()){
			int current=queue.remove();			

			if(current==dummy && !queue.isEmpty()){		//this is IMPORTANT! only add dummy node when current is dummy node and queue is not empty(terminal case)		
				h++;
				queue.add(dummy);
			}else{
				if(visited.contains(current))
					continue;
				visited.add(current);
				if(h==level-1)  //get nodes at given level
					nodes.add(current);
				List<Integer> neighbors=graph.get(current);
				if(neighbors!=null)
					queue.addAll(neighbors);
			}
		}	

		return nodes;
	}


	public int getShortestPathIterative(int start, int end, final HashMap<Integer, List<Integer>> graph){
		int path=0;

		int dummy=Integer.MAX_VALUE;
		Queue<Integer> queue=new LinkedList<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();
		queue.add(start);
		queue.add(dummy);
		boolean found=false;
		while(!queue.isEmpty()){
			int current=queue.remove();			

			if(current==end){
				found=true;
				break;
			}

			if(current==dummy && !queue.isEmpty()){//this is IMPORTANT! only add dummy node when current is dummy node and queue is not empty(terminal case)		
				path++;
				queue.add(dummy);
			}else{				
				if(visited.contains(current))
					continue;
				visited.add(current);
				List<Integer> neighbors=graph.get(current);
				if(neighbors!=null)
					queue.addAll(neighbors);
			}
		}	

		if(!found)
			path=Integer.MAX_VALUE;

		return path;
	}

	public static void getNthNodes(int node, int level,
			final HashMap<Integer, List<Integer>> graph, HashSet<Integer> visited, HashSet<Integer> nthNodes){
		if(level==0){
			nthNodes.add(node);
			return;
		}
		List<Integer> neighbors=graph.get(node);		

		if(neighbors==null || neighbors.size()==0)
			return;
		visited.add(node);

		if(neighbors.size()==2){
			while(neighbors.size()==2)
			{
				for(int neighbor:neighbors){
					if(!visited.contains(neighbor)){
						visited.add(neighbor);
						level=level-1;
						neighbors=graph.get(neighbor);
						node=neighbor;
					}
				}
			}
			getNthNodes(node, level, graph, visited, nthNodes);
		}else{
			for(int neighbor:neighbors){
				if(!visited.contains(neighbor))
					getNthNodes(neighbor, level-1, graph, visited, nthNodes);

			}
		}

	}

	public static List<LinkedList<Integer>> getNthNodesPath(int node,	int level,
			final HashMap<Integer, List<Integer>> graph, HashSet<Integer> visited){

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
		visited.add(node);

		List<LinkedList<Integer>> result=new ArrayList<LinkedList<Integer>>();
		if(neighbors.size()==2){
			LinkedList<Integer> li=new LinkedList<Integer>();
			while(neighbors.size()==2)
			{
				for(int neighbor:neighbors){
					if(!visited.contains(neighbor)){
						visited.add(neighbor);
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
				if(!visited.contains(neighbor)){
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

	public static List<LinkedList<Integer>> getNthNodesPath(int node, HashMap<Integer, List<Integer>> graph,
			int level, HashSet<Integer> visited){
		if(level==0){
			List<LinkedList<Integer>> lists=new ArrayList<LinkedList<Integer>>();
			LinkedList<Integer> ll=new LinkedList<Integer>();
			ll.add(node);
			lists.add(ll);
			return lists;
		}

		List<Integer> neighbors=graph.get(node);
		if(neighbors==null || neighbors.size()==0)
			return null;
		visited.add(node);

		List<LinkedList<Integer>> result=new ArrayList<LinkedList<Integer>>();
		if(neighbors.size()==2){
			LinkedList<Integer> li=new LinkedList<Integer>();
			while(neighbors.size()==2)
			{
				for(int neighbor:neighbors){
					if(!visited.contains(neighbor)){
						visited.add(neighbor);
						li.add(node);
						level=level-1;
						neighbors=graph.get(neighbor);
						node=neighbor;
					}
				}
			}
			List<LinkedList<Integer>> lists=getNthNodesPath(node, graph, level, visited);
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
				if(!visited.contains(neighbor)){
					List<LinkedList<Integer>> lists=getNthNodesPath(neighbor, graph, level-1, visited);
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

	public static void main(String[] args){

		int[][] a=floydWarshallDistance(4, new int[][]{
			{0, 1, 0, 0},
			{0, 0, 2, 0},
			{0, 0, 0, 3},
			{0, 0, 0, 0}
		});
		for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
	}
}
