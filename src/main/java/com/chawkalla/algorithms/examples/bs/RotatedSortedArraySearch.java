/**
 * 
 */
package com.chawkalla.algorithms.examples.bs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author SFargose
 *
 */
public class RotatedSortedArraySearch {

	public int search1(int[] a, int target) {
        //find index of smallest
        int s=0,e=a.length-1;
        while(s<e){
            int m=(s+e)/2;
            if(a[m]>a[e]){
                s=m+1;
            }else{
                e=m;
            }
        }
       	if(s!=0){
       		if(target>=a[s] && target<=a[a.length-1])
       			e=a.length-1;
       		else{
       			e=s-1;
       			s=0;
       		}
       	}else
       		e=a.length-1;
       	
       	while(s<=e){
       		int m=(s+e)/2;
       		if(a[m]==target) return m;
       		else if(a[m]<target) s=m+1;
       		else e=m-1;
       	}
        
        return -1;
    }
	
	public int search(int[] a, int target) {
        
		int s=0, e=a.length-1;
		
		while(s<=e){
			int m=(s+e)/2;
			if(a[m]==target) return m;
			if(a[m]>a[e]){
				if(target>=a[s] && target<a[m]){
					e=m-1;
				}else{
					s=m+1;
				}
					
			}else if(a[m]<a[e]){
				if(target>a[m] && target <=a[e])
					s=m+1;
				else
					e=m-1;
			}else{
				e--;
			}
		}
        
        return -1;
    }
	
	public static void main(String[] args) {

		assertThat(new RotatedSortedArraySearch().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5), is(1));
		
		assertThat(new RotatedSortedArraySearch().search(new int[]{1,3}, 3), is(1));

		assertThat(new RotatedSortedArraySearch().search(new int[]{1}, 1), is(0));

		assertThat(new RotatedSortedArraySearch().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5), is(1));

		System.out.println("all cases passed");
	}

}
