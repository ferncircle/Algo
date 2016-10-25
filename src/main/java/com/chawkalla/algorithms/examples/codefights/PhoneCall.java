package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhoneCall {

	int phoneCall(int min1, int min2_10, int min11, int s) {
		int minutes=0;
		if(min1<=s){
			s=s-min1;
			minutes=1;       

			while(minutes<10 && min2_10<=s){
				minutes++;
				s=s-min2_10;

			}

			if(min11<=s && minutes==10){
				while(min11<=s){
					minutes++;
					s=s-min11;
				}           

			}


		}        

		return minutes;

	}

	public static void main(String[] args) {
		
		assertThat(new PhoneCall().phoneCall(3, 1, 2, 20), is(14));
		System.out.println("All test cases passed");
	}

}
