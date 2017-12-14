/**
 * 
 */
package com.chawkalla.algorithms.examples.matrix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class GameOfLife {

	public int[][] gameOfLife(int[][] board) {
        if(board==null || board.length==0) return null;
        int m=board.length;
        int n=board[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int liveNeighbors=0;
                for(int x=Math.max(i-1,0);x<=Math.min(i+1,m-1);x++){
                    for(int y=Math.max(j-1,0);y<=Math.min(j+1,n-1);y++){
                    	if(x==i && y==j) continue;
                        if(getPrevCellState(board[x][y])==1)
                            liveNeighbors++;
                    }
                }
                if(getPrevCellState(board[i][j])==1){
                    if(liveNeighbors<2 || liveNeighbors>3)
                        board[i][j]=getCompoundState(board[i][j], 0);//die
                    else 
                        board[i][j]=getCompoundState(board[i][j], 1);//survive
                    
                }else if(liveNeighbors==3)
                    board[i][j]=getCompoundState(board[i][j], 1);//re-live
                    
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                board[i][j]=getCurCellState(board[i][j]);
            }
        }
        
        return board;
    }
    
    private int getPrevCellState(int state){
        return state & 1;
    }
    
    private int getCurCellState(int state){
        return state>>1;
    }
    
    private int getCompoundState(int prev, int cur){
        return (cur<<1)| prev;
    }
	public static void main(String[] args) {
		
		assertThat(new GameOfLife().gameOfLife(new int[][]{
			{1,1,1},
			{0,1,0},
			{0,0,0}}
		), is(new int[][]
			{{1,1,1},{1,1,1},{0,0,0}}
		));
		System.out.println("all cases passed");

	}

}
