package com.chawkalla.algorithms.bean;

public class Entry<K,T> {

	public K key;
	public T data;
	public Entry<K,T> next;
	public Entry<K,T> prev;

	
	
	public Entry(T data) {
		super();
		this.data = data;
	}
	
	public Entry(K key, T data, Entry<K,T> prev, Entry<K,T> next) {
		super();
		this.key=key;
		this.data = data;
		this.prev=prev;
		this.next=next;

	}

	public Entry(T data, Entry<K,T> prev, Entry<K,T> next) {
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
