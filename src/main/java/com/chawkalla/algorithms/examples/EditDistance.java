package com.chawkalla.algorithms.examples;


public class EditDistance {

	public int minDistance(String word1, String word2) {
        int dist=0;
        if(word1==null)
        	word1="";
        if(word2==null)
        	word2="";
        
        int[][] D=new int[word1.length()+1][word2.length()+1]; //padding by 1 to accomodate empty strings
        
        for (int i = 0; i <= word1.length(); i++) {
        	
        	for (int j = 0; j <= word2.length(); j++) {
				if(i==0)
					D[i][j]=j;
				else if(j==0)
					D[i][j]=i;
				else{
					if(word1.charAt(i-1)==word2.charAt(j-1)){
						D[i][j]=D[i-1][j-1];
					}else{
						D[i][j]=Math.min(D[i-1][j-1]+1, Math.min(D[i-1][j]+1, D[i][j-1]+1));
					}
				}				
				
			}
			
		}
        /*for (int i = 0; i < D.length; i++) {
			System.out.println(Arrays.toString(D[i]));
		}*/
        dist=D[word1.length()][word2.length()];
        return dist;
    }
	
	public static void main(String[] args) {
		

		System.out.println(new EditDistance().minDistance("", "ab"));
	}

}
