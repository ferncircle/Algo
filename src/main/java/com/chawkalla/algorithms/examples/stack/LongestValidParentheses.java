package com.chawkalla.algorithms.examples.stack;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class LongestValidParentheses {

	public int longestValidParentheses(String s) {
		int l=0;
		if(s==null || s.length()==0)
			return 0;
		

		int longestSoFar=0;
		Map<Integer, Integer> matchingBracket=getMatchingBrackets(s, '(', ')');
		int lastMatchEnd=-1;		
		
		for(int i:matchingBracket.keySet()){
			if(i<=lastMatchEnd)
				continue;
			if((i-1)!=lastMatchEnd)
				longestSoFar=0;
				
			int endBracket=matchingBracket.get(i);
			lastMatchEnd=endBracket;
			longestSoFar+=(endBracket-i)+1;
			if(longestSoFar>l)
				l=longestSoFar;				
		}
		
		return l;
		
	}
	
	public static Map<Integer, Integer> getMatchingBrackets(String s, final char openChar, final char closeChar){
		TreeMap<Integer, Integer> matchingBracket=new TreeMap<Integer, Integer>();
		Stack<Integer> st=new Stack<Integer>();
		
		for (int i = 0; i < s.length(); i++) {	
			char currentchar=s.charAt(i);
			if(currentchar==openChar){
				st.push(i);
			}else if(currentchar==closeChar){
				if(!st.empty()){
					matchingBracket.put(st.pop(), i);
				}
			}
			
		}
		return matchingBracket;
	}
	public static void main(String[] args) {
		LongestValidParentheses test=new LongestValidParentheses();

		System.out.println(test.longestValidParentheses("((()))())"));
		System.out.println();
		System.out.println(test.longestValidParentheses("((())(((()))("));
		System.out.println();
		System.out.println(test.longestValidParentheses("())((()))("));
		

		/*for (int i = 0; i < s.length(); i++) {
			if(matchingBracket.containsKey(i) && i>lastMatchEnd){
				if((i-1)!=lastMatchEnd)
					longestSoFar=0;
					
				int endBracket=matchingBracket.get(i);
				lastMatchEnd=endBracket;
				longestSoFar+=(endBracket-i)+1;
				if(longestSoFar>l)
					l=longestSoFar;				
			}
		}*/
	}

}
