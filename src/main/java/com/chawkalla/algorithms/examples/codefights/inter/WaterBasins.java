/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *https://codefights.com/interview/e6nwBu7qGbGLe5Qc5/
 *
 *A group of farmers has some elevation data that we are going to use to help them understand how rainfall flows over their 
 *farmland. We represent the farmland as a 2D array of altitudes, the grid, and use the following model, based on the 
 *fact that water flows downhill:

If a cell's four neighboring cells all have altitudes not lower that itw own, this cell is a sink in which water collects.
Otherwise, water will flow into the neighboring cell with the lowest altitude. If a cell is not a sink, you can assume 
it has a unique lowest neighbor and that this neighbor will be lower than the cell.
Cells that drain into the same sink, directly or indirectly, are part of the same basin.
Given an n × n grid of elevations, your goal is to partition the map into basins and output the sizes of the basins, in 
descending order.

Example

For

grid = [[1, 5, 2], 
        [2, 4, 7], 
        [3, 6, 9]]
the output should be
calculateBasins(grid) = [7, 2].

The two basins in this map, labeled as As and Bs, are:

  A A B 
  A A B 
  A A A 
The size of the basin labeled as As is 7 cells and the size of the basin labeled as Bs is 2 cells. The A basin drains
 into the sink with an altitude of 1 located at grid[0][0] and the B basin drains into the sink with an altitude of 2
  located at grid[0][2].
 *
 */
public class WaterBasins {

	int[] calculateBasins(int[][] grid) {

		Cell[][] sinks=new Cell[grid.length][grid[0].length];
		HashMap<Integer, Integer> basinCount=new HashMap<Integer, Integer>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				Cell sink=new Cell(i,j).findSink(grid, sinks);
				basinCount.compute(sink.getIndex(grid[0].length), (k,v)->v==null?1:v+1);
			}
		}
		ArrayList<Integer> list=new ArrayList<Integer>(basinCount.values());
		Collections.sort(list,(a,b)->b-a);
		int[] basins=list.stream().mapToInt(Integer::intValue).toArray();
		return basins;

	}

	public class Cell{
		int i;
		int j;
		public Cell(int i,int j){
			this.i=i;
			this.j=j;
		}

		public int getIndex(int size){
			return i*size+j;
		}

		public Cell findSink(int[][] grid, Cell[][] sinks){
			if(sinks[i][j]!=null)//cache
				return sinks[i][j];
			Cell sink=null;
			
			Cell lowNeighbor=this;//first find lowest neighbor
			int[][] neighbors=new int[][]{{i-1,j},{i,j-1},{i,j+1},{i+1,j}};
			for(int[] ns:neighbors){
				int nx=ns[0],ny=ns[1];
				if(nx<0 || nx==grid.length || ny<0 || ny==grid[0].length)
					continue;
				if(grid[nx][ny]<grid[lowNeighbor.i][lowNeighbor.j])
					lowNeighbor=new Cell(nx,ny);
			}
			
			if(lowNeighbor.equals(this))
				sink=this;
			else
				sink=lowNeighbor.findSink(grid, sinks); 

			sinks[i][j]=sink;
			return sink;
		}
	}




	public static void main(String[] args) {
		assertThat(new WaterBasins().calculateBasins(new int[][]{
			{1,0,2,5,8}, 
			{2,3,4,7,9}, 
			{3,5,7,8,9}, 
			{1,2,5,4,3}, 
			{3,3,5,2,1}
		}), is(new int[]{11, 7, 7}));

		assertThat(new WaterBasins().calculateBasins(new int[][]{
			{0, 3},
			{0, 3}
		}), is(new int[]{2,2}));
		assertThat(new WaterBasins().calculateBasins(new int[][]{
			{1,5,2}, 
			{2,4,7}, 
			{3,6,9}
		}), is(new int[]{7,2}));


		System.out.println("All cases passed");
	}

}
