/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * 	https://codefights.com/interview/pGscY3EBKyQq3ro47
 * 
 * You are given an undirected weighted graph, which is represented as an adjacency matrix. Find the shortest path 
 * between a start node and a finish node in the graph. You are allowed to add at most one edge of a given weight 
 * between any two nodes that are not directly connected to each other.

Example

For start = 1, finish = 4, weight = 2 and

   graph = [[0, 2, 0, 4, 0],
            [2, 0, 1, 0, 0], 
            [0, 1, 0, 3, 0], 
            [4, 0, 3, 0, 1], 
            [0, 0, 0, 1, 0]]
the output should be
shortestPathWithEdge(start, finish, weight, graph) = 3.

In the original graph, the shortest distance between nodes 1 and 4 is equal to 4. But you can add an edge of weight 2
 between nodes 1 and 5, making the resulting distance 3.
 *
 *
 *Solution: 1) Use Floyd-warshall to get all nodes shortest path
 *2) Now try to insert edge between every non-connected nodes and see if we get minimum path from source to destination
 *	dist[source][i]+weight+dist[j][dest]
 */
public class ShortestPathWithEdge {

	int shortestPathWithEdge(int start, int finish, int weight, int[][] matrix) {

		int source=start-1;
		int dest=finish-1;
		int n=matrix.length;
		int[][] dist=floydWarshallDistance(n, matrix);
		int min=dist[source][dest];
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(dist[i]));
			for (int j = 0; j < n; j++) {
				
				if(i!=j && matrix[i][j]==0 && weight<dist[i][j]){
					min=Math.min(min, dist[source][i]+weight+dist[j][dest]);
				}
			}

		}

		return min;
	}

	public static int[][] floydWarshallDistance(int n, int[][] matrix){
		int[][] dist=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(matrix[i][j]==0 || matrix[i][j]==Integer.MAX_VALUE)
					dist[i][j]=10000000;
				else
					dist[i][j]=matrix[i][j];
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(i==j)
						dist[i][j]=0;
					else
						if((dist[i][k]+dist[k][j])<dist[i][j])
						dist[i][j]=dist[i][k]+dist[k][j];
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) {

		assertThat(new ShortestPathWithEdge().shortestPathWithEdge(1, 4, 2, new int[][]{
			{0,2,0,4,0}, 
			{2,0,1,0,0}, 
			{0,1,0,3,0}, 
			{4,0,3,0,1}, 
			{0,0,0,1,0}
		}), is(3));
		assertThat(new ShortestPathWithEdge().shortestPathWithEdge(1, 5, 2, new int[][]{
			{0,2,0,4,0}, 
			{2,0,1,0,0}, 
			{0,1,0,3,0}, 
			{4,0,3,0,1}, 
			{0,0,0,1,0}
		}), is(2));
		assertThat(new ShortestPathWithEdge().shortestPathWithEdge(1, 3, 10, new int[][]{
			{0,1,3}, 
			{1,0,0}, 
			{3,0,0}
		}), is(3));
		System.out.println("all cases passed");

	}

}
