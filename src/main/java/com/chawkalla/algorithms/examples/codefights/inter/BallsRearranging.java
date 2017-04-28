package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

/**
 * https://codefights.com/interview/zR3sSovjZ4ny2Me2A/description
 * 
 * An infinite number of boxes are arranged in a row and numbered from left to right. The first box on the left is number 1, 
 * the next box is number 2, etc. n balls are placed in n of the boxes, and there can only be one ball per box. You want to 
 * organize the balls, so you decide to arrange all the balls next to each other. They should occupy a contiguous set of boxes. You can take one ball and move it into an empty box in one move.

Given an array balls that indicates the numbers of the boxes in which the balls are placed, find the minimum number of 
moves needed to organize the balls.

Example

For balls = [6, 4, 1, 7, 10], the output should be
ballsRearranging(balls) = 2.

In this example, the minimum number of moves needed to arrange the balls next to each other is 2. You could move the ball 
in box 1 to box 5 and the ball in box 10 to box 8 (or to box 3).

Solution:
1) Sort the array
2) Use the window that has enough holes to accommodate rest of boxes. If it has more holes then shrink from start 
	otherwise expand from end
	ballsToMove=Totalboxes-boxes in window
 *
 */
public class BallsRearranging {

	int ballsRearranging(int[] balls) {
	    if(balls.length<=1) return 0;
	    
	    //sort the balls
	    Arrays.sort(balls);
	    
	    int start=0, end=0;
	    int holes=0;
	    int windowItems=1;
	    boolean done=false;
	    int min=Integer.MAX_VALUE;
	    // 1 4 6 7 10
	    while(!done){
	        int ballsToMove=balls.length-windowItems;        
	        if(holes<=ballsToMove){            
	            min=Math.min(min, ballsToMove);
	            //expand window and increase holes
	            end++;            
	            windowItems++;
	            if(end<balls.length)
	            	holes+=(balls[end]-balls[end-1]-1);
	        }else{
	            //shrink and reduce holes
	            start++;
	            windowItems--;
	            
	            holes-=(balls[start]-balls[start-1]-1);
	        }
	        
	        if(end==balls.length)
	            done=true;
	    }
	    
	    return min;
	}



	public static void main(String[] args) {


		assertThat(new BallsRearranging().ballsRearranging(new int[]{25,24}),is(0));
		
		assertThat(new BallsRearranging().ballsRearranging(new int[]{1,3,4,5,10,11,12,14}),is(4));
		assertThat(new BallsRearranging().ballsRearranging(new int[]{74, 34, 69, 71, 89, 85, 73, 88, 38, 43, 2, 27, 84, 11, 48, 
				99, 61, 15, 66, 81, 3, 51, 26, 63, 94, 24, 70, 93, 12, 1, 78, 32, 55, 62, 96, 33, 75, 54, 92, 95, 13, 53, 
				36, 28, 64, 49, 65, 72, 31, 45}),is(20));
		assertThat(new BallsRearranging().ballsRearranging(new int[]{6, 4, 1, 7, 10}),is(2));
		
		System.out.println("all cases passed");

	}

}
