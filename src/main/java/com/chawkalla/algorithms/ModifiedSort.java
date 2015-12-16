package com.chawkalla.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ModifiedSort {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] A={4,3,-4,9,34,23,1225, 56,-50, -34, 23, 98, -23, 4545, 5};
		
		Arrays.sort(A, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(Math.abs(o1.intValue())>Math.abs(o2.intValue()))
					return 1;
				if(Math.abs(o1.intValue())<Math.abs(o2.intValue()))
						return -1;
				
				if(Math.abs(o1.intValue())==Math.abs(o2.intValue()))
						return 0;
				return 1;
			}
		});
		
		for(int a:A)
			System.out.print(a+" ");

	}

}
