package com.chawkalla.algorithms.bean;

public class TNode {
	public int value;
	public TNode left;
	public TNode right;
	
	
	public TNode() {
		super();
	}
	
	
	public TNode(int value) {
		super();
		this.value = value;
	}


	public TNode(int value, TNode left, TNode right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}


	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public TNode getLeft() {
		return left;
	}
	public void setLeft(TNode left) {
		this.left = left;
	}
	public TNode getRight() {
		return right;
	}
	public void setRight(TNode right) {
		this.right = right;
	}
	

}
