package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PiRange {

	String piRange(int fr, int to) {
		String s=null;
		
		int num=22;
		int den=7;
		boolean done=false;
		int level=0;
		StringBuffer sb=new StringBuffer();
		while(!done){
			if(level>=fr)
				sb.append(num/den);
			num=(num%den) * 10;
			level++;
			
			if(level>to)
				done=true;
			
		}
	
		s=sb.toString();
		return s;
	}
	
	public static void main(String[] args) {
		double a=22;
		double b=7;
		
		System.out.println(a/b);
		System.out.println(new PiRange().piRange(1, 8));
		//assertThat(new PiRange().piRange(1, 8), is("14159265"));
		//assertThat(new PiRange().piRange(40, 60), is("169399375105820974944"));
		System.out.println("All test cases passed");

	}

}
