package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.chawkalla.algorithms.SubsetSum;
import com.chawkalla.algorithms.examples.math.MathUtil;

/**
 * https://codefights.com/challenge/DkzPcr6rHQgWL4FFm
 * 	Given a positive integer n, find a way to represent it as a sum of some positive integers s1, s2, ..., st such that their least common multiple (the smallest integer that is divisible by each of si) is as large as possible. In case several representations provide the largest least common multiple, choose lexicographically smallest set of summands.

Example:
For n = 8, the output should be
maxLCM(n) = "3+5,15".
 *
 */
public class MaxLCMDP {

	private final String separator=",";
	
	String maxLCM(int n) {
		String sol="";
		
		int[] items=new int[n];
		for (int i = 1; i <=n; i++) {
			items[i-1]=i;
		}
		
		boolean[][] dp=SubsetSum.isSumGetDPTable(items, n);
		
		List<String> paths=new ArrayList<String>();
		
		getAllPathsDP(dp, items, items.length, n, "", paths);
		
		long maxLCM=0;
		String maxLCMPath="";
		for (int i = 0; i < paths.size(); i++) {
			String[] nums=paths.get(i).split(separator);
			int[] numbers=new int[nums.length];
			for (int j = 0; j < numbers.length; j++) {
				numbers[j]=Integer.parseInt(nums[j]);
			}
			
			long lcm=MathUtil.lcm(numbers);
			if(lcm>maxLCM){
				maxLCM=lcm;
				maxLCMPath=paths.get(i).replaceAll(",", "+");
				
			}			
		}		
		sol=maxLCMPath+","+maxLCM;
		
		//System.out.println(sol);
		return sol;
	}
	
	public void getAllPathsDP(boolean[][] dp, int[] A, int sum, int cur, String currentPath, List<String> paths){
		if(sum==0){
			paths.add(currentPath);
			return;
		}
		if(cur<1)
			return;
		
		String sep=(currentPath.length()>0)?separator:"";
		
		int curElement=A[cur-1];
		int curSum=sum;
		if(dp[curElement-1][curSum]){ //don't include current element
			getAllPathsDP(dp, A, sum, cur-1, currentPath, paths);	
		}
		if(curSum-curElement >=0 && dp[curElement-1][curSum-curElement]){ //include current element
			getAllPathsDP(dp, A, sum-curElement,  cur-1, curElement+sep+currentPath, paths);
		}		
			
			
	}
	
	public static void main(String[] args) {
		//new MaxLCMDP().maxLCM(100);
		long before=0;
		before=System.currentTimeMillis();
		assertThat(new MaxLCMDP().maxLCM(25), is("4+5+7+9,1260"));
		assertThat(new MaxLCMDP().maxLCM(8), is("3+5,15"));
		assertThat(new MaxLCMDP().maxLCM(2), is("2,2"));
		assertThat(new MaxLCMDP().maxLCM(10), is("2+3+5,30"));
		assertThat(new MaxLCMDP().maxLCM(20), is("1+3+4+5+7,420"));
		
		before=System.currentTimeMillis();
		new MaxLCMDP().maxLCM(100);
		System.out.println("Execution time="+(System.currentTimeMillis()-before));
		System.out.println("All test cases passed");
	}

}
