package com.triplebyte.challenge;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

public class AsciiDeletion {

	public static int ascii_deletion_distance(String str1, String str2) {
		int temp[][] = new int[str1.length()+1][str2.length()+1];

		for(int i=0;i <= str1.length(); i++){
			for(int j=0;j <= str2.length(); j++){
				if(i==0 && j==0) continue;
				if(i==0){
					temp[i][j] = temp[i][j-1]+str2.charAt(j-1);
					continue;
				}
				if(j==0){
					temp[i][0] = temp[i-1][j]+str1.charAt(i-1);
					continue;
				}
					
				char x=str1.charAt(i-1);
				char y=str2.charAt(j-1);
				if(x == y){
					temp[i][j] = temp[i-1][j-1];
				}else{
					int i1j1=temp[i-1][j-1]+x+y;
					int i1j=temp[i-1][j]+x;
					int ij1=temp[i][j-1]+y;
					temp[i][j] = Math.min(i1j1, 
											Math.min(i1j,
													ij1));
				}
			}
		}

		for (int i = 0; i < temp.length; i++) {
			System.out.println(Arrays.toString(temp[i]));
		}
		return temp[str1.length()][str2.length()];
	}

	public static int bracket_match(String s) {
		int open=0;
		int n=0;

		for(int i=0;i<s.length();i++)
			if(s.charAt(i)=='(')
				open++;			
			else
				if(open==0)
					n++;					
				else
					open--;
		n+=open;
		return n;
	}

	public static void main(String[] args) {

		System.out.println((int)('b'+'g'+'a'));
		assertThat(AsciiDeletion.ascii_deletion_distance("boa", "go"), is(298));
		assertThat(AsciiDeletion.ascii_deletion_distance("at", "cat"), is((int)'c'));
		assertThat(AsciiDeletion.ascii_deletion_distance("cat", "cat"), is(0));

		

		assertThat(AsciiDeletion.bracket_match("((())"), is(1));
		assertThat(AsciiDeletion.bracket_match("))("), is(3));
		assertThat(AsciiDeletion.bracket_match(")))()))))()("), is(8));
		
		assertThat(AsciiDeletion.bracket_match("()()))("), is(3));

		System.out.println("all cases passed");
	}

}
