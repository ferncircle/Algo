package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://codefights.com/challenge/Wg7u4KrHsYbZqvDar
 * You are given a string s that consists of English letters, punctuation marks, whitespace characters and brackets. It is guaranteed that the brackets in s form a regular bracket sequence.

Your task is to reverse the strings in each pair of matching parenthesis, starting from the innermost one.

Example

For string "s = a(bc)de" the output should be
reverseParentheses(s) = "acbde".
 */
public class ReverseParenthesis {

	public String reverseParentheses(String s) {
		return reverseParenthesis(s, false);
	}
	
	public String reverseParenthesis(String s, boolean reverse){
		if(s==null || s.length()<2)
			return s;
		String rev=null;
		Map<Integer, Integer> matchingBracket=getMatchingBrackets(s, '(', ')');
		
		boolean done=false;
		int i=0;
		StringBuffer buf=new StringBuffer();
		while(!done){
			char curChar=s.charAt(i);
			int j=i;
			if(Character.isDigit(curChar) || curChar=='('){
				j=matchingBracket.get(i);
				String subExpression=s.substring(i+1,j);
				if(subExpression!=null && subExpression.length()>0)
					buf.append(reverseParenthesis(subExpression, true));
				i=j+1;
			}else{
				buf.append(curChar);
				i++;
			}
			if(i>=s.length())
				done=true;
		}
		if(reverse)
			buf.reverse();
		rev=buf.toString();
		return rev;
	}
	
	public static Map<Integer, Integer> getMatchingBrackets(String s, final char openChar, final char closeChar){
		HashMap<Integer, Integer> matchingBracket=new HashMap<Integer, Integer>();
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
		
		assertThat(new ReverseParenthesis().reverseParentheses("a(bc)de"), is("acbde"));
		assertThat(new ReverseParenthesis().reverseParentheses("a(bcdefghijkl(mno)p)q"), is("apmnolkjihgfedcbq"));
		assertThat(new ReverseParenthesis().reverseParentheses("co(de(fight)s)"), is("cosfighted"));
		assertThat(new ReverseParenthesis().reverseParentheses("Code(Cha(lle)nge)"), is("CodeegnlleahC"));
		System.out.println("All test cases passed");
	}

}
