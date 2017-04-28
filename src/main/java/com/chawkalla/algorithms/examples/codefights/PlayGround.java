/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import java.util.HashMap;

/**
 * @author SFargose
 *
 */
public class PlayGround {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n=8;
		int or=0;
		for (int i = 0; i <= n; i++) {
			or |=i;
		}
		System.out.println(or);
		System.out.println(((Integer.highestOneBit(n)<<1)-1));
		
		System.out.println(Integer.lowestOneBit(n));
	}

}
