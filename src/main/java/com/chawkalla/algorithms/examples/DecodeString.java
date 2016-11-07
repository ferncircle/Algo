package com.chawkalla.algorithms.examples;

import static org.junit.Assert.assertTrue;

import java.util.Map;

/**
 * https://leetcode.com/problems/decode-string/
 * Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {

	public String decodeString(String s) {
		if(s==null || s.equals(""))
			return "";
		StringBuffer decodedBuf=new StringBuffer();
		
		Map<Integer, Integer> matchingBrackets=LongestValidParentheses.getMatchingBrackets(s, '[',']');
		
		boolean done=false;
		int cursor=0;
		StringBuffer digits=new StringBuffer();
		
		while(!done){
			char currentChar=s.charAt(cursor);
			
			if(currentChar=='['){
				int frequency=Integer.parseInt(digits.toString());
				digits=new StringBuffer();//clear digits
				
				int startBracket=cursor;
				int endBracket=matchingBrackets.get(startBracket);				
				
				String insideStr=s.substring(startBracket+1,endBracket);
				String strToRepeat=decodeString(insideStr);
				
				for (int j = 0; j < frequency; j++) {
					decodedBuf.append(strToRepeat);
				}
				cursor=endBracket+1;				
			}else if(Character.isLetter(currentChar)){
				decodedBuf.append(currentChar);
				cursor++;
				
			}else if(Character.isDigit(currentChar)){
				digits.append(currentChar);
				cursor++;				
			}
			
			if(cursor>=s.length())
				done=true;
		}
		
		return decodedBuf.toString();
	}
		
	
	public static void main(String[] args) {
		
		System.out.println(new DecodeString().decodeString("3[a]12[bc]"));
		System.out.println(new DecodeString().decodeString("3[a2[c]]"));
		System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
		
		assertTrue("aaabcbc".equals(new DecodeString().decodeString("3[a]2[bc]")));
		assertTrue("accaccacc".equals(new DecodeString().decodeString("3[a2[c]]")));
		assertTrue("abcabccdcdcdef".equals(new DecodeString().decodeString("2[abc]3[cd]ef")));
		System.out.println("All test cases passed");

	}

}
