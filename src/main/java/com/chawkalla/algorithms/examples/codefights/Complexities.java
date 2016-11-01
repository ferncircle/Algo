package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://codefights.com/challenge/xC96zeLvenT6FBcLk
 * We define the complexity of an integer n to be the smallest number of operations we need to represent n using only the digits 0, 1, ..., 9 (multiple times each, if necessary).

The operations allowed are addition (+), subtraction (-), multiplication (*), integer division (/, defined as the floor of normal division), and modulo operator (%, defined as a % b = a - b * (a / b)).

Examples:

The complexity of 72 is 1.
We can represent 72 using one operation as 8 * 9.

The complexity of 19 is 2.
We need at least two operations to represent 19. One representation is 5 * 5 - 6.

The complexity of 7 is 0.
We don't need any operations, since the number is already a digit.

The complexity of -7 is 1.
We need one operation to represent -7 as 0 - 7.

The complexity of 4187 is 5.
We need at least five operations to represent 4187. One representation is (5 * 9 + 8) * (8 * 9 + 7).

The complexity of 14771 is 6.
We need at least six operations to represent 14771. One representation is 9 * 9 * 9 * 9 * 9 / 4 + 9.

Given N, find the sum of complexities of all positive integers less than N.
 *
 */
public class Complexities {

	int MAX_VALUE=10000000;
	int[] comp=null;
	int sumOfComplexities(int N) {
		int sum=0;
		
		comp=new int[N*9];
		
		for (int i = 0; i < comp.length; i++) {
			if(i>9)
				comp[i]=MAX_VALUE;
		}
		int path=0;

		//generate all combinations using BFS
		int dummy=Integer.MAX_VALUE;
		Queue<Integer> queue=new LinkedList<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();
		for (int i = 1; i < 10; i++) {
			queue.add(i);
		}
		queue.add(dummy);
		while(!queue.isEmpty()){
			int current=queue.remove();	
			if(current==dummy && !queue.isEmpty()){		
				path++;
				queue.add(dummy);
			}else{				
				if(visited.contains(current))
					continue;
				visited.add(current);
				//generate neighbors
				//addition
				for (int i = 1; i < 10; i++) {
					int num=current+i;
					updateComplexity(num, path+1);
					queue.add(num);
				}			

				//subtraction
				for (int i = 1; i < 10; i++) {
					int num=current-i;
					updateComplexity(num, path+1);
					queue.add(num);
				}

				//multiplication
				for (int i = 1; i < 10; i++) {
					int num=current*i;
					updateComplexity(num, path+1);					
					queue.add(num);
				}
				

				//division
				for (int i = 2; i < 10; i++) {
					int num=current/i;
					updateComplexity(num, path+1);
					queue.add(num);
				}
				
			}
			if(path>5)
				break;
		}	
		
		//now go through again 
		for (int n = 1000; n < N; n++) { //for each number n
			

			if(comp[n]==0) //initialize n to max value
				comp[n]=MAX_VALUE;

			for (int a = 0; a < n; a++) { //for each number before n				
				//addition
				int b=n-a;
				int complexity=1+comp[a]+comp[b];
				if(complexity<comp[n])
					comp[n]=complexity;

				//multiplication
				if(a!=0 && n%a==0){
					b=n/a;
					complexity=1+comp[a]+comp[b];
					if(complexity<comp[n])
						comp[n]=complexity;							
				}
			}
		}
		
		//sum it all
		for (int i = 0; i < N; i++) {
			sum+=comp[i];
		}
		
		return sum;

	}

	
	public void updateComplexity(int num, int complexity){
		if(num >0 &&num<comp.length){
			if(comp[num]>complexity){
				comp[num]=complexity;
			}
				
		}
	}



	public static void main(String[] args) {
		long before=System.currentTimeMillis();
		assertThat(new Complexities().sumOfComplexities(100), is(155));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		assertThat(new Complexities().sumOfComplexities(417), is(1055));		
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		assertThat(new Complexities().sumOfComplexities(17000), is(85405));
		System.out.println("Time taken="+(System.currentTimeMillis()-before));
		
		System.out.println("All test cases passed");
	}

}
