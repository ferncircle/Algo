/**
 * 
 */
package com.hackerrank.challenge;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/crescent-practice-3rd-years/challenges/islands-1/problem
 *
 */
public class NumberOfIslands {
	public static int islands(int[][] m){
        int total=0;
        
         for(int i=0;i<m.length;i++)
            for(int j=0;j<m[0].length;j++)
              if(m[i][j]==1){
                  total++;
                  util(m,i,j);
              }
        return total;
    }
    
    public static void util(int[][] m, int i, int j){
        if(i<0 || i==m.length || j<0 || j==m[0].length)
            return;
        if(m[i][j]==-1 || m[i][j]==0) return;
        
        m[i][j]=-1;
        
        for(int x=i-1;x<=i+1;x++)
            for(int y=j-1;y<=j+1;y++)
                util(m,x,y);
    }

    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][] mat=new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
              mat[i][j]=sc.nextInt();
        
        System.out.println(islands(mat));
        sc.close();
    }
}
