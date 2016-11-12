package com.chawkalla.algorithms;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		int[] lst=new int[]{2, 3, 2, 4, 5, 9, 9};
		
		long sum=0;
		for (int i = 0; i < lst.length; i++) {
			if(lst[i]*lst[i]>0){
				for (int j = 0; j < lst.length; j++) {
					if(lst[i]*lst[i]==lst[j]){
						sum+=lst[i];
						break;
					}
				}
			}
		}
			
		
		System.out.println(sum);
	}

}
