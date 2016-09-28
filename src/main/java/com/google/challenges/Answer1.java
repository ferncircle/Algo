package com.google.challenges;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Answer1 {
	
	public static int[] answer(int[] data, int n){
		int[] a=null;
		
		if(data==null || data.length==0)
			return data;
		
		//create a map of occurrences
		Map<Integer, Integer> f=new HashMap<Integer, Integer>();
		for(int i=0;i<data.length;i++){
			if(f.containsKey(data[i])){
				int newFrequency=f.get(data[i])+1;
				f.put(data[i], newFrequency);
			}else
				f.put(data[i], 1);
				
		}
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<data.length;i++){
			if(f.get(data[i])<=n)
				list.add(data[i]);
		}
		
		a=new int[list.size()];
		for(int i=0;i<a.length;i++)
			a[i]=list.get(i).intValue();
		
		
		return a;
	}
	
	public static void main(String[] args) {
		
		assertArrayEquals(answer(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5}, 1), 
				new int[]{1,4});
		
		assertArrayEquals(answer(new int[]{1, 2, 3}, 0), 
				new int[]{});
		
		assertArrayEquals(answer(new int[]{1, 2, 3}, 6), 
				new int[]{1,2,3});
		
		
		System.out.println("all tests passed");

	}

}
