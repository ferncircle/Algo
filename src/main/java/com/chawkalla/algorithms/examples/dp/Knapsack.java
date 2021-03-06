package com.chawkalla.algorithms.examples.dp;

public class Knapsack {

	public static void main(String[] args) {
		int N = Integer.parseInt("5");   // number of items
		int W = Integer.parseInt("20");   // maximum weight of knapsack

		int[] profit = new int[N+1];
		int[] weight = new int[N+1];

		// generate random instance, items 1..N
		for (int n = 1; n <= N; n++) {
			profit[n] = (int) (Math.random() * 1000);
			weight[n] = (int) (Math.random() * W);
		}

		// opt[n][w] = max profit of packing items 1..n with weight limit w
		// sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
		int[][] opt = new int[N+1][W+1];
		boolean[][] sol = new boolean[N+1][W+1];

		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= W; w++) {

				// don't take item n
				int option1 = opt[n-1][w];

				// take item n
				int option2 = Integer.MIN_VALUE;
				if (weight[n] <= w) option2 = profit[n] + opt[n-1][w-weight[n]];

				// select better of two options
				opt[n][w] = Math.max(option1, option2);
				sol[n][w] = (option2 > option1);
			}
			//System.out.println("first pass");
		}

		// determine which items to take
		boolean[] take = new boolean[N+1];
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w]) { take[n] = true;  w = w - weight[n]; }
			else           { take[n] = false;                    }
		}

		// print results
		System.out.println("item" + "\t" + "profit" + "\t" + "weight" + "\t" + "take");
		for (int n = 1; n <= N; n++) {
			System.out.println(n + "\t" + profit[n] + "\t" + weight[n] + "\t" + take[n]);
		}
		
		System.out.println("Total profit="+opt[N][W]);
		
		System.out.println("Using recursion="+maxValue(N, W, profit, weight));
	}
	
	public static int maxValue(int item, int capacity, final int[] profit, final int[] weight){
		int val=0;
		if(item<=0 || capacity<=0)
			return 0;
		
		// don't take item n
		int option1 = maxValue(item-1, capacity, profit, weight);

		// take item n
		int option2 = Integer.MIN_VALUE;
		if (weight[item] <= capacity) option2 = profit[item] + maxValue(item-1, capacity-weight[item], profit, weight);

		// select better of two options
		val = Math.max(option1, option2);
		
		return val;
	}
}
