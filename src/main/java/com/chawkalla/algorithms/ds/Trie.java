package com.chawkalla.algorithms.ds;

import com.chawkalla.algorithms.bean.TrieNode;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode t = root;
		for(int i=0; i<word.length(); i++){ 
			char c = word.charAt(i);
			
			if(!t.containsKey(c)){
				TrieNode child = new TrieNode(c);
				t.addKey(c, child);
				
			}			
			t = t.getKey(c);
			//set leaf node
			if(i==word.length()-1)
				t.isLeaf = true;    
		}
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode t = searchNode(word);

		if(t != null && t.isLeaf) 
			return true;
		else
			return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		if(searchNode(prefix) == null) 
			return false;
		else
			return true;
	}

	private TrieNode searchNode(String str){
		return searchNode(root, str);
	}
	
	private TrieNode searchNode(TrieNode t, String str){
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(t.containsKey(c)){
				t = t.getKey(c);
			}else{
				return null;
			}
		}

		return t;
	}
	
	public boolean searchRegex(String str){
		return searchRegex(root, str);
	}
	
	private boolean searchRegex(TrieNode node, String str){
		if(node.isLeaf && (str==null || str.length()==0))
			return true;
		if(!node.isLeaf && (str==null || str.length()==0))
			return false;
		
		char currentChar=str.charAt(0);
		String remaining=str.substring(1);
		if(currentChar=='.'){
			//get all paths
			for(char child:node.getAllKeys()){
				TrieNode childNode=node.getKey(child);
				if(searchRegex(childNode, remaining))
					return true;	
			}
			//none found
			return false;
		}else{
			if(node.containsKey(currentChar))
				return searchRegex(node.getKey(currentChar), remaining);
			else 
				return false;
		}
		
	}
	
	public String getClosestMatch(String str){
		StringBuffer sb=new StringBuffer();
		//System.out.println("finding closest match in trie "+str);
		getClosestMatch(root, str, sb);
		return sb.toString();
	}
	
	private int getClosestMatch(TrieNode currentNode, String str, StringBuffer buf){
		if(str==null || str.length()==0 || currentNode==null || currentNode.getAllKeys()==null)
			return 0;
		
		boolean done=false;
		int i=0;
		int matchCounter=0;
		while(!done){
			char c = str.charAt(i);	
			
			if(currentNode.containsKey(c)){ //character matched
				currentNode=currentNode.getKey(c);
				buf.append(c);
				matchCounter++;
			}else{ //character didn't match
				if(currentNode.getAllKeys().size()==1){ //only one option to fork
					char misMatchedChar=currentNode.getAllKeys().iterator().next();
					buf.append(misMatchedChar);
					currentNode=currentNode.getKey(misMatchedChar);
				}else{ //multiple children options to fork, go recursive to get maximum match
					
					int longestRemainingMatch=0;
					String longestRemainingMatchStr="";
					for (Character a:currentNode.getAllKeys()) {
						StringBuffer sb=new StringBuffer(a);
						String remaining=str.substring(i);
						int match=getClosestMatch(currentNode.getKey(a), remaining, sb);
						if(match>longestRemainingMatch){
							longestRemainingMatch=match;
							longestRemainingMatchStr=sb.toString();
						}
							
					}
					buf.append(longestRemainingMatchStr);
					matchCounter+=longestRemainingMatch;
					done=true;
					
				}
			}
			i++;
			if(i>str.length()-1)
				done=true;
		}
		
		return matchCounter;
	}
}
