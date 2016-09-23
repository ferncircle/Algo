package com.chawkalla.algorithms;

public class Bits {

	public static void main(String[] args) {
		System.out.println(convertSign(10));
		//System.out.println(addNumbers(10, 3));
		 
		int number=64;
		System.out.println("-4="+Integer.toBinaryString(-4) +" and 4="+Integer.toBinaryString(4));
		System.out.println("Set 3rd bit for "+Integer.toBinaryString(number)
				+" " +Integer.toBinaryString((number|1<<3)));
		System.out.println("clear 3rd bit for "+Integer.toBinaryString(number)
				+" " +Integer.toBinaryString(number&(~(1<<3))));
		System.out.println("check power of 2?"+(((number&(number-1))==0)?"true":"false"));
		
		System.out.println("all 1s:"+Integer.toBinaryString(~0));
		System.out.println("all 1s - 111="+Integer.toBinaryString(~0-7));
		System.out.println("set first i bits:"+Integer.toBinaryString(((1<<8)-1)^((1<<5)-1)));
		
		System.out.println("set first i to j bits:"+Integer.toBinaryString((1<<5)-1));
		
		System.out.println(Integer.parseInt("11100",2)+ "   "+Integer.parseInt("11010", 2)+"  "+Integer.parseInt("100011",2));
		countOnes(19);
		System.out.println("binary to integer 1000="+Integer.parseInt("1000", 2));
	}
	
	public static int convertSign(int number){
		System.out.println(Integer.toBinaryString(number)+"   ~number="+Integer.toBinaryString(~number)+ " ~number+1="+Integer.toBinaryString(~number+1));
		int other=~number+1;
		
		return other;
	}
	
	public static void countOnes(int number){
		int count=0;
		int temp=number;
		while(temp!=0){
			temp=temp&temp-1;
			count++;
		}
		
		System.out.println("number of ones for"+Integer.toBinaryString(number)+" ="+count);
	}
	
	public static int addNumbers(int a, int b){
		if(b<=0)
			return a;
		int sum=a^b;
		int carry=a&b;
		System.out.println("sum="+Integer.toBinaryString(sum)+ " carry="+Integer.toBinaryString(carry));
		
		return addNumbers(sum, carry);
	}

}
