/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SFargose
 *
 */
public class FirstNonRepeatingCharacter {

	public Character firstChar(String s){
		int[] count=new int[26];
		int[] index=new int[26];
		Arrays.fill(index, Integer.MAX_VALUE);
		
		for (int i = 0; i < s.length(); i++) {
			char cur=s.charAt(i);
			if(count[cur-'a']==0) //0->1 state, note that we are only interested in this state
				index[cur-'a']=i;			
			else if (count[cur-'a']==1) //more than 1, so remove from uniques
				index[cur-'a']=Integer.MAX_VALUE;
			count[cur-'a']++;
		}
		int min=Integer.MAX_VALUE;
		for (int i = 0; i < index.length; i++) 
			min=Math.min(min, index[i]);
		
		if(min==Integer.MAX_VALUE)
			return '0';
		else return s.charAt(min);
	}
	
	public static void main(String[] args) {
		
		assertThat(new FirstNonRepeatingCharacter().firstChar("dpcggaefwefkviwkerpwalwdejfvghkeqoffvnajfvafaefawdpc"), is('i'));
		
		assertThat(new FirstNonRepeatingCharacter().firstChar("aaa"), is('0'));
		assertThat(new FirstNonRepeatingCharacter().firstChar("dcda"), is('c'));
		assertThat(new FirstNonRepeatingCharacter().firstChar("dpcggwdpc"), is('w'));

		assertThat(new FirstNonRepeatingCharacter().firstChar("dpcggaefwefkviwkerpwalwdejfvghkeqoffvnajfvafaefawdpc"), is('i'));
		System.out.println("all cases passed");

	}

}
