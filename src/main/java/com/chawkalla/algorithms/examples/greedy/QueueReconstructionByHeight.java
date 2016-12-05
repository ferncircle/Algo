/**
 * 
 */
package com.chawkalla.algorithms.examples.greedy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

Solution 1: Use insertion sort, to insert each person in sorted queue that meets criteria O(n^2)

BETTER Solution 2: 1) Sort people desc.
2) Start from top
3) directly insert in queue at postion   
 *
 */
public class QueueReconstructionByHeight {

	public int[][] reconstructQueue(int[][] people) {
		if(people.length<2)
			return people;

		Arrays.sort(people, (a,b)->(a[0]==b[0])?a[1]-b[1]:b[0]-a[0]);
		LinkedList<int[]> list=new LinkedList<int[]>();
		
		for (int i = 0; i < people.length; i++) {
			list.add(people[i][1], people[i]);
		}
		/*Arrays.sort(people, (a,b)->(a[1]==b[1])?a[0]-b[0]:a[1]-b[1]);
		LinkedList<int[]> list=new LinkedList<int[]>();
		list.add(people[0]);

		for (int i = 1; i < people.length; i++) {
			int[] cur=people[i];
			int noOfPeopleInFrontSoFar=0;
			int insertIndex=0;
			//find position for this person in existing queue(linkedlist)
			boolean done=false;
			while(!done){
				int[] queuePerson=list.get(insertIndex);
				if(queuePerson[0]>=cur[0])
					noOfPeopleInFrontSoFar++;
				
				insertIndex++;

				if(noOfPeopleInFrontSoFar>cur[1]){
					insertIndex--;
					break;
				}
				if(insertIndex==list.size())
					break;
			}
			if(insertIndex==list.size())
				list.add(cur);
			else
				list.add(insertIndex,cur);
		}*/
		
		people=list.toArray(new int[0][0]);
		return people;
	}

	public static void main(String[] args) {

		assertThat(new QueueReconstructionByHeight().reconstructQueue(new int[][]{
			{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
		}), is(new int[][]{
			{5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}
		}));

		System.out.println("All test cases passed");

	}

}
