/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import java.util.Arrays;

/**
 * abcabcabc -> true (kmp=[0, 0, 0, 1, 2, 3, 4, 5, 6], check if 9 % (9-6)==0)
 * abcabcdddabc ->(kmp= [0, 0, 0, 1, 2, 3, 0, 0, 0, 1, 2, 3], check if 12 % (12-3)==0)
 * 
 * create KMP array. For 
 *
 */
public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern(String s) {
		if(s==null || s.equals("")) return true;

		int n=s.length();
		int[] k=kmp(s);
		int substringLength=k[n-1];

		return (substringLength>0 && (n%(n-substringLength)==0));
	}
	//ababc -> [0,0,1,2,0]
	int[] kmp(String s){
		int[] k=new int[s.length()];

		int j=0,i=1;

		while(i<s.length()){
			if(s.charAt(i)==s.charAt(j)){
				k[i]=j+1;
				i++;j++;
			}else{
				if(j==0){
					k[i]=0;
					i++;
				}else{
					j=k[j-1];
				}
			}
		}
		System.out.println(Arrays.toString(k));
		return k;
	}

	public static void main(String[] args) {

		System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abcabcabc"));
		System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abcabcdddabc"));
		
		System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aaa"));

	}
}
