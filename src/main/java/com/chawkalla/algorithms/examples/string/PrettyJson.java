/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import java.util.ArrayList;

/**
 *https://www.interviewbit.com/problems/pretty-json/
 *
 *Input : {A:"B",C:{D:"E",F:{G:"H",I:"J"}}}
Output : 
{ 
    A:"B",
    C: 
    { 
        D:"E",
        F: 
        { 
            G:"H",
            I:"J"
        } 
    }     
}
 *
 */
public class PrettyJson {

	public ArrayList<String> prettyJSON(String a) {
		ArrayList<String> res=new ArrayList<String>();
		String indent="";
		int i=0;
		StringBuffer content=new StringBuffer();
		char prevChar=0;
		while(i<a.length()){
			
			char curChar=a.charAt(i++);
			if(curChar=='{' || curChar=='['){
				if(content.length()>0){
					res.add(indent+content.toString());
					content=new StringBuffer();
				}
				res.add(indent+curChar);
				indent=indent+"\t";
			}else if(curChar=='}' || curChar==']'){
				if(content.length()>0){
					res.add(indent+content.toString());
					content=new StringBuffer();
				}
				indent=indent.substring(0,indent.length()-"\t".length());
				res.add(indent+curChar);
			}else if(curChar==','){
				if(prevChar=='}' || prevChar==']'){
					String str=res.get(res.size()-1)+curChar;
					res.set(res.size()-1, str);
				}else{
					content.append(curChar);
					res.add(indent+content.toString());
				}				
				content=new StringBuffer();				
			}else{
				content.append(curChar);
			}
			prevChar=curChar;
		
			
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		
		for(String s: new PrettyJson().prettyJSON(""))
			System.out.println(s);
		for(String s: new PrettyJson().prettyJSON("{}"))
			System.out.println(s);
		for(String s: new PrettyJson().prettyJSON("{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}"))
			System.out.println(s);
		for(String s: new PrettyJson().prettyJSON("[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]"))
			System.out.println(s);

	}

}
