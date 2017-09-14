/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author SFargose
 *
 */
public class MaximumGap {

	public int maximumGap(final List<Integer> a) {
		if(a.size()<=1)
			return 0;
	    int[] hr =new int[a.size()];
	    hr[hr.length-1]=-1;
	    int higher=hr.length-1;
	    for(int i=a.size()-2;i>=0;i--){
	        hr[i]=higher;
	        if(a.get(i)>a.get(higher))
	            higher=i;
	    }
	    
	    int start=0, end=0;
	    int max=-1;
	    while(start<hr.length && hr[end]!=-1){
	        if(a.get(start)<=a.get(hr[end])){
	            end=hr[end];
	        }else{
	            start++;
	        }
	        
	        max=Math.max(max,end-start);
	        
	    }
	    
	    return max;
	}
	
	public static void main(String[] args) {
		
		assertThat(new MaximumGap().maximumGap(Arrays.asList(new Integer[]{3,5,4,2})), is((2)));
		
		System.out.println("all cases passed");

	}

}
