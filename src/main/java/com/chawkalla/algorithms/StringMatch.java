package com.chawkalla.algorithms;


public class StringMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String T="abcdefwefwk23awfek43r";
		String P="fedk4";
		int[][] D=editDistance(T,P,false);

		printMatrix(D, T.length(), P.length());

	}

	public static int[][] editDistance(String T, String P, boolean approximateMatch){
		int[][] D=new int[T.length()+1][P.length()+1];

		for (int i=0; i<=T.length(); i++) {
			if(approximateMatch)
				D[i][0] = 0;
			else
				D[i][0] = i;
		}

		for (int j=1; j<=P.length(); j++) {
			D[0][j] = j;
		}

		for (int i=1; i<T.length(); i++) {
			for (int j=1; j<P.length(); j++) {
				if(T.charAt(i)==P.charAt(j)){
					D[i][j]=D[i-1][j-1];
				}
				else {
					int substitute=D[i-1][j-1]+1;  //substitution
					int deletion=D[i-1][j]+1; //deletion
					int insertion=D[i][j-1]+1;  //insertion
					
					int min=Math.min(Math.min(substitute, insertion), deletion);
					D[i][j]=min;
					
					
				}

			}

		}


		return D;
	}
	
	public static int mininum(int[] A){
		int min=A[0];
		for(int i:A){
			if(min>i)
				min=i;
		}
		return min;
	}

	public static void printMatrix(int[][] a, int rows, int columns){

		for(int i=0;i<rows;i++){

			for(int j=0;j<columns;j++){
				System.out.print(a[i][j]+"   ");
			}
			System.out.println();
		}

	}

}
