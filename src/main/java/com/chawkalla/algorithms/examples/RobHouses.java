package com.chawkalla.algorithms.examples;



/**
 * 
 * 
 * https://leetcode.com/problems/house-robber-ii/
 *
 */
public class RobHouses {

	public int rob(int[] nums) {
		return robHouse(nums);
	}

	private int robHouse(int[] nums){

		int[] total=new int[nums.length];

		for(int i=0;i<nums.length;i++){
			int max=0;
			for(int j=0;j<i-1&&i>0;j++){
				if(max<total[j])
					max=total[j];
			}
			total[i]=max+nums[i];
		}

		int max=0;
		for(int i=0;i<total.length;i++){
			if(max<total[i])
				max=total[i];
		}
		return max;

	}

	public int robCircular(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		if(nums.length==1)
			return nums[0];
		int[] excludeLast=new int[nums.length-1];
		System.arraycopy(nums, 0, excludeLast, 0, nums.length-1);
		int maxA=robHouse(excludeLast);

		int[] excludeFirst=new int[nums.length-1];
		System.arraycopy(nums, 1, excludeFirst, 0, nums.length-1);
		int maxB=robHouse(excludeFirst);


		return Math.max(maxA, maxB);
	}


	public static void main(String[] args) {

		RobHouses test=new RobHouses();
		int[] nums={5,1};

		//System.out.println(test.rob(nums));
		System.out.println(test.robCircular(nums));
		
		
		


	}

}
