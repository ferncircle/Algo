/**
 * 
 */
package com.google.challenges;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Escape Pods
===========

You've blown up the LAMBCHOP doomsday device and broken the bunnies out of Lambda's prison - and now you need to escape from the space station as quickly and as orderly as
 possible! The bunnies have all gathered in various locations throughout the station, and need to make their way towards the seemingly endless amount of escape pods 
 positioned in other parts of the station. You need to get the numerous bunnies through the various rooms to the escape pods. Unfortunately, the corridors between the rooms
  can only fit so many bunnies at a time. What's more, many of the corridors were resized to accommodate the LAMBCHOP, so they vary in how many bunnies can move through 
  them at a time. 

Given the starting room numbers of the groups of bunnies, the room numbers of the escape pods, and how many bunnies can fit through at a time in each direction of every
 corridor in between, figure out how many bunnies can safely make it to the escape pods at a time at peak.

Write a function answer(entrances, exits, path) that takes an array of integers denoting where the groups of gathered bunnies are, an array of integers denoting where the
 escape pods are located, and an array of an array of integers of the corridors, returning the total number of bunnies that can get through at each time step as an int. 
 The entrances and exits are disjoint and thus will never overlap. The path element path[A][B] = C describes that the corridor going from A to B can fit C bunnies at each
  time step.  There are at most 50 rooms connected by the corridors and at most 2000000 bunnies that will fit at a time.

For example, if you have:
entrances = [0, 1]
exits = [4, 5]
path = [
  [0, 0, 4, 6, 0, 0],  # Room 0: Bunnies
  [0, 0, 5, 2, 0, 0],  # Room 1: Bunnies
  [0, 0, 0, 0, 4, 4],  # Room 2: Intermediate room
  [0, 0, 0, 0, 6, 6],  # Room 3: Intermediate room
  [0, 0, 0, 0, 0, 0],  # Room 4: Escape pods
  [0, 0, 0, 0, 0, 0],  # Room 5: Escape pods
]

Then in each time step, the following might happen:
0 sends 4/4 bunnies to 2 and 6/6 bunnies to 3
1 sends 4/5 bunnies to 2 and 2/2 bunnies to 3
2 sends 4/4 bunnies to 4 and 4/4 bunnies to 5
3 sends 4/6 bunnies to 4 and 4/6 bunnies to 5

So, in total, 16 bunnies could make it to the escape pods at 4 and 5 at each time step.  (Note that in this example, 
room 3 could have sent any variation of 8 bunnies to 4 and 5, such as 2/6 and 6/6, but the final answer remains the same.)

Test cases
==========

Inputs:
    (int list) entrances = [0]
    (int list) exits = [3]
    (int) path = [[0, 7, 0, 0], [0, 0, 6, 0], [0, 0, 0, 8], [9, 0, 0, 0]]
Output:
    (int) 6

Inputs:
    (int list) entrances = [0, 1]
    (int list) exits = [4, 5]
    (int) path = [[0, 0, 4, 6, 0, 0], [0, 0, 5, 2, 0, 0], [0, 0, 0, 0, 4, 4], [0, 0, 0, 0, 6, 6], [0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0]]
Output:
    (int) 16
 *
 */
public class EscapePods41 {
	
	private static int MAX=Integer.MAX_VALUE;
	public static int answer(int[] entrances, int[] exits, int[][] path){
		int total=0;
		
		int[][] capacity=new int[path.length+2][path[0].length+2];
		for (int i = 0; i < entrances.length; i++) {
			capacity[0][entrances[i]+1]=MAX;
		}
		for (int i = 0; i < exits.length; i++) {
			capacity[exits[i]+1][capacity.length-1]=MAX;
		}
		
		for (int i = 1; i < capacity.length-1; i++) {
			for (int j = 1; j < capacity[0].length-1; j++) {
				capacity[i][j]=path[i-1][j-1];
			}
		}
		
		total=maxFlow(capacity, 0, capacity.length-1);
		
		return total;
	}
	
	public static int maxFlow(int capacity[][], int source, int sink){

        //declare and initialize residual capacity as total avaiable capacity initially.
        int residualCapacity[][] = new int[capacity.length][capacity[0].length];
        for (int i = 0; i < capacity.length; i++) {
            for (int j = 0; j < capacity[0].length; j++) {
                residualCapacity[i][j] = capacity[i][j];
            }
        }

        //this is parent map for storing BFS parent
        Map<Integer,Integer> parent = new HashMap<>();

        //max flow we can get in this network
        int maxFlow = 0;

        //see if augmented path can be found from source to sink.
        while(BFS(residualCapacity, parent, source, sink)){
            int flow = Integer.MAX_VALUE;
            //find minimum residual capacity in augmented path
            //also add vertices to augmented path list
            int v = sink;
            while(v != source){
                int u = parent.get(v);
                if (flow > residualCapacity[u][v]) {
                    flow = residualCapacity[u][v];
                }
                v = u;
            }
            maxFlow += flow;

            v = sink;
            while(v != source){
                int u = parent.get(v);
                residualCapacity[u][v] -= flow;
                residualCapacity[v][u] += flow;
                v = u;
            }
        }
        return maxFlow;
    }

    /**
     * Breadth first search to find augmented path, it finds first possible path from source to sink and return that path through parent map
     */
    private static boolean BFS(int[][] residualCapacity, Map<Integer,Integer> parent,
            int source, int sink){
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        boolean foundAugmentedPath = false;
        //see if we can find augmented path from source to sink
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0; v < residualCapacity.length; v++){
                //explore the vertex only if it is not visited and its residual capacity is
                //greater than 0
                if(!visited.contains(v) &&  residualCapacity[u][v] > 0){
                    //add in parent map saying v got explored by u
                    parent.put(v, u);
                    //add v to visited
                    visited.add(v);
                    //add v to queue for BFS
                    queue.add(v);
                    //if sink is found then augmented path is found
                    if ( v == sink) {
                        foundAugmentedPath = true;
                        break;
                    }
                }
            }
        }
        //returns if augmented path is found from source to sink or not
        return foundAugmentedPath;
    }
}
