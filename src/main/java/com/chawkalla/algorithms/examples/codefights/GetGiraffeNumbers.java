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
 * A natural number is called a giraffe if no digit in the number is preceded by a greater digit. 
 * For example 0, 1, 12, 
 * and 1556 are all giraffe numbers, while 21 and 1565 are not.

Given maxNum, your task is to calculate the number of non-negative giraffe numbers that are smaller than 
maxNum.

Example

For maxNum = "20", the output should be
getGiraffeNumbers(maxNum) = 19.

There are 10 one-digit giraffe numbers from 0 to 9, and 9 two-digit giraffe numbers smaller 
than 20: 11, 12, 13, ..., 19. 
Thus, the answer is 10 + 9 = 19.

Input/Output

[time limit] 3000ms (java)
[input] string maxNum

A number represented as a string.

Constraints:
5 <= int(maxNum) < 10200.

Solution:
Get numbers in the form (Consider 0 0 1 for number 1)
0 0 1
0 0 2	
..
..
..
1 2 0


Previous solution: 
Consider number 120
1) Get the count of such numbers for all numbers of length =numLength-1. e.g. for number 120, get all 
such numbers from 0-99
	Use DP to do that
3) Now for numbers of only that length, 3, get such count using recursion(with cache)
 *
 */
public class GetGiraffeNumbers {

	HashMap<String, Long> cache=new HashMap<String, Long>();


	long getGiraffeNumbers(String maxNum) {
		long total=0;

		//total=getAllOfFixedLengthDP(maxNum.length()-1);
		total+=getNumberUtilDP(maxNum, 0, 0, false, "");
		
		if(maxNum.length()==1 || allNines(maxNum))
			total--;
		System.out.println(total);
		return total;
	}

	private long getNumberUtilDP(final String maxNum, int pos, int start, boolean iterateAll, String res){
		if(pos==maxNum.length()){		
			System.out.println(res);
			return 1;
		}
		long total=0;
		String key=""+pos+":"+start+":"+(iterateAll?1:0);
		if(cache.containsKey(key))
			return cache.get(key);
		int end=iterateAll?9:maxNum.charAt(pos)-'0';

		for (int i = start; i <= end; i++) {
			boolean nextIterateAll=(i==end && !iterateAll)?false:true;
			total+=getNumberUtilDP(maxNum, pos+1, i, nextIterateAll, res+i+" ");
		}
		cache.put(key, new Long(total));
		return total;
	}
	
	
	long N,D[][][]=new long[200][11][2];
	long egeousGo(char[] chars, int pos, int begin, boolean iterateAll, String res) {
	    if(pos==N){
	    	//System.out.println(res);
	        return 1;
	    }
	    int start=begin+1;
	    int takeAll=iterateAll?0:1;
	    int curDigit=chars[pos]-'0';
	    
	    if(D[pos][start][takeAll]>0)
	        return D[pos][start][takeAll];
	    long s=0;
	    for(int i=0;i<10;i++)
	        if((!iterateAll|(iterateAll&i<=curDigit))&begin<=i)
	            s+=egeousGo(chars, pos+1,i,iterateAll&curDigit==i, res+i+" ");
	    return D[pos][start][takeAll]=s;
	}

	long getGiraffeNumbersEgeous(String M) {
		char[] m=M.toCharArray();
	    N=m.length;
	    int r=1;
	    for(int i=1;i<N&r>0;i++)
	        r=m[i-1]>m[i]?0:1;
	    return egeousGo(m, 0,-1,true, "")-r;
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
	
	long getGiraffeNumbersCoung(String N) {
		
		long s=0, t=0, i=0, n=0 , p=0, j=0, m=0, k=0;
		n = N.length() ;
		for(int d : N.getBytes()){
			d -= 48;
			n--;
			i = p;
			p = p < d ? d : 9;
			for( ; i < d ; s += t){
				t = 1;
				j = 0; 
				m = n + 9 - i++;
				for(;  j < 10-i ; ) 
					t = t * m--/++j;
			}
		}
		return s;
	}

	public static void main(String[] args) {

		//new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999");
		String n="1230";
		System.out.println(new GetGiraffeNumbers().getGiraffeNumbers(n));
		
		System.out.println(new GetGiraffeNumbers().getGiraffeNumbersEgeous(n));
		//new GetGiraffeNumbers().getGiraffeNumbersRecurse("9999");
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("7"), is(7L));

		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("123456789101112"), is(1087370L));

		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("20"), is(19L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("100"), is(55L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("1000"), is(220L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("1234567891011121314151617181920"), is(258552150L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999"), 
				is(12196604061069L));
		assertThat(new GetGiraffeNumbers().getGiraffeNumbers("9999"), is(714L));
		 
		//new GetGiraffeNumbers().getGiraffeNumbers("99999399999423423423456799999892342342234234234233429999993499999999999999999999999999999999999999999999999999999");


		System.out.println("All test cases passed");

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

}
