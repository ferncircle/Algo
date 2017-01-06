/**
 * 
 */
package com.chawkalla.algorithms.ds;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**https://www.youtube.com/watch?v=ZBHKZF5w4YU
 * 
 * Create segment tree of size that is number which is closest higher number(than array size) i.e. power of 2
 * Use post-order traversal to populate the tree and use position to track the node position in array
 * 
 * Query:
 * Again use post-order traversal and check for total overlap, no-overlap and partial-overlap
 * 
 * Create tree=O(n) (4n)
 * query tree=O(log(n))
 */
public class SegmentTree {

	int[] segment=null;
	int[] a=null;
	
	public SegmentTree(int[] a) {
		
		this.a=a;
		int tSize=0;
		int power=(int)(Math.log(a.length)/Math.log(2));
		if(Math.pow(2, power)!=a.length)
			power++;
		int closestNumberPowerOf2=(int)Math.pow(2, (int)power);
		
		tSize=2*closestNumberPowerOf2-1;
		
		System.out.println("segment tree size="+tSize);
		segment=new int[tSize];
		
		contruct(a, segment, 0, a.length-1, 0);


		System.out.println(Arrays.toString(segment));
	}
	public void contruct(int[] a, int[] segment, int low, int high, int pos){
		if(low==high){ //leaf node
			segment[pos]=a[low];
			return;
		}
		
		int mid=(low+high)/2;
		
		int leftPos=2*pos+1;
		int rightPos=2*pos+2;
		contruct(a, segment, low, mid, leftPos); //left child
		contruct(a, segment, mid+1, high, rightPos); //right child
		
		segment[pos]=Math.min(segment[leftPos], segment[rightPos]);
	}
	
	public int query(int qlow, int qhigh){
		return queryUtil(qlow, qhigh, 0, a.length-1, 0);
	}
	
	public int queryUtil(final int qlow, final int qhigh, int low, int high, int pos){
		if(qlow<=low && qhigh>=high)
			return segment[pos]; //total overlap
		
		if(low > qhigh || high < qlow)
			return Integer.MAX_VALUE; //no overlap
		
		int mid=(low+high)/2;
		
		return Math.min(queryUtil(qlow, qhigh, low, mid, 2*pos+1),  //partial overlaps
				queryUtil(qlow, qhigh, mid+1, high, 2*pos+2)); 
	}
	public static void main(String[] args) {
		SegmentTree tree=new SegmentTree(new int[]{-1, 3, 4, 0, -2, 1});
		
		assertThat(tree.query(0, 1), is(-1));
		assertThat(tree.query(0, 5), is(-2));
		assertThat(tree.query(1, 4), is(-2));
		assertThat(tree.query(0, 3), is(-1));
		System.out.println("All test cases passed");
	}

}
