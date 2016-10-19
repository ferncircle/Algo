/**
 * 
 */
package com.chawkalla.algorithms.examples.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.chawkalla.algorithms.ds.SuffixTree;

/**
 * 
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {
		
		
	public String shortestPalindrome(String s) {		
		if(s==null || s.length()==0)
			return s;
		//reverse the string and build suffix tree
		SuffixTree tree=new SuffixTree();
		tree.build(new StringBuffer(s).reverse().toString());
		
		
		//navigate original tree in suffix 
		int charsMatched=tree.getCharactersMatched(s);
		
		String remaining=s.substring(charsMatched);
		StringBuffer sb=new StringBuffer(remaining);
		String palindrome=sb.reverse().toString()+s;
		
		return palindrome;
	}


	
	public static void main(String[] args) {
		
		ShortestPalindrome test =new ShortestPalindrome();
		
		assertThat(test.shortestPalindrome("abcd"), is("dcbabcd"));
		assertTrue("aaacecaaa".equals(test.shortestPalindrome("aacecaaa")));
		assertTrue("aba".equals(test.shortestPalindrome("aba")));
		assertTrue("a".equals(test.shortestPalindrome("a")));
		assertTrue("".equals(test.shortestPalindrome("")));
		assertTrue("ababbabbbababbbabbaba".equals(test.shortestPalindrome("ababbbabbaba")));

		System.out.println("All test cases passed");
	}

}
