/**
 * 
 */
package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class Sqrt {

	public int sqrt(int a) {
	    int[] base=new int[]{0,1,1};
	    if(a<3)
	        return base[a];
	        
	    long start=1;
	    long end=a;
	    long ans=0;
	    while(start<=end){
	        long mid=(start+end)/2;
	        long sq=mid*mid;
	        if(sq==a) 
	            return (int)mid;
	        else if(sq<a){
	        	ans=mid;
	            start=mid+1;
	        }
	        else
	            end=mid-1;
	            
	    }
	    
	    return (int)ans;
	}
	
	
	public static void main(String[] args) {

		assertThat(new Sqrt().sqrt(3), is(1));
		assertThat(new Sqrt().sqrt(2147483647), is(46340));
		System.out.println("all cases passed");

	}

}
