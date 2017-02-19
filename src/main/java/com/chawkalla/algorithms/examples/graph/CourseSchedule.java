package com.chawkalla.algorithms.examples.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;



/**
 *
 * https://leetcode.com/problems/course-schedule-ii/
 * Topological sort
 */
public class CourseSchedule {



	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if(numCourses<=0)
			return null;
		int[] ret=new int[numCourses];

		//first crete dependency graph (adjacency list)
		HashMap<Integer, List<Integer>> deps=createAdjList(prerequisites);	

		Stack<Integer> st=new Stack<Integer>();	
		HashSet<Integer> visiting=new HashSet<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();

		try {
			for(int i=0;i<numCourses;i++){
				visit(i, deps, visited, visiting, st);
			}
		} catch (Exception e) {
			return new int[0];
		}

		int i=0;
		while(!st.empty())
			ret[i++]=st.pop();

		return ret;
	}

	public static HashMap<Integer, List<Integer>> createAdjList(int[][] prerequisites){
		HashMap<Integer, List<Integer>> deps=new HashMap<Integer, List<Integer>>();
		if(prerequisites==null || prerequisites.length==0)
			return deps;
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
		return deps;
	}

	public static void visit(int node, HashMap<Integer, List<Integer>> deps, 
			HashSet<Integer> visited, HashSet<Integer> visiting, Stack<Integer> st){
		if(visiting.contains(node))
			throw new RuntimeException(); //cycle
		if(visited.contains(node))
			return;

		visiting.add(node);

		List<Integer> dep=deps.get(node);
		if(dep!=null){
			for(int i:dep){
				visit(i, deps, visited, visiting, st);
			}
		}

		st.add(node);
		visited.add(node);


		visiting.remove(node);
	}

	public static void main(String[] args) {

		assertThat(new CourseSchedule().findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}), is(new int[]{0, 2, 1, 3}));
		assertThat(new CourseSchedule().findOrder(1, new int[][]{}), is(new int[]{0}));
		assertThat(new CourseSchedule().findOrder(2, new int[][]{{1,0},{0,1}}), is(new int[]{}));
		assertThat(new CourseSchedule().findOrder(4, new int[][]{{3,0},{0,1}}), is(new int[]{2,1,0,3}));

		System.out.println("All test cases passed");
	}

}
