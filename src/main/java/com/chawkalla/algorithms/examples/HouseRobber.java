package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 
 * 
 * https://leetcode.com/problems/house-robber-ii/
 *
 */
public class HouseRobber {
	
	public int robHouse1(int[] nums){
		if(nums.length==0)
			return 0;
		
		for (int i = 2; i < nums.length; i++) {
			int t1=nums[i]+nums[i-2];
			int t2=nums[i-1];
			
			nums[i]=Math.max(t1, t2);
		}
		
		return nums[nums.length-1];
	}
	
	public int robHouse2(int[] nums){
		if(nums.length==0)
			return 0;
		/*int[] take=new int[nums.length+1];
		int[] skip=new int[nums.length+1];
		
		for (int i = 1; i < take.length; i++) {
			skip[i]=Math.max(skip[i-1], take[i-1]);
			take[i]=nums[i-1]+skip[i-1];
			
		}*/
		//Above translates to this, since we only care about dp values till i-1
		int prevSkip=0;
		int prevTake=0;
		
		for (int i = 0; i < nums.length; i++) {
			int temp=prevSkip;
			prevSkip=Math.max(prevSkip, prevTake);
			prevTake=nums[i]+temp;
			
		}
		
		return Math.max(prevSkip, prevTake);
	}
	
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
		int maxA=robHouse1(excludeLast);

		int[] excludeFirst=new int[nums.length-1];
		System.arraycopy(nums, 1, excludeFirst, 0, nums.length-1);
		int maxB=robHouse1(excludeFirst);


		return Math.max(maxA, maxB);
	}


	public static void main(String[] args) {

		HouseRobber test=new HouseRobber();
		int[] nums={5,1};

		System.out.println(test.rob(nums));
		System.out.println(test.robCircular(nums));
		
		assertThat(new HouseRobber().robHouse2(new int[]{4,2,1,3,5,2,12,4,4,2,5,0,4,2,4,3,10,2,31,23,5,6,2}), 
				is(new HouseRobber().rob(new int[]{4,2,1,3,5,2,12,4,4,2,5,0,4,2,4,3,10,2,31,23,5,6,2})));
		
		System.out.println("All test cases passed");
		


	}

}
