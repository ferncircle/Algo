package com.chawkalla.algorithms.examples.heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.PriorityQueue;

public class NthUglyNumber {

	public int nthUglyNumber(int n) {
        if(n==1)
        	return 1;
        
        PriorityQueue<Long> queue=new PriorityQueue<Long>();
        
        int kth=0;
		//initial queue items
		queue.add(2l);
		queue.add(3l);
		queue.add(5l);

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
