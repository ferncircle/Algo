/**
 * 
 */
package com.chawkalla.algorithms.examples.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/walls-and-gates/#/description
 * 
 * -1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that 
the distance to a gate is less than 2147483647.

 *
 */
public class WallsAndGates {

	public void wallsAndGates(int[][] rooms) {
		
		Queue<int[]> queue=new LinkedList<int[]>();
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if(rooms[i][j]==0)
					queue.add(new int[]{i,j,0});
			}
		}
		
		int[][] dir=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
		
		while(!queue.isEmpty()){
			int[] cur=queue.poll();
			int x=cur[0], y=cur[1], distSoFar=cur[2];
			if(x<0 || x==rooms.length || y<0 || y==rooms[0].length || 
					rooms[x][y]==-1 ||
					rooms[x][y]<distSoFar)
				continue;
			
			rooms[x][y]=distSoFar;
			
			for (int i = 0; i < dir.length; i++) {
				int xx=dir[i][0], yy=dir[i][1];
				queue.offer(new int[]{x+xx,y+yy,distSoFar+1});
			}
		}
		
	}
	
	public void wallsAndGatesDFS(int[][] rooms) {
	    for (int i = 0; i < rooms.length; i++)
	        for (int j = 0; j < rooms[0].length; j++)
	            if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
	}

	private void dfs(int[][] rooms, int i, int j, int d) {
	    if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) return;
	    rooms[i][j] = d;
	    dfs(rooms, i - 1, j, d + 1);
	    dfs(rooms, i + 1, j, d + 1);
	    dfs(rooms, i, j - 1, d + 1);
	    dfs(rooms, i, j + 1, d + 1);
	}


	public static void main(String[] args) {
		
		int[][] rooms=new int[][]{
			{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}
		};
		
		new WallsAndGates().wallsAndGates(rooms);

		for (int i = 0; i < rooms.length; i++) {
			System.out.println(Arrays.toString(rooms[i]));
		}
	}

}
