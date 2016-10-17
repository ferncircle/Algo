package com.chawkalla.algorithms.examples.combination;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/decode-ways/
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 */
public class DecodeWays {

	HashMap<String, Integer> cache=new HashMap<String, Integer>();
	
	public int numDecodings(String s) {
		int total=0;
		
		try {
			if(s==null || s.length()==0 || s.startsWith("0"))
				return 0;
			if((s.length()==1) || s.equals("10"))
				return 1;
			if(s.length()==2){
				int number=Integer.parseInt(s);
				if(number==10 || number==20)
					return 1;
				if(number<=26 && number>=1)
					return 2;
				if(number<1)
					return 0; 
					
			}			
			
			if(cache.containsKey(s))
				return cache.get(s);
			int n=s.length();
			
			String extractOneStr=""+s.charAt(n-1);
			String option1Remaining=s.substring(0,n-1); //extract one char
			
			String extractTwoStr=s.substring(n-2);
			String option2Remaining=s.substring(0,n-2); //extract two chars
			
			if(extractOneStr.length()>0 && Integer.parseInt(extractOneStr)>0){
				int extractOne=numDecodings(option1Remaining); //recurse for option1
				total+=extractOne;		
			}
					
			if(extractTwoStr.length()>0 && !extractTwoStr.startsWith("0") &&
					Integer.parseInt(extractTwoStr)<=26){ //recurse for option 2 only if <=26
				int extractTwo=numDecodings(option2Remaining);
				total+=extractTwo;
			}
		} catch (NumberFormatException e) {
			return 1;
		}
		cache.put(s, total);
		
        return total;
    }
	
	public int numDecodingsDP(String s) {
		int total=0;
		
		try {
			if(s==null || s.length()==0 || s.startsWith("0"))
				return 0;
			if((s.length()==1) || s.equals("10"))
				return 1;
							
			
			int[] a=new int[s.length()];
			
			String firstTwoChar=""+s.charAt(0)+s.charAt(1);
			a[0]=1;			

			int number=Integer.parseInt(firstTwoChar);
			if(number==10 || number==20)
				a[1]= 1;
			else if(number<=26 && number>=1)
				a[1]= 2;
			else if(number<1 || number%10==0)
				a[1]= 0; 	
			else if(number>26)
				a[1]= 1; 					
			
			for (int i = 2; i < a.length; i++) {
				int sum=0;;
				String extractOneStr=""+s.charAt(i);
				String extractTwoStr=""+s.charAt(i-1)+s.charAt(i);
				
				if(extractOneStr.length()>0 && Integer.parseInt(extractOneStr)>0){
					int extractOne=a[i-1]; //option1
					sum+=extractOne;		
				}				
				
				if(extractTwoStr.length()>0 && !extractTwoStr.startsWith("0") &&
						Integer.parseInt(extractTwoStr)<=26){ //option 2 only if <=26
					int extractTwo=a[i-2];
					sum+=extractTwo;
				}
				a[i]=sum;
				
			}
			total=a[s.length()-1];
		} catch (NumberFormatException e) {
			return 1;
		}
		
        return total;
    
		
	}
	
	public static void main(String[] args) {
		
		assertTrue(5==new DecodeWays().numDecodingsDP("1213"));
		assertTrue(0==new DecodeWays().numDecodingsDP("000"));
		assertTrue(2==new DecodeWays().numDecodingsDP("21"));
		assertTrue(1==new DecodeWays().numDecodingsDP("29"));
		assertTrue(1==new DecodeWays().numDecodingsDP("2a9"));
		assertTrue(0==new DecodeWays().numDecodingsDP("250129"));
		assertTrue(0==new DecodeWays().numDecodingsDP("0"));
		assertTrue(1==new DecodeWays().numDecodingsDP("101"));
		assertTrue(0==new DecodeWays().numDecodingsDP("301"));
		assertTrue(0==new DecodeWays().numDecodingsDP("121212301"));
	}

}
