/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class MinimumWindowSubstring {

	public String minWindow(String s, String t) {

		int start=0,end=0;
		int counter=t.length();

		int[] count=new int[256];
		for(int i=0;i<t.length();i++)
			count[t.charAt(i)]++;
		int min=Integer.MAX_VALUE;
		int begin=0;
		while(end<s.length()){
			if(count[s.charAt(end++)]-->0)
				counter--;

			while(counter==0){
				if(min>end-start){
					min=end-start;
					begin=start;
				}

				if(count[s.charAt(start++)]++==0)
					counter++;
			}
		}

		return min==Integer.MAX_VALUE?"":s.substring(begin,begin+min);
	}
	public static void main(String[] args) {
		
		assertThat(new MinimumWindowSubstring().minWindow("abc", "c"), is("c"));
		System.out.println("all cases passed");

	}

}
