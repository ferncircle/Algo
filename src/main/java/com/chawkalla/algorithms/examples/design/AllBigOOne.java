package com.chawkalla.algorithms.examples.design;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.chawkalla.algorithms.bean.Entry;
import com.chawkalla.algorithms.ds.LinkedList;


/**
 * 
 * Solution: Need three data structure:
 * 1) HashMap : {StringKey -> frequency}
 * 2) HashMap : {frequency -> Entry}
 * 3) LinkedList : {Entry}  (maintains min, max list)
 * 
 * Entry: LinkedHashMap of key->value
 */
public class AllBigOOne {

	HashMap<String, Integer> repo; //{Key -> frequency}
	HashMap<Integer, Entry<Integer, LinkedHashMap<String, String>>> frequencyMap; //{frequency -> Entry}
	LinkedList<Integer, LinkedHashMap<String, String>> minMaxList; //LinkedList : {Entry}  (maintains min, max list)
	

	/** Initialize your data structure here. */
	public AllBigOOne() {
		
		repo=new HashMap<String, Integer>();		
		frequencyMap=new HashMap<Integer, Entry<Integer,LinkedHashMap<String, String>>>();
		minMaxList=new LinkedList<Integer, LinkedHashMap<String, String>>();
	}
	
	public void set(String key, String value){
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
		updateValueAndFrequency(key, value, newValue, oldValue);		
		
	}
	
	public String get(String key){
		String value=null;
		if(key==null || key.length()==0)
			return value;
		
		return value;
	}

	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		set(key, "1");
	}

	public void updateValueAndFrequency(String key, String value, int newValue, int oldValue){

		Entry<Integer, LinkedHashMap<String, String>> oldNode=frequencyMap.get(oldValue);
		Entry<Integer, LinkedHashMap<String, String>> newNode=frequencyMap.get(newValue);


		if(newNode!=null){//if new node exists, then add key to data
			if(newNode.data!=null)
				newNode.data.put(key,value);
		}else if(newValue>0){ //if new node doesn't exist and greater than 1 then create one
			LinkedHashMap<String, String> keys=new LinkedHashMap<String, String>();
			keys.put(key,value);
			newNode=new Entry<Integer, LinkedHashMap<String, String>>(keys, null, null);

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
			updateValueAndFrequency(key,"1", newValue, oldValue);	
		}

	}

	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		String maxKey="";
		
		Entry<Integer, LinkedHashMap<String, String>> lastNode=minMaxList.last;
		if(lastNode!=null && lastNode.data!=null){
			maxKey=lastNode.data.keySet().iterator().next();
		}
		
		return maxKey;

	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		String minKey="";
		
		Entry<Integer, LinkedHashMap<String, String>> firstNode=minMaxList.head;
		if(firstNode!=null && firstNode.data!=null){
			minKey=firstNode.data.keySet().iterator().next();
		}
		return minKey;
	}
	

	public static void main(String[] args) {
		AllBigOOne test=new AllBigOOne();		
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

		test=new AllBigOOne();		
		result=new ArrayList<String>();		
		result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("","")));	
		
		test=new AllBigOOne();		
		result=new ArrayList<String>();		
		test.inc("a");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("a","a")));		

		test=new AllBigOOne();		
		result=new ArrayList<String>();		
		test.dec("3");result.add(test.getMaxKey());	result.add(test.getMinKey());		
		assertThat(result, is(Arrays.asList("","")));	

		test=new AllBigOOne();		
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

		test=new AllBigOOne();		
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
		
		test=new AllBigOOne();		
		result=new ArrayList<String>();		
		test.inc("a");
		test.inc("a");
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		test.dec("a");
		test.dec("a");	
		result.add(test.getMaxKey());	result.add(test.getMinKey());
		assertThat(result, is(Arrays.asList("a","a","","")));	
		
		test=new AllBigOOne();		
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
