package com.chawkalla.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ArrayKthSmallest {

	public class Item {
		int row;
		int col;
		public Item(int rowArray, int rowIndex) {
			super();
			this.row = rowArray;
			this.col=rowIndex;
		}


	}
	
	public int kthSmallest(int[][] matrix, int k) {
		if(matrix==null || matrix.length==0)
			return 0;
		PriorityQueue<Item> queue=new PriorityQueue<Item>(new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return matrix[o1.row][o1.col]-matrix[o2.row][o2.col];
			}
		});
		
		int kth=0;
		for(int i=0;i<matrix.length;i++){
			queue.add(new Item(i,0));
		}

		int counter=1;
		boolean done=false;
		while(!done){
			Item item=queue.remove();
			if(counter==k){
				kth=matrix[item.row][item.col];
				break;
			}
			
			int[] row=matrix[item.row];
			
			Item newItem=null;
			if(item.col+1<row.length){
				newItem=new Item(item.row,item.col+1);
				queue.add(newItem);
			}
			counter++;
		}

		return kth;
	}
	

	public static void main(String[] args) {

		assertThat(new ArrayKthSmallest().kthSmallest(new int[][]{
				{ 1,  5,  9},
				{10, 11, 13},
				{12, 13, 15}
		}, 8), is(13));

		System.out.println("All test cases passed");


	}


}
