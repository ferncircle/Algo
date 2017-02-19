package com.chawkalla.algorithms;

/**
 * * Solution:
 * 1) At each result position, figure out which characters can possibly fit (hence for loop)
 * 2) In result array, keep items in sorted order to avoid duplicates(1,2 vs 2,1). so next resultPos, start from next item
 * 		in array
 * 3) If total items filled in positions are equal to r then we are done for this path

 *
 */
public class AllSubString {

	public static void main(String[] args)throws Exception{
		String str="1234";
		doCombine(str.toCharArray(), new StringBuilder(), 0);
		System.out.println("");
		RecCombine("",str);
	}

	public static void doCombine(char[] instr, StringBuilder outstr, int start){		
		
		for(int i=start;i<instr.length;i++){
			outstr.append(instr[i]);
			System.out.print(outstr+" ");
			
			if(i<instr.length)
				doCombine(instr, outstr, i+1);
			outstr.setLength(outstr.length()-1);
		}
	}

	public static void RecCombine(String prefix,String rest){
		if(rest.length() == 0)
			System.out.print(prefix + " ");
		else{
			
			RecCombine(prefix + rest.charAt(0),rest.substring(1));
			RecCombine(prefix,rest.substring(1));
		}
	}
}
