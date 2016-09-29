/**
 * 
 */
package com.chawkalla.algorithms.examples;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author sfargose
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {

	class TNode{

		HashMap<String, TNode> edges=new HashMap<String, ShortestPalindrome.TNode>();
		
		
	}
	class Node{
		String character=null;
		ArrayList<Node> children=new ArrayList<ShortestPalindrome.Node>();
		
		
		public Node(String character) {
			super();
			this.character = character;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((character == null) ? 0 : character.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (character == null) {
				if (other.character != null)
					return false;
			} else if (!character.equals(other.character))
				return false;
			return true;
		}

	}
	
	public Node buildSuffixTree(String s){
		Node root=new Node("");
		char[] a=s.toCharArray();
		
		for(int i=(a.length-1);i>=0;i--){
			Node current=root;
			
			for(int j=i;j<a.length;j++){
				Node n=new Node(""+a[j]);
				int index=current.children.indexOf(n);
				if(!(index>=0)){
					current.children.add(n);
					current=n;
				}else{
					current=current.children.get(index);
				}
			}
		}
		
		return root;
	}
	
	public TNode buildEdgesSuffixTree(String s){
		TNode root=new TNode();
		char[] a=s.toCharArray();
		
		for(int i=(a.length-1);i>=0;i--){
			TNode current=root;
			
			for(int j=i;j<a.length;j++){
				String c=""+a[j];			
				if(!current.edges.containsKey(c)){
					TNode n=new TNode();
					current.edges.put(c, n);
					current=n;
				}else{
					current=current.edges.get(c);
				}
			}
		}
		
		return root;
	}
	
	public int maxMatch(Node n, String s){
		int count=0;
		
		
		return count;
		
		
	}
	
	
	public String shortestPalindrome(String s) {		
		if(s==null || s.length()==0)
			return s;
		
		TNode root=buildEdgesSuffixTree(new StringBuffer(s).reverse().toString());
		
		//navigate original tree in suffix 
		
		return null;
	}


	
	public static void main(String[] args) {
		
		ShortestPalindrome test =new ShortestPalindrome();
		
		assertTrue("dcbabcd".equals(test.shortestPalindrome("abcd")));
		assertTrue("aaacecaaa".equals(test.shortestPalindrome("aacecaaa")));

		System.out.println("All test cases passed");
	}

}
