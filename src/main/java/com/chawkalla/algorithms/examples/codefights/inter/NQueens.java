/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author SFargose
 *
 */
public class NQueens {

	int[][] nQueens(int n) {

		ArrayList<HashSet<Integer>> placed=new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 4; i++) 
			placed.add(new HashSet<Integer>());
			
		List<String> res=new ArrayList<String>();
		helper(n, 0, new StringBuffer(), res, placed);
		
		//Collections.sort(res);
		
		int[][] out=new int[res.size()][];
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
			out[i]=Arrays.stream(res.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
		}
		return out;
	}
	
	public void helper(int n, int cur, StringBuffer buf, List<String> res,
			ArrayList<HashSet<Integer>> placed){
		if(cur==n){
			res.add(buf.toString());
			return;
		}
		
		for(int i=0;i<n;i++){
			if(!isTaken(placed, cur, i)){
				placeQueen(placed, cur, i);
				String append=(buf.length()==0?"":",")+(i+1);
				buf.append(append);
				helper(n, cur+1, buf, res, placed);
				buf.setLength(buf.length()-append.length());
				unPlaceQueen(placed, cur, i);
			}
		}
	}
	
	public void placeQueen(ArrayList<HashSet<Integer>> placed, int i, int j){
		int[] a=new int[]{i,j,i+j,i-j};
		for (int k = 0; k < a.length; k++) {
			placed.get(k).add(a[k]);
		}
	}
	
	public void unPlaceQueen(ArrayList<HashSet<Integer>> placed, int i, int j){
		int[] a=new int[]{i,j,i+j,i-j};
		for (int k = 0; k < a.length; k++) {
			placed.get(k).remove(a[k]);
		}
	}
	
	public boolean isTaken(ArrayList<HashSet<Integer>> placed, int i, int j){
		
		return (placed.get(0).contains(i) || placed.get(1).contains(j)||
				placed.get(2).contains(i+j) || placed.get(3).contains(i-j));
	}


	public static void main(String[] args) {
		new NQueens().nQueens(10);
		assertThat(new NQueens().nQueens(4), is(new int[][]{
			{2,4,1,3}, 
			{3,1,4,2}
		}));
		assertThat(new NQueens().nQueens(6), is(new int[][]{
			{2,4,6,1,3,5}, 
			{3,6,2,5,1,4}, 
			{4,1,5,2,6,3}, 
			{5,3,1,6,4,2}
		}));
		
		
		System.out.println("all cases passed");

	}

}
