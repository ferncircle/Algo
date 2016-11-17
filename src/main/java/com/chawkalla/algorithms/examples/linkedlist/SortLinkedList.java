package com.chawkalla.algorithms.examples.linkedlist;

/**
 * https://leetcode.com/problems/sort-list/
 * 
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 */
public class SortLinkedList {

	public ListNode sortList(ListNode node) {

		if(node==null || node.next==null)
			return node;
		
		int l=getLength(node);		
		ListNode s=sort(node,l);
		
		return s;
	}
	
	public ListNode sort(ListNode node, int length){
		if(node==null || node.next==null || length<=1)
			return node;
		
		int leftCount=length/2;
		int rightCount=length-length/2;
		
		ListNode left=node;
		ListNode leftEnd=getNthNode(node, leftCount);
		ListNode right=leftEnd.next;
		leftEnd.next=null;	
		
		left=sort(left, leftCount);
		right=sort(right, rightCount);
		
		
		//merge 
		ListNode m=merge(left, right);
		
		return m;
	}
	
	public ListNode merge(ListNode a, ListNode b){
		if(a==null )
			return b;
		if(b==null )
			return a;		
		ListNode r=null;
		if(a.val<=b.val){
			r=a;
			r.next=merge(a.next,b);
		}else{
			r=b;
			r.next=merge(a,b.next);
		}		
		return r;
	}
	
	public ListNode getNthNode(ListNode n, int l){
		ListNode node=n;
		if(n==null)
			return node;
		while(l>1){
			node=node.next;
			l--;
		}
		return node;
	}
	
	public int getLength(ListNode n) {
		int length=0;
		if(n==null)
			return length;
		while(n!=null){
			n=n.next;
			length++;
		}		
		return length;
					
	}
	
	public void print(ListNode n){
		System.out.println();
		if(n==null)
			return;
		while(n!=null){
			System.out.print(n.val+" ");
			n=n.next;
		}
		
	}

	public static void main(String[] args) {
		
		ListNode a=new ListNode(23);
		ListNode b=new ListNode(12);
		ListNode c=new ListNode(5);
		ListNode d=new ListNode(14);
		ListNode e=new ListNode(40);
		ListNode f=new ListNode(2);
		ListNode g=new ListNode(45);
		ListNode h=new ListNode(29);
		ListNode i=new ListNode(23);
		ListNode j=new ListNode(49);
		
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;g.next=h;h.next=i;i.next=j;
		
		//c.next=d;f.next=g;g.next=j;
		SortLinkedList test=new SortLinkedList();
		test.print(test.sortList(a));
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;g.next=h;h.next=i;i.next=j;
		test.print(test.sortList(d));
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;g.next=h;h.next=i;i.next=j;
		test.print(test.sortList(i));
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;g.next=h;h.next=i;i.next=j;
		test.print(test.sortList(j));
		//test.print(test.merge(c, f));
		//System.out.println();
		//System.out.println(test.getLength(a));
		//System.out.println(test.getNthNode(c, 2).val);
	}

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

}

