package com.chawkalla.algorithms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test4 {

	public static void main(String[] args){
		
		//testFinally();
		
		for (int i = 0; i < 10*9*8*7*6*5*4*3*2; i++) {
			System.out.println(i);
		}
	}
	
	public static void regexMatch(){
		// String to be scanned to find the pattern.
	      String line = "This order was places for QT3000! OK?";
	      String pattern = "(.*)(\\d+)(.*)";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	public static StringBuffer testBuffer(StringBuffer sb){
		sb=sb.append("Hello");
		sb=new StringBuffer();
		sb=sb.append("how are u");
		return sb;
	}
	
	public static int testFinally(){
		
		int i=0;
		
		try {
			throw new RuntimeException("error");
		} catch (NumberFormatException e) {
			System.out.println("inside catch");
		} finally{
			System.out.println("inside finally");
		}
		
		return i;
	}
	
	public static void stringReverse(){
		String input = "Reverse Words in String";
		System.out.println("String before reverse is : " +  input);
		
		String[] a = input.split(" ");
		int lengthStr = a.length;
		int tempLength = lengthStr;
		String reversed = "";		
		
		for(int i = 0 ; i<lengthStr/2 ; i++){
			
			String temp = "";		
			
			temp = a[i];
			a[i] = a[tempLength - 1];
			a[tempLength - 1] = temp;
			
			tempLength = tempLength - 1;			
			
			
		}
		
		for(int i = 0; i < a.length; i++){
			
			reversed = reversed + a[i] +" ";
		}
		
		System.out.println("String After reverse is : " + reversed);
		
	}
}
