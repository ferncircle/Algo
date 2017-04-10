/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://codefights.com/interview/MthonrDiNxzE8MChQ
 * 
 * 
 *
 */
public class BitStreanValidation {

	boolean streamValidation(int[] stream) {
		boolean valid=true;
		int i=0;
		while(i<stream.length){
			if((((stream[i]>>7)&1)==1) && (((stream[i]>>6)&1)==0))
				return false;
			int pos=7;
			int n=0;
			for(;((1<<pos)&stream[i])>0;pos--,n++){}
			System.out.println(Integer.toBinaryString(stream[i]) + " with n="+n);
			if(n==0){
				i++;
			}else{
				if((n-1)>(stream.length-1-i))
					return false;
				
				//0 1 2 3 , i=0 , n-1=2, so j -> 1 to 2
				int j=i+1;
				for(;j<i+n;j++){
					System.out.println(Integer.toBinaryString(stream[j]));
					if((((stream[j]>>7)&1)==0) || (((stream[j]>>6)&1)==1))
						return false;
				}
				i=j;
			}
			
			
		}
		
		
		
		return valid;
	}
	
	public static void main(String[] args) {
		assertThat(new BitStreanValidation().streamValidation(new int[]{115, 100, 102, 231, 154, 132, 13, 10}), is(true));

		assertThat(new BitStreanValidation().streamValidation(new int[]{228, 189, 160, 229, 165, 189, 13, 10}), is(true));
		
		
		assertThat(new BitStreanValidation().streamValidation(new int[]{230, 136, 145}), is(true));
		assertThat(new BitStreanValidation().streamValidation(new int[]{230, 136, 145}), is(true));
		
		System.out.println("All cases passed");
	}

}
