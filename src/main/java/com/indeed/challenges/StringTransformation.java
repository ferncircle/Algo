package com.indeed.challenges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Scanner;

public class StringTransformation {

	public static String transform(String s){
		if(s==null || s.equals("")) return s;
		int[] charCount=new int[26];
		StringBuffer buf=new StringBuffer();
		for(int i=0;i<s.length();i++){
			int cur=s.charAt(i)-'a';
			buf.append((char)((cur+charCount[cur])%26 + 'a'));
			charCount[cur]++;    
		}

		return buf.toString();
	}
	public static void main(String args[] ) throws Exception {
		
		assertThat(transform("abcabca"), is("abcbcdc"));
		assertThat(transform("azazaz"), is("azbacb"));
		System.out.println("all cases passed");
		/*Scanner in=new Scanner(System.in);
		int size=in.nextInt();
		for(int i=0;i<size;i++){
			in.nextInt();
			System.out.println(transform(in.next()));
		}*/
	}
}

