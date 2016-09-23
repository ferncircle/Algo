package com.chawkalla.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.chawkalla.algorithms.bean.TNode;

public class BST {
	
	public static int index=0;

	public TNode createSampleTree(){
		TNode root=new TNode(10);
		TNode node5=new TNode(5);
		TNode node2=new TNode(2);
		TNode node7=new TNode(7);
		TNode node15=new TNode(15);
		TNode node12=new TNode(12);
		TNode node18=new TNode(18);
		/*
		 * 					10		
		 * 			5				15
		 * 		2		7		12		18
		 * 
		 * 
		 * 
		 */
		
		root.setLeft(node5);
		root.setRight(node15);
		node5.setLeft(node2);
		node5.setRight(node7);
		node15.setLeft(node12);
		node15.setRight(node18);
		return root;
	}
	
	public static void printInOrder(TNode node){
		if(node==null)
			return;
		printInOrder(node.getLeft());
		System.out.print(node.getValue()+" ");
		printInOrder(node.getRight());
	}
	
	public static void printInOrderUsingStack(TNode node){
		if(node==null)
			return;
		boolean done=false;
		Stack<TNode> stack=new Stack<TNode>();
		TNode cursor=node;
		while(!done){	
			if(cursor!=null){
				stack.push(cursor);			
				cursor=cursor.getLeft();
			}else{
				if(!stack.isEmpty()){
					cursor=stack.pop();
					System.out.print(cursor.getValue()+ " ");
					cursor=cursor.getRight();
				}
				else
					done=true;
			}
		}
	}
	
	public static void printPreOrder(TNode node){
		if(node==null)
			return;
		System.out.print(node.getValue()+" ");

		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}
	
	public void printPostOrder(TNode node){
		if(node==null)
			return;
		printPostOrder(node.getLeft());		
		printPostOrder(node.getRight());
		System.out.print(node.getValue()+" ");
	}
	
	public void printReversePostOrder(TNode node){
		if(node==null)
			return;
		printPostOrder(node.getRight());
		printPostOrder(node.getLeft());		
		System.out.print(node.getValue()+" ");
	}
	

	public void BFS(TNode node){
		Queue<TNode> queue=new LinkedList<TNode>();
		if(node!=null)
			queue.add(node);
		while(!queue.isEmpty()){
			TNode item=queue.remove();
			System.out.print(item.getValue()+" ");
			if(item.getLeft()!=null)
				queue.add(item.getLeft());
			if(item.getRight()!=null)
				queue.add(item.getRight());
		}
	}
	
	public void DFS(TNode node){
		Stack<TNode> stack=new Stack<TNode>();
		if(node!=null)
			stack.push(node);
		while(!stack.isEmpty()){
			TNode item=stack.pop();
			System.out.print(item.getValue()+" ");
			if(item.getRight()!=null)
				stack.push(item.getRight());

			
			if(item.getLeft()!=null)
				stack.push(item.getLeft());
			
		}
	}
	
	public static void findKthNode(TNode node, final int k){
		if(node==null)
			return;
		findKthNode(node.getLeft(),  k);
		if(index==k)
			System.out.print(node.getValue()+" ");
		index++;
		findKthNode(node.getRight(),  k);
	}
	
	
	public static void main(String[] args) {
		BST tree=new BST();

		TNode root=tree.createSampleTree();
		
		System.out.print("In Order:");
		tree.printInOrder(root);
		System.out.println();
		
		System.out.print("In Order Using stack:");
		tree.printInOrderUsingStack(root);
		System.out.println();
		
		System.out.print("Pre Order:");
		tree.printPreOrder(root);
		System.out.println();
		
		System.out.print("Post Order:");
		tree.printPostOrder(root);
		System.out.println();
		
		System.out.print("Reverse Post Order:");
		tree.printReversePostOrder(root);
		System.out.println();
		
		System.out.print("BFS:");
		tree.BFS(root);
		System.out.println();
		
		System.out.print("DFS:");
		tree.DFS(root);
		System.out.println();
		
		findKthNode(root,  5);
		
	}

}
