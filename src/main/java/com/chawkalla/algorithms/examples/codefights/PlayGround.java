/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

/**
 * @author SFargose
 *
 */
public class PlayGround {

	String minSubstringWithAllChars(String s, String t) {
		if(s.isEmpty()) return "";
		int b=0,e=0;
		HashMap<Character, Integer> charCount=
				new HashMap<Character,Integer>();

		boolean[] check=new boolean[26];
		for(int i=0;i<t.length();i++)
			check[t.charAt(i)-'a']=true;

		int minSize=Integer.MAX_VALUE;
		int minIndex=0;
		while(b<s.length()){
			if(charCount.size()>=t.length() || e==s.length()){
				if(charCount.size()>=t.length() && (e-b)<minSize){
					minSize=e-b;
					minIndex=b;
				}

				//shrink
				////remove the char from window
				if(charCount.containsKey(s.charAt(b)))
					charCount.compute(s.charAt(b),(k,v)->v==1?null:v-1);          
				b++;
			}
			else{
				//expand				
				if(check[s.charAt(e)-'a'])
					charCount.compute(s.charAt(e),(k,v)->v==null?1:v+1);   
				e++;
			}



		}

		return s.substring(minIndex, minIndex+minSize);

	}

	public static void main(String[] args) {

		assertThat(new PlayGround().minSubstringWithAllChars("adobecodebanc", "abc"),is("banc"));
		assertThat(new PlayGround().minSubstringWithAllChars("abz", "abz"),is("abz"));
		
		System.out.println("all cases passed");
	}

}
