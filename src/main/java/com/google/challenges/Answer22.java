package com.google.challenges;

public class Answer22 {

	public static void main(String[] args) {

		System.out.println("salutes="+answer("<--->-><-><-->-"));
		System.out.println("salutes="+answer(">----<"));
		System.out.println("salutes="+answer("<<>><"));

	}
	
	public static int answer(String s){
		int salutes=0;
		
		if(s==null || s.length()==0 || s.trim().equals(""))
			return salutes;
		
		char[] a=s.toCharArray();
		
		int movingRight=0;
		for(char c:a){
			switch (c) {
			case '>':
				movingRight++;
				break;
			case '<':
				salutes=salutes+movingRight*2;
				break;

			default:
				break;
			}
		}
		return salutes;
	}

}
