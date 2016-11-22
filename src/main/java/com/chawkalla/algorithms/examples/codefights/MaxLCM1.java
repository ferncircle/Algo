package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chawkalla.algorithms.examples.math.MathUtil;

/**
 * https://codefights.com/challenge/DkzPcr6rHQgWL4FFm
 * 	Given a positive integer n, find a way to represent it as a sum of some positive integers s1, s2, ..., st such that their least common multiple (the smallest integer that is divisible by each of si) is as large as possible. In case several representations provide the largest least common multiple, choose lexicographically smallest set of summands.

Example:
For n = 8, the output should be
maxLCM(n) = "3+5,15".
 *
 */
public class MaxLCM1 {

private final String separator=",";
	
	String maxLCM(int n) {
		String sol="";	
		
		List<String> paths=new ArrayList<String>();
		
		populateAllPaths(n, 1, "", n,paths);
		long maxLCM=0;
		String maxLCMPath="";
		for (int i = 0; i < paths.size(); i++) {
			//System.out.println(paths.get(i));
			String[] nums=paths.get(i).split(separator);
			int[] numbers=new int[nums.length];
			for (int j = 0; j < numbers.length; j++) {
				numbers[j]=Integer.parseInt(nums[j]);
			}
			
			long lcm=MathUtil.lcm(numbers);
			if(lcm>maxLCM){
				maxLCM=lcm;
				maxLCMPath=paths.get(i).replaceAll(",", "+");
				
			}else if (lcm==maxLCM){
				maxLCMPath=maxLCMPath+":"+maxLCMPath;
			}
		}
		String[] maxLCMPaths=maxLCMPath.split(":");
		if(maxLCMPaths.length>1){
			Arrays.sort(maxLCMPaths);
		}
		sol=maxLCMPaths[0]+","+maxLCM;
		
		//System.out.println(sol);
		return sol;
	}
	
	public void populateAllPaths(int sum, int cur, String currentPath, final int size, List<String> paths){
		if(sum==0){
			paths.add(currentPath);
			return;
		}
		if(cur>size)
			return;
		
		if(sum<cur)
			return;
		String sep=(currentPath.length()>0)?separator:"";
		populateAllPaths(sum-cur,  cur+1, currentPath+sep+cur, size, paths);
		populateAllPaths(sum, cur+1, currentPath, size, paths);		
			
	}
	
	public static void main(String[] args) {
		//new MaxLCM1().maxLCM(4);
		long before=0;
		before=System.currentTimeMillis();
		assertThat(new MaxLCM1().maxLCM(25), is("4+5+7+9,1260"));
		assertThat(new MaxLCM1().maxLCM(8), is("3+5,15"));
		assertThat(new MaxLCM1().maxLCM(2), is("2,2"));
		assertThat(new MaxLCM1().maxLCM(10), is("2+3+5,30"));
		assertThat(new MaxLCM1().maxLCM(20), is("1+3+4+5+7,420"));

		before=System.currentTimeMillis();
		new MaxLCM1().maxLCM(100);
		System.out.println("Execution time="+(System.currentTimeMillis()-before));
		System.out.println("All test cases passed");
	}

}
