package com.chawkalla.algorithms.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chawkalla.algorithms.bean.TrieNode;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		insert(word, -1);
	}
	
	public void insert(String word, int indexOfWord) {
		TrieNode t = root;
		for(int i=0; i<word.length(); i++){ 
			char c = word.charAt(i);
			
			if(!t.containsChar(c)){
				TrieNode child = new TrieNode(c);
				t.addChar(c, child);
				
			}			
			t = t.getCharNode(c);
			//set leaf node
			if(i==word.length()-1){
				t.isLeaf = true; 
				if(indexOfWord>=0)
					t.indexOfWord=indexOfWord;
			}
				
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
	
	public List<String> startsWithWords(String prefix) {
		List<String> list=new ArrayList<String>();
		TrieNode p=searchNode(prefix);
		if(p != null) {
			getAllWords("", p, list);
		}
		return list;
	}
	
	private void getAllWords(String prefix, TrieNode node, List<String> list){
		if(node==null)
			return;
		if(node.isLeaf){
			String word=prefix;
			if(node.indexOfWord!=null)
				word=prefix+":"+node.indexOfWord;
			list.add(word);
			
		}
		Set<Character> childChars=node.getAllChars();
		if(childChars!=null){
			for(char ch:childChars){
				getAllWords(prefix+ch, node.getCharNode(ch), list);
			}
		}
		
	}

	private TrieNode searchNode(String str){
		return searchNode(root, str);
	}
	
	private TrieNode searchNode(TrieNode t, String str){
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(t.containsChar(c)){
				t = t.getCharNode(c);
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
			for(char child:node.getAllChars()){
				TrieNode childNode=node.getCharNode(child);
				if(searchRegex(childNode, remaining))
					return true;	
			}
			//none found
			return false;
		}else{
			if(node.containsChar(currentChar))
				return searchRegex(node.getCharNode(currentChar), remaining);
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
		if(str==null || str.length()==0 || currentNode==null || currentNode.getAllChars()==null)
			return 0;
		
		boolean done=false;
		int i=0;
		int matchCounter=0;
		while(!done){
			char c = str.charAt(i);	
			
			if(currentNode.containsChar(c)){ //character matched
				currentNode=currentNode.getCharNode(c);
				buf.append(c);
				matchCounter++;
			}else{ //character didn't match
				if(currentNode.getAllChars().size()==1){ //only one option to fork
					char misMatchedChar=currentNode.getAllChars().iterator().next();
					buf.append(misMatchedChar);
					currentNode=currentNode.getCharNode(misMatchedChar);
				}else{ //multiple children options to fork, go recursive to get maximum match
					
					int longestRemainingMatch=0;
					String longestRemainingMatchStr="";
					for (Character a:currentNode.getAllChars()) {
						StringBuffer sb=new StringBuffer(a);
						String remaining=str.substring(i);
						int match=getClosestMatch(currentNode.getCharNode(a), remaining, sb);
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
