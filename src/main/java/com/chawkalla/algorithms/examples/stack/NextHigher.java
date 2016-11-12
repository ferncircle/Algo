package com.chawkalla.algorithms.examples.stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

public class NextHigher {

	public static int[] constructNextHigher(int[] a){
		if(a==null || a.length==0)
			return a;
		
		int[] b=new int[a.length];
		Stack<Integer> st=new Stack<Integer>();
		
		for (int i = 0; i < a.length; i++) {
			int cur=a[i];
			if(!st.isEmpty()){
				while (!st.isEmpty() && cur>=a[st.peek()]) {
					b[st.pop()]=i;
				}
			}
			st.push(i);
			
		}
		
		return b;
				
	}
	
	
	public static void main(String[] args) {

		assertThat(constructNextHigher(new int[]{5, 2,3,6}), is(new int[]{3,2,3,3}));
		//assertThat(constructNextHigherWithoutStack(new int[]{5, 2,3,6}), is(new int[]{3,2,3,3}));
		
		System.out.println("All test cases passed");

	}

}
