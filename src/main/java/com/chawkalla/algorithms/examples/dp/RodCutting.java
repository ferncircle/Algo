/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * N = 6
A = [1, 2, 5]

If we make cuts in order [1, 2, 5], let us see what total cost would be.
For first cut, the length of rod is 6.
For second cut, the length of sub-rod in which we are making cut is 5(since we already have made a cut at 1).
For third cut, the length of sub-rod in which we are making cut is 4(since we already have made a cut at 2).
So, total cost is 6 + 5 + 4.


Notice we need to group into left and right in form, (  )(  ). Pad cuts with 0 and n to make it easier
[0,1,2,5,6]
 *
 *
 *
 *
 *
 *We rewrite our problem as given N cut points(and you cannot make first and last cut), decide order of these cuts to minimise the cost. So, we insert 0 and N at beginning
 * and end of vector B. Now, we have solve our new problem with respect to this new array(say A).

We define dp(i, j) as minimum cost for making cuts Ai, Ai+1, …, Aj. Note that you are not making cuts Ai and Aj, but they decide the cost for us.

For solving dp(i, j), we iterate k from i+1 to j-1, assuming that the first cut we make in this interval is Ak. The total cost required(if we make first cut at Ak) is 
Aj - Ai + dp(i, k) + dp(k, j).

This is our solution. We can implement this DP recursively with memoisation. Total complexity will be O(N3).
For actually building the solution, after calculating dp(i, j), we can store the index k which gave the minimum cost and then we can build the solution backwards.
 */
public class RodCutting {

	public ArrayList<Integer> rodCut(int A, ArrayList<Integer> B) {
                    
		int n=B.size()+2; //add 0th and nth cut
		
		int[] cuts=new int[n];
		for (int i = 0; i < B.size(); i++) 
			cuts[i+1]=B.get(i);
		
		cuts[n-1]=A;
		
        int[][] dp=new int[n][n];
        int[][] dpCuts=new int[n][n];
        
        for(int len=1;len<=n;len++){ //interval should be at least 1
            
            for(int s=0;s+len<n;s++){
                int e=s+len;
                dp[s][e]=Integer.MAX_VALUE;
                int subRodLen=cuts[e]-cuts[s];
                for(int k=s+1;k<e;k++){
                	int leftCost=dp[s][k];
                	int rightCost=dp[k][e];
                	
                	int cost=subRodLen+leftCost+rightCost;
                    if(dp[s][e]>cost){
                    	dp[s][e]=cost;
                    	dpCuts[s][e]=k;
                    }
                }
            }
        }
        
        ArrayList<Integer> res=new ArrayList<Integer>();
        backtrack(dpCuts, cuts, 0, n-1, res);
        
        return res;        
    }
	
	public void backtrack(int[][] dpCuts, int[] cuts,  int s, int e, ArrayList<Integer> res){
		if(s+1>=e) return;
		
		res.add(cuts[dpCuts[s][e]]);
		backtrack(dpCuts, cuts, s, dpCuts[s][e], res);
		backtrack(dpCuts, cuts, dpCuts[s][e], e, res);
		
	}
	public static void main(String[] args) {
		
		assertThat(new RodCutting().rodCut(6, new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 5}))), 
				is(new ArrayList<Integer>(Arrays.asList(new Integer[]{2, 1, 5}))));
		System.out.println("All cases passed");

	}

}
