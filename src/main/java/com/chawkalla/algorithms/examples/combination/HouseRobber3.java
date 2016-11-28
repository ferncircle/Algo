package com.chawkalla.algorithms.examples.combination;

public class HouseRobber3 {


	public int rob(TreeNode root) {
		int total=0;

		if(root==null)
			return 0;
		int includeCurrent=0;
		
		//include current item 
		includeCurrent=root.val;
		if(root.left!=null){			
			includeCurrent+=rob(root.left.left);
			includeCurrent+=rob(root.left.right);
		}
		if(root.right!=null){			
			includeCurrent+=rob(root.right.left);
			includeCurrent+=rob(root.right.right);
		}
		
		//don't include current item
		int noCurrentItem=0;
		noCurrentItem+=rob(root.left);
		noCurrentItem+=rob(root.right);
		
		total=Math.max(includeCurrent, noCurrentItem);
		
		return total;
	}
	public static void main(String[] args) {
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

		System.out.println(new HouseRobber3().rob(a));
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
