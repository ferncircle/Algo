package com.chawkalla.algorithms.examples.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MathUtil {
	
	public static int lcm(int a, int b){
		return (a*b)/gcd(a,b);
	}
	
	public static int lcm(int[] a){

		int t=0;
		t=lcm(a[0], a[1]);
		
		for (int i = 2; i < a.length; i++) {
			t=lcm(t,a[i]);
		}
		
		return t;
	}
	
	public static int gcd(int a, int b){
		if(b==0) return a;
		return gcd(b, a%b);
	}
	public static void main(String[] args) {
		
		assertThat(MathUtil.gcd(6, 8), is(2));
		assertThat(MathUtil.gcd(9, 36), is(9));		
		assertThat(MathUtil.gcd(7, 8), is(1));
		
		assertThat(MathUtil.lcm(6, 8), is(24));
		assertThat(MathUtil.lcm(9, 36), is(36));		
		assertThat(MathUtil.lcm(7, 8), is(56));
		
		assertThat(MathUtil.lcm(new int[]{100, 23, 98}), is(112700));
		
		
		System.out.println("All test cases passed");

	}

}
