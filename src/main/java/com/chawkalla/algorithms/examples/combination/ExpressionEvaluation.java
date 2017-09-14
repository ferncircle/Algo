/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

/**
 * # f("314159265358", 27182) should print:      

# 3 + 1 - 415 * 92 + 65358 = 27182 
# 3 * 1 + 4 * 159 + 26535 + 8 = 27182 
# 3 / 1 + 4 * 159 + 26535 + 8 = 27182 
# 3 * 14 * 15 + 9 + 26535 + 8 = 27182 
# 3141 * 5 / 9 * 26 / 5 * 3 - 5 * 8 = 27182
 *
 */
public class ExpressionEvaluation {

	public void evaluate(String str, int t){
		
		util(str, 0, 0, 0, "", t);
	}
	
	private void util(final String str, int s, long sum, long prev, String buf, final int t){
		if(s==str.length()){
			if(t==sum)
				System.out.println(buf);
			return ;
		}
		
		for (int i = s+1; i <= str.length(); i++) {
			long cur=Long.parseLong(str.substring(s,i));
			
			if(s==0){
				util(str, i, sum+cur, cur, buf+cur,t);
			}else{				
				util(str, i, sum+cur, cur, buf+"+"+cur, t);//+
				util(str, i, sum-cur, -cur, buf+"-"+cur, t);//-
				util(str, i, (sum-prev)+prev*cur, prev*cur, buf+"*"+cur, t);//*
				util(str, i, (sum-prev)+prev/cur, prev/cur, buf+"/"+cur, t);///
			}
		}
	}
	
	public static void main(String[] args) {
		
		new ExpressionEvaluation().evaluate("314159265358", 27182);
		System.out.println("all cases passed");
	}

}
