package com.chawkalla.algorithms.bean;

import java.util.HashMap;

public class TrieNode {
	public char c;
	public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	public boolean isLeaf;

	public TrieNode() {}

	public TrieNode(char c){
		this.c = c;
	}

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(c+" ,");
		if(children!=null)
			sb.append(children.keySet()+ " ,");
		sb.append("isLeaf="+isLeaf);
		return sb.toString();
	}
}
