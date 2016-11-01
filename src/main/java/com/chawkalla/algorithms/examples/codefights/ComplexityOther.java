package com.chawkalla.algorithms.examples.codefights;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ComplexityOther {

	int sum, totalnum = 9, complexity = 1, N;

	//A map stores : (complexity, the intergers with this complexity)
	Map<Integer,HashSet<Integer>> complexities = new HashMap<>();

	int sumOfComplexities(int N) {
	    if (N < 10) return 0;
	    this.N = N;
	    
	    //0 complexity init
	    HashSet<Integer> t = new HashSet<Integer>();
	    for(int i = 1; i < 10; i++)
	        t.add(i);
	    complexities.put(0,t);
	    
	    

	    for(;;complexity++) {
	        complexities.put(complexity,new HashSet<Integer>());
	        //complexityNeedFiguredOut = complexity1 + complexity2 + 1
	        //So complexity2 = complexityNeedFiguredOut - complexity1 - 1
	        for (int complexity1 = 0; complexity1 <= complexity / 2; complexity1++) {
	            HashSet<Integer> a= complexities.get(complexity1),b = complexities.get(complexity - complexity1 - 1);
	            for (int num1 : a)
	                for(int num2 : b) {
	                    //4 operation
	                    newInput(num1+num2);
	                    newInput(num1<num2 ? num2-num1 : num1-num2);
	                    newInput(num1*num2);
	                    newInput(num1<num2 ? num2/num1 : num1/num2);
	                    
	                    //return sum if find all
	                    if (totalnum == N -1) return sum;
	                }
	        
	                
	        }
	    }
	    
	    
	}

	void newInput(int num) {
	    if (num == 0) return;
	    //if the complexity of this number is already known return;
	    for (int i = 0; i <= complexity; i++)
	        if (complexities.get(i).contains(num))
	            return;
	    //not known, add it to the complexity map
	    complexities.get(complexity).add(num);
	    
	    //if num is less than N, we find one more complexity
	    if (num < N) {
	        totalnum++;
	        sum+=complexity;
	    }
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
