package com.chawkalla.algorithms.examples.array;

import java.util.Arrays;

public class ArrayElementsOccurence {

	public static void main(String[] args) {
		int[] A={1,4,2,4,3,3};
		
		int multiplier=100;
		for(int i=0;i<A.length;i++){
			int currentOriginal=A[i]%multiplier;
			
			int targetOriginal=A[currentOriginal]%multiplier;
			//increment occurence of A[i]
			//get first previous occurence and then add 1
			int occurence=A[currentOriginal]/multiplier+1;
			
			int bloatedValue=occurence*multiplier;
			//int anotherValue=(A[i]%multiplier)*multiplier;
			A[currentOriginal]=targetOriginal+bloatedValue;
		}
		for(int i=0;i<A.length;i++){
			System.out.print(A[i]+" ");
		}
		System.out.println();
		//array copy
		int[] subArray=Arrays.copyOfRange(A, 2, 4);
		for(int i=0;i<subArray.length;i++)
			System.out.print(subArray[i]+" ");
	}

}
