/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://leetcode.com/problems/russian-doll-envelopes/
 *You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


 */
public class RussianDollEnvelopes {

	public class Envelope implements Comparable<Envelope>{
		int width;
		int height;
		int maxEnvelopesHere=1;
		
		public Envelope(int width, int height) {
			super();
			this.width = width;
			this.height = height;
		}

		@Override
		public int compareTo(Envelope o) {
			return width-o.width;
		}

		@Override
		public String toString() {
			return "w="+width+"& h="+height;
		}
		
	}
	public int maxEnvelopes(int[][] envelopes) {
        int sol=0;
        if(envelopes.length<=1)
        	return envelopes.length;
        
        ArrayList<Envelope> list=new ArrayList<Envelope>();
        for (int i = 0; i < envelopes.length; i++) 
        	list.add(new Envelope(envelopes[i][0], envelopes[i][1]));
			
		Collections.sort(list);
		
		for (int i = 1; i < list.size(); i++) {
			Envelope cur=list.get(i);
			for (int j = 0; j < i; j++) {
				Envelope prev=list.get(j);
				if(cur.height>prev.height && cur.width!=prev.width && prev.maxEnvelopesHere+1>cur.maxEnvelopesHere)
					cur.maxEnvelopesHere=prev.maxEnvelopesHere+1;
					
			}
		}
		sol=list.get(0).maxEnvelopesHere;
		for (int i = 1; i < list.size(); i++) {
			if(sol<list.get(i).maxEnvelopesHere)
				sol=list.get(i).maxEnvelopesHere;
		}
        return sol;
    }
	
	public static void main(String[] args) {
		assertThat(new RussianDollEnvelopes().maxEnvelopes(new int[][]{
			{5,4},{6,4},{6,7},{2,3}
		}), is(3));
		assertThat(new RussianDollEnvelopes().maxEnvelopes(new int[][]{
			{5,4},{6,4},{6,7},{2,3}, {1,2}, {6,4}
		}), is(4));
		
		System.out.println("all test cases passed");
	}

}
