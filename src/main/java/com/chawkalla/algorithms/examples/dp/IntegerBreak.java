package com.chawkalla.algorithms.examples.dp;


/**
 * https://leetcode.com/problems/integer-break/
 *
 */
public class IntegerBreak {

	public int integerBreak(int n) {
		if(n==0)
			return 1;
		if(n==1)
			return 1;
		if(n==2)
			return 1;
		if(n==3)
			return 2;
		int[] a=new int[n+1];//1-d array is enough since repetition is allowed
		a[0]=1;
		a[1]=1;
		a[2]=2;
		a[3]=2;
		
		
		for(int i=3;i<=n;i++){
			int maxProduct=0;
			//check all elements before i(current) to see if it'll be there in final result
			for (int j = i; j >=0; j--) {
				int remainder=i-j;
				//use j 
				int product=j*a[remainder];
				if(product>maxProduct)
					maxProduct=product;
			}
			a[i]=maxProduct;
		}
		
		return a[n];
	}
	public static void main(String[] args) {

		System.out.println(new IntegerBreak().integerBreak(9));
		//assertTrue(new IntegerBreak().integerBreak(10)==36);
	}

}
