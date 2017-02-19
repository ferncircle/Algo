package com.chawkalla.algorithms.examples.dp;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * 
 * The following is a description of the instance of this famous puzzle involving n=2 eggs and a building with k=36 floors.

Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from, and which will 
cause the eggs 
to break on landing. We make a few assumptions:

…..An egg that survives a fall can be used again.
…..A broken egg must be discarded.
…..The effect of a fall is the same for all eggs.
…..If an egg breaks when dropped, then it would break if dropped from a higher floor.
…..If an egg survives a fall then it would survive a shorter fall.
…..It is not ruled out that the first-floor windows break eggs, nor is it ruled out that the 36th-floor do not cause an egg 
to break.

If only one egg is available and we wish to be sure of obtaining the right result, the experiment can be carried out in only 
one way. Drop the egg from the first-floor window; if it survives, drop it from the second floor window. Continue upward until 
it breaks. In the worst case, this method may require 36 droppings. Suppose 2 eggs are available. What is the least number of 
egg-droppings that is guaranteed to work in all cases?
The problem is not actually to find the critical floor, but merely to decide floors from which eggs should be dropped so that 
total number of trials are minimized.

Solution: see below
We have to find worst case trials for e eggs and f floors. If e=1, then worst case total trials are f
Goal is to minimize the trials (cost)

Create a trials(cost) matrix[e][f] which tells maximum trials required for e eggs and f floors. 
For each floors, find a divider from 1 to f(since that egg, divider, can be used anywhere from 1 to f). 
There will be two cases:
		a) egg breaks, then reduce eggs and find solution for floors below (e-1, floorsBelow)
		b) egg doesn't break, then find solution for remaining floors using same number of eggs 
			(e, Totalfloors-floorsBelow)

 */
public class EggDropping {
	
	public int calculateRecursive(final int eggs, final int floors){
        if(eggs == 1){
            return floors;
        }
        if(floors == 0){
            return 0;
        }
        int min = 1000;
        for(int i=1; i <= floors; i++){
            int val = 1 //current trial
            		+ Math.max(
            				calculateRecursive(eggs-1, i-1),//total trials on floors below with one less egg(egg breaks here)
            				calculateRecursive(eggs, floors-i)//total trials on floors above with same egg
            				);
            if(val < min){
                min = val;
            }
        }
        return min;
    }

    public int calculate(int eggs, int floors){
        
        int T[][] = new int[eggs+1][floors+1];
        int D[][] = new int[eggs+1][floors+1];
        
        int currentTrials =0;
        for(int i=0; i <= floors; i++){
            T[1][i] = i;
        }
        
        //Note that this is little different than break a sentence problem 
        //For break a sentence, each char is different where as here each floor is same
        for(int e = 2; e <= eggs; e++){
            for(int f = 1; f <=floors; f++){
                T[e][f] = Integer.MAX_VALUE;
                for(int k = 1; k <=f ; k++){ 
                	int remainingFloors=f-k;
                    currentTrials = 1 //current trial
                    				+ Math.max(
	                    				T[e-1][k-1], //total trials on floors below with one less egg(egg breaks here)
	                    				T[e][remainingFloors] //total trials on floors above with same egg
                    						);
                    if(currentTrials < T[e][f]){
                        T[e][f] = currentTrials;
                        D[e][f]=k;
                    }
                }
            }
        }
        
        print(T);
        print(D);
        return T[eggs][floors];
    }
    
    public static void print(int[][] a){
    	for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}

		System.out.println("-------------------");
    }
        
    public static void main(String args[]){
        EggDropping ed = new EggDropping();
        int r = ed.calculate(2,100);
        System.out.println(r);
    }
    
    /**
     * 
     * When we drop an egg from a floor x, there can be two cases (1) The egg breaks (2) The egg doesn’t break.

1) If the egg breaks after dropping from xth floor, then we only need to check for floors lower than x with remaining eggs; 
so the problem reduces to x-1 floors and n-1 eggs
2) If the egg doesn’t break after dropping from the xth floor, then we only need to check for floors higher than x; so the 
problem reduces to k-x floors and n eggs.

Since we need to minimize the number of trials in worst case, we take the maximum of two cases. We consider the max of above 
two cases for every floor and choose the floor which yields minimum number of trials.

  k ==> Number of floors
  n ==> Number of Eggs
  eggDrop(n, k) ==> Minimum number of trials needed to find the critical
                    floor in worst case.
  eggDrop(n, k) = 1 + min{max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)): 
                 x in {1, 2, ..., k}}
     * 
     */
}
