/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 * he set [1, 2, 3, ... , n] contains a total of n! unique permutations. List all the permutations for an integer n in lexicographical order and return the kth permutation in the sequence.

Example

For n = 3 and k = 3, the output should be
permutationSequence(n, k) = "213".

The ordered list of possible permutations for 3! is:

  1) "123"
  2) "132"
  3) "213"
  4) "231"
  5) "312"
  6) "321"
The 3rd permutation in the lexicographically ordered sequence is "213".
 * 
 * Solution: Note that this is a opposite problem of LexicographicRankInPermutation
 * 1) Create a result array and find appropriate number to be placed at each position from [1..n]
 * 2) e.g. for position 0 in result array, find i in [1..n] such that
 * 		i*(n-1)! <= m < (i+1)*(n-1)!
 * 	  we can use binary search for it (but seems overkill)
 * 3) Now, repeat the above process for next position with m=m-i*(n-1)!
 * 
 *
 */
public class KthNumberInPermutation {

	String permutationSequence(int n, int k) {
		String seq="";
		StringBuffer sb=new StringBuffer();
		seqUtil(n, k, new boolean[n], sb);
		seq=sb.toString();
		return seq;
	}

	private void seqUtil(int n, int k, boolean[] used, StringBuffer sb){
		if(n==0 || k==0)
			return;
		if(k==1){
			for (int i = 0; i < used.length; i++) 
				if(!used[i]){
					sb.append(i+1);
					used[i]=true;
				}
			return;	
		}
		int f=fact(n-1);
		int j=1;
		for (; j*f<k ; j++) {}
		j--;
		int charsBeforeCurChar=j;
		int count=0;
		for (int i = 0; i < used.length; i++) {
			if(!used[i])
				if(count<charsBeforeCurChar)
					count++;
				else{
					sb.append(i+1);
					used[i]=true;
					break;
				}
		}
		seqUtil(n-1, k-(charsBeforeCurChar*f), used, sb);
		
	}

	private int fact(int n){
		if(n<2) return 1;

		int f=1;
		for(int i=2;i<=n;i++)
			f*=i;
		return f;

	}



	public static void main(String[] args) {
		
		assertThat(new KthNumberInPermutation().permutationSequence(6, 246), is("312654"));
		
		assertThat(new KthNumberInPermutation().permutationSequence(4, 24), is("4321"));
		assertThat(new KthNumberInPermutation().permutationSequence(3, 6), is("321"));
		assertThat(new KthNumberInPermutation().permutationSequence(3, 5), is("312"));
		assertThat(new KthNumberInPermutation().permutationSequence(3, 3), is("213"));
		
		System.out.println("all cases passed");
	}

}
