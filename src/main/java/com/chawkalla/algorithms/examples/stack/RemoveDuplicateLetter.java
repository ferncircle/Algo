package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-duplicate-letters/
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
 *
 *Solution:
 *https://www.careercup.com/question?id=5758790009880576
 */
public class RemoveDuplicateLetter {

	public String removeDuplicateLetters(String s) {
		String purged=null;
		if(s==null || s.length()==0)
			return "";
		if(s.length()==1)
			return s;
		
		int[] charCount=new int[26];
		for (int i = 0; i < s.length(); i++) 
			charCount[s.charAt(i)-'a']++;
				
		Stack<Character> st=new Stack<Character>();
		boolean done=false;
		int curIndex=0;
		while(!done){
			char curChar=s.charAt(curIndex);
			if(!st.isEmpty()){
				
				if(!st.isEmpty() && curChar<st.peek() && charCount[st.peek()-'a']>1){					
					charCount[st.pop()-'a']--;		
						
				}
			}
			st.push(curChar);
			curIndex++;
			
			if(curIndex>=s.length())
				done=true;
		}
		StringBuffer buf=new StringBuffer();
		while(!st.isEmpty()){
			if(charCount[st.peek()-'a']==1){
				buf.append(st.peek());
				
			}else
				charCount[st.peek()-'a']--;
			st.pop();
		}
		buf.reverse();
		
		purged=buf.toString();
		return purged;
	}
	
	public String removeRepeated(String s){
		
		int N=s.length();
		
		ArrayList<Stack<Integer>> pos=new ArrayList<Stack<Integer>>();
		//Stack<Integer>[] pos=new Stack[26];
		for (int i = 0; i < 26; i++) {
			pos.add(i,new Stack<Integer>());
		}
		
		//int lastPos[26]={-1};
		int[] lastPos=new int[26];
		for (int i = 0; i < lastPos.length; i++) {
			lastPos[i]=-1;
		}
		
		for (int i=N-1;i>=0;i--){
			int cur= s.charAt(i)-'a';
			if (pos.get(cur).isEmpty())
				lastPos[cur]=i;

			pos.get(cur).push(i);
		}

		boolean[] visited=new boolean[26];
		//vector<bool> visited(26,false);
		String ans="";
		for (int i=0;i<N;i++){
			int cur = s.charAt(i)-'a';

			if (visited[cur]) continue;
			if (pos.get(cur).size()==1){ //last character of cur
				ans+=s.charAt(i);
				visited[cur]=true;
			}
			else{
				boolean isSmaller=false;
				int minpos=N;
				for (int k=0;k<26;k++){
					if (!visited[k] && !pos.get(k).empty())
						minpos=Math.min(minpos,lastPos[k]);
				}

				for (int k=0;k<cur && !isSmaller;k++){
					if (visited[k] || pos.get(k).empty()) continue;
					if (pos.get(k).peek()<=minpos)
						isSmaller=true;
				}

				if (isSmaller==false){
					ans+=s.charAt(i);
					visited[cur]=true;
				}
			}

			pos.get(cur).pop();
		}

		return ans;
	}

	public static void main(String[] args) {
		/*assertThat(new RemoveDuplicateLetter().removeDuplicateLetters("jdkfgjdkfjgd"), is("dfgjk"));
		
		assertThat(new RemoveDuplicateLetter().removeDuplicateLetters("bcabc"), is("abc"));
		assertThat(new RemoveDuplicateLetter().removeDuplicateLetters("cbacdcbc"), is("acdb"));
*/
		
		assertThat(new RemoveDuplicateLetter().removeRepeated("jdkfgjdkfjgd"), is("dfgjk"));
		assertThat(new RemoveDuplicateLetter().removeRepeated("bcabc"), is("abc"));
		assertThat(new RemoveDuplicateLetter().removeRepeated("cbacdcbc"), is("acdb"));
		System.out.println("All test cases passed");

	}

}
