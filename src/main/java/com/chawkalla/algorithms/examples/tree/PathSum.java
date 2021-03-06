package com.chawkalla.algorithms.examples.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * https://leetcode.com/problems/path-sum-ii/
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
 */
public class PathSum {

	public List<List<Integer>> pathSum(TreeNode node, int sum) {
		List<List<Integer>> lists=new ArrayList<List<Integer>>();
		
		if(node==null)
			return lists;
		if(node.left==null && node.right==null){
			if(node.val==sum){
				lists=new ArrayList<List<Integer>>();
				List<Integer> l=new ArrayList<Integer>();
				l.add(node.val);
				lists.add(l);
				
				return lists;
			}
		}
		List<List<Integer>> leftList=null;
		if(node.left!=null){
			leftList=pathSum(node.left, sum-node.val);
			if(leftList!=null){
				for(List<Integer> l:leftList){
					if(l!=null){
						l.add(0,node.val);
					}
				}
			}
		}
		
		List<List<Integer>> rightList=null;
		if(node.right!=null){
			rightList=pathSum(node.right, sum-node.val);
			if(rightList!=null){
				for(List<Integer> l:rightList){
					if(l!=null){
						l.add(0,node.val);
					}
				}
			}
		}
		
		if(leftList!=null){
			if(rightList!=null)
				leftList.addAll(rightList);
			lists=leftList;
		}else if(rightList!=null){
			lists=rightList;
		}
			
		
		return lists;
	}

	public static void main(String[] args) {
		PathSum test=new PathSum();

		TreeNode a=new TreeNode(5);
		TreeNode b=new TreeNode(4);
		TreeNode c=new TreeNode(8);
		TreeNode d=new TreeNode(11);
		TreeNode e=new TreeNode(7);
		TreeNode f=new TreeNode(2);
		TreeNode g=new TreeNode(4);
		TreeNode h=new TreeNode(5);

		a.left=b; a.right=c;
		b.left=d;
		c.right=g;
		g.left=h;
		d.left=e;
		d.right=f;

		List<List<Integer>> lists=test.pathSum(a, 22);
		if(lists!=null)
			for(List<Integer> l:lists)
				System.out.println(l);
		
		System.out.println(test.pathSum(null, 1));

	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}
