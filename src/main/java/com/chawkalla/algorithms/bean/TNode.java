package com.chawkalla.algorithms.bean;

public class TNode<T> {
	public T value;
	public TNode<T> left;
	public TNode<T> right;
	
	
	public TNode() {
		super();
	}
	
	
	public TNode(T value) {
		super();
		this.value = value;
	}


	public TNode(T value, TNode<T> left, TNode<T> right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}


	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public TNode<T> getLeft() {
		return left;
	}
	public void setLeft(TNode<T> left) {
		this.left = left;
	}
	public TNode<T> getRight() {
		return right;
	}
	public void setRight(TNode<T> right) {
		this.right = right;
	}
	

}
