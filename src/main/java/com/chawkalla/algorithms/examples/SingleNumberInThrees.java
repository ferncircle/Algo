package com.chawkalla.algorithms.examples;

import static org.junit.Assert.assertTrue;

public class SingleNumberInThrees {

	public int singleNumber(int[] nums) {
		int number=0;
		if(nums==null || nums.length<4)
			return 1;
		
		int[] bitCounts=new int[32];
		int[] negativeBitCounts=new int[32];
		StringBuffer sb=new StringBuffer();
		int outOfRange=0;
		for(int n:nums){
			int i=0;
			int[] counts=bitCounts;
			System.out.println("processing "+n);
			if(n<0){
				counts=negativeBitCounts;
				n=-1*n;
			}
			if(n<0){
				outOfRange++;
				continue;
			}
				
			while(n!=0){
				
				if((n & 1)==1)
					counts[i]++;
				i++;
				n=n>>1;					
			}
		}
		for(int i=0;i<32;i++){
			sb.append(bitCounts[i]%3);
		}
		if(outOfRange==1)
			return Integer.MIN_VALUE;
		sb.reverse();
		number=Integer.parseInt(sb.toString(),2);
		if(number==0){
			sb=new StringBuffer();
			for(int i=0;i<32;i++){
				sb.append(negativeBitCounts[i]%3);
			}
			sb.reverse();
			number=Integer.parseInt(sb.toString(),2);
			number=-1*number;
		}
		
		return number;
		
	}
	public static void main(String[] args) {

		SingleNumberInThrees test=new SingleNumberInThrees();
		//System.out.println(Integer.MIN_VALUE);
		assertTrue(3==(test.singleNumber(new int[]{2,2,3,2})));
		assertTrue(5==(test.singleNumber(new int[]{1,2,1,1,2,2,5})));
		assertTrue(7==(test.singleNumber(new int[]{1,2,1,1,2,2,5,5,7,5})));
		assertTrue(-4==(test.singleNumber(new int[]{-2,-2,1,1,-3,1,-3,-3,-4,-2})));
		assertTrue(2147483647==(test.singleNumber(new int[]{-2147483647,43,16,45,89,45,45,2147483646,-2147483647,-2147483648,43,2147483647,
				-2147483646,-2147483648,89,-2147483646,89,
				-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43})));
		
		System.out.println("all test cases passed");

	}

}
