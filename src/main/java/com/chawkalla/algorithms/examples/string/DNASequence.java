/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author SFargose
 *
 */
public class DNASequence {

	int mask=0xFFFFF;
	private static HashMap<Character, Integer> map=new HashMap<Character, Integer>();
    static{map.put('A',0);map.put('C',1);map.put('G',2);map.put('T',3);}
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res=new ArrayList<String>();
        if(s.length()<10) return res;
        
        
        int curNumber=0;
        for(int i=0;i<10;i++)
            curNumber=getNumber(curNumber, map.get(s.charAt(i)));
        
        HashMap<Integer, Integer> set=new HashMap<Integer,Integer>();
        set.put(curNumber,1);
        
        for(int i=10;i<s.length();i++){
            curNumber=getNumber(curNumber, map.get(s.charAt(i)));
            set.compute(curNumber, (k,v)->v==null?1:v+1);
            if(set.get(curNumber)==2)
            	res.add(s.substring(i-9,i+1));
        }
        return res;
    }
    
    private int getNumber(int curNumber, int curDigit){
        
        return (mask & (curNumber<<2))|curDigit;
    }
    
	public static void main(String[] args) {
		
		assertThat(new DNASequence().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"), is(Arrays.asList(new String[]{
				"AAAAACCCCC","CCCCCAAAAA"
		})));
		System.out.println("all cases passed");
	}

}
