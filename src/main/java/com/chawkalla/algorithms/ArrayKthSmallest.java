package com.chawkalla.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class ArrayKthSmallest {

	private PriorityQueue<Item> queue=new PriorityQueue<Item>();
	private ArrayList<Item> firstColumnItems=new ArrayList<Item>();
	int[][] matrix=null;

	public class Item implements Comparable<Item>{
		int rowArray;
		int rowIndex;
		int value;
		@Override
		public int compareTo(Item o) {

			//return o.value-this.value;
			return matrix[o.rowArray][o.rowIndex] - matrix[this.rowArray][this.rowIndex];
		}
		public Item(int rowArray, int rowIndex, int value) {
			super();
			this.rowArray = rowArray;
			this.rowIndex=rowIndex;
			this.value = value;
		}
		

	}
	
	public void setData(int[][] matrix){
		this.matrix=matrix;
	}
	public static void main(String[] args) {

		int[][] matrix =new int[][]{
				{ 1,  5,  9},
				{10, 11, 13},
				{12, 13, 15}
		};
		
		ArrayKthSmallest test=new ArrayKthSmallest();
		test.setData(matrix);
		
		System.out.println(test.kthSmallest(matrix, 3));


	}
	
	public int kthSmallest(int[][] matrix, int k) {
		int kth=0;
		//sort first column items
		for(int i=0;i<matrix.length;i++){
			int[] row=matrix[i];
			firstColumnItems.add(new Item(i,0,row[0]));
		}
		Collections.sort(firstColumnItems);
		
		
		int firstItemsCursor=firstColumnItems.size()-1;
		//add lowest item from first column
		queue.add(firstColumnItems.get(firstItemsCursor--));
		
		int counter=0;
		while(counter<k){
			Item item=queue.peek();
			int[] row=matrix[item.rowArray];
			Item newItem=null;
			if(item.rowIndex+1<row.length){
				newItem=new Item(item.rowArray,item.rowIndex+1,row[item.rowIndex+1]);
				if(newItem.value>firstColumnItems.get(firstItemsCursor).value)
					newItem=firstColumnItems.get(firstItemsCursor--);
			}else
				newItem=firstColumnItems.get(firstItemsCursor--);
			
			queue.add(newItem);
			counter++;
		}
		
		kth=queue.peek().value;

		return kth;
	}

}
