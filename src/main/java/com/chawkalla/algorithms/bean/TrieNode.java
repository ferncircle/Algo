package com.chawkalla.algorithms.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TrieNode {
	public char c;
	public final int OFFSET=48; //for a-z=97, for 0-9=48
	public final int SIZE=10;  //for a-z=26, for 0-9=10
	public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	TrieNode[] letters=new TrieNode[SIZE];
	public boolean isLeaf;
	
	public boolean containsKey(char key){
		//return children.containsKey(key);
		return letters[(int)key-OFFSET]!=null;
	}
	
	public void addKey(char key, TrieNode node){
		//children.put(key, node);
		letters[(int)key-OFFSET]=node;
	}
	
	public TrieNode getKey(char key){
		//return children.get(key);
		return letters[(int)key-OFFSET];
	}
	
	public Set<Character> getAllKeys(){
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
		if(children!=null)
			sb.append(children.keySet()+ " ,");
		sb.append("isLeaf="+isLeaf);
		return sb.toString();
	}
}
