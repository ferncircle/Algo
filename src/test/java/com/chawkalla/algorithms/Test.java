package com.chawkalla.algorithms;

public class Test {

	public boolean repeatedSubstringPattern(String s) {
        if(s==null || s.equals("")) return true;
        
        int n=s.length();
        int[] k=kmp(s);
        int substringLength=k[n-1];
        
        return (substringLength>0 && (n%(n-substringLength)==0));
    }
    //ababc -> [0,0,1,2,0]
    int[] kmp(String s){
        int[] k=new int[s.length()];
        
        int j=0,i=1;
        
        while(i<s.length()){
            if(s.charAt(i)==s.charAt(j)){
                k[i]=j+1;
                i++;j++;
            }else{
                if(j==0){
                    k[i]=0;
                    i++;
                }else{
                    j=k[j-1];
                }
            }
        }
        
        return k;
    }
	
	public static void main(String[] args) {
		
		System.out.println(new Test().repeatedSubstringPattern("abcabcabc"));
		System.out.println(new Test().repeatedSubstringPattern("aaa"));
		
		System.out.println(Integer.toBinaryString(27));
		System.out.println(Integer.toBinaryString(27*3));
		
	}

}
