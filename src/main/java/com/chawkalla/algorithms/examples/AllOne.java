package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class AllOne {

	LinkedList<Integer> minStack;
	LinkedList<Integer> maxStack;
	int max;
	int min;
	HashMap<String, Integer> repo;
	HashMap<Integer, HashSet<String>> frequency;
	
	
	/** Initialize your data structure here. */
    public AllOne() {
        max=Integer.MIN_VALUE;
        min=Integer.MAX_VALUE;
        repo=new HashMap<String, Integer>();
    	frequency=new HashMap<Integer, HashSet<String>>();
    	minStack=new LinkedList<Integer>();
    	maxStack=new LinkedList<Integer>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(key==null || key.length()==0)
        	return;
        int newValue;
        int oldValue=0;
    	if(repo.containsKey(key)){
    		oldValue=repo.remove(key);
    		newValue=oldValue+1;
    		
    	}else{
    		newValue=1;  
    		
    	}
    	
		repo.put(key, newValue);
		
		//update frequency
		updateFrequency(key, newValue, oldValue);	
		
    }
    
    public void updateFrequency(String key, int newValue, int oldValue){
    	//remove old frequency
    	if(oldValue>0 && frequency.containsKey(oldValue)){
			frequency.get(oldValue).remove(key);
		}
    	if(newValue>=1){

        	//add new frequency
        	if(frequency.containsKey(newValue)){
    			frequency.get(newValue).add(key);
    		}else{
    			HashSet<String> keys=new HashSet<String>();
    			keys.add(key);
    			frequency.put(newValue, keys);
    		}    
    	}    		
    	
    	if(newValue>oldValue){//inc
    		if(newValue>max)
    			max=newValue;
    		if(newValue<min)
    			min=newValue;
        	if(oldValue==min){
        		if(frequency.get(min)==null || frequency.get(min).size()==0)
        			min=newValue;
        	}        		
    	}else{//dec
    		if(newValue>0 && newValue<min)
    			min=newValue;
    		if(oldValue==max){
        		if(frequency.get(max)==null || frequency.get(max).size()==0)
        			max=newValue;
        	} 
    		
    	}
    	//initial calls
    	if(min==Integer.MAX_VALUE)
    		min=max;
    	if(max==Integer.MIN_VALUE)
    		max=min;
		
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(key==null || key.length()==0)
        	return;
        int newValue;
        int oldValue=0;
    	if(repo.containsKey(key)){
    		oldValue=repo.remove(key);
    		newValue=oldValue-1;
    		
    		if(newValue>0)
    			repo.put(key, newValue);
    		
    		//update frequency
    		updateFrequency(key, newValue, oldValue);	
    	}
    	
		
		
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
    	String maxKey=null;
        if(max>0){
        	if(frequency.get(max)!=null && frequency.get(max).size()>0){
        		maxKey= frequency.get(max).iterator().next();
        	}
        }	
       return maxKey;
        	
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
    	String minKey=null;
        if(min>0){
        	if(frequency.get(min)!=null && frequency.get(min).size()>0){
        		minKey= frequency.get(min).iterator().next();
        	}
        }	
       return minKey;
    }
    
	public static void main(String[] args) {
		AllOne test=new AllOne();		
		ArrayList<String> result=new ArrayList<String>();		
		/*test.inc("a");
		test.inc("b");result.add(test.getMaxKey());		
		test.inc("a");result.add(test.getMaxKey());result.add(test.getMinKey());
		test.dec("b");
		test.dec("a");result.add(test.getMaxKey());result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("a","a","b","a", "a")));
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("a","a")));		
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.dec("3");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList(null,null)));	*/	

		/*test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("a");
		test.inc("b");
		test.inc("a");	result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.dec("a");
		test.dec("a");
		test.dec("a");		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("a","b","b","b")));		*/
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("a");
		test.inc("b");
		test.inc("b");
		test.inc("b");
		test.inc("c");
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.inc("a");	
		test.inc("c");	
		test.dec("a");
		test.dec("a");
		test.dec("a");	
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("b","c","b","c")));		
		
		
		System.out.println("All test cases passed");
	}

}
