/**
 * 
 */
package com.chawkalla.algorithms.examples.array;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * https://www.interviewbit.com/problems/merge-intervals/
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Given intervals [1,3],[6,9] insert and merge [2,5] would result in [1,5],[6,9].

Example 2:

Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would result in [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

Make sure the returned intervals are also sorted.

Solution:
Notice different use cases: a) New interval n, could be completely on left side of current
b) n could be completely on right side
c) n overlaps with current

 *
 */
public class MergeIntervals {

	//[1,2],[3,5],[6,7],[8,10],[12,16] and n=[4,9] -->[1,2],[3,10],[12,16]
	public ArrayList<Interval> insert(ArrayList<Interval> a, Interval n) {
		ArrayList<Interval> res=new ArrayList<Interval>();

		Interval m=null;
		boolean merged=false;
		//[1,3],[6,9] and merge [2,5]
		for(int i=0;i<a.size();i++){
			Interval cur=a.get(i);
			if(cur.end < n.start){// n on right side e.g. [1,2] and n=[4,9]
				res.add(cur);
			}
			else if(cur.start > n.end){ //n on left side e.g. [12,16] and n=[4,9]
				if(m==null)
					m=new Interval(n.start, n.end);
				res.add(m);
				merged=true;
				res.addAll(a.subList(i, a.size())); //add rest of them
				break;

			}else{//n overlaps e..g [2,5] and n=[3,7] Also, [5 ,8] and n=[3, 6]
				if(m==null)
					m=new Interval(n.start, n.end);
				m.start=Math.min(m.start, cur.start);
				m.end=Math.max(m.end, cur.end);
			}

		}
		if(!merged){
			if(m!=null)
				res.add(m);
			else 
				res.add(n);
		}
		

		return res;
	}

	public ArrayList<Interval> insert1(ArrayList<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> res=new ArrayList<Interval>();

		int startIndex=Collections.binarySearch(intervals, new Interval(newInterval.start,0), ((Interval a, Interval b)->a.start-b.start));
		int endIndex=Collections.binarySearch(intervals, new Interval(newInterval.end,0), ((Interval a, Interval b)->a.start-b.start));

		res.addAll(intervals.subList(0, startIndex));

		Interval merged=new Interval(intervals.get(startIndex).start, intervals.get(endIndex).end);
		res.add(merged);

		res.addAll(intervals.subList(endIndex, intervals.size()));
		return res;
	}

	private int getIndex(ArrayList<Interval> a, int val){
		int ind=-1;
		int s=0;
		int e=a.size()-1;

		while(s<=e){
			int mid=(s+e)/2;
			if(a.get(mid).start==val){
				ind=mid;
				break;
			}
			else if(a.get(mid).start<val){
				s=mid+1;
				ind=s;
			}else{
				e=mid-1;
				ind=e;
			}
		}

		return ind;
	}


	public static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
		@Override
		public boolean equals(Object obj) {
			Interval cur=(Interval) obj;
			return this.start==cur.start && this.end==cur.end;
		}


	}

	public static void main(String[] args) {
		//[1,2],[3,5],[6,7],[8,10],[12,16]


		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
				new Interval(1, 2),
				new Interval(3, 5),
				new Interval(6, 7),
				new Interval(8, 10),
				new Interval(12, 16)
		})), new Interval(4,9)),
				is(Arrays.asList(new Interval[]{
						new Interval(1, 2),
						new Interval(3, 10),
						new Interval(12, 16)
				})));

		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
				new Interval(3, 5),
				new Interval(6, 7),
				new Interval(12, 16)
		})), new Interval(1, 2)),
				is(Arrays.asList(new Interval[]{
						new Interval(1, 2),
						new Interval(3, 5),
						new Interval(6, 7),
						new Interval(12, 16)
				})));
		
		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
				new Interval(3, 5),
				new Interval(12, 16)
		})), new Interval(6, 7)),
				is(Arrays.asList(new Interval[]{
						new Interval(3, 5),
						new Interval(6, 7),
						new Interval(12, 16)
				})));
		
		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
				new Interval(3, 5),
				new Interval(12, 16)
		})), new Interval(14, 18)),
				is(Arrays.asList(new Interval[]{
						new Interval(3, 5),
						new Interval(12, 18)
				})));
		
		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
				new Interval(1, 2),
				new Interval(3, 6)
		})), new Interval(8, 10)),
				is(Arrays.asList(new Interval[]{
						new Interval(1, 2),
						new Interval(3, 6),
						new Interval(8, 10)
				})));

		assertThat(new MergeIntervals().insert(new ArrayList<Interval>(Arrays.asList(new Interval[]{
		})), new Interval(8, 10)),
				is(Arrays.asList(new Interval[]{
						new Interval(8, 10)
				})));
		System.out.println("all cases passed");

	}

}
