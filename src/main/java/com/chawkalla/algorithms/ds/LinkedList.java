package com.chawkalla.algorithms.ds;

import com.chawkalla.algorithms.BST;
import com.chawkalla.algorithms.bean.Entry;
import com.chawkalla.algorithms.bean.TNode;

public class LinkedList<K, T> {

	public Entry<K,T> head;
	public Entry<K,T> last;
	public int size;

	public void convertToBST(){
		Entry<K,T> temp=head;
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
		Entry<K,T> current=head;
		Entry<K,T> prev=null;
		Entry<K,T> next=current.next;

		while(next!=null){			
			Entry<K,T> nextToNext=next.next;  //save nexttonext
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
		Entry<K,T> node=new Entry<K,T>(data);
		node.next=head;
		head=node;
		size++;
	}
	
	public void push (Entry<K,T> node){
		if(node==null)
			return;
		if(head==null){ //first node
			head=node;
			last=node;
		}else{
			node.next=head;
			head.prev=node;			
			head=node;
		}
		
		size++;
	}

	public void add (Entry<K,T> node){
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

	public Entry<K,T> removeFirst(){
		Entry<K,T> toBeRemoved=null;
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

	public void removeAndMoveToLast(Entry<K,T> node){
		if(node==null || size<=1 || last==node)
			return;
		//remove from current position
		remove(node);	//will reduce the size	
		//append at end
		last.next=node;
		node.prev=last;
		node.next=null;
		last=node;		
		//add back the size
		size++;
	}

	public void remove(Entry<K,T> node){
		if(node==null)
			return;
		//remove from current position
		Entry<K,T> before=node.prev;
		Entry<K,T> next=node.next;
		
		node.prev=null;
		node.next=null;		

		if(head==last){ //only node
			head=null; last=null;
		}		
		else if(before!=null && next!=null){ //middle node
			before.next=next;
			next.prev=before;
		}
		else if(node==head){ //first node
			head=next;
			head.prev=null;
		}
		else if(node==last){//last node
			last=before;
			last.next=null;
		}		
		size--;		
			
	}

	public Entry<K,T> getBefore(Entry<K,T> currentNode){
		if(currentNode==null)
			return null;
		return currentNode.prev;
	}

	public void insertBefore(Entry<K,T> currentNode, Entry<K,T> beforeNode){
		if(beforeNode==null || currentNode==null)
			return;
		Entry<K,T> currentBefore=currentNode.prev;

		currentNode.prev=beforeNode;
		beforeNode.next=currentNode;
		beforeNode.prev=currentBefore;
		if(currentBefore!=null)
			currentBefore.next=beforeNode;	
		else
			head=beforeNode;
		size++;
	}

	public Entry<K,T> getAfter(Entry<K,T> currentNode){
		if(currentNode==null)
			return null;
		return currentNode.next;
	}

	public void insertAfter(Entry<K,T> currentNode, Entry<K,T> afterNode){
		if(afterNode==null || currentNode==null)
			return;
		Entry<K,T> currentAfter=currentNode.next;

		currentNode.next=afterNode;
		afterNode.prev=currentNode;
		afterNode.next=currentAfter;
		if(currentAfter!=null)
			currentAfter.prev=afterNode;
		else
			last=afterNode;
		size++;
	}




	public void print(){
		Entry<K,T> cursor=head;
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

	int countNodes(Entry<K,T> head) 
	{
		int count = 0;
		Entry<K,T> temp = head;
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
