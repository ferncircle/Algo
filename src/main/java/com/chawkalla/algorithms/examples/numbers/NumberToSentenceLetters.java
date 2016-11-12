	package com.chawkalla.algorithms.examples.numbers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumberToSentenceLetters {

	int NumberOfLetters(int n) {
		if(n==4)
			return 0;
		int count=countSentenceLetters(IntToEnglish.english_number(n));
		
		return 1+NumberOfLetters(count);
	}
	
	int countSentenceLetters(String s){
		int count=0;
		for (int i = 0; i < s.length(); i++) 
			count+=Character.isLetter(s.charAt(i))?1:0;
		
		return count;
	}
	
	
	
	public static void main(String[] args) {
		
		assertThat(new NumberToSentenceLetters().NumberOfLetters(14), is(3));
		assertThat(new NumberToSentenceLetters().NumberOfLetters(1000000), is(4));

		System.out.println("All test cases passed");
	}

}
