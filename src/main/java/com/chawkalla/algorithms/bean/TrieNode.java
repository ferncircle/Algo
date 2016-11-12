package com.chawkalla.algorithms.bean;

import java.util.HashSet;
import java.util.Set;

public class TrieNode {
	public char c;
	public final int OFFSET=0; //for a-z=97, for 0-9=48, for all chars=0
	public final int SIZE=256;  //for a-z=26, for 0-9=10, for all chars=256
	//public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	TrieNode[] letters=new TrieNode[SIZE];
	public Integer indexOfWord;
	
	public boolean isLeaf;
	
	public boolean containsChar(char ch){
		//return children.containsKey(key);
		return letters[ch-OFFSET]!=null;
	}
	
	public void addChar(char ch, TrieNode node){
		//children.put(key, node);
		letters[ch-OFFSET]=node;
	}
	
	public TrieNode getCharNode(char ch){
		//return children.get(key);
		return letters[ch-OFFSET];
	}
	
	public Set<Character> getAllChars(){
		//return children.keySet();
		
		HashSet<Character> keys=new HashSet<Character>();
		for (int i = 0; i < letters.length; i++) {
			if(letters[i]!=null)
				keys.add((char)(i+OFFSET));
			
		}
		return keys;
		
	}

	public TrieNode() {}

	public TrieNode(char c){
		this.c = c;
	}

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(c+" ,");
		if(letters!=null)
			sb.append(letters+ " ,");
		sb.append("isLeaf="+isLeaf);
		return sb.toString();
	}
}
