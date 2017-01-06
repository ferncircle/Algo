package com.chawkalla.algorithms;

public class Test {

	public static void main(String[] args) {
		
		int index=7;
		
		System.out.println("index="+Integer.toBinaryString(index));
		System.out.println("-index="+Integer.toBinaryString(-index));
		System.out.println("index & -index="+Integer.toBinaryString(index & -index));
		System.out.println("index - (index & -index)="+Integer.toBinaryString(index - (index & -index)));
		System.out.println("parent="+(index - (index & -index)));
		
		
	}

}
