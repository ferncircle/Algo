/**
 * 
 */
package com.chawkalla.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.google.challenges.EscapePods41;

/**
 * @author SFargose
 *
 */
public class TestAnswer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		assertThat(EscapePods41.answer(new int[]{0}, new int[]{3}, new int[][]{
			{0, 7, 0, 0}, 
			{0, 0, 6, 0}, 	
			{0, 0, 0, 8}, 
			{9, 0, 0, 0}}), 
				is(6));
		assertThat(EscapePods41.answer(new int[]{0, 1}, new int[]{4, 5}, new int[][]{
			{0, 0, 4, 6, 0, 0}, 
			{0, 0, 5, 2, 0, 0}, 
			{0, 0, 0, 0, 4, 4}, 
			{0, 0, 0, 0, 6, 6}, 
			{0, 0, 0, 0, 0, 0}, 
		    {0, 0, 0, 0, 0, 0}}), 
				is(16));
				
		System.out.println("all cases passed");

	}

}
