/**
 * 
 */
package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/is-subsequence/
 * 
 * Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. 
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see 
if T has its subsequence. In this scenario, how would you change your code?
Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Solution: Notice this problem is different from longest common subsequence (DP)
1) preprocess t to store positions of each character
2) for each char in s, find the closest position (using binary search) from last position of t
 *
 */
public class IsSubsequence {

	public boolean isSubsequence(String s, String t) {
        boolean sub=true;
        HashMap<Character, ArrayList<Integer>> charPositions=new HashMap<Character, ArrayList<Integer>>();
        //preprocess by creating map of char -->positions
        for (int i = 0; i < t.length(); i++) {
        	char curChar=t.charAt(i);
			if(!charPositions.containsKey(curChar))
				charPositions.put(curChar, new ArrayList<Integer>());
			charPositions.get(curChar).add(i);
		}
        
        int lastPosInText=0;
        for (int i = 0; i < s.length(); i++) {
			char curChar=s.charAt(i);
			
			ArrayList<Integer> charArray=charPositions.get(curChar);
			if(charArray==null || charArray.size()==0)
				return false;
			lastPosInText=findClosestCharacter(charArray, lastPosInText,0,charArray.size()-1);
			if(lastPosInText<0)
				return false;
        	
		}
        return sub;
    }
	
	private int findClosestCharacter(final ArrayList<Integer> list, final int pos, int start, int end){
		if(pos < list.get(start))
			return list.get(start);
		if(pos > list.get(end))
			return -1;
		
		if(end<start)
			return -1;
		if(end-start <=1)
			if(list.get(end)>=pos)
				return list.get(end);
			else 
				return -1;
		int mid=(start+end)/2;
		if(pos<list.get(mid))
			return findClosestCharacter(list, pos, start, mid);
		else 
			return findClosestCharacter(list, pos, mid, end);
			
	}
	
	public static void main(String[] args) {
		
		assertThat(new IsSubsequence().isSubsequence("leeeeetcode", "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyylyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyeyyyyyyyyeyyyyyyyyyyyyyyeyyyyyyyyyeyyyyytyyyyyyyyyyyyyyyyyyyyyyyyyyycyyyyyyyyyyyyyyyyoyyyyyyyyyyyyyyyyyyyyyyydyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyyyy"),
				is(true));
		assertThat(new IsSubsequence().isSubsequence("abc", "abc"), is(true));	
		assertThat(new IsSubsequence().isSubsequence("abc", "ab"), is(false));			
		assertThat(new IsSubsequence().isSubsequence("abc", "ahcccccccccbgdccc"), is(true));
		assertThat(new IsSubsequence().isSubsequence("ace", "ahegcdc"), is(false));
		assertThat(new IsSubsequence().isSubsequence("ace", "ahegcccdsdssccccsdcsdccdc"), is(false));
		assertThat(new IsSubsequence().isSubsequence("ace", "ahegcccdsdssccccsdcsdeeeccdc"), is(true));
		
		
		System.out.println("All test cases passed");
	}

}
