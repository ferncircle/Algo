package com.chawkalla.algorithms.examples.heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 */
public class NthUglyNumber {

	public int nthUglyNumber(int n) {
        if(n==1)
        	return 1;
        
        PriorityQueue<Long> queue=new PriorityQueue<Long>();
        
        int kth=0;
		//initial queue items
		queue.add(2L);
		queue.add(3L);
		queue.add(5L);

		int counter=2;
		boolean done=false;
		while(!done){
			//remove smallest item 
			long item=queue.remove();
			if(counter==n){
				kth=(int)item;
				break;
			}			
			
			//generate neighbors of smallest item and add to queue
			long newIitem=item*2;
			if(newIitem>0 && !queue.contains(newIitem))
				queue.add(newIitem);
			newIitem=item*3;
			if(newIitem>0 && !queue.contains(newIitem))
				queue.add(newIitem);
			newIitem=item*5;
			if(newIitem>0 && !queue.contains(newIitem))
				queue.add(newIitem);
			counter++;
		}

		return kth;
    }
	
	public static void main(String[] args) {
		
		assertThat(new NthUglyNumber().nthUglyNumber(6), is(6));
		assertThat(new NthUglyNumber().nthUglyNumber(7), is(8));
		assertThat(new NthUglyNumber().nthUglyNumber(10), is(12));
		assertThat(new NthUglyNumber().nthUglyNumber(1407), is(536870912));
		assertThat(new NthUglyNumber().nthUglyNumber(1600), is(1399680000));
		System.out.println("all test cases passed");
	}

}
