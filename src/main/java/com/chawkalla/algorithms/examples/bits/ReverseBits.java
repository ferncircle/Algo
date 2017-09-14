/**
 * 
 */
package com.chawkalla.algorithms.examples.bits;

/**
 * @author SFargose
 *
 */
public class ReverseBits {

	public long reverse(long a) {
	    long result=0;
	    int MAX_BITS=32;
	    for(long i=0;i<MAX_BITS;i++){
	    	result |= a & 1;
	        a >>>= 1;   // CATCH: must do unsigned shift
	        if (i < 31) // CATCH: for last digit, don't shift!
	            result <<= 1;
	    }
	    return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
