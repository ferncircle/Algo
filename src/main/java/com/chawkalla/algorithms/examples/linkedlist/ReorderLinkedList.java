package com.chawkalla.algorithms.examples.linkedlist;



/**
 * https://leetcode.com/problems/reorder-list/
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *
 */
public class ReorderLinkedList {

	public void reorderList(ListNode head) {
		if(head==null)
			return;
		
		int size=countNodes(head);
		if(size<3)
			return;
		int middle=(size%2==0)?size/2:size/2+1;
		
		//get middle node
		ListNode middleNode=getIthNode(head, middle);
		
		ListNode a=head;
		ListNode b=middleNode.next;
		middleNode.next=null; //separate two lists
		
		//reverse b
		b=reverse(b);
		
		//merge two lists
		a=merge(a, b);
		
		head=a;
	}

	
	
	public ListNode getIthNode(ListNode node, int i){
		if(node==null)
			return node;
		
		ListNode ithNode=null;
		int counter=1;
		while(counter<i){
			node=node.next;
			counter++;
		}
		ithNode=node;
		return ithNode;
		
	}
	
	int countNodes(ListNode head) 
	{
		int count = 0;
		ListNode temp = head;
		while (temp != null)
		{
			temp = temp.next;
			count++;
		}
		return count;
	}
	
	public ListNode reverse(ListNode head){

		if(head==null)
			return null;
		ListNode current=head;
		ListNode prev=null;
		ListNode next=current.next;

		while(next!=null){			
			ListNode nextToNext=next.next;  //save nexttonext
			current.next=prev;//change pointers
			next.next=current;

			//now move the window
			prev=current;
			current=next;
			next=nextToNext;
		}
		head=current;
		
		return head;
	}
	
	public ListNode merge(ListNode a, ListNode b){
		if(a==null) return b;
		if(b==null) return a;
		
		ListNode merged=a;
		
		
		boolean done=false;
		while(!done){
			//remove first item from b
			ListNode bNode=b;
			b=b.next;
			
			//insert bNode into a
			ListNode aNext=a.next;
			a.next=bNode;
			bNode.next=aNext;
			a=aNext;
			
			if(b==null)
				done=true;
		}		
		return merged;
	}
	
	public void print(ListNode head){
		ListNode cursor=head;

		while(cursor!=null){
			System.out.print(cursor.val+" ");
			cursor=cursor.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(4);	
		ListNode node5=new ListNode(5);		
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		
		ReorderLinkedList test=new ReorderLinkedList();
		test.print(node1);
		test.reorderList(node1);
		test.print(node1);

	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }		
	}

}
