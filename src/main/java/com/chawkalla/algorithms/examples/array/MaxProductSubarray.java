package com.chawkalla.algorithms.examples.array;

public class MaxProductSubarray {

	public int maxProduct(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;
		
		if(nums.length==1)
			return nums[0];
		
		int maxProductSoFar=0;
		int minProductSoFar=0;
		
		int currentMax=0;
		int currentMin=0;
		
		for(int i=0;i<nums.length;i++){
			int current=nums[i];
			if(current>0){
				if(currentMax>0){
					currentMax=currentMax*current;
				}
				else if(currentMax==0)
					currentMax=current;
				
				if(currentMin<0){
					currentMin=currentMin*current;
				}
				else if(currentMin==0)
					currentMin=0;
					
			}
			if(current==0){
				currentMax=0;
				currentMin=0;
			}
			if(current<0){
				
				int temp=currentMax;
				currentMax=currentMin*current;
				currentMin=temp*current;
				
				if(currentMin==0)
					currentMin=current;
			}
			
			if(currentMax>maxProductSoFar)
				maxProductSoFar=currentMax;
			
			if(currentMin<minProductSoFar)
				minProductSoFar=currentMin;
		}
		
		return maxProductSoFar;
	}

	public static void main(String[] args) {
		MaxProductSubarray test=new MaxProductSubarray();

		System.out.println(test.maxProduct(new int[]{2,3,0, 4,-6, -5}));

	}

}
