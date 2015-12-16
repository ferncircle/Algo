package com.chawkalla.algorithms;

public class PermuteString {

	public static int total;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String str="abc";
		permuteStrings(str.toCharArray(), new boolean[str.length()], new StringBuilder());
		System.out.println(total);
	}

	public static void permuteStrings(char[] str1, boolean[] used1, StringBuilder out){
		if(out.length()==(str1.length)){
			System.out.println(out);
			total++;
			return;
		}
		for(int i=0;i<str1.length;i++){
			if(!used1[i]){
				used1[i]=true;
				out.append(str1[i]);
				permuteStrings(str1, used1, out);
				used1[i]=false;
				out.setLength(out.length()-1);
			}

		}
	}

}
