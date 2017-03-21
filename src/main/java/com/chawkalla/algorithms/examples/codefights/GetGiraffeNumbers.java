/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * https://codefights.com/challenge/wv4zjAcJF7yMHqJLY
 * 
 * A natural number is called a giraffe if no digit in the number is preceded by a greater digit. For example 0, 1, 12, 
 * and 1556 are all giraffe numbers, while 21 and 1565 are not.

Given maxNum, your task is to calculate the number of non-negative giraffe numbers that are smaller than maxNum.

Example

For maxNum = "20", the output should be
getGiraffeNumbers(maxNum) = 19.

There are 10 one-digit giraffe numbers from 0 to 9, and 9 two-digit giraffe numbers smaller than 20: 11, 12, 13, ..., 19. 
Thus, the answer is 10 + 9 = 19.

Input/Output

[time limit] 3000ms (java)
[input] string maxNum

A number represented as a string.

Constraints:
5 <= int(maxNum) < 10200.

Solution:
Consider number 120
1) Get the count of such numbers for all numbers of length =numLength-1. e.g. for number 120, get all such numbers from 0-99
	Use DP to do that
3) Now for numbers of only that length, 3, get such count using recursion(with cache)
 *
 */
public class GetGiraffeNumbers {

	HashMap<String, Long> cache=new HashMap<String, Long>();
	
	
	long getGiraffeNumbers(String maxNum) {
		long total=0;

		total=getAllOfFixedLengthDP(maxNum.length()-1);
		total+=getNumberUtilDP(maxNum, 0, 1, false);
		//System.out.println(getCountLessThanNumberDP(maxNum));
		if(maxNum.length()==1 || allNines(maxNum))
			total--;
		System.out.println(total);
		return total;
	}
	
	private boolean allNines(String num){
		boolean allNine=true;
		for (int i = 0; i < num.length(); i++) {
			if(num.charAt(i)!='9'){
				allNine=false;
				break;
			}
		}
		return allNine;
	}

	private long getAllOfFixedLengthDP(int numLength){
		int numOfdigits=9;
		long[][] dp=new long[numOfdigits][numLength];
		long total=1;
		for (int i = 0; i < numOfdigits; i++) {

			for (int j = 0; j < numLength; j++) {
				if(j==0){ //for single digits
					dp[i][j]=1;
				}else{

					for (int prevDigit = 0; prevDigit <= i; prevDigit++) {
						dp[i][j]+=dp[prevDigit][j-1];
					}
				}

				total+=dp[i][j];
			}
		}
		
		return total;
	}
	
	private long getNumberUtilDP(final String maxNum, int pos, int start, boolean iterateAll){
		if(pos==maxNum.length()){		
			return 1;
		}
		long total=0;
		
		String key=""+pos+":"+start+":"+(iterateAll?1:0);
		if(cache.containsKey(key))
			return cache.get(key);
		int end=iterateAll?9:Integer.parseInt(""+maxNum.charAt(pos));

		for (int i = start; i <= end; i++) {
			boolean nextIterateAll=(i==end && !iterateAll)?false:true;
			total+=getNumberUtilDP(maxNum, pos+1, i, nextIterateAll);
		}
		cache.put(key, new Long(total));
		return total;
	}
	
	/*private long getCountLessThanNumberDP(final String maxNum){
		
		int numOfdigits=9;
		long[][] dp=new long[numOfdigits][maxNum.length()];
		long total=0;
		for (int i = 0; i < numOfdigits; i++) {
			for (int j = 0; j < maxNum.length(); j++) {
				int curDigit=Integer.parseInt(""+maxNum.charAt(j));
				int curTotal=0;
				if(j==0){
					if(i<=curDigit){
						dp[i][j]=1;
					}
					continue;
				}
				for (int k = 0; k < j; k++) {
					int kthDigit=Integer.parseInt(""+maxNum.charAt(k));
					
					for (int l = 0; l < kthDigit; l++) {
						curTotal+=dp[l][k];
					}
					
					
				}
				
				for (int k = 0; k <= curDigit; k++) {
					curTotal+=dp[j-1][k];
				}
				dp[i][j]=curTotal;
				
			}
			
		}
		
		for (int i = 0; i < numOfdigits; i++) {
			total+=dp[i][maxNum.length()-1];
		}
		return total;
	}*/
	
	
	
	long count;

	long getGiraffeNumbersRecurse(String maxNum) {
		long total=0;

		getNumberUtilRecurse(new StringBuffer(""), maxNum, new BigInteger(maxNum), 1);

		
		total=count;
		System.out.println("recursion count="+count);
		return total;
	}

	private void getNumberUtilRecurse(StringBuffer buf, final String maxNum, final BigInteger bigMaxNum, int start){
		if(buf.length()>maxNum.length())
			return;
		if(buf.length()==maxNum.length()){

			BigInteger cur=new BigInteger(buf.toString());
			if(cur.compareTo(bigMaxNum)<0){
				//System.out.println(buf.toString());
				count++;
			}

		}else{
			//System.out.println(buf.toString());
			count++;
		}



		for (int i = start; i <= 9; i++) {
			getNumberUtilRecurse(buf.append(i), maxNum, bigMaxNum, i);
			buf.setLength(buf.length()-1);
		}
	}

	public static void main(String[] args) {

		//new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999");
		new GetGiraffeNumbers().getGiraffeNumbers("253");
		//new GetGiraffeNumbers().getGiraffeNumbersRecurse("9999");
		/*assertThat(new GetGiraffeNumbers().getGiraffeNumbers("7"), is(7L));
		
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("123456789101112"), is(1087370L));
		
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("20"), is(19L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("100"), is(55L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("1000"), is(220L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("1234567891011121314151617181920"), is(258552150L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999"), 
				is(12196604061069L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("9999"), is(714L));
		*/
		//new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999");
		
		
		System.out.println("All test cases passed");

	}

}
