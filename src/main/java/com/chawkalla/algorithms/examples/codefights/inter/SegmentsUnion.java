/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://codefights.com/tournaments/NfsMqkaNkZDWhAajY/A
 * 
 * Consider some segments with positive lengths on a straight line. You need to find the length of segments union.

Example

For left = [1, 2, 5] and right = [3, 4, 6], the output should be
segmentsUnion(left, right) = 4.


 *
 */
public class SegmentsUnion {

	int segmentsUnion(int[] left, int[] right) {

		  class Pair implements Comparable<Pair> {
		    int first;
		    int second;

		    Pair(int first, int second) {
		      this.first = first;
		      this.second = second;
		    }

		    @Override
		    public int compareTo(Pair p) {
		      return first != p.first ? Integer.compare(first, p.first) :
		          Integer.compare(second, p.second);
		    }
		  }

		  int answer = 0;
		  int opened = 0;
		  ArrayList<Pair> events = new ArrayList<>();

		  for (int i = 0; i < left.length; i++) {
		    events.add(new Pair(left[i], 1));
		    events.add(new Pair(right[i], -1));
		  }

		  Collections.sort(events);

		  for (int i = 0; i < events.size(); i++) {
		    if (opened > 0) {
		      answer += events.get(i).first -events.get(i - 1).first ;
		    }
		    opened += events.get(i).second;
		  }

		  return answer;
		}



	public static void main(String[] args) {
		
		
		assertThat(new SegmentsUnion().segmentsUnion(new int[]{1, 2, 5}, new int[]{3, 4, 6}), is(4));
		System.out.println("passed");
	}

}
