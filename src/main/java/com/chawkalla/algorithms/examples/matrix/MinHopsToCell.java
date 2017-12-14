/**
 * 
 */
package com.chawkalla.algorithms.examples.matrix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sfargose
 *
 */
public class MinHopsToCell {

	public int minHops(int[][] board, int si, int sj, int di, int dj){
		int min=Integer.MAX_VALUE;
		int m=board.length,n=board[0].length;
		
		Queue<Cell> q=new LinkedList<Cell>();
		boolean[][] visited=new boolean[m][n];
		q.add(new Cell(si, sj, 0));
		
		while(!q.isEmpty()){
			Cell cur=q.remove();
			if(cur.i<0 || cur.i==m || cur.j<0 || cur.j==n || board[cur.i][cur.j]==1)
				continue;
			if(visited[cur.i][cur.j])
				continue;
			visited[cur.i][cur.j]=true;
			if(cur.i==di && cur.j==dj){
				min=Math.min(min, cur.hops);
				continue;
			}
			
			for(int[] neighbor:new int[][]{{-1,0},{1,0},{0,-1},{0,1}})
				q.add(new Cell(cur.i+neighbor[0], cur.j+neighbor[1], cur.hops+1));
			
		}		
		
		return min;
	}
	
	class Cell{
		int i,j,hops;
		public Cell(int i,int j, int hops){
			this.i=i;
			this.j=j;
			this.hops=hops;
		}
	}
	
	public static void main(String[] args) {
		
		assertThat(new MinHopsToCell().minHops(new int[][]{
			{0, 1, 0, 0},
			{1, 0, 0, 1},
			{0, 1, 0, 0},
			{0, 0, 0, 0}
		}, 0, 2, 2, 0), is(6));
		
		assertThat(new MinHopsToCell().minHops(new int[][]{
			{0, 0, 0, 0},
			{1, 0, 0, 1},
			{0, 1, 0, 0},
			{0, 0, 0, 0}
		}, 1, 2, 0, 0), is(3));
		
		assertThat(new MinHopsToCell().minHops(new int[][]{
			{0, 0, 0, 0},
			{0, 0, 0, 1},
			{0, 1, 0, 0},
			{0, 0, 0, 0}
		}, 1, 1, 3, 1), is(4));
		
		System.out.println("all cases passed");

	}

}
