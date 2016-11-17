package com.chawkalla.algorithms.examples.dp;


/**
 * https://leetcode.com/problems/wiggle-subsequence/
 * 
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

 *
 */
public class WiggleSequence {

	public int wiggleMaxLength(int[] nums) {
		int len=0;		
		if(nums==null || nums.length==0)
			return len;		
		if(nums.length==1)
			return 1;
		
		int[] up=new int[nums.length];
		int[] down=new int[nums.length];
		up[0]=down[0]=1;
		len=1;
		for(int i=1;i<nums.length;i++){
			int currentNumber=nums[i];
			for(int j=0;j<i;j++){
				if(currentNumber>nums[j]){
					int upLength=down[j]+1;
					if(upLength>up[i])
						up[i]=upLength;
					if(upLength>len)
						len=upLength;
				}
				else if(currentNumber<nums[j]){
					int downLength=up[j]+1;
					if(downLength>down[i])
						down[i]=downLength;
					if(downLength>len)
						len=downLength;
				}
			}
		}
		
		return len;
		
	}
	
	
	public static void main(String[] args) {
		WiggleSequence test=new WiggleSequence();
		
		System.out.println(test.wiggleMaxLength(new int[]{1,7,4,9,2,5}));
		System.out.println(test.wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}));
		System.out.println(test.wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9}));

	}

}
