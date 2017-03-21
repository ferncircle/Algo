/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://codefights.com/interview/wjewD7BPuQDhfa5yx
 *
 *Given an array of points on a 2D plane, find the maximum number of points that are visible from point (0, 0) with a 
 *viewing angle that is equal to 45 degrees.

Example

For

  points = [[1, 1], [3, 1], [3, 2], [3, 3],
            [1, 3], [2, 5], [1, 5], [-1, -1],
            [-1, -2], [-2, -3], [-4, -4]]
the output should be visiblePoints(points) = 6.

Solution:
1) Get all angles relative to positive x-axis (using arc tan (y/x)) for each point(x,y)
	Math.toDegrees(Math.atan2(y, x));
	
2) Sort those angles array and find the largest window 
 */
public class VisiblePoints {

	int visiblePoints(int[][] points) {
		int total=0;

		double[] angles=new double[points.length];


		for (int i = 0; i < points.length; i++) {
			//other way is
			//double angle=180*(Math.atan2(points[i][1], points[i][0]))/Math.PI;
			double angle=Math.toDegrees(Math.atan2(points[i][1], points[i][0]));
			angles[i]=angle<0?180+(180+angle):angle;
		}

		Arrays.sort(angles);
		
		int start=0;
		int end=0;		

		int windowSize=0;
		
		while(start<angles.length){

			if (angles[end]-angles[start]<=45) { //if it's proper window then check window size
				windowSize=(end-start)+1;	
				if(windowSize>total)
					total=windowSize;
			}			
			
			if(angles[end]-angles[start]<=45 && end<angles.length-1){//if window is smaller then expand at end					
				end++;
			}else{
				start++; //otherwise contract from start
			}
			
		}

		return total;

	}

	public static void main(String[] args) {

		assertThat(new VisiblePoints().visiblePoints(new int[][]{
			{1,1}, 
			{3,1}, 
			{3,2}, 
			{3,3}, 
			{1,3}, 
			{2,5}, 
			{1,5}, 
			{-1,-1}, 
			{-1,-2}, 
			{-2,-3}, 
			{-4,-4}
		}), is(6));
		
		System.out.println(180*(Math.atan2(-1, 0))/Math.PI);
		System.out.println("All test cases passed");

	}

}
