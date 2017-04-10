/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://codefights.com/interview/a4qH6YSuqGQEBhZQN/description
 * 
 * Given a string that contains only digits 0-9 and a target value, return all expressions that are created by 
 * adding some binary operators (+, -, or *) between the digits so they evaluate to the target value. In some 
 * cases there may not be any binary operators that will create valid expressions, in which case the function 
 * should return an empty array. The numbers in the new expressions should not contain leading zeros.

The function should return all valid expressions that evaluate to target, sorted lexicographically.

Example

For digits = "123" and target = 6, the output should be
composeExpression(digits, target) = ["1*2*3", "1+2+3"].

Solution:
Check out the difference between two solution (second solution looks ugly)
Improvements
1) Create and pass strings instead of stringbuffer to subroutine
2) Pass sumSofar and prevNumber to subroutine
3) To handle *, you can new total sum from previous sum as
	sumSoFar=sumSoFar-prevNumber + (prevNumber*curNumber)
	
	Pass prevNumber=prevNumber*curNumber (to handle case 2*3*4)
	
	Note that this is similar to eval using stack where in case of * we pop previous element, multiply with current
	and again push it back to stack as prevElement
 *
 */
public class ComposeExpression {

	String[] composeExpression(String digits, int target) {
		String[] res=null;
		List<String> list=new ArrayList<String>();
		//expressionUtil(digits, target, 0, new StringBuffer(), list);
		helper(digits, 0, target, 0, 0, "", list);
		res=list.toArray(new String[list.size()]);
		Arrays.sort(res);
		return res;
	}

	private void helper(String digits, double sumSoFar,final double target, int start, double prevNumber,
			String exp, List<String> list){
		if(start==digits.length()){			
			if(sumSoFar==target)
				list.add(exp);
			return;
		}
		
		int end=digits.charAt(start)=='0'?start+1:digits.length();		
		
		for (int i = start; i < end; i++) {			
			long curNumber=Long.parseLong(digits.substring(start,i+1));
			if(exp.length()>0){ 
				helper(digits, sumSoFar+curNumber, target, i+1, curNumber, exp+"+"+curNumber, list);
				helper(digits, sumSoFar-curNumber, target, i+1, -curNumber, exp+"-"+curNumber, list);
				helper(digits, sumSoFar-prevNumber+(prevNumber*curNumber), target, i+1, prevNumber*curNumber, 
						exp+"*"+curNumber, list);
				/*if(curNumber!=0)
					helper(digits, sumSoFar-prevNumber+(prevNumber/curNumber), target, i+1, prevNumber/curNumber, 
						exp+"/"+curNumber, list);*/
			}else
				helper(digits, curNumber, target, i+1, curNumber, exp+curNumber, list);
			
		}
		
	}

	@SuppressWarnings("unused")
	private void expressionUtil(String digits, double target, int start, StringBuffer buf, List<String> list){
		if(start==digits.length()){
			String exp=buf.toString();
			try {
				if(evaluate(exp)==target)
					list.add(exp);
			} catch (Exception e) {}
			return;
		}
		int end=digits.length();
		if(digits.charAt(start)=='0')
			end=start+1;
		char[] operators={'*','+','-'};
		StringBuffer curNumber=new StringBuffer();
		// 1234
		for (int i = start; i < end; i++) {
			char curChar=digits.charAt(i);
			curNumber.append(""+curChar);
			if(buf.length()>0){
				for (int j = 0; j < operators.length; j++) {
					buf.append(operators[j]);//add operator
					buf.append(curNumber);//add number
					expressionUtil(digits, target, i+1, buf, list);
					buf.setLength(buf.length()-curNumber.length());//remove number
					buf.setLength(buf.length()-1);//remove cur operator
				}
			}else{
				buf.append(curNumber);//add number
				expressionUtil(digits, target, i+1, buf, list);
				buf.setLength(buf.length()-curNumber.length());//remove number
			}
		}
	}

	public static double evaluate(String exp){
		double value=0;

		double n=0;
		Stack<Double> st=new Stack<Double>();
		int i=0;
		for(;i<exp.length()&& Character.isDigit(exp.charAt(i));i++){
			n=n*10+(exp.charAt(i)-'0');
		}
		st.push(n);
		n=0;
		while(i<exp.length()){
			char curChar=exp.charAt(i++);
			if(curChar==' ')
				continue;

			for(;i<exp.length()&& Character.isDigit(exp.charAt(i));i++)
				n=n*10+(exp.charAt(i)-'0');

			double curDigit=n;
			n=0;
			switch (curChar) {
			case '+':
				st.push(curDigit);
				break;
			case '-':
				st.push(-curDigit);
				break;
			case '*':
				st.push(st.pop()*curDigit);
				break;
			case '/':
				if(curDigit==0)
					throw new IllegalArgumentException();
				st.push(st.pop()/curDigit);
				break;

			default:
				break;
			}
		}

		while(!st.isEmpty())
			value+=st.pop();
		return value;
	}


	public static void main(String[] args) {

		assertThat(new ComposeExpression().composeExpression("232", 8), is(new String[]{"2*3+2","2+3*2"}));
		
		assertThat(new ComposeExpression().composeExpression("123", 6), is(new String[]{"1*2*3", "1+2+3"}));
		assertThat(new ComposeExpression().composeExpression("3456237490", 9191), is(new String[]{}));
		System.out.println(Arrays.toString(new ComposeExpression().composeExpression("3456237490", 1000)));
		System.out.println(evaluate("1-1-1/1"));
		System.out.println("all cases passed");
	}

}
