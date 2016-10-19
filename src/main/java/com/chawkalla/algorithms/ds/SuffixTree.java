package com.chawkalla.algorithms.ds;

import com.chawkalla.algorithms.bean.SuffixNode;

public class SuffixTree {

	public SuffixNode root;
	
	public SuffixTree() {
		root=new SuffixNode();
	}

	public void build(String s){

		char[] a=s.toCharArray();
		
		for(int i=(a.length-1);i>=0;i--){
			SuffixNode current=root;
			
			for(int j=i;j<a.length;j++){
				String c=""+a[j];			
				if(!current.edges.containsKey(c)){
					SuffixNode n=new SuffixNode();
					if(j==(a.length-1))
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
		while(true){
			if(current.isLeaf)
				charsMatched=i;
			
			if(i>=s.length() || current==null)
				break;
			
			String c=""+s.charAt(i);		
			if(current.edges.containsKey(c)){
				i++;
				current=current.edges.get(c);
			}
			else
				break;
		}
		
		return charsMatched;
	}
}
