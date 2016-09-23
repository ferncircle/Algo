package com.chawkalla.algorithms.external;

import java.util.Arrays;

public class Rearrange {

	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4, 5, 6, 7, 100, 123};
		System.out.println(Arrays.toString(arr));;
		rearrange(arr, arr.length);
		System.out.println(Arrays.toString(arr));;

	}
	
	public static void rearrange(int arr[], int n)
	{
	    // Traverse through all numbers
	    for (int i=0; i<n; i++)
	    {
	        int temp = arr[i];
	 
	        // If number is negative then we have already
	        // processed it. Else process all numbers which
	        // are to be replaced by each other in cyclic way
	        while (temp > 0)
	        {
	            // Find the index where arr[i] should go
	            int j = (i < n/2)? 2*i + 1 : 2*(n-1-i);
	 
	            // If arr[i] is already at its correct
	            // position, mark it as negative
	            if (i == j)
	            {
	                arr[i] = -temp;
	                break;
	            }
	 
	            // Swap the number 'temp' with the current number
	            // at its target position
	            //swap(temp, arr[j]);
	            int a=temp; temp=arr[j];arr[j]=a;
	 
	            // Mark the number as processed
	            arr[j] = -arr[j];
	 
	            // Next process the previous number at target position
	            i = j;
	        }
	    }
	 
	    // Change the number to original value
	    for (int i=0; i<n; i++)
	        arr[i] = -arr[i];
	}
	
	

}
