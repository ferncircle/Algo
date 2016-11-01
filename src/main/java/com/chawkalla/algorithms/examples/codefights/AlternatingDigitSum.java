package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class AlternatingDigitSum {

	public static int sumOddMinusEvenDigits(int n){
		int sum=0;
		int oddSum=0;
		int evenSum=0;
		int num=n;
		int i=1;
		while(num>0){
			int digit=(int) (num%10);
			if(i%2==0)
				evenSum+=digit;
			else
				oddSum+=digit;
			num=num/10;
			i++;
		}
		
		sum=oddSum-evenSum;
	    
		return sum;
	}
	
	public static int sumOddMinusEvenDigits(String n){
		int sum=0;
		int oddSum=0;
		int evenSum=0;
		int counter=1;
		for (int i = n.length()-1; i >=0; i--) {
			int digit=Integer.parseInt(""+n.charAt(i));
			if(counter%2==0)
				evenSum+=digit;
			else
				oddSum+=digit;
			counter++;
		}
		sum=oddSum-evenSum;
		
		
		return sum;
	}
	
	
	public static void main(String[] args) {

		//assertThat(sumOddMinusEvenDigits(13572), is(-2));

		for (int n = 1; n < 30; n++) {
			BigInteger sum=new BigInteger("1");
			for (int i = 1; i <= n; i++) {
				sum=sum.multiply(new BigInteger(""+i));
				
			}
			
			System.out.println(n+", with sum "+sum+"="+sumOddMinusEvenDigits(""+sum.toString()));
		}
		

	}

}
