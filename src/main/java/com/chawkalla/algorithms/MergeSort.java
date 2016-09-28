package com.chawkalla.algorithms;

import java.util.Arrays;

public class MergeSort{
	
	public static int inversionCount;
	public static void main(String a[]){
		
		int array[] = {12,9,4,99,120,1,3,10};
		System.out.println("Values Before the sort:");
		System.out.println(Arrays.toString(array));
		mergeSort_srt(array,0, array.length-1);
		System.out.print("Values after the sort:\n");
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		System.out.println(Arrays.toString(merge(new int[]{2,  4, 6},
				new int[]{3, 7, 9})));
		System.out.println("inversion count="+inversionCount);
	}

	public static void mergeSort_srt(int array[],int lo, int n){
		int low = lo;
		int high = n;
		if (low >= high) {
			return;
		}

		int middle = (low + high) / 2;
		mergeSort_srt(array, low, middle);
		mergeSort_srt(array, middle + 1, high);
		int end_low = middle;
		int start_high = middle + 1;
		while ((lo <= end_low) && (start_high <= high)) {
			if (array[low] < array[start_high]) {
				low++;
			} else {
				int Temp = array[start_high];
				for (int k = start_high- 1; k >= low; k--) {
					array[k+1] = array[k];
				}
				array[low] = Temp;
				low++;
				end_low++;
				start_high++;
			}
		}
	}  
	
	public static int[] merge(int[] a, int[] b){
		if(a==null && b==null)
			return null;
		if(a==null && b!=null)
			return b;
		if(a!=null && b==null)
			return a;
		
		int[] m=new int[a.length+b.length];
		
		boolean done=false;
		int ai=a.length-1;
		int bi=b.length-1;
		int mi=m.length-1;
		while(!done){
			if(ai<0){
				System.arraycopy(b, 0, m, 0, bi+1);
				break;
			}
			if(bi<0){
				System.arraycopy(a, 0, m, 0, ai+1);
				break;
			}
				
			if(a[ai]>b[bi]){
				inversionCount=inversionCount+(bi+1);
				m[mi]=a[ai];
				mi--;ai--;
			}
			else if(a[ai]<b[bi]){
				m[mi]=b[bi];
				mi--;bi--;
			}
			else if(a[ai]==b[bi]){
				m[mi]=b[bi];
				mi--;
				m[mi]=b[bi];
				mi--;bi--;ai--;
			}	
			
			if(mi<0)
				done=true;
		}
		
		
		return m;
	}
}