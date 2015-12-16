package com.chawkalla.algorithms;

public class InterleavingString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str1="abcdef";
		String str2="ghijk";
		permuteStrings(str1.toCharArray(), str2.toCharArray(), 
				new boolean[str1.length()], new boolean[str2.length()], new StringBuilder());

	}
	
	public static void permuteStrings(char[] str1, char[] str2, boolean[] used1, boolean[] used2, StringBuilder out){
		if(out.length()==(str1.length+str2.length))
			System.out.println(out);
		for(int i=0;i<str1.length;i++){
			if(!used1[i]&& canBeUsed(used1, i)){
				used1[i]=true;
				out.append(str1[i]);
				permuteStrings(str1, str2, used1, used2, out);
				used1[i]=false;
				out.setLength(out.length()-1);
			}
				
		}
		
		for(int i=0;i<str2.length;i++){
			if(!used2[i] && canBeUsed(used2, i)){
				used2[i]=true;
				out.append(str2[i]);
				permuteStrings(str1, str2, used1, used2, out);
				used2[i]=false;
				out.setLength(out.length()-1);
			}
				
		}
	}
	
	public static boolean canBeUsed(boolean[] used, int i){
		boolean done=true;
		
		for(int j=0;j<i;j++){
			if(!used[j]){
				done=false;
				break;
			}
				
				
		}
				
		return done;
	}

}
