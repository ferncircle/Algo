package com.chawkalla.algorithms.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.chawkalla.algorithms.bean.Entry;
import com.chawkalla.algorithms.ds.LinkedList;


public class AllOne {

	LinkedList<Integer, HashSet<String>> minMaxList;
	HashMap<Integer, Entry<Integer, HashSet<String>>> frequencyMap;
	HashMap<String, Integer> repo;


	/** Initialize your data structure here. */
	public AllOne() {
		
		repo=new HashMap<String, Integer>();		
		frequencyMap=new HashMap<Integer, Entry<Integer,HashSet<String>>>();
		minMaxList=new LinkedList<Integer, HashSet<String>>();
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

		Entry<Integer, HashSet<String>> oldNode=frequencyMap.get(oldValue);
		Entry<Integer, HashSet<String>> newNode=frequencyMap.get(newValue);


		if(newNode!=null){//if new node exists, then add key to data
			if(newNode.data!=null)
				newNode.data.add(key);
		}else if(newValue>0){ //if new node doesn't exist and greater than 1 then create one
			HashSet<String> keys=new HashSet<String>();
			keys.add(key);
			newNode=new Entry<Integer, HashSet<String>>(keys, null, null);

			if(oldNode!=null){
				//now add new node in chain
				if(newValue<oldValue){//add before
					minMaxList.insertBefore(oldNode, newNode);
				}
				else if(newValue>oldValue) //or after 
					minMaxList.insertAfter(oldNode, newNode);
			}else{ //first node
				minMaxList.push(newNode);
			}

		}
		
		if(oldNode!=null){
			//remove key from old node
			if(oldNode.data!=null)
				oldNode.data.remove(key);
			//remove old node if empty
			if(oldNode.data.size()<=0){
				minMaxList.remove(oldNode);
				frequencyMap.remove(oldValue);
			}
		}
		
		//add newnode in frequency map
		if(newNode!=null && newValue>0){
			frequencyMap.put(newValue, newNode);
		}
		

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
		String maxKey="";
		
		Entry<Integer, HashSet<String>> lastNode=minMaxList.last;
		if(lastNode!=null && lastNode.data!=null){
			maxKey=lastNode.data.iterator().next();
		}
		
		return maxKey;

	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		String minKey="";
		
		Entry<Integer, HashSet<String>> firstNode=minMaxList.head;
		if(firstNode!=null && firstNode.data!=null){
			minKey=firstNode.data.iterator().next();
		}
		return minKey;
	}
	

	public static void main(String[] args) {
		AllOne test=new AllOne();		
		ArrayList<String> result=new ArrayList<String>();
		test.inc("a");
		test.inc("b");
		result.add(test.getMaxKey());		
		test.inc("a");
		result.add(test.getMaxKey());result.add(test.getMinKey());
		test.dec("b");
		test.dec("a");
		result.add(test.getMaxKey());result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("a","a","b","a", "a")));

		test=new AllOne();		
		result=new ArrayList<String>();		
		result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("","")));	
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("a","a")));		

		test=new AllOne();		
		result=new ArrayList<String>();		
		test.dec("3");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("","")));	

		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("a");
		test.inc("b");
		test.inc("a");	
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.dec("a");
		test.dec("a");
		test.dec("a");		
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("a","b","b","b")));		

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
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("a");
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.dec("a");
		test.dec("a");	
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("a","a","","")));	
		
		test=new AllOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("b");
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.dec("a");
		test.dec("a");	
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("a","a","b","b")));		


		System.out.println("All test cases passed");
	}

}
