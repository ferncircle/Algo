/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/challenge/mudnXf6iHBHG7ukYw
 *In mathematics, the Fibonacci numbers are the numbers of the Fibonacci sequence that goes as follows: 1, 1, 2, 3, 5, 8,....

Your mission today is to calculate the nth non-negative integer that does NOT belong to the Fibonacci sequence.

Since the values can be quite big, both input and output are of type string.

Example

remainFib("1") = "4".

It's clear from the list above the 1st element that does not belong to it is number 4.

remainFib("2") = "6".

The 2nd element not in the sequence is 6.
 */
public class RemainFib {

	String remainFib(String number) {
		String r="";
		long n=Long.parseLong(number);
		
		long nth=0;
		long a=1,b=1;
		long count=0;
		boolean done=false;
		while(!done){
			nth=a+b;
			
			long totalInBetween=nth-b-1;
			if(n-totalInBetween >0)
				n=n-totalInBetween;
			else{
				count=b+n;
				done=true;
			}
			a=b;
			b=nth;
			
		}	
		
		r=""+count;
		return r;
	}
	
	
	public static void main(String[] args) {
		
		assertThat(new RemainFib().remainFib("1"), is("4"));

		assertThat(new RemainFib().remainFib("3"), is("7"));
		assertThat(new RemainFib().remainFib("69"), is("78"));
		assertThat(new RemainFib().remainFib("40"), is("48"));
		assertThat(new RemainFib().remainFib("56"), is("65"));
		System.out.println(new RemainFib().remainFib("4503599627370496"));
		System.out.println("all test cases passed");
	}

}
