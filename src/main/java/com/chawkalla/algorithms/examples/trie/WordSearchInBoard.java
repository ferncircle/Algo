package com.chawkalla.algorithms.examples.trie;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chawkalla.algorithms.ds.Trie;


/**
 * https://leetcode.com/problems/word-search-ii/
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
 *
 */
public class WordSearchInBoard {

	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> foundWords=new ArrayList<String>();
		
		if(board==null || board.length==0 || board[0].length==0
				|| words==null || words.length==0)
			return foundWords;
		
		//insert all words in Trie Dictionary
		Trie trie=new Trie();		
		for (int i = 0; i < words.length; i++) {
			trie.insert(words[i]);
		}
		
		Set<String> found=new HashSet<String>();
		//go through each cell
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				wordSearchUtil(board, trie, i, j, new HashSet<Integer>(), new StringBuffer(), found);
			}
		}
		
		List<String> wordsFound=new ArrayList<String>(found);
		Collections.sort(wordsFound);
		return wordsFound;
	}
	
	public void wordSearchUtil(char[][] board, Trie trie, int i, int j, HashSet<Integer> visited, StringBuffer wordSoFar, Set<String> found){
		if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return;
        }
		
		int cell=i*board[0].length+j;
		
		if(visited.contains(cell))
			return;		
		
		wordSoFar.append(board[i][j]); //use this char
		
		String word=wordSoFar.toString();
		if(!trie.startsWith(word)){
			wordSoFar.deleteCharAt(wordSoFar.length()-1); //delete this char
			return;
		}
		visited.add(cell);
		
		if(trie.search(word))
			found.add(word);
		
		wordSearchUtil(board, trie, i+1, j, visited, wordSoFar, found);
		wordSearchUtil(board, trie, i, j+1, visited, wordSoFar, found);
		wordSearchUtil(board, trie, i-1, j, visited, wordSoFar, found);
		wordSearchUtil(board, trie, i, j-1, visited, wordSoFar, found);
		
		wordSoFar.deleteCharAt(wordSoFar.length()-1); //delete this char
		
		visited.remove(cell);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		long before=System.currentTimeMillis();
				
		String fileName="C:\\code\\Github\\sandbox\\Misc\\src\\test\\resources\\dictionary_words.txt";
		List<String> listOfWords=new ArrayList<String>();
		String line=null;	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] words = line.split(",");
            listOfWords.addAll(Arrays.asList(words));
        }
        br.close();
		System.out.println("total words="+listOfWords.size());
		String[] mat=new String[]{"seenew","tmriva","obsibd","wmysen","ltsnsa","iezlgn"};
		char[][] board=new char[mat.length][mat[0].length()];
		for (int i = 0; i < mat.length; i++) {
			
			for (int j = 0; j < mat[i].length(); j++) {
				board[i][j]=mat[i].charAt(j);
			}
		}
		before=System.currentTimeMillis();
		System.out.println(new WordSearchInBoard().findWords(board, listOfWords.toArray(new String[listOfWords.size()])));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		
		before=System.currentTimeMillis();
		assertThat(new WordSearchInBoard().findWords(new char[][]{				
				  {'a','b'},
				  {'a','a'}		
		}, new String[]{"aaa","aaab","aaba","aba","baa"}), is(Arrays.asList("aaa","aaab","aaba","aba","baa")));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		before=System.currentTimeMillis();
		assertThat(new WordSearchInBoard().findWords(new char[][]{				
				  {'a','a'}		
		}, new String[]{"aaa"}), is(Arrays.asList()));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		before=System.currentTimeMillis();
		assertThat(new WordSearchInBoard().findWords(new char[][]{				
				  {'o','a','a','n'},
				  {'e','t','a','e'},
				  {'i','h','k','r'},
				  {'i','f','l','v'}				
		}, new String[]{"oath","pea","eat","rain"}), is(Arrays.asList("eat","oath")));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		
		
		System.out.println("All test cases passed");
	}

}
