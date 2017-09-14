/**
 * 
 */
package com.indeed.challenges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class SquaresInRange {

	static int[] getMinimumUniqueSum(String[] a) {
        int[] result=new int[a.length];        
        
        for(int i=0;i<a.length;i++){
            String[] nums=a[i].split("[ ]+");
            long start=Long.parseLong(nums[0]);
            long end=Long.parseLong(nums[1]);
            result[i]=(int)Math.sqrt(end)-(int)Math.sqrt(start);  
            
            //check if start is proper square
            int startSqRoot=(int)Math.sqrt(start);
            if(startSqRoot*startSqRoot==start)
            	result[i]++;
        }
        
        return result;
    }
	
	
	public static void main(String[] args) {

		assertThat(getMinimumUniqueSum(new String[]{"1 36"}), is(new int[]{6}));
		assertThat(getMinimumUniqueSum(new String[]{"2 27"}), is(new int[]{4}));
		assertThat(getMinimumUniqueSum(new String[]{"2 37"}), is(new int[]{5}));

		assertThat(getMinimumUniqueSum(new String[]{"2 35"}), is(new int[]{4}));
		assertThat(getMinimumUniqueSum(new String[]{"2 36"}), is(new int[]{5}));
		
		System.out.println("all cases passed");

	}

}
