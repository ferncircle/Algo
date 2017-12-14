/**
 * 
 */
package com.chawkalla.algorithms.examples.math;

import java.awt.Point;
import java.util.HashMap;

/**
 * @author SFargose
 *
 */
public class MaxPointsOnSameLine {

	public int maxPoints(Point[] points) {
        if(points.length<2) return points.length;
        int max=Integer.MIN_VALUE;
        
        HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<Integer,HashMap<Integer,Integer>>();
        for(int i=0;i<points.length;i++){
            map.clear();
            int curMax=0,dups=0;
            for(int j=i+1;j<points.length;j++){
                int dy=points[i].y-points[j].y;
                int dx=points[i].x-points[j].x;
                if(dy==0 && dx==0){
                    dups++;
                    continue;
                }
                int gcd=gcd(dx, dy);
                if(gcd!=0){
                    dy=dy/gcd;
                    dx=dx/gcd;
                }
                
                map.compute(dy,(k,v)->v==null?new HashMap<Integer,Integer>():v);
                
                map.get(dy).compute(dx, (k,v)->v==null?1:v+1);
                
                int count=map.get(dy).get(dx);
                
                curMax=Math.max(curMax,count);
                
            }
            max=Math.max(max,curMax+dups+1);
            
        }
        
        return max;
    }
    
    private int gcd(int a, int b){
        if(a==0) return b;
        
        return gcd(b%a, a);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
