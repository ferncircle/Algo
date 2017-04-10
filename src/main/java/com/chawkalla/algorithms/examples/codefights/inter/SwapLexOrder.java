/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * https://codefights.com/interview/vG6SManzGDZsoqsh7
 * 
 * Given a string str and array of pairs that indicates which indices in the string can be swapped, return the 
 * lexicographically largest string that results from doing the allowed swaps. You can swap indices any number of times.

Example

For str = "abdc" and pairs = [[1, 4], [3, 4]], the output should be
swapLexOrder(str, pairs) = "dbca".

By swapping the given indices, you get the strings: "cdba", "cbad", "dbac", "dbca". The lexicographically largest string 
in this list is "dbca".

Solution:
1) Create connected components using graph dfs (first construct graph using edges)
2) Sort each connected components
 *
 */
public class SwapLexOrder {

	String swapLexOrder(String str, int[][] pairs) {
		if(str==null || str.length()<=1 || pairs==null || pairs.length==0)
			return str;


		StringBuffer sol=new StringBuffer(str);

		Collection<ArrayList<Integer>> comps=getComponentsDFS(str, pairs);
		for (ArrayList<Integer> component:comps) {
			if(component==null || component.size()==0)
				continue;
			ArrayList<Integer> sortedComponent=new ArrayList<Integer>(component);
			Collections.sort(sortedComponent);
			Collections.sort(component, (a,b)->{
				if(sol.charAt(a)<sol.charAt(b)){					
					return 1;
				}
				else if(sol.charAt(a)>sol.charAt(b)){
					return -1;
				}				
				return 0;
			});

			for (int i = 0; i < sortedComponent.size(); i++) {
				sol.setCharAt(sortedComponent.get(i), str.charAt(component.get(i)));				
			}
		}
		str=sol.toString();

		return str;
	}

	private Collection<ArrayList<Integer>> getComponentsDFS(String str, int[][] pairs){
		Collection<ArrayList<Integer>> comps=new ArrayList<ArrayList<Integer>>();

		boolean[] visited=new boolean[str.length()];
		HashMap<Integer, ArrayList<Integer>> graph=new HashMap<Integer, ArrayList<Integer>>();

		for (int i = 0; i < pairs.length; i++) {
			int key=pairs[i][0]-1;
			int value=pairs[i][1]-1;
			graph.compute(key, (k,v)->v==null?new ArrayList<Integer>():v);
			graph.get(key).add(value);
			key=pairs[i][1]-1;
			value=pairs[i][0]-1;
			graph.compute(key, (k,v)->v==null?new ArrayList<Integer>():v);
			graph.get(key).add(value);
		}

		for (int i = 0; i < str.length(); i++) {
			if(!visited[i]){
				ArrayList<Integer> component=new ArrayList<Integer>();
				comps.add(component);
				dfsUtil(graph, i, visited, component);
			}
		}
		return comps;
	}

	private void dfsUtil(HashMap<Integer, ArrayList<Integer>> graph, int cur, boolean[] visited, ArrayList<Integer> component){
		if(visited[cur])
			return;
		visited[cur]=true;

		component.add(cur);
		if(graph.get(cur)!=null)
			for (int child:graph.get(cur)) {
				dfsUtil(graph, child, visited, component);
			}
	}

	@SuppressWarnings("unused")
	private Collection<ArrayList<Integer>> getComps(String str, int[][] pairs){
		HashMap<String, ArrayList<Integer>> components=new HashMap<String, ArrayList<Integer>>();

		String[] mapping =new String[str.length()]; //contains char index to set mapping

		int lastSetKey=0;

		for (int i = 0; i < pairs.length; i++) {
			int x=pairs[i][0]-1;
			int y=pairs[i][1]-1;
			if(x<0 || x>=str.length() || y<0 || y>=str.length())
				continue;
			//both not in any component
			if(mapping[x]==null && mapping[y]==null){
				ArrayList<Integer> newComponent=new ArrayList<Integer>();
				newComponent.add(x);
				newComponent.add(y);
				lastSetKey++;
				components.put(""+lastSetKey, newComponent);
				mapping[x]=""+lastSetKey;
				mapping[y]=""+lastSetKey;
			}
			else if(mapping[x]==null && mapping[y]!=null){
				ArrayList<Integer> yComponent=components.get(mapping[y]);
				yComponent.add(x);
				mapping[x]=mapping[y];
			}
			else if(mapping[x]!=null && mapping[y]==null){
				ArrayList<Integer> xComponent=components.get(mapping[x]);
				xComponent.add(y);
				mapping[y]=mapping[x];
			}
			else if(mapping[x]!=null && mapping[y]!=null){


				//merge into x component
				ArrayList<Integer> xComponent=components.get(mapping[x]);
				ArrayList<Integer> yComponent=components.get(mapping[y]);

				String mergeKey="";
				ArrayList<Integer> mergedComponent=null;
				ArrayList<Integer> deleteComponent=null;
				String deleteKey="";
				if(xComponent.size()>yComponent.size()){
					mergeKey=mapping[x];
					deleteKey=mapping[y];
					mergedComponent=xComponent;
					deleteComponent=yComponent;
				}else{
					mergeKey=mapping[y];
					deleteKey=mapping[x];
					mergedComponent=yComponent;
					deleteComponent=xComponent;
				}

				for (int j = 0; j < deleteComponent.size(); j++) {
					Integer c=deleteComponent.get(j);
					if(c!=null && c>=0){
						mergedComponent.add(c);
						mapping[c]=mergeKey;
					}	
				}

				if(components.containsKey(deleteKey))
					components.remove(deleteKey);

			}
		}

		return components.values();

	}

	public static void main(String[] args) {


		assertThat(new SwapLexOrder().swapLexOrder("acxrabdz", new int[][]{
			{1,3}, 
			{6,8}, 
			{3,8}, 
			{2,7}
		}), is("zdxrabca"));
		assertThat(new SwapLexOrder().swapLexOrder("abdc", new int[][]{
		}), is("abdc"));
		assertThat(new SwapLexOrder().swapLexOrder("", new int[][]{
		}), is(""));
		assertThat(new SwapLexOrder().swapLexOrder("ad", null), is("ad"));
		assertThat(new SwapLexOrder().swapLexOrder("abdc", new int[][]{
			{1,4}, 
			{3,4}
		}), is("dbca"));
		assertThat(new SwapLexOrder().swapLexOrder("abcdefgh", new int[][]{
			{1,4}, 
			{7,8}
		}), is("dbcaefhg"));

		assertThat(new SwapLexOrder().swapLexOrder("fixmfbhyutghwbyezkveyameoamqoi", new int[][]{
			{8,5}, 
			{10,8}, 
			{4,18}, 
			{20,12}, 
			{5,2}, 
			{17,2}, 
			{13,25}, 
			{29,12}, 
			{22,2}, 
			{17,11}
		}), is("fzxmybhtuigowbyefkvhyameoamqei"));

		System.out.println("All test cases passed");
	}

}
