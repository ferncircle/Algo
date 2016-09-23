package com.chawkalla.algorithms.bean;

public class LNode {

	public  int data;
	public LNode(int data) {
		super();
		this.data = data;
	}
	public LNode next;
	
	public LNode getNext() {
		return next;
	}
	public void setNext(LNode next) {
		this.next = next;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
}
