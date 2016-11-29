package com.chawkalla.algorithms.examples.design;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * 
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 */
public class BSTIterator {
	Stack<TreeNode> st;
	TreeNode cur;
	
	public BSTIterator(TreeNode root) {
		st=new Stack<TreeNode>();
		cur=root;			
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return (cur!=null || !st.isEmpty());
	}

	/** @return the next smallest number */
	public int next() {
		int val=0;

		boolean done=false;		
		while(!done){
			if(cur!=null){
				st.push(cur);
				cur=cur.left;
			}else if(!st.isEmpty()){
				cur=st.pop();
				val=cur.val;
				cur=cur.right;
				done=true;
			}else
				done=true; //done when stack empty here
		}
		return val;
	}
	public static void main(String[] args) {
		TreeNode root=new TreeNode(10);
		TreeNode node5=new TreeNode(5);
		TreeNode node2=new TreeNode(2);
		TreeNode node7=new TreeNode(7);
		TreeNode node15=new TreeNode(15);
		TreeNode node12=new TreeNode(12);
		TreeNode node18=new TreeNode(18);
		/*
		 * 					10		
		 * 			5				15
		 * 		2		7		12		18
		 * 
		 * 
		 * 
		 */
		
		root.left=node5;
		root.right=node15;
		node5.left=node2;
		node5.right=node7;
		node15.left=node12;
		node15.right=node18;

		BSTIterator iter=new BSTIterator(root);
		while(iter.hasNext())
			System.out.println(iter.next());
	}
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}
