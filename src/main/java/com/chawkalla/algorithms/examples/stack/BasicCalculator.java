/**
 * 
 */
package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
 */
public class BasicCalculator {

	public int calculate(String s) {
		if(s==null || s.length()==0)
			return 0;
		s=s.trim();
		if(s.length()==0)
			return 0;
		Map<Integer, Integer> matchingBracket=LongestValidParentheses.getMatchingBrackets(s, '(', ')');
		
		int total=0;
		
		boolean done=false;
		int i=0;
		Stack<String> st=new Stack<String>();
		while(!done){
			char curChar=s.charAt(i);
			if(Character.isDigit(curChar) || curChar=='('){
				int d=0;
				int j=i;
				if(Character.isDigit(curChar)){					
					while (j < s.length() && Character.isDigit(s.charAt(j))){
						d=d*10+s.charAt(j)-'0';
						j++;
					}						
					i=j;
				}else if(curChar=='('){
					j=matchingBracket.get(i);
					String subExpression=s.substring(i+1,j);
					d=calculate(subExpression);
					i=j+1;
				}		
				
				
				if(!st.isEmpty()){
					String op=st.pop();
					int prevDigit=Integer.parseInt(st.pop());
					if(op=="+")
						total=prevDigit+d;
					else if(op=="-")
						total=prevDigit-d;
					st.push(""+total);
					
				}else
					st.push(""+d);
				
				
			}else if(curChar=='+'){
				st.push("+");
				i++;
			}else if(curChar=='-'){
				st.push("-");
				i++;
			}
			else{
				i++;
			}
			if(i>=s.length())
				done=true;
				
			
		}
		total=Integer.parseInt(st.pop());
		return total;
	}

	
	public static void main(String[] args) {

		assertThat(new BasicCalculator().calculate("(121 -2) +40"), is(159));
		assertThat(new BasicCalculator().calculate("121 +2  +40"), is(163));
		assertThat(new BasicCalculator().calculate("121 -2  +40"), is(159));
		assertThat(new BasicCalculator().calculate(" 2-1 + 2 "), is(3));
		assertThat(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"), is(23));
		
		System.out.println("All test cases passed");
	}

}
