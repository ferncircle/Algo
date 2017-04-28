/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SFargose
 *
 */
public class FillByPowers {

	int fillByPowers(int size, int bound, int exponent) {

		int keys=0;
		List<Integer> items=new ArrayList<Integer>();
		
		for (int i = 1; Math.pow(i, exponent) <= bound; i++) {
			items.add((int)Math.pow(i, exponent));
		}
		keys=sumByChoosingKElements(bound, size, items);
		return keys;
		
	}

	
	public int sumByChoosingKElements(int sum, int k, List<Integer> items){	
		
		
		boolean[][] dp=new boolean[sum+1][k+1];
		int count=0;
		for (int i = 0; i < dp.length; i++) {
			
			for (int j = 0; j < dp[0].length; j++) {
				if(i==0 && j==0){
					dp[i][j]=true;
					continue;
				}
				if(j==0){
					continue;
				}
				for (int it = 0; it < items.size(); it++) {
					if(i-items.get(it)>=0){
						dp[i][j]=dp[i-items.get(it)][j-1] || dp[i][j];
					}
				}
				
				if(j==k && dp[i][j])
					count++;
			}
			
			System.out.println(Arrays.toString(dp[i]));
		}
		
		
		return count;
	}
	
	
	public static void main(String[] args) {
		
		
		assertThat(new FillByPowers().fillByPowers(3, 10, 2), is(3));

	}

}
