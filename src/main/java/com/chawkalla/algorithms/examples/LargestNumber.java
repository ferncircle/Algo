package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

	public String largestNumber(int[] nums) {

		
		if(nums==null || nums.length==0)
			return "0";
		Integer[] a=new Integer[nums.length];
		int i = 0;
		for (int value : nums) {
		    a[i++] = Integer.valueOf(value);
		}
		Arrays.sort(a, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				String s1=""+o1+o2;
				String s2=""+o2+o1;
				long s_1=Long.parseLong(s1);
				long s_2=Long.parseLong(s2);
				if(s_1==s_2)
					return 0;
				else if(s_1<s_2)
					return 1;
				else 
					return -1;
						
			}
		});
		StringBuffer sb=new StringBuffer();
		for(Integer item:a)
			sb.append(item);
		int j=0;
		for(;j<sb.length();j++){
			if(sb.charAt(j)!='0')
				break;
		}
		if(j!=0)
			sb.delete(0, j);
		if(sb.length()==0)
			sb.append("0");
		return sb.toString();
	}
	public static void main(String[] args) {
		LargestNumber test=new LargestNumber();
		assertThat(test.largestNumber(new int[]{3, 30, 34, 5, 9}), is("9534330"));
		assertThat(test.largestNumber(new int[]{999999998,999999997,999999999}), is("999999999999999998999999997"));
		
		System.out.println("All test cases passed");	

	}

}
