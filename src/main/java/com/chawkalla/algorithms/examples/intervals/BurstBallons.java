/**
 * 
 */
package com.chawkalla.algorithms.examples.intervals;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * 
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 *
 *
 *Solution: 1) sort by start time
 *2) For each point, look ahead and check if current end is greater than or equal to next start. Adjust end by comparing it with next point's end
 *
 * O(nlogn)+O(n)
 * 
 * Other Solution: Adjust endBoundary at each point. Check if previous endBoundary is greater than current start, if yes, check if you need to continue using
 * previous endBoundary
 */
public class BurstBallons {

	public int findMinArrowShots(int[][] points) {
		if(points.length==0)
			return 0;
		int total=0;
		Arrays.sort(points, (a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]); //TODO nice way of sorting array
		
		/*for (int i = 0; i < points.length; i++) {
			int[] cur=points[i];
			int end=cur[1];
			int j=i+1;
			while(j<points.length && points[j][0] <= end){
				end=Math.min(end, points[j][1]);
				j++;
			}
			total++;
			i=j-1;
			
		}
		*/
		total=1;
		int endBoundary=points[0][1];
        
        for (int i = 1; i < points.length; i++) {
			if(endBoundary >= points[i][0]){
				endBoundary=Math.min(endBoundary, points[i][1]);
			}else{
				endBoundary=points[i][1];
				total++;
			}
        }

		return total;
	}


	public static void main(String[] args) {
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{1,9},{7,16},{2,5},{7,12},{9,11},{2,10},{9,16},{3,9},{1,3}
		}), is(2));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{1,5},{2,3},{4,5},{6,8},{7,10}
		}), is(3));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{1,2},{2,3},{3,4},{4,5}
		}), is(2));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{1,2},{3,4},{5,6},{7,8}
		}), is(4));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
		}), is(2));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
		}), is(0));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{-2147483648,2147483647}
		}), is(1));
		assertThat(new BurstBallons().findMinArrowShots(new int[][]{
			{10,16}, {2,8}, {1,6}, {7,12}
		}), is(2));

		System.out.println("All test cases passed");
	}

}
