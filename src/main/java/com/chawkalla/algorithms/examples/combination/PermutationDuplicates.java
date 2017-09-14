package com.chawkalla.algorithms.examples.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class PermutationDuplicates {
	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
	    
		Collections.sort(a);
	    ArrayList<ArrayList<Integer>> res=new ArrayList<ArrayList<Integer>>();
	    
	    util(a, new boolean[a.size()], new LinkedList<Integer>(), res);
	    
	    return res;
	    
	}
	
	public void util(ArrayList<Integer> a, boolean[] used, LinkedList<Integer> buf, 
	                ArrayList<ArrayList<Integer>> res){
	    if(a.size()==buf.size()){
	        res.add(new ArrayList<Integer>(buf));
	        return;
	    }
	    
	    for(int i=0;i<a.size();i++){
	        int curNum=a.get(i);
	        if(i>0 && curNum==a.get(i-1) && !used[i-1])
	            continue;
	        if(!used[i]){
	            used[i]=true;
	            buf.add(a.get(i));
	            
	            util(a, used, buf,res);
	            
	            buf.removeLast();
	            used[i]=false;
	        }
	    }
	       
	}
	
	public static void main(String[] args){
		
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i:new int[]{1,1,2})
			a.add(i);
		for(ArrayList<Integer> o: new PermutationDuplicates().permute(a)){
			System.out.println(o);
			
		}
	}
}
