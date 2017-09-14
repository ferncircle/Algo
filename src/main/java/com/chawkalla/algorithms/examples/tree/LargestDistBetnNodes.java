/**
 * 
 */
package com.chawkalla.algorithms.examples.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.interviewbit.com/problems/largest-distance-between-nodes-of-a-tree/
 * 
 * Given an arbitrary unweighted rooted tree which consists of N (2 <= N <= 40000) nodes. The goal of the problem is to find largest distance between two nodes in a tree. Distance between two nodes is a number of edges on a path between the nodes (there will be a unique path between any pair of nodes since it is a tree). The nodes will be numbered 0 through N - 1.

The tree is given as an array P, there is an edge between nodes P[i] and i (0 <= i < N). Exactly one of the i’s will have P[i] equal to -1, it will be root node.

 Example:
If given P is [-1, 0, 0, 0, 3], then node 0 is the root and the whole tree looks like this: 
          0
       /  |  \
      1   2   3
               \
                4  
 One of the longest path is 1 -> 0 -> 3 -
 *
 */
public class LargestDistBetnNodes {

	int dist=Integer.MIN_VALUE;
	public int solve(ArrayList<Integer> A) {
		if(A.size()==1) return 0;
		Map<Integer,ArrayList<Integer>> tree=new HashMap<Integer, ArrayList<Integer>>();
		int root=-1;

		for(int i=0;i<A.size();i++){
			int parent=A.get(i);
			if(parent==-1){
				root=i;
				continue;
			}
			tree.compute(parent, (k,v)->v==null?new ArrayList<Integer>():v);
			tree.get(parent).add(i);
		}
		util(tree, root);
		return dist;

	}

	public int util(Map<Integer,ArrayList<Integer>> tree, int cur){
		if(!tree.containsKey(cur)){
			return 1;
		}
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->a-b);

		for(int child:tree.get(cur)){
			pq.add(util(tree, child));
			if(pq.size()>2)
				pq.remove();
		}
		int longest=0;
		int secondLongest=0;
		if(pq.size()>1){
			secondLongest=pq.remove();
			longest=pq.remove();
		}else
			longest=pq.remove();

		dist=Math.max(dist,longest+secondLongest);
		return longest+1;

	}

	public int solve2(ArrayList<Integer> A){   	

		Map<Integer,ArrayList<Integer>> tree=new HashMap<Integer, ArrayList<Integer>>();

		for(int i=0;i<A.size();i++){
			int cur=i;
			int parent=A.get(i);
			if(parent==-1){
				continue;
			}
			tree.compute(parent, (k,v)->v==null?new ArrayList<Integer>():v);
			tree.get(parent).add(i);

			tree.compute(cur, (k,v)->v==null?new ArrayList<Integer>():v);
			tree.get(cur).add(parent);
		}

		//get furthest node
		int furthest=-1;
		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visited=new boolean[A.size()];
		q.add(0);
		while(!q.isEmpty()){
			int cur=q.remove();
			if(visited[cur]) continue;
			visited[cur]=true;
			for(int c:tree.get(cur))
				q.add(c);
			furthest=cur;
		}

		q=new LinkedList<Integer>();
		Integer marker=Integer.MAX_VALUE;
		visited=new boolean[A.size()];
		q.add(furthest);
		q.add(marker);
		int dist=0;
		while(!q.isEmpty()){
			int cur=q.remove();
			if(cur==marker){
				if(!q.isEmpty()){
					dist++;
					q.add(marker);

				}
				continue;
			}

			if(visited[cur]) continue;
			visited[cur]=true;
			for(int c:tree.get(cur))
				q.add(c);        	
		}

		return dist-1;
	}


	public static void main(String[] args) {

		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i:new int[]{-1, 0, 0, 0, 3})
			a.add(i);

		assertThat(new LargestDistBetnNodes().solve(a), is(3));
		assertThat(new LargestDistBetnNodes().solve2(a), is(3));

		a.clear();
		for(int i:new int[]{-1, 0, 1, 1, 2, 0, 5, 0, 3, 0, 0, 2, 3, 1, 12})
			a.add(i);
		assertThat(new LargestDistBetnNodes().solve(a), is(6));
		assertThat(new LargestDistBetnNodes().solve2(a), is(6));


		System.out.println("all cases passed");
	}

}
