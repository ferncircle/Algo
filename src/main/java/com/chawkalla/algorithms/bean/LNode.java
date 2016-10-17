package com.chawkalla.algorithms.bean;

public class LNode<K,T> {

	public K key;
	public T data;
	public LNode<K,T> next;
	public LNode<K,T> prev;

	
	
	public LNode(T data) {
		super();
		this.data = data;
	}

	public LNode(T data, LNode<K,T> prev, LNode<K,T> next) {
		super();
		this.data = data;
		this.prev=prev;
		this.next=next;

	}

}
