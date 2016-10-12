package com.chawkalla.algorithms.examples;


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
