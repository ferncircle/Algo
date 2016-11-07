package com.chawkalla.algorithms.examples.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.chawkalla.algorithms.bean.TNode;

public class BST<T> {
	
	public static int index=0;

	public TNode<Integer> createSampleTree(){
		TNode<Integer> root=new TNode<Integer>(10);
		TNode<Integer> node5=new TNode<Integer>(5);
		TNode<Integer> node2=new TNode<Integer>(2);
		TNode<Integer> node7=new TNode<Integer>(7);
		TNode<Integer> node15=new TNode<Integer>(15);
		TNode<Integer> node12=new TNode<Integer>(12);
		TNode<Integer> node18=new TNode<Integer>(18);
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
	
	public static <T> void printInOrder(TNode<T> node){
		if(node==null)
			return;
		printInOrder(node.getLeft());
		System.out.print(node.getValue()+" ");
		printInOrder(node.getRight());
	}
	
	public static <T> void printInOrderUsingStack(TNode<T> node){
		if(node==null)
			return;
		boolean done=false;
		Stack<TNode<T>> stack=new Stack<TNode<T>>();
		TNode<T> cursor=node;
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
	
	public static<T> void printPreOrder(TNode<T> node){
		if(node==null)
			return;
		System.out.print(node.getValue()+" ");

		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}
	
	public void printPostOrder(TNode<T> node){
		if(node==null)
			return;
		printPostOrder(node.getLeft());		
		printPostOrder(node.getRight());
		System.out.print(node.getValue()+" ");
	}
	
	public void printReversePostOrder(TNode<T> node){
		if(node==null)
			return;
		printPostOrder(node.getRight());
		printPostOrder(node.getLeft());		
		System.out.print(node.getValue()+" ");
	}
	

	public void BFS(TNode<T> node){
		Queue<TNode<T>> queue=new LinkedList<TNode<T>>();
		if(node!=null)
			queue.add(node);
		while(!queue.isEmpty()){
			TNode<T> item=queue.remove();
			System.out.print(item.getValue()+" ");
			if(item.getLeft()!=null)
				queue.add(item.getLeft());
			if(item.getRight()!=null)
				queue.add(item.getRight());
		}
	}
	
	public void DFS(TNode<T> node){
		Stack<TNode<T>> stack=new Stack<TNode<T>>();
		if(node!=null)
			stack.push(node);
		while(!stack.isEmpty()){
			TNode<T> item=stack.pop();
			System.out.print(item.getValue()+" ");
			if(item.getRight()!=null)
				stack.push(item.getRight());

			
			if(item.getLeft()!=null)
				stack.push(item.getLeft());
			
		}
	}
	
	public static <T> void findKthNode(TNode<T> node, final int k){
		if(node==null)
			return;
		findKthNode(node.getLeft(),  k);
		if(index==k)
			System.out.print(node.getValue()+" ");
		index++;
		findKthNode(node.getRight(),  k);
	}
	
	
	public static <T> void main(String[] args) {
		BST<Integer> tree=new BST<Integer>();

		TNode<Integer> root=tree.createSampleTree();
		
		System.out.print("In Order:");
		BST.printInOrder(root);
		System.out.println();
		
		System.out.print("In Order Using stack:");
		BST.printInOrderUsingStack(root);
		System.out.println();
		
		System.out.print("Pre Order:");
		BST.printPreOrder(root);
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
