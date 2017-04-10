/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

/**
 * https://codefights.com/interview/Q8td3XApSWuobjEaJ
 *
 *Write a function that takes an integer n and returns all of the ways that integers not greater than n can be multiplied 
 *together in order to equal n. Do not repeat sets of factors - for instance, if the output contains [4, 3], it shouldn't
 * also contain [3, 4]. Both your sets and the numbers in the sets should be sorted in descending order. 1 should only be 
 * included in a set if the set also contains n.

Example

For n = 12, the output should be
setsOfFactors(n) = [[12, 1], [6, 2], [4, 3], [3, 2, 2]].
 */
public class SetOfFactors {

	int[][] setsOfFactors(int n) {

		ArrayList<String> sol=new ArrayList<String>();

		setUtil(n, n, sol, new StringBuffer(), n);

		int[][] set=new int[sol.size()+1][];
		set[0]=new int[2];
		set[0][0]=n;set[0][1]=1;

		for(int k=0;k<sol.size();k++){
			String r=sol.get(k);
			String[] elements=r.split(",");
			int[] way=new int[elements.length];
			for(int i=0;i<elements.length;i++){
				if(elements[i].length()>0)
					way[i]=Integer.parseInt(elements[i]);
			}
			set[k+1]=way;
		}

		return set;
	}

	public void setUtil(int N, int n,ArrayList<String> sol, StringBuffer res, int prev){
		if(isPrime(n)){
			if(res.length()>0 && n<=prev){

				String append=","+n;
				res.append(append);
				sol.add(res.toString());
				res.setLength(res.length()-append.length());
				return;
			}else
				return;
		}

		if(n!=N && n<=prev){
			sol.add(res.toString()+","+n);
		}
		for(int i=n/2;i>1;i--){
			if(n%i==0){
				if(i<=prev){
					String append=(res.length()>0)?","+i:""+i;
					res.append(append);
					setUtil(N, n/i, sol,  res, i);
					res.setLength(res.length()-append.length());
				}

			}
		}


	}

	private boolean isPrime(int n){
		if(n==2 || n==3)
			return true;
		if(n%2==0)
			return false;        
		for(int i=3;i<=Math.sqrt(n);i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {

		assertThat(new SetOfFactors().setsOfFactors(32), is(new int[][]{
			{32,1}, 
			{16,2}, 
			{8,4}, 
			{8,2,2}, 
			{4,4,2}, 
			{4,2,2,2}, 
			{2,2,2,2,2}
		}));
		assertThat(new SetOfFactors().setsOfFactors(2), is(new int[][]{
			{2,1}
		}));
		assertThat(new SetOfFactors().setsOfFactors(12), is(new int[][]{
			{12,1}, 
			{6,2}, 
			{4,3}, 
			{3,2,2}
		}));
		System.out.println("All test cases passed");
	}

}
