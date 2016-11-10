package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *
 * Logic: 
 * 	cases:
 * 			1) current element greater than or equal to previous then just add to stack
 * 			2) current element less than previous, then process each element from stack until current element is greater than or equal to top of stack
 * Notice the case: 2 5 4 6 1
 * When you are processing element 4, you need the range [5 4 6], the stack needs to have the values as [2 4], notice 2 appears just before 5 that'll give 
 * starting range, end range is the current index
 */
public class LargestRectangleHistogram {

	public int largestRectangleArea(int[] heights) {
		if(heights.length==0)
			return 0;
		if(heights.length==1)
			return heights[0];
		int max=0;

		Stack<Integer> st=new Stack<Integer>();
		boolean done=false;
		int curIndex=0;
		while(!done){
			int curValue=heights[curIndex];
			if(!st.isEmpty()){
				if(curValue<heights[st.peek()]){ 
					while(!st.isEmpty() && curValue<heights[st.peek()]){
						int elementToProcess=st.pop(); //get element to process from stack
						int dist=st.isEmpty()?curIndex: curIndex-st.peek()-1; //NOTICE st.peek here that points to smaller element before current element
						int tempMax=heights[elementToProcess]*dist;
						if(max<tempMax)
							max=tempMax;
					}
				}				

			}
			st.push(curIndex);
			
			curIndex++;
			if(curIndex>=heights.length)
				done=true;
		}

		while(!st.isEmpty()){
			int prevIndex=st.pop();
			int dist=st.isEmpty()?curIndex: curIndex-st.peek()-1;
			int tempMax=heights[prevIndex]*dist;
			if(max<tempMax)
				max=tempMax;
		}


		return max;
	}	

	public static void main(String[] args) {

		assertThat(new LargestRectangleHistogram().largestRectangleArea(new int[]{6, 2, 5, 4, 6, 1, 6}), is(12));
		assertThat(new LargestRectangleHistogram().largestRectangleArea(new int[]{2,2,5,6,2,3}), is(12));
		assertThat(new LargestRectangleHistogram().largestRectangleArea(new int[]{2,1,5,6,2,3}), is(10));

		System.out.println("all test cases passed");
	}


	int getMaxArea(int hist[])
	{
		// Create an empty stack. The stack holds indexes of hist[] array
		// The bars stored in stack are always in increasing order of their
		// heights.
		Stack<Integer> s=new Stack<Integer>();
		int n=hist.length;

		int max_area = 0; // Initalize max area
		int tp;  // To store top of stack
		int area_with_top; // To store area with top bar as the smallest bar

		// Run through all bars of given histogram
		int i = 0;
		while (i < n)
		{
			// If this bar is higher than the bar on top stack, push it to stack
			if (s.empty() || hist[s.peek()] <= hist[i])
				s.push(i++);

			// If this bar is lower than top of stack, then calculate area of rectangle 
			// with stack top as the smallest (or minimum height) bar. 'i' is 
			// 'right index' for the top and element before top in stack is 'left index'
			else
			{
				tp = s.pop();  // store the top index

				// Calculate the area with hist[tp] stack as smallest bar
				area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

				// update max area, if needed
				if (max_area < area_with_top)
					max_area = area_with_top;
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (s.empty() == false)
		{
			tp = s.peek();
			s.pop();
			area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

			if (max_area < area_with_top)
				max_area = area_with_top;
		}

		return max_area;
	}

}
