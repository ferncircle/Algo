/**
 * 
 */
package com.chawkalla.algorithms.examples.linkedlist;

import com.chawkalla.algorithms.examples.linkedlist.SortLinkedList.ListNode;
import com.chawkalla.algorithms.examples.tree.TreeNode;

/**
 * https://www.interviewbit.com/problems/convert-sorted-list-to-binary-search-tree/
 * 
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 A height balanced BST : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees 
 of every node never differ by more than 1. 
Example :


Given A : 1 -> 2 -> 3
A height balanced BST  :

      2
    /   \
   1     3
 *
 */
public class SortedListToBST {

	private ListNode cur;
	public TreeNode sortedListToBST(ListNode a) {
	    int s=size(a);
	    
	    if(s==0) return null;
	    cur=a;
	    return util(0,s-1);
	}
	
	private TreeNode util(int l, int h){
	    if(l>h) return null;
	    
	    int m=(l+h)/2;
	    
	    TreeNode left=util(l, m-1);
	    TreeNode n=new TreeNode(cur.val);	  
	    cur=cur.next;
	    n.left=left;
	    n.right=util(m+1, h);
	    
	    return n;
	    
	}
	
	private int size(ListNode a){
	    if(a==null) return 0;
	    int s=0;
	    while(a!=null){
	        s++;
	        a=a.next;
	    }
	    return s;
	}

	
	public static void main(String[] args) {
		ListNode a=new ListNode(2);
		ListNode b=new ListNode(4);
		ListNode c=new ListNode(5);
		ListNode d=new ListNode(8);
		ListNode e=new ListNode(9);
		
		a.next=b;b.next=c;c.next=d;d.next=e;
		
		TreeNode n=new SortedListToBST().sortedListToBST(a);
		
		inOrder(n);

	}
	
	public static void inOrder(TreeNode a){
		if(a==null) return;
		inOrder(a.left);
		System.out.println(a.val+" ");
		inOrder(a.right);
	}

}
