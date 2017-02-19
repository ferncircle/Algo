package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * 
 * Solution: Construct lps(longest prefix i.e. suffix)
 * create two pointers j(prefix)=0, i(suffix)=1. 
 * Move i. If s[i]=s[j] then a[i]=j+1; otherwise shift (prefix ptr)j back till s[i]==s[j] or start of array
 */
public class KMP {

	public static int[] lps(String s){
		if(s==null)
			return null;
		
		int[] a=new int[s.length()];
		
		boolean done=false;
		int j=0;
		int i=1;
		while(!done){
			/*if(s.charAt(i)==s.charAt(j)){	
				a[i]=j+1;
				j++;
			}else{
				while(j>0 && (s.charAt(i)!=s.charAt(j))){
					j=a[j-1];
				}	
				if(s.charAt(i)==s.charAt(j)){	
					a[i]=j+1;
					j++;
				}	
				
			}

			i++;
			
			if(i>=a.length)
				done=true;*/
			
			//better option
			if(s.charAt(i)==s.charAt(j)){	
				a[i]=j+1;
				j++;
				i++;
			}else{
				if(j!=0){
					//move j back but don't increment i yet 
					//notice how you move back. consider "aabaa c aabaa b"
					//c(at position j) doesn't match b(at position i). We want to move j to b instead of directly 
					//at position 0
					j=a[j-1]; 
				}else{
					//reached at start and still characters didn't match, so put 0 and move on
					a[i]=0; 
					i++;
				}				
			}
			
			if(i>=a.length)
				done=true;
		}
		
		return a;
		
	}
	public static void main(String[] args) {
		
		KMP.lps("abcabcabc");
		assertThat(KMP.lps("aabaabaaa"), is(new int[]{0,1,0,1,2,3,4,5,2}));
		
		assertThat(KMP.lps("AAAA"), is(new int[]{0, 1, 2, 3}));
		
		assertThat(KMP.lps("abcdabca"), is(new int[]{0,0,0,0,1,2,3,1}));
		assertThat(KMP.lps("AAACAAAAAC"), is(new int[]{0, 1, 2, 0, 1, 2, 3, 3, 3, 4}));
		assertThat(KMP.lps("ABCDE"), is(new int[]{0, 0, 0, 0, 0}));
		assertThat(KMP.lps("AAABAAA"), is(new int[]{0, 1, 2, 0, 1, 2, 3}));

		
		System.out.println("All test cases passed");
	}

}
