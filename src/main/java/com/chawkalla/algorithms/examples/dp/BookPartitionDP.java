/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/allocate-books/
 * 
 * N number of books are given. 
The ith book has Pi number of pages. 
You have to allocate books to M number of students so that maximum number of pages alloted to a student is minimum. A book will be allocated to exactly one student. Each
 student has to be allocated at least one book. Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.

NOTE: Return -1 if a valid assignment is not possible

Input:
 List of Books M number of students 

Your function should return an integer corresponding to the minimum number.

Example:

P : [12, 34, 67, 90]
M : 2

Output : 113

There are 2 number of students. Books can be distributed in following fashion : 
  1) [12] and [34, 67, 90]
      Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
  2) [12, 34] and [67, 90]
      Max number of pages is allocated to student 2 with 67 + 90 = 157 pages 
  3) [12, 34, 67] and [90]
      Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages

Of the 3 cases, Option 3 has the minimum pages = 113. 


 *
 */
public class BookPartitionDP {

	public int books(ArrayList<Integer> a, int b) {
	    
		int[] arr=new int[a.size()];
		for (int i=0;i<a.size();i++) 
			arr[i]=a.get(i);
		
	    return partition(arr, b);
	}
	public int partition(int[] s, int k){

		int maxCost=0;

		int n=s.length;

		int[][] m=new int[n+1][k+1]; //DP for cost of [s][k]

		int[] prefixSum=new int[n+1]; //prefix sum

		for (int i = 1; i <= n; i++) 
			prefixSum[i]=prefixSum[i-1]+s[i-1];
		

		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				if(j==1){ //only one partition
					m[i][j]=prefixSum[i];
					continue;
				}
				if(i==1){ //only one element
					m[i][j]=s[0];
					continue;
				}
				m[i][j]=Integer.MAX_VALUE; //max cost for m[i][j], upto only i and using only j dividers
				for (int x = 1; x <= i-1; x++) {

					m[i][j]=Math.min(m[i][j], Math.max(
							m[x][j-1], //one less divider for sub problem
							prefixSum[i]-prefixSum[x]
							));
				}
			}
		}

		maxCost=m[n][k];
		
		return maxCost;
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i:new int[]{12, 34, 67, 90})
			a.add(i);
		
		assertThat(new BookPartitionDP().books(a, 2), is(113));
		
		System.out.println("all cases passed");

	}

}
