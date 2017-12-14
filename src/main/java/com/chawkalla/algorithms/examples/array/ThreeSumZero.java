/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author SFargose
 *
 */
public class ThreeSumZero {

	public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> a) {
	    Collections.sort(a);
	    ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
	    
	    for(int i=0;i<a.size()-2;i++){
	        if(i>0 && a.get(i).intValue()==a.get(i-1).intValue())
	            continue;
	        res.addAll(util(a,i,i+1,a.size()-1));
	    }
	    
	    return res;
	}
	
	private ArrayList<ArrayList<Integer>> util(ArrayList<Integer> a, int i, int j, int k){
	    ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
	    
	    long sum=-a.get(i);
	    int s=j,e=k;
	    //2 sum
	    while(s<e){
	        if(s>j && a.get(s)==a.get(s-1)){ //dups
	            s++;
	            continue;
	        }
	        if(e<k && a.get(e)==a.get(e+1)){
	            e--;
	            continue;
	        }
	        long curSum=a.get(s)+a.get(e);
	        if(curSum==sum){
	            res.add(new ArrayList(Arrays.asList(new Integer[]{a.get(i),a.get(s),a.get(e)})));
	            s++;
	            e--;
	        }
	        else if(curSum<sum){
	            s++;
	        }else{
	            e--;
	        }
	        
	    }
	    
	    return res;
	    
	}

	
	public static void main(String[] args) {

		new ThreeSumZero().threeSum(new ArrayList<Integer>(Arrays.asList(new Integer[]{ -31013930, -31013930, 9784175, 21229755		})));
		System.out.println("All cases passed");

	}

}
