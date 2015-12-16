package com.chawkalla.algorithms;

public class LCS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String str1=" banana";
		String str2=" ananab";
		lcs(str1.toCharArray(), str2.toCharArray());

	}
	
	public static void lcs(char[] a, char[] b){
		
		int[][] c=new int[a.length+1][b.length+1];
		
		for(int i=0;i<=a.length;i++)
			c[i][0]=0;
		for(int j=0;j<=b.length;j++)
			c[0][j]=0;
		
		for(int i=1;i<a.length;i++)
			for(int j=1;j<b.length;j++){
				if(a[i]==b[j]){
					c[i][j]=c[i-1][j-1]+1;
				}
				else
					c[i][j]=Math.max(c[i][j-1], c[i-1][j]);
			}
		System.out.println(c[a.length-1][b.length-1]);
		System.out.println(backtrack(c, a, b, a.length-1, b.length-1));
		printArray(c, a.length, b.length);
		
	}
	
	public static void printArray(int[][] a, int x, int y){
		for(int i=0;i<=x;i++){
			System.out.println("");
			for(int j=0;j<=y;j++){
				System.out.print(a[i][j]+"  ");
				
			}
		}
			
	}
	
	public static String backtrack(int[][] c, char[] a, char[] b, int i, int j){
		if(i==0 || j==0)
			return "";
		else
			if(a[i]==b[j])
				return backtrack(c, a, b, i-1, j-1)+a[i];
			else
				if(c[i][j-1]>c[i-1][j])
					return backtrack(c, a, b, i, j-1);
				else
					return backtrack(c, a, b, i-1, j);	
					
	}

}
