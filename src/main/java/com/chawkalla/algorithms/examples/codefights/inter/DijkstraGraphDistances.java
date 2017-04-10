/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://codefights.com/interview/LKGQzeAbX5AdSK6tx/description
 * 
 * You have a connected directed graph that has positive weights in the adjacency matrix g. The graph is 
 * represented as a square matrix, where g[i][j] is the weight of the edge (i, j), or -1 if there is no such edge.

Given g and the index of a start vertex s, find the distances between the start vertex s and each of the vertices 
of the graph.

Example

For

g = [[-1, 3, 2],
     [2, -1, 0],
     [-1, 0, -1]]
and s = 0, the output should be
graphDistances(g, s) = [0, 2, 2].

Example

The distance from the start vertex 0 to itself is 0.
The distance from the start vertex 0 to vertex 1 is 2 + 0 = 2.
The distance from the start vertex 0 to vertex 2 is 2.
 *
 */
public class DijkstraGraphDistances {

	int[] graphDistances(int[][] g, int s) {

		HashMap<Integer, List<Integer>> graph=new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				graph.compute(i, (k,v)->v==null?new ArrayList<Integer>():v);
				if(g[i][j]>=0)
					graph.get(i).add(j);
			}
		}
		
		int[] dist=new int[g.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s]=0;
		PriorityQueue<Integer> queue=new PriorityQueue<Integer>((a,b)->dist[a]-dist[b]);
		queue.add(s);
		while(!queue.isEmpty()){
			Integer cur=queue.remove();
			int curDist=dist[cur];
			for(Integer next:graph.get(cur)){
				if(dist[next]>curDist+g[cur][next]){
					dist[next]=curDist+g[cur][next];
					queue.add(next);
				}
				
			}
		}
		return dist;
	}


	public static void main(String[] args) {

		assertThat(new DijkstraGraphDistances().graphDistances(new int[][]{
			{-1,3,2}, 
			{2,-1,0}, 
			{-1,0,-1}
		}, 0), is(new int[]{0, 2, 2}));
		System.out.println("all cases passed");
	}

}
