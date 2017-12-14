package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 * 
 * Better logic:
 * For arrays {-1,0,2,6,9,20}, {1,3,5,8,10}, we want to find 6th element, every time we can eliminate k/2 elements in both arrays. Calculate the value at k/2th element 
 * from start of both arrays ie. a[aStart+k/2-1] and b[bStart+k/2-1]. Use those mid values to eliminate either aLeft+bRight or bLeft+aRight
 * Every recursion, value of k reduces by k/2
 * 
 *
 */
public class KthElementTwoSortedArrays {

	public double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length, n = B.length;
		int l = (m + n + 1) / 2;
		int r = (m + n + 2) / 2;
		return (getKthElement(A, B, l) + getKthElement(A, B, r)) / 2.0;
	}

	public double getKthElement(int[] A, int[] B, int k){
		return getkth(A,0,B,0,k);
	}

	public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
		if (aStart == A.length) return B[bStart + k - 1];            
		if (bStart == B.length) return A[aStart + k - 1];                
		if (k == 1) return Math.min(A[aStart], B[bStart]);

		int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
		if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
		if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        

		if (aMid < bMid) 
			return getkth(A, aStart + k/2, B, bStart,       k - k/2);// Check: aRight + bLeft 
		else 
			return getkth(A, aStart,       B, bStart + k/2, k - k/2);// Check: bRight + aLeft
	}	

	public static void main(String[] args){
		

		
		
		assertThat(new KthElementTwoSortedArrays().findMedianSortedArrays(new int[]{-1,0,2,6,9,20},new int[]{1,3,5,8,10}), is(5.0));

		int[] a=new int[]{-50, -41, -40, -19, 5, 21, 28, -50, -21, -10};
		Arrays.sort(a);
		assertThat(new KthElementTwoSortedArrays().getKthElement(new int[]{-50, -41, -40, -19, 5, 21, 28}, 
				new int[]{-50, -21, -10}, 6), is(-19.0));

		assertThat(new KthElementTwoSortedArrays().getKthElement(new int[]{2, 3, 3, 3, 6, 7, 9}, 
				new int[]{1, 4, 8, 10}, 5), is(3.0));

		assertThat(new KthElementTwoSortedArrays().getKthElement(new int[]{1, 2}, new int[]{3}, 3), is(3.0));

		assertThat(new KthElementTwoSortedArrays().getKthElement(new int[]{1}, new int[]{2}, 1), is(1.0));

		assertThat(new KthElementTwoSortedArrays().getKthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5), is(6.0));




		/*assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 3, 3, 6, 7, 9}, 
				new int[]{1, 4, 8, 10}, 5), is(3));


		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{1, 2}, new int[]{3}, 2), is(2));
		

		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{-50, -41, -40, -19, 5, 21, 28}, 
				new int[]{-50, -21, -10}, 6), is(-19));	

		
		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{1, 2}, new int[]{3}, 3), is(3));

		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{1}, new int[]{2}, 1), is(1));

		assertThat(new KthElementTwoSortedArrays().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5), is(6));
*/

		System.out.println("all cases passed");
	}



	public int kthElement(int[] a, int[] b, int k){

		//return findUtil(a, b, 0, a.length-1, 0, b.length-1, k-1);

		return findKthIterative(a, b, k-1);
	}

	public int findKthIterative(int[] a, int[] b, int k){
		int s1=0, e1=a.length-1,  s2=0,  e2=b.length-1;

		while(true){
			
			if(s1>e1)
				return b[s1+k];						
			else if(s2>e2)
				return a[s2+k];	
			else if(k==0)
				return Math.min(a[s1], b[s2]);
			

			int m1=(s1+e1)/2;
			int m2=(s2+e2)/2;

			if(m1+m2<k){ 
				//number is on right halfs, so remove one of left halfs
				if(a[m1]<b[m2]){
					k=k-(m1-s1)-1;
					s1=m1+1; //remove first left half
					
				}else{
					k=k-(m2-s2)-1;
					s2=m2+1; //remove second left half
					
				}
			}else{
				//number is on left halfs, so remove one of right halfs
				if(a[m1]<b[m2]){
					e2= m2-1; //remove second right half
				}else{
					e1= m1-1; //remove first right half
				}
			}
		}

	}


}
