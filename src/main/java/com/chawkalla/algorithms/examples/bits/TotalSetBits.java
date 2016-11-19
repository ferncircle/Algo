package com.chawkalla.algorithms.examples.bits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://stackoverflow.com/questions/9812742/finding-the-total-number-of-set-bits-from-1-to-n
 * 
 * Get previous number that is power of 2
 * int x=log(n)
 * int a=2^x
 * F(n)= F(a-1)+F(n-a)+(n-a+1)
 * 
 * Now we now if number a is power of 2, then (constant operation)
 * F(a-1)=x*2^(x-1) e.g. * 
0    0 000
1    0 001
2    0 010
3    0 011
4    0 100
5    0 101
6    0 110
7    0 111
 */
public class TotalSetBits {

	public long countSetBitsUptoN(int n){
		if(n<1)
			return 0;
		int[] initialSolution=new int[]{0,1,2,4,5,7,9,12,13};
		if(n<9)
			return initialSolution[n];
		
		//get closest number that is power of 2 e.g. for n=12, a=8
		int x=(int)(Math.log(n)/Math.log(2)); //log2(n)
		
		int a=(int)Math.pow(2, x);
		
		long totalOnesUptoAMinusOne=x*((int)Math.pow(2, x-1));
		int countOfNumbersAToN=(n-(a-1));
		long totalOnesAToN=countSetBitsUptoN(n-a);  //recursive
		
		
		return totalOnesUptoAMinusOne+ totalOnesAToN+ countOfNumbersAToN ;
		
	}
	
	public static int totalBits(int n){
		int total=0;
		for (int i = 0; i <=n; i++) {
			total+=Bits.countOnes(i);
		}
		return total;
	}
	public static void main(String[] args) {
		
		System.out.println(new TotalSetBits().countSetBitsUptoN(Integer.MAX_VALUE));
		assertThat(new TotalSetBits().countSetBitsUptoN(12), is(new Long(totalBits(12))));
		assertThat(new TotalSetBits().countSetBitsUptoN(8), is(new Long(totalBits(8))));
		
		assertThat(new TotalSetBits().countSetBitsUptoN(17), is(new Long(totalBits(17))));
		
		System.out.println("all test cases passed");

	}

}
