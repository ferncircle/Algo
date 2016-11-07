package com.chawkalla.algorithms.examples.trie;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


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

	public class Cell{
		int row;
		int col;
		String wordSoFar;
		HashSet<Cell> visited=new HashSet<Cell>();
		public Cell(int row, int col, String wordSoFar) {
			super();
			this.row = row;
			this.col = col;
			this.wordSoFar = wordSoFar;
		}
		@Override
		public String toString() {
			return "row="+row+" col="+col+" word="+wordSoFar;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
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
			Cell other = (Cell) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
		
		
		
	}
	public List<String> findWords(char[][] board, String[] words) {
		List<String> foundWords=new ArrayList<String>();
		
		if(board==null || board.length==0 || board[0].length==0
				|| words==null || words.length==0)
			return foundWords;
		
		HashSet<String> foundWordsSet=new HashSet<String>();
		//insert all words in Trie Dictionary
		Trie trie=new Trie();		
		for (int i = 0; i < words.length; i++) {
			trie.insert(words[i]);
		}
		
		//go through each cell
		for (int i = 0; i < board.length; i++) {
			char[] row=board[i];
			for (int j = 0; j < row.length; j++) {
				//do BFS
				Queue<Cell> queue=new LinkedList<Cell>();
				HashSet<Cell> visited=new HashSet<Cell>();
				
				Cell root=new Cell(i, j, ""+board[i][j]);
				root.visited=visited;
				if(trie.startsWith(root.wordSoFar))
					queue.add(root);
				while(!queue.isEmpty()){
					Cell cell=queue.remove();
					visited=cell.visited;
					
					visited.add(cell);
					
					if(trie.search(cell.wordSoFar)){
						foundWordsSet.add(cell.wordSoFar);
					}
					
					//get neighbors
					//top
					if(cell.row-1>=0){
						Cell top=new Cell(cell.row-1, cell.col, cell.wordSoFar+board[cell.row-1][cell.col]);
						if(!visited.contains(top) && trie.startsWith(top.wordSoFar)){
							HashSet<Cell> visitedBefore=new HashSet<Cell>();
							visitedBefore.addAll(visited);
							top.visited=visitedBefore;
							
							queue.add(top);
						}
					}
					
					//right
					if(cell.col+1<row.length){
						Cell right=new Cell(cell.row, cell.col+1, cell.wordSoFar+board[cell.row][cell.col+1]);
						if(!visited.contains(right) && trie.startsWith(right.wordSoFar)){
							HashSet<Cell> visitedBefore=new HashSet<Cell>();
							visitedBefore.addAll(visited);
							right.visited=visitedBefore;
							
							queue.add(right);
						}
					}
					
					//bottom
					if(cell.row+1<board.length){
						Cell bottom=new Cell(cell.row+1, cell.col, cell.wordSoFar+board[cell.row+1][cell.col]);
						if(!visited.contains(bottom) && trie.startsWith(bottom.wordSoFar)){
							HashSet<Cell> visitedBefore=new HashSet<Cell>();
							visitedBefore.addAll(visited);
							bottom.visited=visitedBefore;
							
							queue.add(bottom);
						}
					}
					
					//left
					if(cell.col-1>=0){
						Cell left=new Cell(cell.row, cell.col-1, cell.wordSoFar+board[cell.row][cell.col-1]);
						if(!visited.contains(left) && trie.startsWith(left.wordSoFar)){
							HashSet<Cell> visitedBefore=new HashSet<Cell>();
							visitedBefore.addAll(visited);
							left.visited=visitedBefore;
							
							queue.add(left);
						}
					}
					
				}
				
			}
		}
		foundWords.addAll(foundWordsSet);
		Collections.sort(foundWords);
		return foundWords;
	}
	
	public class TrieNode {
		public char c;
		public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		TrieNode[] letters=new TrieNode[26];
		public boolean isLeaf;
		
		public boolean containsKey(char key){
			//return children.containsKey(key);
			return letters[(int)key-97]!=null;
		}
		
		public void addKey(char key, TrieNode node){
			//children.put(key, node);
			letters[(int)key-97]=node;
		}
		
		public TrieNode getKey(char key){
			//return children.get(key);
			return letters[(int)key-97];
		}
		
		public Set<Character> getAllKeys(){
			//return children.keySet();
			
			HashSet<Character> keys=new HashSet<Character>();
			for (int i = 0; i < letters.length; i++) {
				if(letters[i]!=null)
					keys.add((char)(i+97));
				
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
	}
	
	
	public static void main(String[] args) throws Exception {
		long before=System.currentTimeMillis();
				
		String fileName="C:\\Github\\sandbox\\Algo\\src\\test\\resources\\dictionary_words.txt";
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
