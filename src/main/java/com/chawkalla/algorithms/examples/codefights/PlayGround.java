/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

/**
 * @author SFargose
 *
 */
public class PlayGround {

	int[] partialSort(int[] input, int k) {
		  int[] answer = new int[input.length];
		  int m = 0;
		  int infinity = (int) 1e3;

		  for (int i = 0; i < k; i++) {
		    int index = 0;
		    for (int j = 0; j < input.length; j++) {
		      if (input[j] < input[index]) {
		        index = j;
		      }
		    }
		    answer[m++] = input[index];
		    input[index] = infinity;
		  }
		  m--;
		  for (int i = 0; i < input.length; i++) {
		    if (input[i] != infinity) {
		      answer[m++] = input[i];
		    }
		  }

		  return answer;
		}

	
	

	public static void main(String[] args) {


		assertThat(new PlayGround().partialSort(new int[]{999999998, 999999999, 1}, 2), is(new int[]{1, 999999998, 999999999}));
		System.out.println("all cases passed");
	}

}
