/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author sfargose
 *
 */
public class LongestSubstringWithoutRepeating {

	int lengthOfLongestSubstring(String s) {
		int d=0;
		int[] count=new int[256];
		int start=0;
		int end=0;
		int counter=0;
		while(end<s.length()){
			if(count[s.charAt(end++)]++>=1)
				counter++;
			while(counter>0){
				if(count[s.charAt(start++)]-->=2)
					counter--;
			}
			
			d=Math.max(d, end-start);
		}
		return d;
		
	}
	public static void main(String[] args) {
		
		assertThat(new LongestSubstringWithoutRepeating().lengthOfLongestSubstring("abca"), is(3));
		System.out.println("all cases passed");

	}

}
