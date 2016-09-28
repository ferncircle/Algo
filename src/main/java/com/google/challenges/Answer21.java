package com.google.challenges;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Answer21 {
	
	
	
	public static final int COL=7;
	public static final int ROW=7;

	public static int answer(int src, int dest){

		Node start=new Node(src);

		Node end=new Node(dest);

		Queue<Node> queue=new LinkedList<Node>();
		HashSet<Node> visited=new HashSet<Answer21.Node>();
		if(start!=null){
			queue.add(start);
			queue.add(new Node(true));
		}

		int dist=0;
		while(!queue.isEmpty()){
			Node current=queue.remove();

			if(current.marker){
				dist++;
				//System.out.println();
				queue.add(new Node(true));
				continue;
			}else{

				//System.out.print(current.getValue()+" ");
				if(current.equals(end))
					return dist;
			}

			visited.add(current);
			//get all neighbors
			List<Node> moves=current.getPossibleNeighbors();
			for(Node n:moves)
			{
				if(!visited.contains(n))
					queue.add(n);
			}


		}

		return dist;
	}


	public static class Node{
		int row;
		int col;
		boolean marker=false;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		public Node(int value){
			this.row=value/(ROW+1);
			this.col=value%(COL+1);
		}

		public Node(boolean marker){
			this.marker=marker;
		}

		public List<Node> getPossibleNeighbors(){
			List<Node> nodes=new ArrayList<Answer21.Node>();
			int i=row;
			int j=col;
			if((i-2)>=0 && (j-1)>=0)
				nodes.add(new Node(i-2, j-1));
			if((i-2)>=0 && (j+1)<=COL)
				nodes.add(new Node(i-2, j+1));
			if((i-1)>=0 && (j-2)>=0)
				nodes.add(new Node(i-1, j-2));			
			if((i+1)<=ROW && (j-2)>=0)
				nodes.add(new Node(i+1, j-2));			
			if((i+2)<=ROW && (j-1)>=0)
				nodes.add(new Node(i+2, j-1));			
			if((i+2)<=ROW && (j+1)<=COL)
				nodes.add(new Node(i+2, j+1));			
			if((i-1)>=0 && (j+2)<=COL)
				nodes.add(new Node(i-1, j+2));			
			if((i+1)<=ROW && (j+2)<=COL)
				nodes.add(new Node(i+1, j+2));


			return nodes;
		}

		public int getValue(){
			int value=row*(ROW+1)+col;
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}


	}

}
