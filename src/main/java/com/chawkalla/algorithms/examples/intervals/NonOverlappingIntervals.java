/**
 * 
 */
package com.chawkalla.algorithms.examples.intervals;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * 
 *
 */
public class NonOverlappingIntervals {

	public int eraseOverlapIntervals(Interval[] intervals) {
		if(intervals.length<2)
			return 0;
		
		int total=0;
		
		Arrays.sort(intervals, (a,b)->a.start==b.start?a.end-b.end:a.start-b.start);
		
		//total=1;
		int endBoundary=intervals[0].end;
		for (int i = 1; i < intervals.length; i++) {
			Interval cur=intervals[i];
			if(endBoundary>cur.start){
				endBoundary=Math.min(endBoundary, cur.end);
				total++;
			}else{
				endBoundary=cur.end;
			}
		}
		return total;
	}
	

	public static void main(String[] args) {
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 6),
				new Interval(2, 5),
				new Interval(3, 4),
				new Interval(1, 3)
				}), is(2));		
		
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 4),
				new Interval(2, 4),
				new Interval(3, 4),
				new Interval(1, 3)
				}), is(2));
		
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 2),
				new Interval(2, 3),
				new Interval(3, 4),
				new Interval(1, 3)
				}), is(1));
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 5),
				new Interval(1, 3),
				new Interval(2, 3),
				new Interval(3, 4)
				}), is(2));
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 2),
				new Interval(1, 2),
				new Interval(1, 2)
				}), is(2));
		assertThat(new NonOverlappingIntervals().eraseOverlapIntervals(new Interval[]{
				new Interval(1, 2),
				new Interval(2, 3)
				}), is(0));
		System.out.println("All test cases passed");

	}

	public static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}

}
