/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.TreeSet;

/**
 * Given a sequence of numbers in an array, find the length of its longest increasing subsequence (LIS).
The longest increasing subsequence is a subsequence of a given sequence in which the subsequence's elements are 
in strictly increasing order, and in which the subsequence is as long as possible. This subsequence is 
not necessarily contiguous or unique.

Example

For sequence = [1, 231, 2, 4, 89, 32, 12, 234, 33, 90, 34, 100], the output should be

longestIncreasingSubsequence(sequence) = 7.

The LIS itself is [1, 2, 4, 32, 33, 34, 100].


 *
 */
public class LongestIncreasingSubsequence {


	int longestIncreasingSubsequence(int[] sequence) {
		int lis=0;
		
		TreeSet<Integer> set=new TreeSet<Integer>((a,b)->sequence[a]-sequence[b]);
		
		for (int i = 0; i < sequence.length; i++) {
			//find higher number
			Integer higher=set.ceiling(i);
			if(higher!=null){
				set.remove(higher);
			}
			set.add(i);
		}
		
		lis=set.size();
		
		return lis;
	}
	
	public static void main(String[] args) {
		
		assertThat(new LongestIncreasingSubsequence().longestIncreasingSubsequence
				(new int[]{3, 49, 114, 127, 98, 65, 16, 94, 108, 38, 19, 139, 25, 71, 184, 146, 129, 60, 84, 22, 
						138, 161, 150, 124, 84, 106, 48, 114, 187, 185, 10, 35, 101, 171, 40, 158, 69, 57, 86, 193, 
						23, 134, 136, 184, 28, 100, 62, 91, 136, 101, 80, 174, 107, 86, 46, 10, 37, 182, 140, 100, 
						102, 8, 196, 20, 85, 28, 156, 161, 134, 53, 1, 4, 89, 199, 76, 170, 105, 191, 44, 158, 58, 
						120, 76, 150, 191, 147, 199, 68, 147, 110, 81, 80, 182, 8, 120, 139, 26, 100, 171, 15, 111, 
						156, 33, 166, 19, 121, 38, 65, 110, 104, 145, 51, 142, 102, 145, 28, 142, 111, 97, 25, 84, 
						99, 41, 132, 171, 155, 136, 104, 46, 10, 15, 33, 118, 31, 119, 33, 14, 91, 62, 133, 36, 
						118, 46, 122, 19, 6, 134, 122, 47, 171, 189, 195, 9, 28, 36, 178, 76, 6, 51, 43, 55, 119, 
						191, 125, 180, 166, 155, 172, 23, 45, 68, 110, 11, 73, 153, 75, 178, 98, 171, 142, 68, 142, 
						18, 169, 66, 122, 56, 128, 115, 84, 138, 133, 150, 163, 83, 36, 193, 136, 131, 135, 121, 
						129, 89, 125, 35, 34, 4, 126, 123, 183, 161, 66, 131, 65, 24, 123, 87, 109, 39, 32, 166, 
						27, 163, 164, 179, 20, 52, 142, 116, 77, 169, 122, 99, 188, 132, 40, 140, 153, 31, 75, 182, 
						143, 119, 101, 101, 35, 7, 81, 191, 124, 200, 169, 86, 35, 56, 145, 45}), is(29));
		
		System.out.println("all cases passed");
	}

}
