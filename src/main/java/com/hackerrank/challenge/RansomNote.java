/**
 * 
 */
package com.hackerrank.challenge;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author SFargose
 *
 */
public class RansomNote {

	public static boolean ransomNote(String[] magazine, String[] ransom){
        HashMap<String, Integer> freq=new HashMap<String,Integer>();
        
        for(String s:magazine)
            freq.compute(s, (k,v)->v==null?1:v+1);
        
        for(String s: ransom){
            if(freq.containsKey(s) && freq.get(s)>0)
                freq.put(s, freq.get(s)-1);
            else
                return false;
        }
        
        return true;
    }
    public static void main(String[] args) {
    	
    	Scanner in=new Scanner(System.in);  
    	
    	
    	int m=in.nextInt();
    	int n=in.nextInt();
    	
    	String[] magazine=new String[m];
    	String[] ransom=new String[n];
    	
    	for(int i=0;i<m;i++)
    		magazine[i]=in.next();
    	for(int i=0;i<n;i++)
    		ransom[i]=in.next(); 
    	
        /*Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }
        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }*/
        
        System.out.println(ransomNote(magazine,ransom)?"Yes":"No");
        in.close();
    }

}
