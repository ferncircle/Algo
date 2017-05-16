/**
 * 
 */
package com.chawkalla.algorithms.examples.segmentTree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/#/description
 *
 *Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
 */
public class NumArray {

	long[] tree;
	int[] nums;
	public NumArray(int[] nums) {
		this.nums=nums;
		if(nums.length==0) return;
		int height=(int)Math.ceil(Math.log(nums.length)/Math.log(2));
		tree=new long[2*(int)Math.pow(2, height)-1];
		construct(0, 0, nums.length-1);
		//System.out.println("Aftre init  :"+Arrays.toString(tree));
	}

	private void construct(int pos, int lo, int hi){
		if(lo>hi) return ;
		if(lo==hi){
			tree[pos]=nums[lo];
			return;
		}    	
		int mid=(lo+hi)/2;
		int left=2*pos+1;
		int right=2*pos+2;
		construct(left, lo, mid);
		construct(right, mid+1, hi);

		tree[pos]=tree[left]+tree[right];
	}

	public void update(int i, int val) {
		if(i>=nums.length) return;		
		int delta=val-nums[i];
		nums[i]=val;
		updateUtil(0, i, 0, nums.length-1, delta);
		//System.out.println("After update:"+Arrays.toString(tree));
	}

	private void updateUtil(int pos, int index, int lo, int hi, int delta){
		if(lo>hi) return;
		if(index<lo || index>hi) return; //outside range
		if(lo==hi){
			tree[pos]+=delta;
			return;
		}
		tree[pos]+=delta;
		int mid=(lo+hi)/2;
		updateUtil(2*pos+1, index, lo, mid, delta);
		updateUtil(2*pos+2, index, mid+1, hi, delta);  
		
	}


	public int sumRange(int i, int j) {        
		if(i>j) return 0;
		return (int)sumRangeUtil(i, j, 0, nums.length-1, 0);    	
	}

	private long sumRangeUtil(int i, int j, int lo, int hi, int pos){
		if(hi<i || lo>j) return 0; //outside range
		if(i<=lo && hi<=j) return tree[pos]; //completely covers    	

		int sum=0;
		int mid=(lo+hi)/2;
		sum+=sumRangeUtil(i, j, lo, mid, 2*pos+1);
		sum+=sumRangeUtil(i, j, mid+1, hi, 2*pos+2);    	
		return sum;
	}

	public static void main(String[] args){


		NumArray test=new NumArray(new int[]{9, -8});
		test.update(0, 3);
		assertThat(test.sumRange(1, 1), is(-8));
		assertThat(test.sumRange(0, 1), is(-5));

		test=new NumArray(new int[]{1, 3, 5});
		assertThat(test.sumRange(2, 2), is(5));
		assertThat(test.sumRange(0, 2), is(9));
		test.update(1, 2);
		assertThat(test.sumRange(0, 2), is(8));

		test=new NumArray(new int[]{1, 3, 5, 2, 7, 4});
		assertThat(test.sumRange(3, 5), is(13));
		assertThat(test.sumRange(0, 2), is(9));
		test.update(1, 2);
		assertThat(test.sumRange(0, 5), is(21));

		System.out.println("All cases passed");
	}
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
