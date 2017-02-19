/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * 
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

 *
 */
public class RemoveInvalidParentheses {


	public List<String> removeInvalidParentheses(String s) {
		Set<String> result=new HashSet<String>();
		
		int extraLeftCount=0;
		int extraRightCount=0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='(')
				extraLeftCount++;
			else if(s.charAt(i)==')'){
				if(extraLeftCount<=0)
					extraRightCount++;
				else
					extraLeftCount--;
			}			
		}
		
		removeUtil(s, 0, extraLeftCount, extraRightCount, 0, new StringBuilder(), result);
		return new ArrayList<String>(result);
		
	}
	
	public void removeUtil(String s, int curPos, int extraLeftCount, int extraRightCount, int openCount, 
			StringBuilder buf, Set<String> result){
		if(curPos==s.length() && extraLeftCount==0 && extraRightCount==0 && openCount==0){
			result.add(buf.toString());
			return;
		}
		
		if(curPos==s.length() || extraLeftCount<0 || extraRightCount<0 || openCount<0)
			return;
		
		char curChar=s.charAt(curPos);
		
		if(curChar=='('){

			removeUtil(s, curPos+1, extraLeftCount, extraRightCount, openCount+1, buf.append(curChar), result);//use it
			buf.setLength(buf.length()-1); //remove just used char at end
			removeUtil(s, curPos+1, extraLeftCount-1, extraRightCount, openCount, buf, result); //delete this extra char
		}
		else if(curChar==')'){

			removeUtil(s, curPos+1, extraLeftCount, extraRightCount, openCount-1, buf.append(curChar), result);//use it
			buf.setLength(buf.length()-1);//remove just used char at end
			removeUtil(s, curPos+1, extraLeftCount, extraRightCount-1, openCount, buf, result); //delete this char OR
		}else{			
			removeUtil(s, curPos+1, extraLeftCount, extraRightCount, openCount, buf.append(curChar), result);
			buf.setLength(buf.length()-1);//remove just used char at end
		}		
		
	}

	public static void main(String[] args) {
		
		assertThat(new RemoveInvalidParentheses().removeInvalidParentheses("()())()"), 
				is(Arrays.asList("()()()", "(())()")));
		assertThat(new RemoveInvalidParentheses().removeInvalidParentheses("(r(()()("), 
				is(Arrays.asList("(r())", "(r)()","r(())", "r()()")));
		assertThat(new RemoveInvalidParentheses().removeInvalidParentheses(")("), 
				is(Arrays.asList("")));
		assertThat(new RemoveInvalidParentheses().removeInvalidParentheses("(a)())()"), 
				is(Arrays.asList("(a)()()", "(a())()")));
		
		System.out.println("All test cases passed");
	}

}
