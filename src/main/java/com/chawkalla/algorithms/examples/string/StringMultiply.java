/**
 * 
 */
package com.chawkalla.algorithms.examples.string;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

/**
 * @author SFargose
 *
 */
public class StringMultiply {

	public String multiply(String a, String b) {
		ArrayList<Integer> res=new ArrayList<Integer>();
		for(int m=a.length()-1;m>=0;m--){
			int i=a.length()-m-1;
			int carry=0;
			for(int n=b.length()-1;n>=0;n--){
				int j=b.length()-n-1;
				
				int product=(a.charAt(m)-'0')*(b.charAt(n)-'0');
				if(i+j>res.size()-1)
					res.add(0);
				
				int sum=res.get(i+j)+product+carry;
				res.set(i+j, sum%10);
				carry=sum/10;				
				
			}
			if(carry>0)
				res.add(carry);
				
		}
		
		StringBuffer buf=new StringBuffer();
		for (int i = 0; i < res.size(); i++) {
			buf.append(res.get(i));
		}
		
		buf.reverse();
		int start=0;
		while(start<buf.length()&& buf.charAt(start)=='0')
			start++;
		String r=buf.substring(start);
		if(r==null || r.equals("") || r.charAt(0)=='0')
			return "0";
		return r;
	}
	
	
	public static void main(String[] args) {
		assertThat(new StringMultiply().multiply("5", "7"), is("35"));

		
		assertThat(new StringMultiply().multiply("31243242535342", "0"), is("0"));
		assertThat(new StringMultiply().multiply("6020453667958309279424408570378228292268488402", "0021473700594524297017810575200827941459805716642468749607585313713214621412"), 
				is("129281419508942330644788914772375911909165364374172850648846234013189757981044692486872392891670352883617068289942863624"));
		
		assertThat(new StringMultiply().multiply("45", "123"), is("5535"));
		assertThat(new StringMultiply().multiply("12", "10"), is("120"));

		assertThat(new StringMultiply().multiply("12", "10"), is("120"));

		System.out.println("all cases passed");
	}

}
