package com.chawkalla.algorithms.examples.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.chawkalla.algorithms.Graph;


/**
 * https://leetcode.com/problems/minimum-height-trees/
 *
 */
public class MinimumHeightTreesPlay {
	
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> middles=new ArrayList<Integer>();
		if(edges==null || edges.length==0){
			middles.add(0);
			return middles;
		}

		//build adjacency list for graph
		HashMap<Integer, List<Integer>> graph=Graph.createAdjList(edges);

		//get any leaf node
		int leaf=-1;
		for(int i:graph.keySet()){
			if(graph.get(i).size()==1){
				leaf=i;
				break;
			}
		}
		
		//get max height from random node
		int maxHeight=Graph.getMaxHeightIterative(leaf, graph);		
		
		//get furthest node from random leaf node		
		HashSet<Integer> nthNodes=Graph.getNthNodesIterative(leaf, maxHeight, graph);
		
		leaf=nthNodes.iterator().next();
		
		//now measure max height from that node		
		maxHeight=Graph.getMaxHeightIterative(leaf, graph);
		
		//now get nodes appearing around half the distance
		HashSet<Integer> candidateNodes=new HashSet<Integer>();
		candidateNodes.addAll(Graph.getNthNodesIterative(leaf, maxHeight/2, graph));
		if(maxHeight % 2!=0){
			candidateNodes.addAll(Graph.getNthNodesIterative(leaf, maxHeight/2+1, graph));
		}
		
		//get the final set by choosing min height of candidates
		int minHeight=Integer.MAX_VALUE;
		HashSet<Integer> finalSet=new HashSet<Integer>();
		for (int candidate:candidateNodes) {
			int h=Graph.getMaxHeightIterative(candidate, graph);
			
			if(h<minHeight){
				finalSet.clear();
				finalSet.add(candidate);
				minHeight=h;
			}
			else if(h==minHeight){
				finalSet.add(candidate);
			}
		}
		middles.addAll(finalSet);

		return middles;
	}
	
	public static void main(String[] args) {
		MinimumHeightTreesPlay test=new MinimumHeightTreesPlay();
		assertThat(test.findMinHeightTrees(7, new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}}), is(Arrays.asList(1,2)));
		assertThat(test.findMinHeightTrees(2, new int[][]{{0,1},{0,2}}), is(Arrays.asList(0)));
		assertThat(test.findMinHeightTrees(1, new int[][]{}), is(Arrays.asList(0)));
		assertThat(test.findMinHeightTrees(2, new int[][]{{0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}}), is(Arrays.asList(0)));
		assertThat(test.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}), is(Arrays.asList(1)));
		assertThat(test.findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}), is(Arrays.asList(3,4)));		
		int n=10121;
		int[][] a=new int[n][2];
		for(int i=0;i<n;i++){
			a[i]=new int[]{i,i+1};
		}
		System.out.println(new MinimumHeightTreesPlay().findMinHeightTrees(n+1, a));

		System.out.println("All test cases passed");
	}

}
