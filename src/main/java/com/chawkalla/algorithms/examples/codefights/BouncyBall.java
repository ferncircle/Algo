package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

/**
 * https://codefights.com/challenge/iNbqN6rnfbphx74Q2
 * On each bounce, the ball may jump over one step or it may jump over several steps. 
 *write a program that, given the acceleration of the ball a and the length of the flight of stairs n, 
 *returns the number of valid paths the ball can take to cover the flight.
 *For n = 10 and a = 1, the output should be
bouncyBall(n, a) = 10.
 If a ball has acceleration a, each bounce must jump over at least a more steps than the previous bounce. 
 For example, if a ball with acceleration 2 makes its way down a flight of 11 stairs, 
 it may follow the path 1, 4, 6 but it may not follow the path 2, 3, 6. 
 *
 *Solution(for DP): For test cases, n=10, a=3
 * Start from backwards, notice one easy middle case when you are at step 3 and your next step is 7. 
 * How many previous steps are allowed before 3 with acceleration a=3?
 * That would be 7-3=4 (nextjump-a). ie From steps -1, 0, 1, 2
 * Also, notice the special case at step 1
 */
public class BouncyBall {

	HashMap<String, Integer> cache=new HashMap<String, Integer>();
	int bouncyBall(int n, int a) {

		//int total=jump(n, 0, 3, a);
		//int total=jumpReverse(n, n, 0, a);
		int total=jumpReverseDP(n, a);
		return total;
	}
	
	int jump(final int n, int currentStep, int prevJumpLength, final int a){
		if(currentStep==n)
			return 1;
		if(currentStep>n)
			return 0;
		String key=currentStep+":"+prevJumpLength;
		if(cache.containsKey(key))
			return cache.get(key);
		int minAcceleration=a;
		if(currentStep==0)
			minAcceleration=1;
		
		int total=0;
		
		for (int i = prevJumpLength+minAcceleration; i <= n; i++) {
			total=total+jump(n, currentStep+i, i, a);
		}
		cache.put(key, total);
		return total;
	}
	
	int jumpReverse(final int n, int currentStep, int nextJumpLength, final int a){
		
		int backjumpLimit=(nextJumpLength==0)?n:(nextJumpLength-a);		
		
		if(currentStep==0 )
			return 1;
		if(currentStep==1 && backjumpLimit>0)
			return 1;
		if(currentStep<0)
			return 0;
		if(nextJumpLength<0)
			return 0;
		if(backjumpLimit<0)
			return 0;
		int total=0;
		
				
		for(int backJumpLength=1;backJumpLength<=backjumpLimit;backJumpLength++){			
			total=total+jumpReverse(n, currentStep-backJumpLength, backJumpLength, a);
		}
		return total;
	}
	
	int jumpReverseDP(final int n, final int a){

		int[][] dp=new int[n+1][n+1];		
		for (int currentStep = 0; currentStep < dp.length; currentStep++) {
			
			for (int nextJumpLength = 0; nextJumpLength < dp.length; nextJumpLength++) {
				int backjumpLimit=(nextJumpLength==0)?n:(nextJumpLength-a);		
				
				if(currentStep==0 || (currentStep==1 && backjumpLimit>0)){
					dp[currentStep][nextJumpLength]=1;
				}
				else{
					int total=0;					
					
					for(int backJumpLength=1;backJumpLength<=backjumpLimit;backJumpLength++){	
						int backStep=currentStep-backJumpLength;
						if(backStep>=0)
							total=total+dp[currentStep-backJumpLength][backJumpLength];
					}
					dp[currentStep][nextJumpLength]=total;
				}
			}
		}
		
		return dp[n][0];
	}

	public static void main(String[] args) {
		
		assertThat(new BouncyBall().bouncyBall(10, 3), is(4)); // 10-0, 9-1, 8-2, 7-3
		assertThat(new BouncyBall().bouncyBall(10, 1), is(10));		
		assertThat(new BouncyBall().bouncyBall(10, 0), is(42));
		assertThat(new BouncyBall().bouncyBall(36, 2), is(239));
		assertThat(new BouncyBall().bouncyBall(79, 36), is(22)); 
		System.out.println("all test cases passed");
	}

}
