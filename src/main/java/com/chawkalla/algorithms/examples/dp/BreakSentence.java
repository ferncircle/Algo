package com.chawkalla.algorithms.examples.dp;

import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

public class BreakSentence {
	Set<String> dictionary=new HashSet<String>();
	
	public void setup(){
		dictionary.add("this");
		dictionary.add("is");
		dictionary.add("a");
		dictionary.add("cat");
		dictionary.add("sentence");
	}
	
	public boolean isValidSentence(String sentence){
		
		if(sentence==null) return false;
		if(dictionary.contains(sentence)) return true;
		
		for(int i=1;i<sentence.length();i++){
			String left =sentence.substring(0,i);
			String right=sentence.substring(i,sentence.length());
			if( dictionary.contains(left)&&isValidSentence(right))
				return true;
		}
		
		return false;
	}
	
	public boolean isValidDP(String sentence){
		if(sentence==null) return false;
		if(dictionary.contains(sentence)) return true;
		char[] s=sentence.toCharArray();
		
		//create two dimentional array
		int n=s.length;
		
		boolean[][] dp=new boolean[n][n];
		
		//fill up one and two char substrings
		for (int i = 0; i < n; i++) {
			String oneChar=""+s[i];
			dp[i][i]=dictionary.contains(oneChar);
			if(i<n-1){
				String twoChar=""+s[i]+s[i+1];
				dp[i][i+1]=dictionary.contains(twoChar);
			}
			
		}		
		//PrimeSubsets.printBooleanMatrix(dp, n, n);
		System.out.println();
		for(int size=2;size<n;size++){ //from smaller to larger strings size
			
			for(int start=0;start<n-size;start++){
				int end=start+size;
				//if entire string present then continue
				String substring=new String(s,start,end-start+1);
				dp[start][end]=dictionary.contains(substring);
				if(dp[start][end]) continue;
				
				//create two halves, left(dp[i][k-1]) and right(dp[k][j]) by placing divider at various 
				//locations(similar to recursive solution but for smaller instance)
				for(int k=start+1;k<=end;k++){
					
					dp[start][end]=dp[start][end] || (dp[start][k-1]&dp[k][end]);
				}
				//System.out.println("setting "+substring+" =dp["+i+"]["+j+"]="+ dp[i][j]);
				
			}
		}
		//PrimeSubsets.printBooleanMatrix(dp, n, n);
		
		return dp[0][n-1];
	}
	

	public static void main(String[] args) {
		BreakSentence s=new BreakSentence();
		s.setup();
		
		System.out.println(s.isValidSentence("thisisacat"));
		System.out.println(s.isValidDP("iscatsentence"));
		
		
		

	}

}
