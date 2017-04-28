/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

/**
 * https://codefights.com/interview/TCiYLJHaLcbPRCHfx
 * 
 * You have two arrays of strings, words and parts. Return an array that contains the strings from words,
 *  modified so that any occurrences 
 * of the substrings from parts are surrounded by square brackets [], following these guidelines:

If several parts substrings occur in one string in words, choose the longest one. If there is still more 
than one such part, then choose the one that appears first in the string.

Example

For words = ["Apple", "Melon", "Orange", "Watermelon"] and parts = ["a", "mel", "lon", "el", "An"], the 
output should be
findSubstrings(words, parts) = ["Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"].

While "Watermelon" contains three substrings from the parts array, "a", "mel", and "lon", "mel" is the longest 
substring that appears first in the string.
 *
 */
public class FindSubstrings {

	String[] findSubstrings(String[] words, String[] parts) {

	    HashSet<String> set=new HashSet<String>();
	    for(int i=0;i<parts.length;i++)
	        set.add(parts[i]);
	        
	        
	    String[] res=new String[words.length];
	    
	    for(int i=0;i<words.length;i++){
	        String cur=words[i];
	        res[i]=words[i];
	        int longest=0;
	        for(int l=5;l>=0;l--){ //intervals    
	        	for (int k = 0; k+l <= cur.length(); k++) {
	        		String part=cur.substring(k,k+l);
		            if(set.contains(part) && part.length()>longest){
		                longest=part.length();
		                res[i]=cur.replaceFirst(part,"["+part+"]"); 
		                break;
		            }
				}
	            
	        }
	    }
	    return res;
	}
		
	public static void main(String[] args) {

		
		assertThat((new FindSubstrings().findSubstrings(
				new String[]{"Apple", "Melon", "Orange", "Watermelon"}, new String[]{"a", "mel", "lon", "el", "An"})),
				is(new String[]{"Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"}));
		assertThat((new FindSubstrings().findSubstrings(
				new String[]{"aaaaaaa"}, 
				new String[]{"aaaaa", 
						 "aaaa"})),
				is(new String[]{"[aaaaa]aa"}));
		
		System.out.println("all cases passed");
	}

}
