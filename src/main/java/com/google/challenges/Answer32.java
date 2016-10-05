package com.google.challenges;

public class Answer32 {

	public static int answer(int start, int length){
		int val=0;
		
		int begin=start;
		int rows=length;
		int col=length;
		while(rows>0){
			for(int i=begin;i<begin+col;i++){				
				val=val^i;
			}
			
			begin=begin+length;
			rows--;
			col--;
		}
		
		
		return val;
	}
	
	
	public static void main(String[] args) {
		System.out.println(answer(17, 4));
		System.out.println();
		System.out.println(answer(23, 2));

	}

}
