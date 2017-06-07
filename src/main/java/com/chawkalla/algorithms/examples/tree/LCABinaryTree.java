package com.chawkalla.algorithms.examples.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.


        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 */
public class LCABinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
		if(cur==null || cur==p || cur==q) return cur;
		
		TreeNode left=lowestCommonAncestor(cur.left, p, q);
		TreeNode right=lowestCommonAncestor(cur.right, p, q);
		if(left!=null && right!=null)
			return cur;
		if(left!=null)
			return left;
		return right;
	}

	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

		if(root==null || p==null || q==null)
			return null;		
		TreeNode lca=null;
		
		LinkedList<TreeNode> pathP=getNodePathBFS(root, p);
		LinkedList<TreeNode> pathq=getNodePathBFS(root, q);
		
		if(pathP.size()>pathq.size()){
			int i=pathP.size()-pathq.size();
			for (int j = 0; j < i; j++) {
				pathP.removeFirst();
			}
		}
		if(pathP.size()<pathq.size()){
			int i=pathq.size()-pathP.size();
			for (int j = 0; j < i; j++) {
				pathq.removeFirst();
			}
		}
		if(pathP!=null && pathq!=null){
			Iterator<TreeNode> pathPIter=pathP.iterator();
			Iterator<TreeNode> pathQIter=pathq.iterator();
			while(pathPIter.hasNext() && pathQIter.hasNext()){
				TreeNode pNode=pathPIter.next();
				TreeNode qNode=pathQIter.next();
				if(pNode==qNode){
					lca=pNode;
					break;
				}
			}
		}
		return lca;
	}
	
	/**
	 * Recursive solution
	 * if node found THEN create linkedlist and return it (so that only one list gets created)
	 */
	public LinkedList<TreeNode> getNodePathDFS(TreeNode node, final TreeNode nodeToFind){
		if(node==null)
			return null;
		
		if(node==nodeToFind){
			LinkedList<TreeNode> path=new LinkedList<TreeNode>();
			path.add(node);
			return path;
		}
		//go left
		LinkedList<TreeNode> leftPath=getNodePathDFS(node.left, nodeToFind);
		if(leftPath!=null){
			leftPath.add(node);
			return leftPath;
		}
		//go right
		LinkedList<TreeNode> rightPath=getNodePathDFS(node.right, nodeToFind);
		if(rightPath!=null){
			rightPath.add(node);
			return rightPath;
		}		
		return null;
			
	}
	
	/**
	 * Iterative way of finding node path from root
	 */
	public LinkedList<TreeNode> getNodePathBFS(TreeNode node, final TreeNode nodeToFind){
		if(node==null)
			return null;
		LinkedList<TreeNode> path=new LinkedList<TreeNode>();
		
		//backtrack map (parent map)
		HashMap<TreeNode, TreeNode> parentMap=new HashMap<TreeNode, TreeNode>();
		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		queue.add(node);
		while(!queue.isEmpty()){
			TreeNode currentNode=queue.remove();
			if(currentNode==nodeToFind)
				break;
			if(currentNode.left!=null){
				parentMap.put(currentNode.left, currentNode);
				queue.add(currentNode.left);
			}
			if(currentNode.right!=null){
				parentMap.put(currentNode.right, currentNode);
				queue.add(currentNode.right);
			}
		}
		
		//create LinkedList based on backtrack map
		boolean done=false;
		TreeNode curNode=nodeToFind;
		while(!done){
			path.add(curNode);
			curNode=parentMap.get(curNode);
			if(curNode==null)
				done=true;
		}
		
		return path;
	}

	public static void main(String[] args) {
		TreeNode _0=new TreeNode(0);
		TreeNode _1=new TreeNode(1);
		TreeNode _2=new TreeNode(2);
		TreeNode _3=new TreeNode(3);
		TreeNode _4=new TreeNode(4);
		TreeNode _5=new TreeNode(5);
		TreeNode _6=new TreeNode(6);
		TreeNode _7=new TreeNode(7);
		TreeNode _8=new TreeNode(8);
		
		_3.left=_5;
		_5.left=_6;
		_5.right=_2;
		_2.right=_4;
		_2.left=_7;
		_3.right=_1;
		_1.left=_0;
		_1.right=_8;
		
		TreeNode root=_3;
		

		assertThat(new LCABinaryTree().lowestCommonAncestor(root, _5, _4), is(_5));
		assertThat(new LCABinaryTree().lowestCommonAncestor(root, _5, _1), is(_3));
		assertThat(new LCABinaryTree().lowestCommonAncestor(root, _6, _0), is(_3));
		assertThat(new LCABinaryTree().lowestCommonAncestor(root, _6, _7), is(_5));
		
		System.out.println("All test cases passed");

	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
