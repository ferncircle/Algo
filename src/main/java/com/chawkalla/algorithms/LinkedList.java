package com.chawkalla.algorithms;

import com.chawkalla.algorithms.bean.LNode;
import com.chawkalla.algorithms.bean.TNode;

public class LinkedList {

	public LNode head;


	public static void main(String[] args) {
		LinkedList list=new LinkedList();
		list.push(29);
		list.push(26);
		list.push(20);
		list.push(18);
		list.push(10);
		list.push(5);
		list.push(3);

		list.print();

		list.convertToBST();

		list.reverse();
		list.print();
	}



	public void convertToBST(){
		LNode temp=head;
		TNode root=sortedListToBSTRecur(countNodes(head));
		BST.printInOrder(root);
		head=temp;
	}

	//idea is to do inorder traversal of the tree
	TNode sortedListToBSTRecur(int n) 
	{

		/* Base Case */
		if (n <= 0) 
			return null;

		/* Recursively construct the left subtree */
		TNode left = sortedListToBSTRecur(n / 2);

		/* head_ref now refers to middle node, 
           make middle node as root of BST*/
		TNode root = new TNode(head.data);

		// Set pointer to left subtree
		root.left = left;

		/* Change head pointer of Linked List for parent 
           recursive calls */
		head = head.next;

		/* Recursively construct the right subtree and link it 
           with root. The number of nodes in right subtree  is 
           total nodes - nodes in left subtree - 1 (for root) */
		root.right = sortedListToBSTRecur(n - n / 2 - 1);

		return root;
	}

	//create window of [prev,curr,next]
	//just change the next pointers of each
	public void reverse(){
		
		if(head==null)
			return;
		LNode current=head;
		LNode prev=null;
		LNode next=current.next;

		while(next!=null){			
			LNode nextToNext=next.next;  //save nexttonext
			current.next=prev;//change pointers
			next.next=current;

			//now move the window
			prev=current;
			current=next;
			next=nextToNext;
		}
		head=current;
	}

	public void push (int data){
		LNode node=new LNode(data);
		node.setNext(head);
		head=node;
	}

	public void print(){
		LNode cursor=head;
		System.out.println();

		while(cursor!=null){
			System.out.print(cursor.getData()+" ");
			cursor=cursor.next;
		}
		System.out.println();
	}

	int countNodes(LNode head) 
	{
		int count = 0;
		LNode temp = head;
		while (temp != null)
		{
			temp = temp.next;
			count++;
		}
		return count;
	}

}
