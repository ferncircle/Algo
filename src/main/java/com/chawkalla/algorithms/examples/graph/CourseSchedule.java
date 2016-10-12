package com.chawkalla.algorithms.examples.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;



/**
 *
 * https://leetcode.com/problems/course-schedule-ii/
 * Topological sort
 */
public class CourseSchedule {
	
	Stack<Integer> st=new Stack<Integer>();
	Set<Integer> visiting=new HashSet<Integer>();
	Set<Integer> visited=new HashSet<Integer>();
	HashMap<Integer, List<Integer>> deps=new HashMap<Integer, List<Integer>>();
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if(numCourses<=0)
			return null;
		int[] ret=new int[numCourses];
		
		//first crete dependency graph (adjacency list)
		createAdjList(prerequisites);		
			
		try {
			for(int i=0;i<numCourses;i++){
				visit(i);
			}
		} catch (Exception e) {
			return new int[0];
		}
		
		int i=0;
		while(!st.empty())
			ret[i++]=st.pop();
		
		return ret;
	}
	
	private void createAdjList(int[][] prerequisites){
		if(prerequisites==null || prerequisites.length==0)
			return;
		for(int[] a:prerequisites){
			int key=a[1];
			int value=a[0];
			if(deps.containsKey(key))
				deps.get(key).add(value);
			else{
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(value);
				deps.put(key, list);
			}
				
		}
	}
	
	public void visit(int node){
		if(visiting.contains(node))
			throw new RuntimeException(); //cycle
		visiting.add(node);
		if(!visited.contains(node)){
			List<Integer> dep=deps.get(node);
			if(dep!=null){
				for(int i:dep){
					visit(i);
				}
			}
			
			st.add(node);
			visited.add(node);
			
		}
		visiting.remove(node);
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new CourseSchedule().findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
		System.out.println(Arrays.toString(new CourseSchedule().findOrder(1, new int[][]{})));
		System.out.println(Arrays.toString(new CourseSchedule().findOrder(2, new int[][]{{1,0},{0,1}})));
		System.out.println(Arrays.toString(new CourseSchedule().findOrder(4, new int[][]{{3,0},{0,1}})));
		

	}

}
