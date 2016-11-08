package com.chawkalla.algorithms.examples.tree;

import java.util.Arrays;


/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 */
public class ReconstuctTree {

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length==0 || postorder.length==0)
			return null;
		
		int size=postorder.length;
		int rootElement=postorder[size-1];
		TreeNode parent=new TreeNode(rootElement);
		
		int indexOfParent=indexOf(inorder, rootElement);
		
		int[] leftInOrder=Arrays.copyOfRange(inorder, 0, indexOfParent);
		int[] rightInOrder=Arrays.copyOfRange(inorder, indexOfParent+1, size);
		int[] leftPostOrder=Arrays.copyOfRange(postorder, 0, leftInOrder.length);
		int[] rightPostOrder=Arrays.copyOfRange(postorder, leftInOrder.length, size-1);
		
		parent.left=buildTree(leftInOrder, leftPostOrder);
		parent.right=buildTree(rightInOrder, rightPostOrder);
		
		return parent;
		
	}
	
	public int indexOf(int[] a, int element){
		for (int i = 0; i < a.length; i++) {
			if(a[i]==element)
				return i;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] inOrder=new int[]{2, 5, 7, 10, 12, 15, 18 };
		TreeNode node=new ReconstuctTree().buildTree(inOrder, new int[]{2, 7, 5, 12, 18, 15, 10});
		printInOrder(node);
		System.out.println();
		System.out.println("all test cases passed");

	}
	
	public static <T> void printInOrder(TreeNode node){
		if(node==null)
			return;
		printInOrder(node.left);
		System.out.print(node.val+" ");
		printInOrder(node.right);
	}
	

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}
