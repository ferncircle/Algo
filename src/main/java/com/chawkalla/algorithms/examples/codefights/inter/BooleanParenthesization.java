/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/interview/mNppZEkFBske9ht5u
 * 
 * Given a boolean expression with the following symbols:

T - true
F - false
and the following operators between the symbols:

& - boolean AND
| - boolean OR
^ - boolean XOR
Count the number of ways that you can parenthesize the expression so that the expression evaluates to true, and return 
this answer modulo 1003.

Example

For expression = "T&T|F^F", the output should be
booleanParenthesization(expression) = 5.

Here are all of the possible combinations:

((T&T)|(F^F))
(T&((T|F)^F))
(((T&T)|F)^F)
((T&(T|F))^F)
(T&(T|(F^F)))
For expression = "F|T^F", the output should be
booleanParenthesization(expression) = 2.

Here are all of the possible combinations:

(F|(T^F))
((F|T)^F)

Solution:
1) notice I separated operators from operands (easier to deal with)
2) Better to take small example with array index (gives you idea of the bounds of i and j)
		Index:	 0 1 2
		Operands:F T F
		Operator:| ^	
3) Use DP gap technique
 *
 */
public class BooleanParenthesization {

	int booleanParenthesization(String expression) {
		int total=0;
		int noOfOperands=expression.length()/2+1;
		int[][][] dp=new int[noOfOperands][noOfOperands][2];

		char[] operands=new char[noOfOperands];
		char[] operators=new char[noOfOperands-1];
		int operandsCounter=0;
		int operatorCounter=0;
		for (int i = 0; i < expression.length(); i++) 
			if(expression.charAt(i)=='F' || expression.charAt(i)=='T')
				operands[operandsCounter++]=expression.charAt(i);
			else
				operators[operatorCounter++]=expression.charAt(i);

		for (int i = 0; i < operands.length; i++) {
			if(operands[i]=='T')
				dp[i][i][1]=1;
			else
				dp[i][i][0]=1;
		}
		
		int gap=1;
		//0	1 2
		//F T F
		//| ^		
		for (; gap < operands.length; gap++) {

			for (int i = 0; i+gap < operands.length; i++) {
				int j=i+gap;

				for (int k = i; k <j; k++) {
					int[] left=dp[i][k];
					int[] right=dp[k+1][j];
					char operator=operators[k];
					switch (operator) {
					case '&':
						dp[i][j][1]+=left[1]*right[1];
						dp[i][j][0]+=(left[0]*right[0])+(left[1]*right[0])+(left[0]*right[1]);
						break;
					case '|':
						dp[i][j][1]+=(left[1]*right[1])+(left[1]*right[0])+(left[0]*right[1]);
						dp[i][j][0]+=(left[0]*right[0]);
						break;
					case '^':
						dp[i][j][1]+=(left[1]*right[0])+(left[0]*right[1]);
						dp[i][j][0]+=(left[0]*right[0])+(left[1]*right[1]);
						break;
					default:
						break;
					}
					dp[i][j][0]=dp[i][j][0] % 1003;
					dp[i][j][1]=dp[i][j][1] % 1003;
				}
			}
		}
		
		total=dp[0][noOfOperands-1][1] % 1003;
		return total;
	}


	public static void main(String[] args) {

		assertThat(new BooleanParenthesization().booleanParenthesization("F|T^F"), is(2));		
		assertThat(new BooleanParenthesization().booleanParenthesization("T&T|F^F"), is(5));

		System.out.println("all casess passed");
	}

}
