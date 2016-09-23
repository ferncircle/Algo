package com.chawkalla.algorithms;

import java.util.Arrays;

public class Utils {

	public static void main(String[] args) {
		int[] src  = new int[]{1,2,3,4,5};
		int[] dest = new int[5];

		System.arraycopy( src, 0, dest, 0, 2 );
		
		System.out.println(Arrays.toString(dest));

	}

}
