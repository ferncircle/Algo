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
	
	public LNode(K key, T data, LNode<K,T> prev, LNode<K,T> next) {
		super();
		this.key=key;
		this.data = data;
		this.prev=prev;
		this.next=next;

	}

	public LNode(T data, LNode<K,T> prev, LNode<K,T> next) {
		super();
		this.data = data;
		this.prev=prev;
		this.next=next;

	}

	@Override
	public String toString() {
		StringBuffer buf=new StringBuffer();
		buf.append("{");
		buf.append("key="+key);		
		buf.append(", data="+data);
		buf.append("}");
		return buf.toString();
	}
	
}
