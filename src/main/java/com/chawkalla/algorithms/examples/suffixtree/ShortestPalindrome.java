/**
 * 
 */
package com.chawkalla.algorithms.examples.suffixtree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;

import com.chawkalla.algorithms.ds.SuffixTree;
import com.chawkalla.algorithms.examples.string.KMP;

/**
 * 
 * https://leetcode.com/problems/shortest-palindrome/
 * 
 * Goal: find longest prefix that is palindrome
 */
public class ShortestPalindrome {


	//using suffix tree
	public String shortestPalindrom1(String s) {		
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

	//suboptimal solution
	public String shortestPalindrome2(String s) {		
		if(s==null || s.length()==0)
			return s;
		//find the longest palindrome starting at index 0
		int longestStartingPalindrome=0;
		for (int i = s.length()-1; i>=0;i--) {
			boolean palindrome=true;
			//check if this substring ending at i is palindrome
			for (int j = 0; j < i+1/2; j++) {
				if(s.charAt(j)!=s.charAt(i-j)){
					palindrome=false;
					break;
				}

			}
			if(palindrome){
				longestStartingPalindrome=i;
				break;
			}
		}

		String remaining=s.substring(longestStartingPalindrome+1);
		StringBuffer sb=new StringBuffer(remaining);
		String palindrome=sb.reverse().toString()+s;

		return palindrome;
	}

	//optimal solution using KMP 
	public String shortestPalindrome(String s) {		
		if(s==null || s.length()==0)
			return s;

		//create string+$+revString
		StringBuffer orig=new StringBuffer(s);

		String compound=orig.toString()+"$"+orig.reverse().toString();

		int palindromeEnd=KMP.lps(compound)[compound.length()-1];
		String remaining=s.substring(palindromeEnd);

		StringBuffer sb=new StringBuffer(remaining);

		String palindrome= sb.reverse().toString()+s;
		return palindrome;
	}



	public static void main(String[] args) {

		assertThat(new ShortestPalindrome().shortestPalindrome("abcd"), is("dcbabcd"));
		assertThat(new ShortestPalindrome().shortestPalindrome("aacecaaa"), is("aaacecaaa"));
		assertThat(new ShortestPalindrome().shortestPalindrome("aba"), is("aba"));
		assertThat(new ShortestPalindrome().shortestPalindrome("a"), is("a"));
		assertThat(new ShortestPalindrome().shortestPalindrome("a"), is("a"));
		assertThat(new ShortestPalindrome().shortestPalindrome("ababbbabbaba"), is("ababbabbbababbbabbaba"));
		assertThat(new ShortestPalindrome().shortestPalindrome("aefekadkaldwe"), is("ewdlakdakefeaefekadkaldwe"));
		assertThat(new ShortestPalindrome().shortestPalindrome("aallaldjj"), is("jjdlallaallaldjj"));

		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < 10000; i++) {
			sb.append((char)(new Random().nextInt(26)+ 'a'));
			//sb.append('a');
		}

		long before=0;
		String testString=sb.toString();
		before=System.currentTimeMillis();
		new ShortestPalindrome().shortestPalindrome(testString);
		System.out.println("Excution time="+(System.currentTimeMillis()-before));

		System.out.println("All test cases passed");
	}

}
