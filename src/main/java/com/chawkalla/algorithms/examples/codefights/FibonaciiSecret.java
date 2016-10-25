package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FibonaciiSecret {

	String FibonacciSecret(String message) {
		if(message ==null)
			return message;
		message=message.trim();
		if(message.length()<=1)
			return message.toUpperCase();
		message=removeSpaces(message);
		StringBuffer sb=new StringBuffer();
		int a=0;
		int b=1;
		boolean done=false;
		while(!done){
			
			if(a<message.length()){
				sb.append(Character.toUpperCase(message.charAt(a))+"-");
			}
			
			if(b<message.length()){
				sb.append(Character.toUpperCase(message.charAt(b))+"-");
			}
			a=a+b;
			b=a+b;
			
			if(a>message.length()-1)
				done=true;
		}
		
		if(sb.length()>0)
			sb.deleteCharAt(sb.length()-1);
		
		
		return sb.toString();
	}
	
	public String removeSpaces(String str){
		if(str==null)
			return str;
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isWhitespace(str.charAt(i)))
				sb.append(str.charAt(i));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		
		assertThat(new FibonaciiSecret().FibonacciSecret(
				"The Da Vinci Code is a 2003 mystery-detective novel by Dan Brown"), 
				is("T-H-H-E-D-V-C-E-M-T"));
		System.out.println("all test cases passed");
	}
}
