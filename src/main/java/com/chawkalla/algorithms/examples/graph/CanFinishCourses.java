package com.chawkalla.algorithms.examples.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/course-schedule-ii/
here are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 *
 */
public class CanFinishCourses {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if(numCourses<2)
			return true;

		HashMap<Integer, List<Integer>> deps=createAdjList(prerequisites);	

		Stack<Integer> st=new Stack<Integer>();	
		HashSet<Integer> visiting=new HashSet<Integer>();
		HashSet<Integer> visited=new HashSet<Integer>();


		for(int i=0;i<numCourses;i++){
			if(!visit(i, deps, visited, visiting, st))
				return false;
		}

		return true;
	}

	public static HashMap<Integer, List<Integer>> createAdjList(int[][] prerequisites){
		HashMap<Integer, List<Integer>> deps=new HashMap<Integer, List<Integer>>();
		if(prerequisites==null || prerequisites.length==0)
			return deps;
		for(int[] a:prerequisites){
			int key=a[1];
			int value=a[0];
			deps.compute(key, (k,v)->v==null?new ArrayList<Integer>():v);
			deps.get(key).add(value);				
		}
		return deps;
	}

	public static boolean visit(int node, HashMap<Integer, List<Integer>> deps, 
			HashSet<Integer> visited, HashSet<Integer> visiting, Stack<Integer> st){
		if(visiting.contains(node))
			return false; //cycle
		visiting.add(node);
		if(!visited.contains(node)){
			List<Integer> dep=deps.get(node);
			if(dep!=null){
				for(int i:dep){
					if(!visit(i, deps, visited, visiting, st))
						return false;
				}
			}

			st.add(node);
			visited.add(node);

		}
		visiting.remove(node);
		return true;
	}


	public static void main(String[] args) {

		assertThat(new CanFinishCourses().canFinish(2, new int[][]{{1,0}}), is(true));
		assertThat(new CanFinishCourses().canFinish(2, new int[][]{{1,0}, {0,1}}), is(false));

		System.out.println("All test cases passed");
	}

}
