/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/permutation-in-string/#/description
 * 
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the 
 * substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
 *
 */
public class PermutationInString {

	public boolean checkInclusion(String s1, String s2) {
		boolean ret=false;
		
		int count[]=new int[128];
		int start=0,end=0;
		for(int i=0;i<s1.length();i++)
			count[s1.charAt(i)-'a']++;
		int counter=s1.length();
				
		while(end<s2.length()){
			if(count[s2.charAt(end++)-'a']-->0){ //only decrease count for the ones in s1
				counter--;
				
				while(counter==0){
					if(end-start==s1.length())
						return true;
					if(count[s2.charAt(start++)-'a']++>=0)//ignore all negative counts since they are not part of s1
						counter++;
				}
			}
			
		}
		return ret;
	}
	public static void main(String[] args) {

		assertThat(new PermutationInString().checkInclusion("adc", "dcda"), is(true));
		assertThat(new PermutationInString().checkInclusion("ab", "eidbaooo"), is(true));
		assertThat(new PermutationInString().checkInclusion("ab", "eidboaoo"), is(false));
		assertThat(new PermutationInString().checkInclusion("abcd", "eidbcadoo"), is(true));
		System.out.println("all cases passed");

	}

}
