package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-pairs/
 *
 *Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Solution 1: For each word get all its suffixes and check if they are palindrome, if yes reverse the first part of word and check if it exists in map. Do 
same for all prefixes

Better Solution 2: For each word, break that word into two parts for all i=0 to l. Check if str1 is palindrome and then if str2 is palindrome. 
See example Palindrome pair

 */
public class PalindromePairs {

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		
		//add all words in map
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}
		
		for (int i = 0; i < words.length; i++) {
			String curWord=words[i];
			
			//check all suffixes of current word for palindrome
			for (int j = 0; j < curWord.length(); j++) {
				String suffix=curWord.substring(j);
				boolean isPalin=isPalindrome(suffix);				
				if(isPalin){
					StringBuffer sb=new StringBuffer("");
					if(j>0)
						for (int k = j-1; k >=0; k--) {
							sb.append(curWord.charAt(k));
						}
					String matchingPair=sb.toString();
					if(map.containsKey(matchingPair) && (i!=map.get(matchingPair))){
						ArrayList<Integer> pair=new ArrayList<Integer>();
						pair.add(i);
						pair.add(map.get(matchingPair));
						list.add(pair);
					}
				}
				
			}
			
			//now check all prefixes of current word for palindromes
			for (int j = 1; j <= curWord.length(); j++) {
				String prefix=curWord.substring(0,j);
				boolean isPalin=isPalindrome(prefix);				
				if(isPalin){
					StringBuffer sb=new StringBuffer("");
					if(j<curWord.length())
						for (int k = curWord.length()-1; k >= j; k--) {
							sb.append(curWord.charAt(k));
						}
					String matchingPair=sb.toString();
					if(map.containsKey(matchingPair) && (i!=map.get(matchingPair))){
						ArrayList<Integer> pair=new ArrayList<Integer>();
						pair.add(map.get(matchingPair));
						pair.add(i);
						
						list.add(pair);
					}
				}
				
			}
			
			//now check if the reverse of string exists
			StringBuffer wordString=new StringBuffer(curWord);
			String reverseWord=wordString.reverse().toString();
			if(map.containsKey(reverseWord) && (i!=map.get(reverseWord))){
				ArrayList<Integer> pair=new ArrayList<Integer>();
				pair.add(i);
				pair.add(map.get(reverseWord));
				list.add(pair);				
			}
			
		}		
		
		return list;
	}
	
	public List<List<Integer>> palindromePairs1(String[] words) {
		if(words==null || words.length<2)
			return Collections.emptyList();
		List<List<Integer>> list=new ArrayList<List<Integer>>();
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		
		//add all words in map
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], i);
		}
		
		for (int i = 0; i < words.length; i++) {
			String curWord=words[i];
			
			for (int j = 0; j < curWord.length(); j++) {
				String prefix=curWord.substring(0,j+1);				
				String suffix=curWord.substring(j+1);			
				
				if(isPalindrome(suffix)){
					StringBuffer prefixRev=new StringBuffer(prefix).reverse();
					String matchingPair=prefixRev.toString();
					if(map.containsKey(matchingPair)){
						addPair(list, i, map.get(matchingPair));
					}
				}
				
				if(isPalindrome(prefix)){
					StringBuffer suffixRev=new StringBuffer(suffix).reverse();
					String matchingPair=suffixRev.toString();
					if(map.containsKey(matchingPair)){
						addPair(list, map.get(matchingPair), i);
						if(matchingPair.length()==0) //handles the case of empty string (note that it's only added once)
							addPair(list, i, map.get(matchingPair));
					}
				}
				
			}		
			
			
		}		
		
		return list;
	}
	
	public boolean isPalindrome(String s){
		boolean isPalin=true;
		/*if(s.length()==0)
			return false;*/
		if(s.length()==1)
			return true;
		for (int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i)!=s.charAt(s.length()-1-i)){
				isPalin=false;
				break;
			}
		}
		return isPalin;
	}
	
	public void addPair(List<List<Integer>> list, int i, int j){
		if(i==j)
			return;
		ArrayList<Integer> pair=new ArrayList<Integer>();
		pair.add(i);
		pair.add(j);
		list.add(pair);		
	}

	public static void main(String[] args) {
		List<List<Integer>> list=null; 
		
		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(0, 1));
		list.add(Arrays.asList(1, 0));	
		list.add(Arrays.asList(3, 2));
		list.add(Arrays.asList(2, 4));	
		assertThat(new PalindromePairs().palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}), is(list));
				
		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(0, 3));
		list.add(Arrays.asList(4, 0));	
		list.add(Arrays.asList(0, 1));
		list.add(Arrays.asList(1, 0));	
		list.add(Arrays.asList(4, 2));	
		assertThat(new PalindromePairs().palindromePairs(new String[]{"bat", "tab", "cat","ab", "ta"}), is(list));
		
		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(0, 3));
		list.add(Arrays.asList(3, 0));	
		list.add(Arrays.asList(2, 3));
		list.add(Arrays.asList(3, 2));	
		assertThat(new PalindromePairs().palindromePairs(new String[]{"a","abc","aba",""}), is(list));
				
		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(0, 1));
		list.add(Arrays.asList(1, 0));	
		list.add(Arrays.asList(3, 2));
		list.add(Arrays.asList(2, 4));	
		assertThat(new PalindromePairs().palindromePairs1(new String[]{"abcd","dcba","lls","s","sssll"}), is(list));

		
		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(4, 0));	
		list.add(Arrays.asList(0, 3));	
		list.add(Arrays.asList(0, 1));
		list.add(Arrays.asList(1, 0));
		list.add(Arrays.asList(4, 2));	
		assertThat(new PalindromePairs().palindromePairs1(new String[]{"bat", "tab", "cat","ab", "ta"}), is(list));

		list=new ArrayList<List<Integer>>();
		list.add(Arrays.asList(3, 0));	
		list.add(Arrays.asList(0, 3));
		list.add(Arrays.asList(3, 2));	
		list.add(Arrays.asList(2, 3));
		assertThat(new PalindromePairs().palindromePairs1(new String[]{"a","abc","aba",""}), is(list));
		
		System.out.println("All test cases passed");
	}

}
