package com.chawkalla.algorithms.examples.trie;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Random;



/**
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 <= ai < 231.

Find the maximum result of ai XOR aj, where 0 <= i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

Solution:
1) create a trie of two nodes, insert all elements into for bits 31 to 0(height is 32). Again for each element,
	see the max value we can get (don't use recursion)
 */
public class MaxXorTwoNumbers {

	
	class Tr{
		Tr[] ch;
		public Tr(){
			ch=new Tr[2];
		}
	}

	public int findMaximumXOR(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return 0;
		Tr root=new Tr();
		int maxSum=Integer.MIN_VALUE;
		
		for (int j = 0; j < nums.length; j++) {
			int curNumber=nums[j];
			
			Tr curT=root;
			if(j>0){
				int sum=0;
				for (int i = 31; i>=0; i--) {
					int curBit=(curNumber>>i)&1;
					if(curT.ch[curBit^1]!=null){
						sum|=1<<i;
						curT=curT.ch[curBit^1];
					}else{
						curT=curT.ch[curBit];
					}				
				}
				maxSum=Math.max(maxSum, sum);
			}
			
			
			//insert cur
			curT=root;
			for (int i = 31; i>=0; i--) {
				int curBit=(curNumber>>i)&1;
				if(curT.ch[curBit]==null)
					curT.ch[curBit]=new Tr();
				curT=curT.ch[curBit];
			}
		}
		
		return maxSum;
	}


	public int findMaximumXOROptimum(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)			
			return 0;
		int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i);
            
            HashSet<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num & mask); // reserve Left bits and ignore Right bits
            }
            
            int tmp = max | (1 << i); // in each iteration, there are pair(s) whoes Left bits can XOR to max
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                }
            }
        }
        return max;
	}
	
	public int findMaximumXOR1(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return 0;
		int max=0;

		for (int i = 0; i < nums.length; i++) {

			for (int j = i+1; j < nums.length; j++) {
				int val=nums[i] ^ nums[j];
				if(val>max)
					max=val;
			}
		}

		return max;
	}

	public static void main(String[] args) {

		assertThat(new MaxXorTwoNumbers().findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}), is(28));
		assertThat(new MaxXorTwoNumbers().findMaximumXOR(
				new int[]{3,10,5,25,2,8, 23, 5934, 19, 239, 2, 4,12132}), is(14410));


		int[] b=new int[]{4653,6,234,6,7};
		assertThat(new MaxXorTwoNumbers().findMaximumXOR(b), 
				is(new MaxXorTwoNumbers().findMaximumXOR1(b)	) );

		//load test
		int n=100000;
		int [] a=new int[n];
		Random rn = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i]=rn.nextInt(1000000000) + 1;
		}

		long before=0;
		long after=0;

		before=System.currentTimeMillis();
		int x1=new MaxXorTwoNumbers().findMaximumXOROptimum(a);	
		after=System.currentTimeMillis();
		System.out.println("Excution 1="+(after-before));

		before=System.currentTimeMillis();
		int x=new MaxXorTwoNumbers().findMaximumXOR(a);	
		after=System.currentTimeMillis();
		System.out.println("Excution 2="+(after-before));

		assertThat(x, is(x1));
		System.out.println("All test cases passed");

	}

}
