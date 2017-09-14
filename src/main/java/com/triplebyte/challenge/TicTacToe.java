/**
 * 
 */
package com.triplebyte.challenge;

import java.util.Scanner;

/**
 * @author SFargose
 *
 */
public class TicTacToe {

	private int[][] board;
	private int cpuPlayer=1;
	private int humanPlayer=2-cpuPlayer+1;

	int[][] rows;
	int[][] cols;
	int[] diag;
	int[] antiDiag;
	int moves=0;


	public TicTacToe(int size){
		board=new int[size][size];
		rows=new int[2][size];
		cols=new int[2][size];
		diag=new int[2];
		antiDiag=new int[2];
	}

	//0:success; 
	//1:game over, human won; 
	//2: game over, draw; 
	//-1: wrong move
	public int placeMove(int x, int y){
		if(board[x][y]!=0) return -1;
		if(placePlayer(x, y, humanPlayer)){
			return 1;
		}	
		placeCPUMove();		
		moves+=2;
		if(moves>=board.length*board.length)
			return 2;
		return 0;
	}

	private void placeCPUMove(){		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j]==0 ){
					
					if(!placePlayer(i,j, cpuPlayer)){// && canWin(humanPlayer)
						System.out.println("placing cpu player at "+i+":"+j);
						return;
					}else
						unPlacePlayer(i, j, cpuPlayer);

				}					
			}
		}

	}

	private boolean placePlayer(int x, int y, int player){
		board[x][y]=player;
		rows[player-1][x]++;
		boolean playerWon=false;
		if(rows[player-1][x]==board.length)
			playerWon=true;
		cols[player-1][y]++;
		if(cols[player-1][y]==board.length)
			playerWon=true;
		if(x==y){
			diag[player-1]++;
			if(diag[player-1]==board.length)
				playerWon=true;
		}
		if(x+y==(board.length-1)){
			antiDiag[player-1]++;
			if(antiDiag[player-1]==board.length)
				playerWon=true;
		}

		return playerWon;
	}

	private void unPlacePlayer(int x, int y, int player){		
		rows[player-1][x]--;
		cols[player-1][y]--;
		if(x==y)
			diag[player-1]--;
		if(x+y==(board.length-1))
			antiDiag[player-1]--;
		
		board[x][y]=0;
	}

	private boolean canWin(int player){		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j]==0){ //try all available open slots for this player
					boolean playerWon=placePlayer(i, j, player); //If by placing here, player wins then short circuit it since game over
					if(playerWon || !canWin(2-player+1)){
						unPlacePlayer(i, j, player);
						return true;
					}
					unPlacePlayer(i, j, player);
				}
			}
		}


		return false;

	}

	public void printBoard(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j]==0)
					System.out.print("_ ");
				else
					System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}





	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		TicTacToe game=new TicTacToe(3);

		boolean done=false;
		while(!done){
			game.printBoard();
			System.out.println("Please enter your input");
			int x=in.nextInt();
			int y=in.nextInt();

			int res=game.placeMove(x, y);
			if(res==1){
				System.out.println("game over you won!");
				done=true;
			}else if(res==2){
				System.out.println("game over Draw!");
				done=true;
			}
				

		}

	}


}
