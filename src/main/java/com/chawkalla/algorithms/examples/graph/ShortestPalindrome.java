/**
 * 
 */
package com.chawkalla.algorithms.examples.graph;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

/**
 * @author sfargose
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {

	class TNode{
		boolean eof=false;
		HashMap<String, TNode> edges=new HashMap<String, TNode>();
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
					if(j==(a.length-1))
						n.eof=true;
					current.edges.put(c, n);
					current=n;
				}else{
					current=current.edges.get(c);
				}
			}
		}
		
		return root;
	}	
	
	public String shortestPalindrome(String s) {		
		if(s==null || s.length()==0)
			return s;
		//reverse the string and build suffix tree
		TNode root=buildEdgesSuffixTree(new StringBuffer(s).reverse().toString());
		
		//navigate original tree in suffix 
		int i=0;
		int charsMatched=0;
		TNode current=root;
		while(true){
			if(current.eof)
				charsMatched=i;
			
			if(i>=s.length() || current==null)
				break;
			
			String c=""+s.charAt(i);		
			if(current.edges.containsKey(c)){
				i++;
				current=current.edges.get(c);
			}
			else
				break;
		}
		
		String remaining=s.substring(charsMatched);
		StringBuffer sb=new StringBuffer(remaining);
		String palindrome=sb.reverse().toString()+s;
		System.out.println(palindrome);
		return palindrome;
	}


	
	public static void main(String[] args) {
		
		ShortestPalindrome test =new ShortestPalindrome();
		
		assertTrue("dcbabcd".equals(test.shortestPalindrome("abcd")));
		assertTrue("aaacecaaa".equals(test.shortestPalindrome("aacecaaa")));
		assertTrue("aba".equals(test.shortestPalindrome("aba")));
		assertTrue("a".equals(test.shortestPalindrome("a")));
		assertTrue("".equals(test.shortestPalindrome("")));
		assertTrue("ababbabbbababbbabbaba".equals(test.shortestPalindrome("ababbbabbaba")));

		System.out.println("All test cases passed");
	}

}
