package com.chawkalla.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(~7+1);
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(0,0);
		int index=7;
		Arrays.sort("".toCharArray());
		String s=new String("".toCharArray());
		System.out.println("index="+Integer.toBinaryString(index));
		System.out.println("-index="+Integer.toBinaryString(-index));
		System.out.println("index & -index="+Integer.toBinaryString(index & -index));
		System.out.println("index - (index & -index)="+Integer.toBinaryString(index - (index & -index)));
		System.out.println("parent="+(index - (index & -index)));
		
		
	}

}
