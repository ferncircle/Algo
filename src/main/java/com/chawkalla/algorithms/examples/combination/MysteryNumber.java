package com.chawkalla.algorithms.examples.combination;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MysteryNumber {
	int number=Integer.MIN_VALUE;
	int level=Integer.MIN_VALUE;
	
	public int findNumber(int limit){
		long before=System.currentTimeMillis();
		//findNumberUtil(limit, 1, new HashSet<Integer>(), 0);
		findNumberIterative(limit, 1);
		
		System.out.println("Execution time="+(System.currentTimeMillis()-before));
		return number;
		
	}
	
	private void findNumberUtil(final int limit, int cur, HashSet<Integer> visited, int l){

		if(visited.contains(cur))
			return;
		visited.add(cur);		
		if(cur>limit)
			return;
		
		
		
		if(l > level)
			number=cur;
		
		int a=2*cur;
		if(a>0 && !visited.contains(a))
			findNumberUtil(limit, a, visited, l+1);
		
		int b=(cur-1)/3;
		if(b>0 && b%2!=0 && !visited.contains(b)) 
			findNumberUtil(limit, b, visited, l+1);
	}
	
	private void findNumberIterative(final int limit, int seed){
		
		Queue<Integer> q=new LinkedList<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();
		q.add(seed);
		int cur=0;
		while(!q.isEmpty()){
			cur=q.remove();
			
			if(visited.contains(cur))
				continue;
			visited.add(cur);	
						
			if(cur>limit)
				continue;
			
			int a=2*cur;
			if(a<=limit)
				q.add(a);
			
			int b=(cur-1)/3;
			if(b%2!=0) 
				q.add(b);
		}
		number=cur;
	}
	
	public static void printPath(int n){
		int count=0;
		while(n!=1){
			count++;
			System.out.print(n+"->");
			if(n%2==0)
				n=n/2;
			else
				n=3*n+1;
		}
		System.out.print("1");
		System.out.println();
		System.out.println("Length of path="+count);
	}
	
	
	public static void main(String[] args) {

		MysteryNumber.printPath(new MysteryNumber().findNumber(10000));

	}

}
