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
		long x=(int)(Math.log(n)/Math.log(2)); //log2(n)

		int a=(int)Math.pow(2, x);

		long totalOnesUptoAMinusOne=x*((int)Math.pow(2, x-1)); //constant operation, other way of doing this opeartion
		totalOnesUptoAMinusOne=x*(1<<(x-1)); //which is equal to
		totalOnesUptoAMinusOne=x<<(x-1);

		long countOfNumbersAToN=(n-a);

		//return totalOnesUptoAMinusOne+ countSetBitsUptoN(n-a)+ countOfNumbersAToN +1;
		return (x<<(x-1))+ countSetBitsUptoN(n-a)+ (n-a) +1;
		
	}


	//another way of doing it
	long totalOnes(int k) {
		long s=0 , j=31;
		for(; j-- > 0 ; ){
			// s += (k>>j)<1? 0 : (j<<j-1) +(k-=1<<j) + 1 ;//short and nice way of doing it
			if((k>>j)>0){  //
				s+=j<<j-1;  //total ones from 1 to a (where a is highest number that is power of 2 and less than current number
				k=k-(1<<j);
				s+=k+1;
			}
		}

		return s;
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
		System.out.println(new TotalSetBits().totalOnes(Integer.MAX_VALUE));

		assertThat(new TotalSetBits().countSetBitsUptoN(12), is(new Long(totalBits(12))));
		assertThat(new TotalSetBits().countSetBitsUptoN(8), is(new Long(totalBits(8))));

		assertThat(new TotalSetBits().countSetBitsUptoN((int)Math.pow(2, 18)), is(new Long(totalBits((int)Math.pow(2, 18)))));

		System.out.println("all test cases passed");

	}

}
