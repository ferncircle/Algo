/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Given 2-d matrix of distances from houses to mailboxes, WAP that returns total minimal distance from houses 
 * to mailboxes
 *
 *Note that this problem is NP-Hard and even with caching, will stall after 20 houses
 */
public class HouseMailboxes {
	
	HashMap<String, Result> cache=new HashMap<String, Result>();
	int cacheHit=0;
	public int minHouseToMailboxes(int[][] dist){
		boolean[] mailBoxTaken=new boolean[dist.length];
		Result r=findMin(dist, dist.length-1, mailBoxTaken);
		int total=r.minCost;
		System.out.println("CacheHit="+cacheHit);
		
		System.out.println("Total="+total);
		printMapping(r.path);
		return total;
		
	}
	
	private Result findMin(int[][] dist, int curHouse, boolean[] mailBoxTaken){
		if(curHouse<0)
			return new Result(0, "");
		
		
		String cacheKey=createCacheKey(curHouse, mailBoxTaken);
		if(cache.containsKey(cacheKey)){
			cacheHit++;
			return cache.get(cacheKey);
		}
		
		
		int min=Integer.MAX_VALUE;
		String path="";
		for (int i = 0; i < mailBoxTaken.length; i++) {
			if(!mailBoxTaken[i]){
				mailBoxTaken[i]=true;
				Result r=findMin(dist, curHouse-1, mailBoxTaken);
				if(r!=null){
					int t=dist[curHouse][i]+r.minCost;
					if(t<min){
						min=t;
						path=i+","+r.path;
					}
				}
				
				mailBoxTaken[i]=false;
			}
		}
		Result minResult=new Result(min, path);
		cache.put(cacheKey, minResult);
		return minResult;
	}
	
	private String createCacheKey(int curHouse, boolean[] mailBoxTaken){
		StringBuffer cacheKeyBuffer=new StringBuffer(""+curHouse+":");
		ArrayList<Integer> list=new ArrayList<Integer>();
		for (int i = 0; i < mailBoxTaken.length; i++) 
			if(!mailBoxTaken[i])
				list.add(i);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			cacheKeyBuffer.append(list.get(i)+",");
		}
		
		String cacheKey=cacheKeyBuffer.toString();
		
		return cacheKey;
	}
	
	public void printMapping(String path){
		String[] mails=path.split(",");
		for (int i = 0; i < mails.length; i++) {
			System.out.println(i+" --> "+mails[mails.length-1-i]);
		}
	}
	
	public static void main(String[] args) {
		
		assertThat(new HouseMailboxes().minHouseToMailboxes(new int[][]{
			{2,	1,	3},

			{1,	2,	4},

			{1,	4,	6}
		}), is(6));
		
		System.out.println();
		assertThat(new HouseMailboxes().minHouseToMailboxes(new int[][]{
			{2,	1,	3, 	7},

			{1,	2,	4,	3},

			{3,	1,	6,	3},
			{3,	1,	1,	3}
		}), is(6));
		System.out.println();
		
		int n=15;
		Random rand=new Random();
		int[][] dist=new int[n][n];
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				dist[i][j]=rand.nextInt(20);
			}
		}
		long before=0;
		before = System.currentTimeMillis();
		new HouseMailboxes().minHouseToMailboxes(dist);
		System.out.println("Excution Time="+(System.currentTimeMillis()-before));
		
		System.out.println("all test cases passed");

	}
	
	public class Result{
		int minCost;
		String path;
		public Result(int minDist, String path) {
			super();
			this.minCost = minDist;
			this.path = path;
		}
		
	}

}
