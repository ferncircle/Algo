/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *https://codefights.com/interview/EDaACHNYHyH6qQFAL
 *
 *Given two words, beginWord and endWord, and a wordList of approved words, find the length of the shortest transformation 
 *sequence from beginWord to endWord such that:

Only one letter can be changed at a time
Each transformed word must exist in the wordList.
Return the length of the shortest transformation sequence, or 0 if no such transformation sequence exists.

Note: beginWord does not count as a transformed word. You can assume that beginWord and endWord are not empty and are not the
 same.

Example

For beginWord = "hit", endWord = "cog", and wordList = ["hot", "dot", "dog", "lot", "log", "cog"], the output should be
wordLadder(beginWord, endWord, wordList) = 5.

The shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with a length of 5.
 *
 */
public class WordLadder {

	int wordLadder(String beginWord, String endWord, String[] wordList) {
		int dist=1;
		
		Map<String, List<String>> map=new HashMap<String, List<String>>();
		
		for (int i = 0; i < wordList.length; i++) {
			oneEditWordUtil(wordList[i], map, true);
		}
		
		Queue<String> queue=new LinkedList<String>();
		HashSet<String> visited=new HashSet<String>();
		String marker="|";
		queue.add(beginWord);
		queue.add(marker);
		boolean found=false;
		while(!queue.isEmpty()){
			String curWord=queue.poll();
			if(curWord.equals(endWord)){
				found=true;
				break;
			}
			if(visited.contains(curWord))
				continue;
			if(curWord.equals(marker)){
				if(!queue.isEmpty()){
					dist++;
					queue.add(marker);
				}
				continue;
					
			}
			for (String child:oneEditWordUtil(curWord, map, false)) {
				queue.add(child);
			}
			visited.add(curWord);
			
		}
		if(!found)
			return 0;
		else
			return dist;
	}
	
	private HashSet<String> oneEditWordUtil(String curWord, Map<String, List<String>> map, boolean add){
		HashSet<String> set=new HashSet<String>();
		StringBuffer word=new StringBuffer(curWord);
		for (int j = 0; j < word.length(); j++) {
			char curChar=word.charAt(j);
			word.setCharAt(j, '*');
			String key=word.toString();
			if(add){
				map.compute(key, (k,v)->(v==null)?new ArrayList<String>():v);
				map.get(key).add(curWord);
			}else
				if(map.get(key)!=null)
					set.addAll(map.get(key));
			word.setCharAt(j, curChar);
		}
		return set;
	}
	
	
	public static void main(String[] args) {
		
		assertThat(new WordLadder().wordLadder("hit", "cog", new String[]{"hot", 
				 "dot", 
				 "dog", 
				 "lot", 
				 "log", 
				 "cog"}), is(5));
		assertThat(new WordLadder().wordLadder("hot", "dog", new String[]{"hot", 
				 "dog", 
				 "cog", 
				 "pot", 
				 "dot"}), is(3));

		System.out.println("All test cases passed");
	}

}
