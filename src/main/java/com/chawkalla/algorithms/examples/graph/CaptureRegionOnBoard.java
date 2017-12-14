/**
 * 
 */
package com.chawkalla.algorithms.examples.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sfargose
 *
 */
public class CaptureRegionOnBoard {


    
    private int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    private char marker='1';
    
	public char[][] solve(char[][] a) {
	    if(a==null || a.length==0) return a;
	    int m=a.length, n=a[0].length;
	    Queue<Cell> q=new LinkedList<Cell>();
	    
	    for(int i=0;i<m;i++){
	        q.add(new Cell(i,0));
	        q.add(new Cell(i,n-1));
	    }
	    for(int i=0;i<n;i++){
	        q.add(new Cell(0,i));
	        q.add(new Cell(m-1,i));
	    }
	    while(!q.isEmpty()){
	        Cell cur=q.remove();
	        if(cur.i<0 || cur.i>=m || cur.j<0 || cur.j>=n) continue;
	        char ch=a[cur.i][cur.j];
	        if(ch=='X' || ch==marker) continue;
	        a[cur.i][cur.j]=marker;
    	    for(int[] d:dir)
    	        q.add(new Cell(cur.i+d[0], cur.j+d[1]));
	    }
	    
	    for(int i=0;i<m;i++)
	        for(int j=0;j<n;j++)
	            if(a[i][j]==marker)
	            	a[i][j]='O';
	            else 
	            	a[i][j]='X';
	    return a;
	}
	
	class Cell{
	    int i,j;
	    public Cell(int i,int j){
	        this.i=i;this.j=j;
	    }
	}
	
	public static void main(String[] args) {
		
		assertThat(new CaptureRegionOnBoard().solve(new char[][]{
			"XOXXXXOOXX".toCharArray(), 
			"XOOOOXOOXX".toCharArray(), 
			"OXXOOXXXOO".toCharArray(), 
			"OXOXOOOXXO".toCharArray(), 
			"OXOOXXOOXX".toCharArray(), 
			"OXXXOXXOXO".toCharArray(), 
			"OOXXXXOXOO".toCharArray()}), 
				is(new char[][]{
					"XOXXXXOOXX".toCharArray(),
					"XOOOOXOOXX".toCharArray(),
					"OXXOOXXXOO".toCharArray(),
					"OXXXOOOXXO".toCharArray(),
					"OXXXXXOOXX".toCharArray(),
					"OXXXXXXOXO".toCharArray(),
					"OOXXXXOXOO".toCharArray()
				}));
		System.out.println("all cases passed");

	}

}
