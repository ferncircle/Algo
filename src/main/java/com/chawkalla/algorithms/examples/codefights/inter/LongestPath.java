/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.inter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Stack;

/**
 * https://codefights.com/interview/6cH6crkjcD3YaAZ3Z

Suppose we represent our file system as a string. For example, the string "user\n\tpictures\n\tdocuments\n\t\tnotes.txt" 
represents:

user
    pictures
    documents
        notes.txt    
The directory user contains an empty sub-directory pictures and a sub-directory documents containing a file notes.txt.

The string "user\n\tpictures\n\t\tphoto.png\n\t\tcamera\n\tdocuments\n\t\tlectures\n\t\t\tnotes.txt" represents:

user
    pictures
        photo.png
        camera
    documents
        lectures
            notes.txt
The directory user contains two sub-directories pictures and documents. pictures contains a file photo.png and an empty 
second-level sub-directory camera. documents contains a second-level sub-directory lectures containing a file notes.txt.

We want to find the longest (as determined by the number of characters) absolute path to a file within our system. 
For example, in the second example above, the longest absolute path is "user/documents/lectures/notes.txt", and its 
length is 33 (not including the double quotes).

Given a string representing the file system in this format, return the length of the longest absolute path to a file 
in the abstracted file system. If there is not a file in the file system, return 0.
 *
 */
public class LongestPath {

	int longestPath(String fileSystem) {
	    if(fileSystem==null || fileSystem.length()==0)
	        return 0;
	    int max=0;
	    String[] files=fileSystem.split("\r");
	    
	    Stack<Integer> st=new Stack<Integer>();
	    int pos=0;
	    int sumSoFar=0;
	    while(pos<files.length){
	    	String curWord=files[pos];
	    	int curLevel=getWordLevel(files[pos]);
	    	if(curWord.indexOf(".")>=0 && (st.isEmpty() || curLevel>getWordLevel(files[st.peek()]))){//it's a file
	    		max=Math.max(max,sumSoFar+curWord.length()-curLevel);
	    		pos++;
	    	}else{
	    		if(st.isEmpty() || curLevel>getWordLevel(files[st.peek()]) ){
	    			st.push(pos);
	    			sumSoFar+=curWord.length()-curLevel+1;
	    			pos++;
	    		}else{
	    			sumSoFar-=(files[st.peek()].length()-getWordLevel(files[st.peek()])+1);
	    			st.pop();
	    		}
	    	}
	    }
	    
	    
	    return max;
	}
	
	private int getWordLevel(String word){
		int tabCount=0;
	    int i=0;
	    for(i=0;i<word.length() && word.charAt(i)=='\t';i++,tabCount++){}
	    return tabCount;
	}
	
		
	public static void main(String[] args) {
		
		
		assertThat(new LongestPath().longestPath("user\r\tpictures\r\tdocuments\r\t\tnotes.txt"), is(24));
		assertThat(new LongestPath().longestPath("a\r\tb1\r\t\tf1.txt\r\taaaaa\r\t\tf2.txt"), is(14));

		System.out.println("all cases passed");
	}

}
