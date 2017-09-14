/**
 * 
 */
package com.chawkalla.algorithms.examples.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author SFargose
 *
 */
public class SlidingWindowMaximum {

	public ArrayList<Integer> slidingMaximum(final List<Integer> a, int b) {
	    Deque<Integer> dq=new LinkedList<Integer>();
	    
	    ArrayList<Integer> res=new ArrayList<Integer>();
	    
	    dq.add(0);
	    for(int i=1;i<b;i++){
	        while(!dq.isEmpty() && a.get(dq.peekLast()) <= a.get(i))
	            dq.pollLast();
	        dq.offerLast(i);
	    }
	    res.add(a.get(dq.peekFirst()));
	    
	    for(int i=b;i<a.size();i++){
	        if(i-dq.peekFirst()>=b) //make space if needed
	            dq.pollFirst();
	        while(!dq.isEmpty() && a.get(dq.peekLast()) <= a.get(i))
	            dq.pollLast();
	        dq.offerLast(i);
	        res.add(a.get(dq.peekFirst()));
	    }
	    
	    
	    return res;      
	    
	    
	}
	public static void main(String[] args) {
		
		System.out.println(new SlidingWindowMaximum().slidingMaximum(IntStream.of(new int[]{3, 8, -1, 7, 5, 3, 6, 7}).
				boxed().collect(Collectors.toList()), 3));

	}

}
