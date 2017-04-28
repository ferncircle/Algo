/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

/**
 * https://codefights.com/challenge/8MvdB4ckhrEAwQFfT
 * 
 * Given a list of values, your task is to insert operators *, /, + and - between them so that the result of the equation 
 * would be equal to the given result. Return the correct equation as an answer.

It is guaranteed that each test case has exactly one solution.

Example

For values = [1, 3, 2, 3] and result = 1, the output should be
findOperation(values, result) = "1 / 3 + 2 / 3 = 1".
 *
 */
public class FindOperations {

	char[] operators=new char[]{'-','+','*','/'};
	
	String findOperation(int[] values, int result) {
		StringBuffer operations=new StringBuffer();
		
		String sol=evalCombinations(values, new StringBuffer(), result);
		
		if(sol!=null){
			operations.append(values[0]);
			
			for (int i = 1; i < values.length; i++) {
				operations.append(" "+sol.charAt(i-1)+" "+values[i]);
			}
			operations.append(" = "+result);
		}
		
		return operations.toString();
	}
	
	String evalCombinations(final int[] values, StringBuffer ops, final int result){
		if(ops.length()==values.length-1){
			String combination=ops.toString();
			int evalValue=eval(values, combination.toCharArray());
			if(evalValue==result){
				return combination;
			}else
				return null;
		}
		for (int i = 0; i < operators.length; i++) {			
			String sol=evalCombinations(values, ops.append(operators[i]), result);
			if(sol!=null)
				return sol;
			ops.deleteCharAt(ops.length()-1);			
		}
		
		
		return null;
	}
	
	public static int eval(int[] values, char[] op){
		int total=0;
		Stack<Double> st=new Stack<Double>();
		st.push((double)values[0]);
		
		for (int i = 1; i < values.length; i++) {
			char curOp=op[i-1];
			double curValue=(double)values[i];
			switch (curOp) {
			case '+':
				st.push(curValue);
				break;
			case '-':
				st.push(-curValue);			
				break;
			case '*':
				st.push(st.pop()*curValue);
				break;
			case '/':
				if(curValue==0)
					return Integer.MIN_VALUE;
				st.push(st.pop()/curValue);
				break;
			}
			
		}		
		double sum=0;
		while (!st.isEmpty()) {
			sum+=st.pop();
			
		}
		if(sum==Math.floor(sum))
			total=(int)sum;
		else
			total=Integer.MIN_VALUE;
		return total;
	}
	
	public static void main(String[] args) {

		/*System.out.println(eval(new int[]{1, 3, 2, 3}, "/+/".toCharArray()));
		System.out.println(eval(new int[]{1, 3, 2, 3}, "/-+".toCharArray()));
*/

		assertThat(new FindOperations().findOperation(new int[]{1, 3, 2, 3}, 1), is("1 / 3 + 2 / 3 = 1"));
		
		assertThat(new FindOperations().findOperation(new int[]{58, 11}, 47), is("58 - 11 = 47"));
		
		assertThat(new FindOperations().findOperation(new int[]{59, 0, 81, 55, 66}, 40), is("59 * 0 - 81 + 55 + 66 = 40"));
		assertThat(new FindOperations().findOperation(new int[]{0, 8, 1, 90, 45}, 52), is("0 + 8 - 1 + 90 - 45 = 52"));
		assertThat(new FindOperations().findOperation(new int[]{19, 38, 15, 10}, 62), is("19 + 38 + 15 - 10 = 62"));
		
		System.out.println("All test cases passed");

	}

}
