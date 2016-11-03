package com.chawkalla.algorithms.examples.bits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Random;


public class MaxXorTwoNumbers {

	private final static int MSB=31;
		
	public int findMaximumXOR(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return 0;
		int max=0;
		
		Trie trie=new Trie();
		trie.insert(getBinaryString(nums[0], MSB));
		//System.out.println();
		for (int i = 1; i < nums.length; i++) {
			//convert number to binary
			String numString=getBinaryString(nums[i], MSB);
			//System.out.println(numString);
			String flippedNum=getBinaryString(~nums[i], MSB);
			//search for closest match
			String closestMatch=trie.getClosestMatch(flippedNum);
			//System.out.println("closest match="+closestMatch);
			int closestMatchedNum=Integer.parseInt(closestMatch,2);
			
			int xor=nums[i] ^ closestMatchedNum;
			if(xor>max)
				max=xor;
			
			trie.insert(numString);
			//System.out.println();
			
		}
		
		return max;
	}
	

	public static String getBinaryString(int n, int msb){
		StringBuffer sb=new StringBuffer();
		for(int j=msb;j>=0;j--){
			sb.append((((n >> j) & 1)==1)?'1':'0');
		}
		return sb.toString();
		
	}
	
		
	public class Trie {

		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			//System.out.println("Adding to trie "+word);
			HashMap<Character, TrieNode> children = root.children;

			for(int i=0; i<word.length(); i++){
				char c = word.charAt(i);

				TrieNode t;
				if(children.containsKey(c)){
					t = children.get(c);
				}else{
					t = new TrieNode(c);
					children.put(c, t);
				}

				children = t.children;

				//set leaf node
				if(i==word.length()-1)
					t.isLeaf = true;    
			}
		}

		public String getClosestMatch(String str){
			StringBuffer sb=new StringBuffer();
			//System.out.println("finding closest match in trie "+str);
			getClosestMatch(root, str, sb);
			return sb.toString();
		}
		
		private int getClosestMatch(TrieNode currentNode, String str, StringBuffer buf){
			if(str==null || str.length()==0 || currentNode==null || currentNode.children==null)
				return 0;
			
			boolean done=false;
			int i=0;
			int matchCounter=0;
			while(!done){
				char c = str.charAt(i);	
				
				if(currentNode.children.containsKey(c)){ //character matched
					currentNode=currentNode.children.get(c);
					buf.append(c);
					matchCounter++;
				}else{ //character didn't match
					if(currentNode.children.size()==1){ //only one option to fork
						char misMatchedChar=currentNode.children.keySet().iterator().next();
						buf.append(misMatchedChar);
						currentNode=currentNode.children.get(misMatchedChar);
					}else{ //multiple children options to fork, go recursive to get maximum match
						
						int longestRemainingMatch=0;
						String longestRemainingMatchStr="";
						for (Character a:currentNode.children.keySet()) {
							StringBuffer sb=new StringBuffer(a);
							String remaining=str.substring(i);
							int match=getClosestMatch(currentNode.children.get(a), remaining, sb);
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
	
	public class TrieNode {
		public char c;
		public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		public boolean isLeaf;

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

	
	

	public int findMaximumXOR1(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return 0;
		int max=0;
		
		for (int i = 0; i < nums.length; i++) {
			
			for (int j = i+1; j < nums.length; j++) {
				int val=nums[i] ^ nums[j];
				if(val>max)
					max=val;
			}
		}
		
		return max;
	}

	public static void main(String[] args) {

		assertThat(new MaxXorTwoNumbers().findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}), is(28));
		assertThat(new MaxXorTwoNumbers().findMaximumXOR(
				new int[]{3,10,5,25,2,8, 23, 5934, 19, 239, 2, 4,12132}), is(14410));
		
		
		int[] b=new int[]{4653,6,234,6,7};
		assertThat(new MaxXorTwoNumbers().findMaximumXOR(b), 
				is(new MaxXorTwoNumbers().findMaximumXOR1(b)	) );
		
		//load test
		int n=100000;
		int [] a=new int[n];
		Random rn = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i]=rn.nextInt(1000000000) + 1;
		}
		
		long before=0;
		long after=0;
		
		/*before=System.currentTimeMillis();
		int x1=new MaxXorTwoNumbers().findMaximumXOR1(a);	
		after=System.currentTimeMillis();
		System.out.println("Excution 1="+(after-before));*/
		
		before=System.currentTimeMillis();
		//int x=new MaxXorTwoNumbers().findMaximumXOR(a);	
		after=System.currentTimeMillis();
		System.out.println("Excution 2="+(after-before));
		
		//assertThat(x, is(x1));
		System.out.println("All test cases passed");
		
	}

}
