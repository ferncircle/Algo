/**
 * 
 */
package com.chawkalla.algorithms.examples.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author SFargose
 *
 */
public class BreakSentence2 {

	public ArrayList<String> wordBreak(String a, ArrayList<String> b) {

		HashSet<String> dict=new HashSet<String>();
		for(String str:b)
			dict.add(str);
		ArrayList<ArrayList<String>> dp=new ArrayList<ArrayList<String>>();

		for(int i=0;i<=a.length();i++){
			dp.add(new ArrayList<String>());
			if(i==0)
				dp.get(i).add("");
			
			for(int j=0;j<=i;j++){
				String suffix=a.substring(j,i);
				if(dict.contains(suffix)){
					for(String str: dp.get(j))
						dp.get(i).add(str+(str.length()>0?" ":"")+suffix);
				}
			}
		}

		Collections.sort(dp.get(dp.size()-1));
		return dp.get(dp.size()-1);
	}

	public static void main(String[] args) {
		
		ArrayList<String> b=new ArrayList<String>();
		b.add("cat");b.add("cats");b.add("and");b.add("sand");b.add("dog");
		
		System.out.println(new BreakSentence2().wordBreak("catsanddog", b));

	}

}
