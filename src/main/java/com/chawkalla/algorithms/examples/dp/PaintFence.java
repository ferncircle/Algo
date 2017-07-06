/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.chawkalla.algorithms.Matrix;

/**
 * 
 * https://leetcode.com/problems/paint-fence/#/description
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Hide Company Tags Google
Hide Tags


Solution:
We divided it into two cases.

the last two posts have the same color, the number of ways to paint in this case is sameColorCounts.

the last two posts have different colors, and the number of ways in this case is diffColorCounts.

The reason why we have these two cases is that we can easily compute both of them, and that is all I do. When adding a new post

, we can use the same color as the last one (if allowed) or different color. If we use different color, there're k-1 options,
 and the outcomes should belong to the diffColorCounts category. If we use same color, there's only one option, and we can only
  do this when the last two have different colors (which is the diffColorCounts). There we have our induction step.

Here is an example, let's say we have 3 posts and 3 colors. The first two posts we have 9 ways to do them, (1,1), (1,2), (1,3), 
(2,1), (2,2), (2,3), (3,1), (3,2), (3,3). Now we know that

diffColorCounts = 6;
And

sameColorCounts = 3;
Now for the third post, we can compute these two variables like this:

If we use different colors than the last one (the second one), these ways can be added into diffColorCounts, so if the last one
 is 3, we can use 1 or 2, if it's 1, we can use 2 or 3, etc. Apparently there are (diffColorCounts + sameColorCounts) * (k-1) 
 possible ways.

If we use the same color as the last one, we would trigger a violation in these three cases (1,1,1), (2,2,2) and (3,3,3). 
This is because they already used the same color for the last two posts. So is there a count that rules out these kind of 
cases? YES, the diffColorCounts. So in cases within diffColorCounts, we can use the same color as the last one without worrying 
about triggering the violation. And now as we append a same-color post to them, the former diffColorCounts becomes the current 
sameColorCounts.

Then we can keep going until we reach the n. And finally just sum up these two variables as result.
 *
 */
public class PaintFence {

	public long numWays0(int n, int k) {
	    if(n == 0) return 0;
	    else if(n == 1) return k;
	    int[] diffColorCounts=new int[n+1];
	    int[] sameColorCounts=new int[n+1];
	    
	    diffColorCounts[0]=1; diffColorCounts[1]=k; diffColorCounts[2]=k*(k-1); //k ways to choose first fence and k-1 ways to paint second fence
	    sameColorCounts[0]=1; sameColorCounts[1]=k; sameColorCounts[2]=k; //k ways to paint first fence which is continued to second fence
	    for (int i = 3; i <=n; i++) { //start with third post now
			sameColorCounts[i]=diffColorCounts[i-1]* 1; //only one way of doing it
			diffColorCounts[i]=(sameColorCounts[i-1]+diffColorCounts[i-1])*(k-1); // k-1 ways of doing it
		}
	    return diffColorCounts[n]+sameColorCounts[n];
	    		
	    //space optimized way of doing
	   /* int diffColorCounts = k*(k-1);
	    int sameColorCounts = k;
	    for(int i=2; i<n; i++) {
	        int temp = diffColorCounts;
	        diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
	        sameColorCounts = temp;
	    }
	    return diffColorCounts + sameColorCounts;*/
	}
	
	
	/**
	 * Approach 2: For 3 posts:
	 * if first and second have same color(k) then third can have k-1 options=k*(k-1)
	 * if first and second have diff color(k*k-1) then third has k options= k*(k-1)*k
	 * 
	 * total for f(3)=k*(k-1)+k*(k-1)*k=(k-1)(k+k*k)
	 * but we know f(1)=k and f(2)=k*k
	 * so 
	 * f(3) =(k-1)(f(1)+f(2))
	 */
	public long numWays1(int n, int k) {
	    if(n == 0) return 0;
	    else if(n == 1) return k;
	    
	    int[] dp={0,k,k*k,0};
	    
	    for (int i = 2; i < n; i++) {
			
	    	dp[3]=(k-1)*(dp[1]+dp[2]);
	    	dp[1]=dp[2];
	    	dp[2]=dp[3];
		}
	    
	    return dp[3];
	}
	
	/**
	 * Linear recurrence using Matrix
	 */
	public long numWays(int n, int k) {
	    if(n == 0) return 0;
	    else if(n == 1) return k;
	    
	    long[][] Q={{0, 1},{k-1, k-1}};
	    long[][] initial={{k},{k*k}};
	    
	    long[][] p=Matrix.matrixPower(Q, n-1);
	    
	    long[][] res=Matrix.matrixMultiply(p, initial);
	    
	    return res[0][0];
	}
	
	
	public static void main(String[] args) {
		
		assertThat(new PaintFence().numWays(3, 3), is(24L));
		assertThat(new PaintFence().numWays(5, 3), is(180L));
		assertThat(new PaintFence().numWays(10, 3), is(27408L));
		
		System.out.println("all cases passed");

	}

}
