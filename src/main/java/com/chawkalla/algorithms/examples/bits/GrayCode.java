package com.chawkalla.algorithms.examples.bits;

public class GrayCode {

	public static void main(String[] args) {
		grayCode(3);

	}
	
	public static void grayCode(int number) {
        double total = Math.pow(2, number)-1;
        for(int i = 0; i <= total; i++){
            System.out.println(Integer.toBinaryString(toGrayCode(i)));
        }
    }

    public static int toGrayCode(int number){
    	int originalNumber=number;
    	int rightShiftOneNumber=number >>> 1;
    	int rightShiftNumberExOrNumber=(number >>> 1) ^ number;
    	
    	System.out.println("original number:"+Integer.toBinaryString(originalNumber)+
    			", shift >>> 1: "+Integer.toBinaryString(rightShiftOneNumber)+
    			", ex-or with number "+Integer.toBinaryString(rightShiftNumberExOrNumber));
    	
        return rightShiftOneNumber ^ originalNumber;
    }

}
