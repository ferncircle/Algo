package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/challenge/NbDwPvtLjfXBB63Wx
 * 
 * Given an array of non-negative integers arr, you task is to calculate the sum of bitwise OR operations 
 * (arr[i] | arr[j]) for all the pairs of integers in it.

Example

For arr = [1, 2, 3], the output should be
pairSumOr(arr) = 9.

arr[0] | arr[1] = 1 | 2 = 12 | 102 = 112 = 3
arr[0] | arr[2] = 1 | 3 = 12 | 112 = 112 = 3
arr[1] | arr[2] = 2 | 3 = 102 | 112 = 112 = 3
Thus, the answer is 3 + 3 + 3 = 9.

For arr = [1, 2, 3, 4, 5], the output should be
pairSumOr(arr) = 51.

The answer can be obtained as follows:

(1 | 2) + (1 | 3) + (1 | 4) + (1 | 5) + 
          (2 | 3) + (2 | 4) + (2 | 5) +
          (3 | 4) + (3 | 5) +
          (4 | 5) 
        = 51
        
Solution:
1) Count zeroes at each position (or ones)
2) For each position check if either zero or one set in each pair=(all pairs(nC2) - pairs that has both zeroes(zC2))
	(2^i)*(nC2-zC2)
 *
 */
public class PairSumOr {


	long pairSumOr(int[] a) {

	    int n=a.length;
	    long[] z=new long[32];
	    //count bits at each position
	    for(int i=0;i<n;i++){
	        for(int j=0;j<32;j++){
	            if(((a[i]>>j) & 1) ==0)
	                z[j]+=1;
	        }
	    }
	    long sum=0;
	    for(int j=0;j<32;j++){
	        
	        double count=((long)1<<j)*(n*(n-1)/2-(z[j]*(z[j]-1)/2));
	        sum+=count;
	    }
	    
	    return sum;
	}

	
	public static void main(String[] args){
		assertThat(new PairSumOr().pairSumOr(new int[]{1, 2, 3, 4, 5}), is(51L));
		System.out.println("all cases passed");
	}
}
