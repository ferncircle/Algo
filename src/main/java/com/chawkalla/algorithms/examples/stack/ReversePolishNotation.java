package com.chawkalla.algorithms.examples.stack;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class ReversePolishNotation {

	public int evalRPN(String[] tokens) {
		int val=0;
		if(tokens==null || tokens.length==0)
			return val;
		Stack<String> st=new Stack<String>();
		
		for(String s:tokens){
			if(s!=null && s.length()>0){
				if(isDigit(s))
					st.push(s);
				else{
					int b=Integer.parseInt(st.pop());
					int a=Integer.parseInt(st.pop());
					int value=0;
					switch (s) {
					case "+":
						value=a+b;
						break;
					case "-":
						value=a-b;
						break;
					case "*":
						value=a*b;
						break;
					case "/":
						value=a/b;
						break;

					default:
						break;
					}
					st.push(""+value);
				}
			}
		}
		val=Integer.parseInt(st.pop());
		return val;
	}

	public boolean isDigit(String s){
		boolean digit=false;
		Pattern p = Pattern.compile("[+-]*[0-9]+(.[0-9]+){0,1}");
		Matcher m = p.matcher(s);
		digit=m.matches();
		return digit;
	}
	public static void main(String[] args) {
		ReversePolishNotation test=new ReversePolishNotation();
		System.out.println(test.isDigit("34.56"));
		System.out.println(test.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
		System.out.println(test.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
		System.out.println(test.evalRPN(new String[]{"3","-4","+"}));
	}

}
