package com.chawkalla.algorithms.examples.design;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 * Solution: 
 * 1) Use pre-order traversal to serialize tree, put $ if current node is null
 * 2) Use pre-order to reconstruct tree from string data
 * 3) Note that this can  be extended to n-ary tree. We just need to store how many children a node had
 * 		Put that number after each node.
 * Time: O(n)
 * Space: O(n)
 */
public class SerializeDeserialzeBinaryTree {

	private final String MARKER="$";
	private final String DELIM=",";
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root==null)
			return "";
		StringBuffer buf=new StringBuffer();
		serializePreOrder(root, buf);
		
		return buf.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data==null || data.length()==0)
			return null;
		return deserializePreOrder(data.split(DELIM));
	}
	
	public void serializePreOrder(TreeNode node, StringBuffer buf){
		if(node==null){
			buf.append(DELIM+MARKER);
			return;
		}
		if(buf.length()!=0)
			buf.append(DELIM);
		buf.append(node.val);
		serializePreOrder(node.left, buf);
		serializePreOrder(node.right, buf);
	}
	
	int cur=0;
	public TreeNode deserializePreOrder(String[] data){
		if(cur==data.length || data[cur].equals(MARKER)){			
			return null;
		}
		int val=Integer.parseInt(data[cur]);
		TreeNode curNode=new TreeNode(val);
		cur++;
		curNode.left=deserializePreOrder(data);
		cur++;
		curNode.right=deserializePreOrder(data);
		
		return curNode;
	}

	public static void main(String[] args) {

		TreeNode _1=new TreeNode(1);
		TreeNode _2=new TreeNode(2);
		TreeNode _3=new TreeNode(3);
		TreeNode _5=new TreeNode(5);
		TreeNode _6=new TreeNode(6);
		TreeNode _7=new TreeNode(7);
		
		_3.left=_5;
		_5.left=_6;
		_5.right=_2;
		_2.left=_7;
		_3.right=_1;
		/*		3
			   / \
			  5	  1
			 / \
			6 	2
				/
			   7
	*/
			 
		
		TreeNode root=_3;
		printInOrder(root);
		System.out.println();
		String serializedData=new SerializeDeserialzeBinaryTree().serialize(root);
		System.out.println(serializedData);
		root=new SerializeDeserialzeBinaryTree().deserialize(serializedData);
		printInOrder(root);

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
