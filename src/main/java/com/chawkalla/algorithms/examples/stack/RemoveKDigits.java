package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-k-digits/
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is 
 * the smallest possible.
 * http://stackoverflow.com/questions/28223580/how-to-get-the-least-number-after-deleting-k-digits-from-the-input-number
 *
 * Goal is to keep stack ascending starting from bottom
 */
public class RemoveKDigits {

	public String removeKdigits(String num, int k) {
		if(k<=0)
			return num;
		if(num.length()<=k)
			return "0";
		
		int n=num.length();
		
		Stack<Integer> st=new Stack<Integer>();
		
		for (int i = 0; i < num.length(); i++) {
			int curDigit=Character.getNumericValue(num.charAt(i));
			if(st.size()>0){
				//get remaining size
				int remaining=n-1-i;
				
				//peek top of stack
				int top=st.peek();
				if(top>curDigit){
					//remove from stack until stack becomes ascending(1,2,3 where 3 is top) and you still can 
					//potentially make up n-k digits using remaining+stack
					while(top>curDigit && (st.size()+remaining)>=(n-k)){
						//System.out.println("Removing "+st.peek()+" from stack");
						st.pop();
						if(st.size()==0)
							break;
						top=st.peek();
					}
				}		
			}
			st.push(curDigit);
			
		}
		
		//remove excess of n-k chars from stack if any
		while(st.size()>n-k)
			st.pop();
		
		//unload into string and reverse
		StringBuffer sb=new StringBuffer();
		while(!st.isEmpty())
			sb.append(st.pop());		
		sb.reverse();		

		//remove starting 0s
		int i = 0;
		for (; i<sb.length() && sb.charAt(i) =='0'; i++) {}
		if(i>0)
			sb.delete(0, i);
		
		if(sb.length()==0)
			return "0";
		else		
			return sb.toString();
    }
	public static void main(String[] args) {
		

		assertThat(new RemoveKDigits().removeKdigits("10", 1), is("0"));
		assertThat(new RemoveKDigits().removeKdigits("10200", 1), is("200"));
		assertThat(new RemoveKDigits().removeKdigits("1432219", 3), is("1219"));
		assertThat(new RemoveKDigits().removeKdigits("24635", 3), is("23"));
		assertThat(new RemoveKDigits().removeKdigits("10", 2), is("0"));
		
		System.out.println("All test cases passed");

	}

}
