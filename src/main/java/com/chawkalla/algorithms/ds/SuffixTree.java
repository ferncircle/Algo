package com.chawkalla.algorithms.ds;

import com.chawkalla.algorithms.bean.SuffixNode;

public class SuffixTree {

	public SuffixNode root;
	
	public SuffixTree() {
		root=new SuffixNode();
	}

	public void build(String s){

		
		for(int i=(s.length()-1);i>=0;i--){
			SuffixNode current=root;
			
			for(int j=i;j<s.length();j++){
				String c=""+s.charAt(j);			
				if(!current.edges.containsKey(c)){
					SuffixNode n=new SuffixNode();
					if(j==(s.length()-1))
						n.isLeaf=true;
					current.edges.put(c, n);
					current=n;
				}else{
					current=current.edges.get(c);
				}
			}
		}		
	
	}
	
	public int getCharactersMatched(String s){
		int i=0;
		int charsMatched=0;
		SuffixNode current=root;
		boolean done=false;
		while(!done){
			
			String c=""+s.charAt(i);					
			
			if(current.edges.containsKey(c)){
				i++;
				current=current.edges.get(c);
			}
			else
				done=true;
			
			if(current.isLeaf){
				charsMatched=i;
			}				
			
			
			if(i>=s.length())
				done=true;
		}
		
		return charsMatched;
	}
}
