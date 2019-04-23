/**
 * 
 */
package com.chawkalla.algorithms.examples.combination;

/**
 * @author farstany
 *
 */
public class GenerateValidBrackets {

	public void generate(int lCount, int rCount, StringBuffer buf) {
		if (lCount > rCount)
			return;
		if (lCount < 0 || rCount < 0)
			return;
		if (lCount == 0 && rCount == 0) {
			System.out.println(buf);
			return;
		}		

		buf.append(")");
		generate(lCount, rCount-1, buf);
		buf.setLength(buf.length() - 1);
		
		buf.append("(");
		generate(lCount-1, rCount, buf);
		buf.setLength(buf.length() - 1);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenerateValidBrackets test = new GenerateValidBrackets();
		test.generate(3, 3, new StringBuffer());

	}

}
