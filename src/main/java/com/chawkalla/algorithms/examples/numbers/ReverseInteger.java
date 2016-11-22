/**
 * 
 */
package com.chawkalla.algorithms.examples.numbers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/reverse-integer/
 *Reverse digits of an integer.
964632435
Example1: x = 123, return 321
Example2: x = -123, return -321
 */
public class ReverseInteger {
	public int reverse(int x){
		
		int n=Math.abs(x);
		long sol=0;
		while(n!=0){
			int lsb=n%10;
			sol=sol*10+lsb;
			n=n/10;
		}
		if(x<0)
			sol=-sol;
		
		int r=0;
		if(sol<=Integer.MAX_VALUE && sol>=Integer.MIN_VALUE)
			r=(int)sol;
		return r;
	}
	public static void main(String[] args) {
		
		//assertThat(new ReverseInteger().reverse(1234), is(4321));
		assertThat(new ReverseInteger().reverse(1534236469), is(0));
		assertThat(new ReverseInteger().reverse(-1563847412), is(0));
		System.out.println("all test cases passed");
	}

}
