package com.chawkalla.algorithms;

import com.chawkalla.algorithms.bean.LNode;
import com.chawkalla.algorithms.bean.TNode;

public class LinkedList<K, T> {

	public LNode<K,T> head;
	public LNode<K,T> last;
	public int size;

	public void convertToBST(){
		LNode<K,T> temp=head;
		TNode<T> root=sortedListToBSTRecur(countNodes(head));
		BST.printInOrder(root);
		head=temp;
	}

	//idea is to do inorder traversal of the tree
	TNode<T> sortedListToBSTRecur(int n) 
	{

		/* Base Case */
		if (n <= 0) 
			return null;

		/* Recursively construct the left subtree */
		TNode<T> left = sortedListToBSTRecur(n / 2);

		/* head_ref now refers to middle node, 
           make middle node as root of BST*/
		TNode<T> root = new TNode<T>(head.data);

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
		LNode<K,T> current=head;
		LNode<K,T> prev=null;
		LNode<K,T> next=current.next;

		while(next!=null){			
			LNode<K,T> nextToNext=next.next;  //save nexttonext
			current.next=prev;//change pointers
			next.next=current;

			//now move the window
			prev=current;
			current=next;
			next=nextToNext;
		}
		head=current;
	}

	public void push (T data){
		LNode<K,T> node=new LNode<K,T>(data);
		node.next=head;
		head=node;
		size++;
	}
	
	public void add (LNode<K,T> node){
		if(size==0){
			head=last=node;
		}else{
			node.prev=last;
			node.next=null;
			last.next=node;
			last=node;
		}
		size++;
	}

	public LNode<K,T> removeFirst(){
		LNode<K,T> toBeRemoved=null;
		if(size>0){
			toBeRemoved=head;
			if(size==1){
				head=last=null;
			}			
			else{			
				head=head.next;
				head.prev=null;
			}			
			size--;
			toBeRemoved.next=toBeRemoved.prev=null;
		}
		

		return toBeRemoved;
	}

	public void removeAndMoveToLast(LNode<K,T> node){
		if(node==null || size<=1 || last==node)
			return;
		//remove from current position
		LNode<K,T> before=node.prev;
		LNode<K,T> next=node.next;
		
		if(before==null){
			head=next;
			head.prev=null;
		}			
		else{
			before.next=next;
			next.prev=before;
		}
		//append at end
		last.next=node;
		node.prev=last;
		node.next=null;
		last=node;
	}



	public void print(){
		LNode<K,T> cursor=head;
		System.out.println();

		while(cursor!=null){
			System.out.print(cursor.data+" ");
			cursor=cursor.next;
		}
		System.out.println();
	}
	
	public int size(){
		return size;
	}

	int countNodes(LNode<K,T> head) 
	{
		int count = 0;
		LNode<K,T> temp = head;
		while (temp != null)
		{
			temp = temp.next;
			count++;
		}
		return count;
	}
	

	public static void main(String[] args) {
		LinkedList<Integer, Integer> list=new LinkedList<Integer, Integer>();
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



}
