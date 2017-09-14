/**
 * 
 */
package com.chawkalla.algorithms.examples.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

/**
 * @author SFargose
 *
 */
public class OrderOfPeopleHeights {

	public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		for (int i=0; i<heights.size(); i++) 
			map.put((int)heights.get(i), (int)infronts.get(i));


		while(!map.isEmpty()) {
			int height = map.lastKey();
			int taller = map.get(height);
			map.remove(height);

			result.add(taller,height);

		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public ArrayList<Integer> orderLightweight(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
		ArrayList<Integer> result = new ArrayList(Collections.nCopies(heights.size(), (Integer)null));

		ArrayList<Pair> input = new ArrayList<Pair>(heights.size());
		for(int i = 0; i < heights.size(); i++) {
			input.add(new Pair(heights.get(i), infronts.get(i)));
		}

		Collections.sort(input);

		for(int i = 0; i < input.size(); i++) {
			Pair p = input.get(i);
			int j = 0;
			int count = 0;
			while(count < p.infront) {
				if(result.get(j) == null) {
					count++;
				}
				j++;
			}
			while(result.get(j) != null) j++;
			result.set(j, p.height);
		}

		return result;
	}

	private class Pair implements Comparable<Pair> {
		private int height;
		private int infront;
		private Pair(int h, int i) {
			height = h;
			infront = i;
		}

		public int compareTo(Pair other) {
			return this.height - other.height;
		}
	}
	public static void main(String[] args) {

		assertThat(new OrderOfPeopleHeights().order(
				new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 3, 2, 6, 1, 4})), 
				new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 2, 0, 3, 2}))), 
				is(new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 3, 2, 1, 6, 4 }))));

		assertThat(new OrderOfPeopleHeights().orderLightweight(
				new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 3, 2, 6, 1, 4})), 
				new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 1, 2, 0, 3, 2}))), 
				is(new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 3, 2, 1, 6, 4 }))));


		System.out.println("all cases passed");
	}

}
