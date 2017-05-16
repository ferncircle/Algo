/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://codefights.com/challenge/DHR7CkgCwZMKZTbxz
 * 
 * A square display is controlled by several tumblers. Each tumbler is connected to one or more pixels, and when 
 * flipped it switches the state of each connected pixel. That is, it turns on a pixel if it was turned off, and 
 * turns it back off in case it was turned on.

Determine whether it is possible to display the given pattern on the screen using some combination of the available 
tumblers. Each pixel of the display screen is initially turned off.

Example

For

tumblers = ["a1, b1, b3, d3", "a1, b1, c2, d4",
            "a1, b1, b3, d3", "b3, c2, c4",  
            "c2, c4, d3",     "b3, c2, d3, d4",  
            "a1, b1"]
and

pattern = ["...0",
           "....",
           "..0.",
           "...."]
the output should be
switchingTumblers(tumblers, pattern) = true.

Solution:

It boils down to choosing subset of tumblers that xors to given pattern. This problem is similar to choosing
subset of elements that has max xor value. http://www.geeksforgeeks.org/find-maximum-subset-xor-given-set/

1) Start with 100 down to 1, find any element that has ith bit set,x, and then update all elements that has 
	same ith bit set by XORing them with x. (gaussing elimination)
2) In the end, for remaining elements, starting from msb to lsb, check if bit is set for given k to current element
	if yes, then k=k ^ a[i]
	If k becomes zero, then return true

 *
 */
public class SwitchingTumblersGaussian {

	boolean switchingTumblers(String[] tumblers, String[] pattern) {

		BigInteger[] a=new BigInteger[tumblers.length];
		for (int i = 0; i < tumblers.length; i++) {
			a[i]=getNumber(tumblers[i], pattern[0].length());
		}

		int gaussianSetIndex=0;
		for (int i = 100; i >=0; i--) 
			for (int j = gaussianSetIndex; j < a.length; j++) 
				if(a[j].testBit(i)){ //find any element that has ith bit set
					//move it to another set(or we can maintain that set at start by swapping)
					BigInteger temp=a[gaussianSetIndex];
					a[gaussianSetIndex]=a[j];
					a[j]=temp;

					//now update all elements that has same ith bit set by XORing them with x
					for (int b = 0; b < a.length; b++) {
						if(b!=gaussianSetIndex && a[b].testBit(i))
							a[b]=a[b].xor(a[gaussianSetIndex]);
					}
					gaussianSetIndex++;
					
					break;
				}
			

		
		/*for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]+"="+a[i].toString(2));
		}*/

		BigInteger k=BigInteger.ZERO;
		int c=0;
		for (int i = pattern.length-1; i >=0; i--) 
			for (int j = 0; j < pattern[i].length(); j++,c++) 
				if(pattern[i].charAt(j)=='0')
					k=k.setBit(c);
				
			

		//System.out.println("\n\n"+k+"="+k.toString(2));

		for (int i = 100; i >=0; i--) {	
			for (int j = 0; j < a.length; j++) {
				if(a[j].testBit(i) && k.testBit(i)){
					k=k.xor(a[j]);
					//break;
				}
			}
		}

		//System.out.println("\n\n");
		return k.equals(BigInteger.ZERO)?true:false;
	}

	public static BigInteger getNumber(String tum, int col){
		BigInteger bi=new BigInteger("0");
		for (String cell:tum.split(", ")) {
			int j=cell.charAt(0)-'a';
			int i=Integer.parseInt(cell.substring(1))-1;
			int v=i*col+j;
			bi=bi.setBit(v);
		}		
		return bi;
	}
	

	public static void main(String[] args) {

		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{
				"a1, d1",
				"d1",
				"a1, c1"
		}, new String[]{
				"0..."
		}), is(true));	

		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{				
				"b3, a1", 
				"a3, b3, a2, b2, a1", 
				"b2, c2, a1, b1", 
				"a3, b3, a2, c2, b1"
		}, new String[]{
				"...", 
				".00", 
				"00."
		}), is(true));
		/*System.out.println(new BigInteger("18").getLowestSetBit());
		System.out.println(Integer.toBinaryString(69));

		System.out.println(Integer.toBinaryString(37));
		System.out.println(Integer.toBinaryString(21));*/

		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{				
				"b1, b2, b3, b4, c1, c10, c3, c4, c6, c8, d2, d4, d7, d8, e1, e10, e3, e6, e7, e8, e9, f3, f4, f5, f6, g10, g2, g7, h10, h2, h3, h7, i1, i10, i2, i6, i7, j10, j3, j8", 
				"a2, a5, a6, a8, b10, b3, b5, b6, b7, b8, b9, c1, c2, c4, c5, c9, d10, d4, d6, d8, d9, e2, e6, e7, e9, f10, f2, f5, g1, g10, g4, g5, g6, g8, h1, h10, h2, h3, h7, h8, h9, i3, i5, i8, j1, j10, j3, j4, j7, j8", 
				"a10, a5, a6, a9, b1, b10, b2, b3, b6, b7, b9, c7, c8, d6, d7, d8, d9, e1, e10, e2, e4, e8, f3, f4, f5, f7, f8, f9, g10, g2, g7, g8, h10, h6, h9, i1, i3, i4, i5, i7, i8, i9, j1, j10, j2, j3, j5, j7, j9", 
				"a1, a10, a4, a6, a7, b1, b5, b7, b8, c1, c2, c5, c6, c7, d1, d3, d4, d5, d6, d8, d9, e1, e10, e2, e5, e6, f6, f7, f8, f9, g1, g10, g8, g9, h3, h4, h8, i10, i2, i3, i4, i8, i9, j1, j10, j2, j4, j7", 
				"a1, a10, a2, a5, a6, a7, a8, a9, b1, b2, b3, b4, b5, b7, b8, b9, c1, c2, c4, c5, c6, c7, c8, c9, d10, d2, d3, d4, d5, d9, e1, e10, e2, e3, e4, e5, e6, e9, f1, f10, f2, f3, f7, f8, g1, g10, g2, g3, g4, g5, g6, g7, h1, h10, h2, h5, h6, h7, h8, i1, i10, i2, i4, i5, i7, i9, j1, j10, j2, j4, j5, j6, j7, j8", 
				"a7, c5, c7, e9, f5, g2, g7, h10, h2, i2, i3, i5, i6", 
				"a10, a2, a4, a5, a6, a8, b10, b2, b3, b4, b5, b9, c1, c10, c3, c4, c5, c6, c9, d1, d10, d2, d3, d4, d6, d8, e10, e6, e7, e8, f2, f5, f6, g1, g4, g5, g7, g8, h1, h10, h2, h4, h6, h7, i10, i2, i3, i5, i6, i8, j1, j2, j4, j7, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a9, b1, b10, b2, b3, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c7, c9, d1, d2, d3, d4, d5, d7, d8, d9, e1, e10, e2, e3, e6, e7, e8, e9, f1, f10, f3, f5, f6, f7, f8, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h5, h6, h7, h8, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a5, a6, b1, b10, b3, b7, b8, b9, c1, c10, c2, c4, c6, c9, d10, d2, d4, d5, d6, d7, d9, e1, e10, e2, e5, e6, e8, e9, f1, f10, f2, f4, f5, f6, f7, f8, f9, g2, g5, g6, g7, g8, g9, h1, h10, h3, h5, h7, h8, h9, i10, i2, i3, i5, i6, i8, i9, j1, j2, j4, j5, j6, j8", 
				"a1, a10, a2, a4, a8, b1, b10, b3, b5, b7, b9, c1, c2, c3, c4, c9, d1, d2, d3, d4, d5, d9, e1, e10, e4, e6, e8, e9, f10, f4, f5, f8, f9, g10, g2, g3, g4, g5, g6, g8, g9, h10, h5, h6, h7, h8, h9, i1, i10, i4, i6, i7, j10, j2, j4, j5, j6, j8", 
				"a1, a2, a3, a4, a6, b1, b5, b6, b7, b9, c10, c3, c4, c5, c8, d10, d3, d5, d6, d9, e10, e2, e4, e7, e9, f10, f2, f4, f6, f8, f9, g10, g2, g4, g5, g9, h4, h5, h7, i1, i4, i6, i8, j7, j8", 
				"a10, a3, a4, a7, a8, b1, b4, b6, b7, b8, b9, c1, c2, c3, c5, c8, c9, d5, d6, d8, e2, e5, e6, e7, e9, f1, f10, f2, f4, f7, f8, f9, g1, g2, g4, g6, g7, h1, h5, h6, h7, h8, i1, i10, i2, i3, i6, i8, i9, j1, j10, j2, j3, j4, j5, j7, j8, j9", 
				"a1, a4, a5, a6, a9, b1, b10, b2, b3, b4, b5, c1, c10, c3, c5, c6, c7, c9, d1, d5, d7, d8, e2, e3, e6, e8, f4, f5, f6, f7, g5, g6, g9, h3, h6, h7, h9, i1, i2, i5, i7, i8, i9, j1, j3, j6", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b2, b3, b5, b6, b7, c1, c2, c3, c5, c6, c7, c9, d10, d2, d3, d4, d6, d7, e1, e3, e5, e6, e7, e8, e9, f1, f2, f7, g1, g10, g3, g4, g5, g6, g8, h3, h4, h6, h9, i10, i2, i4, i6, i7, j1, j2, j3, j4, j7, j9", 
				"a1, a10, a2, a4, a6, a7, b1, b4, b6, b9, c1, c4, c5, c6, c8, d1, d5, d7, d9, e6, e7, e8, e9, f10, f2, f3, f4, f8, g10, g2, g5, g6, g7, g9, h1, h2, h3, h4, h5, h7, h9, i1, i10, i3, i7, i8, i9, j2, j7", 
				"a2, a3, a4, a6, a7, b1, b10, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c9, d2, d3, d5, d8, e1, e2, e3, e4, e5, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f9, g2, g3, g5, g6, g7, g8, g9, h1, h10, h4, h5, h7, h8, i1, i10, i2, i3, i4, i5, i7, i8, j1, j10, j2, j3, j6, j7, j8", 
				"a1, a6, b10, b2, b3, b4, c10, c2, c3, c5, c6, c7, c8, d3, d6, d8, e5, e8, f2, f3, f6, f7, f9, g10, g3, g4, g5, h10, h5, h8, h9, i1, i10, i2, i3, i4, i7, i8, i9, j1, j10, j3, j4, j5, j6, j9", 
				"a1, a10, a2, a3, a4, a5, a8, a9, b1, b10, b2, b3, b4, b5, b7, b9, c10, c2, c3, c4, c6, c7, c8, d1, d3, d4, d5, d7, e2, e3, e9, f10, f5, f7, g10, g2, g7, g8, h1, h3, h4, h6, h7, h9, i1, i2, i3, i4, i5, i9, j2, j3, j4, j9", 
				"a1, a10, a2, a3, b1, b10, b2, b4, b6, b8, b9, c1, c2, c3, c4, c7, c8, c9, d1, d3, d9, e1, e2, e3, e6, e8, e9, f10, f5, g2, g3, g4, g5, g6, h1, h2, h4, h6, h7, i2, i3, i9, j1, j3, j4, j5, j6, j7, j8, j9", 
				"a2, a6, a8, b8, b9, c1, c2, c3, c4, c5, c7, d3, d8, e4, e6, f8, g10, g5, g6, h1, i10", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a3, a8, b1, b8, b9, d10, d6, e4, f3, f6, f7, g10, g2, g4, g7, h3, h5, h7, j8", 
				"a1, a2, a3, a8, b1, b2, b5, b6, b7, b8, b9, c6, c7, c8, d10, d3, d5, d7, e1, e3, e4, e8, e9, f1, f3, f5, g10, g5, g7, g8, h1, h10, h3, h5, h6, h7, i3, i6, j1, j2, j4, j5, j6, j8", 
				"a10, a3, a4, a8, b10, b5, b7, b9, c2, c4, c6, c7, c8, c9, d2, d4, d5, d6, d7, e10, e2, e3, e4, e5, e7, e9, f10, f2, f3, f5, f6, f8, f9, g10, g2, g8, h10, h2, h4, h9, i1, i2, i3, i6, i8, i9, j2, j3, j8", 
				"a1, a4, a5, a7, a9, b5, b7, c2, c4, c5, c6, c7, c8, c9, d10, d3, d6, d7, d8, d9, e1, e2, e3, e5, e7, e8, e9, f1, f2, f3, f4, f6, f9, g10, g2, g3, g6, g8, g9, h1, h3, h4, h6, h7, h8, i2, i6, i7, j3, j6, j8, j9", 
				"a1, a10, a8, b1, b10, b2, b4, b6, b8, c1, c10, c3, c5, c7, c8, d1, d3, d6, d7, d8, e3, e6, e9, f10, f4, f6, f8, g1, g10, g2, g4, g5, g7, g9, h1, h2, h4, h6, h7, h8, h9, i1, i10, i3, i4, i5, i7, i8, i9, j1, j3, j4, j7, j8", 
				"a10, a3, a4, a6, a7, a8, a9, b1, b3, b6, b7, b9, c1, c4, c5, c6, c7, c8, d2, d5, e1, e3, e5, e6, e8, f2, f4, f7, f8, f9, g6, g7, g8, h1, h10, h4, i1, i10, i2, i4, i7, j1, j2, j3, j4, j5, j6, j9", 
				"a1, a10, a3, a4, a7, a8, b1, b10, b2, b6, b7, b9, c1, c10, c2, c3, c5, c6, c7, c8, c9, d10, d2, d3, d5, d6, d7, d8, d9, e1, e10, e3, e4, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f7, f8, f9, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h8, h9, i1, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a4, a7, a9, b2, b6, b7, b8, b9, c2, c3, c4, c7, d2, d3, d4, d5, e1, e2, e3, e4, e5, e6, f2, f4, f5, f7, f9, g1, g10, g3, h10, h2, h3, h4, h5, h7, i1, i3, i4, i5, i7, j1, j3, j4, j6, j7", 
				"a2, a3, a4, a5, a6, a7, a8, b10, b2, b3, b4, c1, c2, c4, c6, c7, c9, d2, d6, d7, d8, e1, e10, e3, e4, e5, e6, e7, e9, f1, f2, f3, f4, g2, g3, g6, g7, g9, h3, h6, h8, i1, i10, i5, i6, j10, j2, j5, j6, j9", 
				"a1, a10, a2, a4, a6, a7, a8, a9, b1, b10, b2, b4, b7, b8, c10, c2, c4, c5, c6, c7, c8, c9, d2, d3, d4, d5, d6, d7, d8, e4, e6, e8, f1, f10, f3, f4, f5, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, h1, h10, h3, h5, h6, h7, h9, i3, i6, i9, j10, j2, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a2, a3, a4, b2, b7, b8, b9, c2, c5, c6, d3, d6, d7, e2, e5, f10, f2, f7, g10, g2, g3, g9, h1, h10, h2, h3, h7, i3, i4, i7, j3, j8, j9", 
				"a2, a4, a6, a8, b10, b3, b4, b6, b7, c1, c10, c7, d2, d3, d4, d5, e10, e2, e3, e4, e6, e7, e9, f1, f4, f5, g1, g10, g4, g5, g7, g8, g9, h1, h10, h2, h5, h6, i1, i10, i2, i4, i5, i6, i7, j1, j10, j2, j3, j4, j5, j6", 
				"a10, a2, a6, a7, b1, b2, b6, b8, c10, c2, c3, c9, d2, d3, d4, d7, d8, e1, e10, e6, e7, e8, f1, f2, f4, f6, f7, f9, g10, g3, g7, g8, g9, h5, h6, h7, h8, h9, i1, i4, i5, i6, i8, j1, j10, j2, j3, j5, j6, j8, j9", 
				"a1, a9, b1, b5, c1, c10, c2, c4, c5, c7, c8, c9, d10, d5, d6, e10, e5, e7, f10, f3, f4, f5, f9, g1, g10, g2, g3, g4, h1, h2, h3, h6, h7, i10, i4, i8, j1, j2, j4, j6, j7", 
				"a1, a4, a6, a7, a8, a9, b4, b6, b7, b9, c1, c10, c2, c5, c7, d2, d5, d6, d9, e1, e2, e3, e5, e6, e9, f1, f2, f4, f7, f9, g10, g2, g5, g7, g8, h1, h2, h7, h8, i1, i2, i3, i4, i5, i6, i7, i8, j1, j2, j4, j5, j6, j9", 
				"a1, a10, a3, a4, a7, a9, b2, b3, b4, b5, b6, b7, b8, b9, c1, c2, c3, c4, c5, c6, c7, c9, d2, d3, d4, d6, d9, e2, e3, e5, e7, e8, f3, f5, f8, g4, g7, h1, h10, h2, h3, h5, h6, h7, h8, h9, i2, i3, i4, i6, i7, i8, i9, j1, j3, j4, j6, j8, j9", 
				"c8, i4, j4", 
				"a10, a2, a3, a4, a9, b10, b3, b4, b5, b6, b7, b8, c2, c4, c5, c6, c7, c8, d1, d2, d3, d5, d6, d8, e10, e3, e4, e6, f2, f3, f7, f8, f9, g10, g3, g4, g6, g8, g9, h1, h3, h5, h7, i3, i5, i7, i8, j10, j3, j4, j5, j7, j9", 
				"a10, a4, a6, a7, b1, b9, c1, c3, c5, c6, c7, c8, d2, d3, d4, d9, e1, e6, f1, f4, f5, g1, g3, g6, g7, g9, h2, h4, h5, h6, h9, i10, i3, i5, i7, i9, j1, j3, j4, j5, j6, j7, j8", 
				"a1, a10, a3, a4, a5, a6, a7, a9, b1, b10, b2, b4, b5, b6, b8, b9, c1, c10, c2, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f8, g1, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, i1, i10, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j9", 
				"a1, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b6, b7, b9, c10, c2, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e5, e6, e8, e9, f1, f10, f2, f5, f6, f7, f8, f9, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h6, h7, h8, i1, i10, i4, i5, i8, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a2, a4, a5, a6, b1, b10, b3, b5, c2, c3, c4, c5, c6, c7, d1, d10, d3, d6, d8, e10, e4, e5, e6, e7, e8, e9, f1, f2, f4, f6, f7, f8, f9, g10, g3, g5, g6, g7, g8, g9, h10, h2, h5, h7, h9, i2, i3, i5, i6, i7, j10, j4, j5", 
				"a1, a2, a4, a6, a8, a9, b1, b3, b4, b5, b7, b8, b9, c1, c5, c6, c7, c9, d1, d10, d3, d4, d5, d6, d7, d9, e10, e3, e4, e5, e6, e9, f1, f10, f2, f3, f6, f9, g1, g2, g3, g6, h10, h5, h6, h7, i10, i2, i4, i5, i6, i9, j1, j2, j5, j6, j7, j9", 
				"c10", 
				"a1, a10, a3, a5, a6, b1, b10, b4, b9, c1, c8, d1, d2, d5, d6, d8, e1, e2, e3, e6, f10, f3, f5, f6, f7, f9, g10, g2, g3, g5, g6, g8, g9, h1, h2, h8, i2, i4, i6, i8, i9, j2, j6, j7, j9", 
				"a1, a4, a5, a8, b1, b8, c3, c5, c8, c9, d1, d3, d4, d6, d7, d9, e10, e2, e3, e9, f10, f2, f3, f4, f5, f6, f7, g1, g5, g6, g7, g9, h2, h3, h7, i10, i2, i5, i6, i7, i8, j3, j7, j9", 
				"a3, a5, a6, a8, b1, b5, b6, c3, d10, d7, d8, e2, f2, g4, h10, h5, i4, i6, j10, j2, j6", 
				"a4, a7, a9, b3, b6, b7, b9, c10, c3, c5, c7, c8, d1, d4, d6, d7, d8, e1, e10, e2, e3, e4, e5, e7, e9, f1, f10, f2, f6, g2, g4, g6, h1, h2, h6, h8, h9, i1, i10, i4, i9, j1, j10, j4, j6", 
				"a1, a2, a4, a8, b1, b10, b2, b3, b8, c1, c10, c3, c5, c6, c8, c9, d1, d10, d2, d4, d5, d8, d9, e2, e5, e7, e9, f2, f3, f4, f7, g10, g3, g4, g5, g6, g7, h1, h2, h3, h4, h9, i1, i10, i4, i6, i8, j10, j3, j8, j9", 
				"a1, a10, a4, a6, a9, b1, b2, b5, b6, b7, b9, c10, c3, c5, c7, c8, d1, d3, d4, d7, d8, e10, e2, e6, e7, e8, e9, f10, f5, f6, f8, f9, g2, g5, g6, g7, h2, h4, h9, i10, i3, i4, i5, i7, i8, j3, j6, j7", 
				"a10, a2, a3, a4, a6, a7, a9, b2, b4, b5, b6, b7, c1, c2, c3, c5, c7, c8, c9, d2, d3, d5, d9, e1, e10, e2, e4, e6, e8, e9, f1, f10, f2, f4, f7, f8, g1, g4, g6, g9, h3, h7, h8, h9, i1, i4, i5, j2, j3, j5, j6", 
				"a10, a5, a6, a8, b1, b2, b5, b8, b9, c1, c3, c5, c7, c8, d1, d10, d5, d7, d8, e2, e3, e4, e5, e7, e8, f1, f2, f6, f9, g10, g7, g8, g9, h1, h2, h5, h7, h8, i3, i5, i9, j1, j4, j5, j7, j8, j9", 
				"a1, a10, a2, a5, a7, b1, b3, b4, b7, b9, c10, c2, c4, c8, c9, d1, d6, d9, e1, e8, f10, f2, f5, g1, g10, g5, g6, g8, h10, h2, h5, h7, h9, i1, i10, i2, i4, i5, i6, i9", 
				"j2, j6", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i5, i6, i7, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a5, a6, a7, a8, a9, b1, b10, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, e1, e10, e2, e4, e6, e7, e8, e9, f1, f10, f2, f4, f5, f6, f7, f8, f9, g1, g10, g3, g4, g5, g6, g7, g8, g9, h10, h2, h3, h4, h5, h6, h7, h8, i1, i10, i3, i4, i5, i6, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b5, b7, b8, c10, c2, c5, c8, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e4, e5, e6, e7, e8, e9, f1, f3, f4, f5, f6, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h6, h7, h9, i1, i10, i2, i3, i6, i8, i9, j1, j10, j2, j3, j4, j5, j7, j8, j9", 
				"a1, a10, a2, a4, a5, a7, a8, a9, b1, b2, b5, b7, b8, b9, c10, c3, c5, c6, c7, c8, d1, d10, d3, d4, d5, d6, d8, e10, e4, e5, e8, f10, f2, f4, f5, g1, g2, g3, g4, g6, g8, g9, h10, h3, h4, h5, h7, h8, i1, i10, i2, i3, i5, i7, i9, j1, j3, j5, j9", 
				"a6, b1, b2, b3, b4, b9, c1, c6, c8, c9, d1, d7, d8, d9, e10, e2, e4, e6, e7, e8, f10, f2, f4, f9, g10, g4, g6, g9, h3, h7, i3, i5, j1, j10, j8", 
				"a1, a10, a3, a4, b1, b7, b8, c10, d7, d8, e1, e10, e2, e3, e5, e8, e9, f1, f5, f6, f7, f9, g1, g10, g3, g4, g9, h10, h2, h3, h4, h5, h6, h7, h8, h9, i2, i3, i4, i6, i8, j1, j2, j7, j8, j9", 
				"a1, a10, a3, a4, a6, a7, a8, b10, b5, b6, b8, c10, c2, c3, c6, c8, d3, d5, d6, d7, d8, d9, e3, e5, f1, f8, g10, g6, g7, g8, h2, h5, h7, i2, i3, i5, i6, i7, i9, j10, j4, j5, j6, j8, j9", 
				"a1, a10, a2, a4, a5, a6, a7, a8, b1, b10, b4, b5, b6, b7, b8, b9, c10, c2, c3, c5, c7, c8, c9, d1, d2, d3, d5, d9, e1, e10, e4, e5, e7, e8, e9, f1, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g7, g8, h1, h10, h2, h3, h5, h6, h8, h9, i10, i2, i3, i4, i6, i7, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a4, a5, a6, b1, b3, c1, c4, c7, c8, d9, e2, e3, e9, f6, f8, g2, g3, g5, g6, g7, h1, h2, h6, i2, i4, i7, j2, j3, j4, j5, j9", 
				"a10, a4, a8, a9, b2, b3, b4, b7, b9, c1, c10, c2, c3, c4, c6, c7, d1, d3, d5, d6, d9, e1, e4, e5, e8, f10, f3, f4, f8, f9, g1, g5, h1, h10, h3, h5, h6, h7, h8, h9, i10, i2, i3, i4, i6, i7, i8, i9, j1, j2, j3, j4, j5, j6, j9", 
				"a1, a10, a4, a5, a7, a8, b1, b10, b2, b3, b4, b7, b8, c1, c10, c4, c7, c8, c9, d10, d2, d4, d5, d6, d7, d8, d9, e1, e10, e2, e4, e5, e6, e7, e8, e9, f1, f2, f5, f7, f8, f9, g10, g2, g4, g5, g6, g7, g8, g9, h4, h5, h7, h8, i1, i10, i2, i3, i4, i5, i8, i9, j1, j10, j2, j3, j4, j6, j7, j8, j9", 
				"a1, a3, a4, b4, b5, b9, c10, c5, c6, d2, d3, d6, e10, e5, e7, e8, f3, f7, f9, g1, g4, h1, h6, h8, i4, i7, j6", 
				"a10, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c6, c7, c8, c9, d1, d10, d4, d5, d6, d7, d9, e1, e10, e2, e3, e5, e6, e7, e8, f10, f2, f3, f4, f5, f6, f7, f9, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i10, i2, i4, i5, i6, i7, j1, j10, j2, j3, j4, j5, j6, j7, j9", 
				"a2, a5, a6, a7, b1, b2, b5, b6, b7, b8, b9, c2, c3, c4, c6, c7, c9, d1, d10, d2, d7, e1, e10, e2, e3, e4, e8, e9, f1, f2, f3, f4, f6, f7, f8, g1, g10, g3, g4, g5, h1, h8, h9, i1, i4, i5, j1, j3, j5, j6, j7, j8, j9", 
				"a10, a6, a7, b4, b5, b6, b7, b9, c5, c7, c8, d2, d8, e4, f1, f10, f2, f3, f4, f5, f6, f9, g3, g5, h1, h10, h7, h8, h9, i1, i2, i3, i5, i8, j6, j8", 
				"a1, a10, a2, a4, a5, a6, b10, b2, b3, b5, c1, c10, c3, c5, c6, c7, d1, d2, d3, d5, d6, d7, e10, e2, e5, e7, e8, f1, f2, f3, f7, f8, f9, g1, g10, g2, g3, g6, g7, g8, h2, h3, h5, h6, h7, h8, h9, i1, i10, i3, i4, i7, i9, j1, j10, j3, j4, j5, j6", 
				"a1, a2, a3, a5, a6, a9, b1, b10, b2, b3, b4, b8, b9, c1, c3, c4, c7, c8, c9, d2, d4, d5, d6, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f3, f6, f7, f8, g10, g2, g4, g6, g8, h1, h2, h4, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j10, j2, j4, j5, j6, j7, j8", 
				"a2, a3, a4, a7, a8, b10, b5, b6, b9, c10, c2, c6, d1, d2, d3, d4, d6, d7, d8, e3, e4, e6, e7, e9, f1, f10, f3, f4, f6, f7, g10, g2, g3, g6, g7, h1, h10, h3, h5, h7, i1, i2, i3, i4, i6, i8, j1, j2, j6, j8", 
				"a10, a3, a7, a8, b10, b2, b5, b6, c10, c2, c4, c7, c9, d4, d5, d7, d9, e10, e3, e6, f2, f3, f4, g1, g10, g4, g5, g7, g8, h1, h4, h6, h8, h9, i1, i2, i5, i6, i7, i9, j1, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a4, a7, b1, b2, b5, b6, b8, c2, c6, c9, d10, d3, d6, d8, d9, e3, e7, e8, f1, f10, f2, f3, f7, f9, g1, g10, g5, g8, h1, h2, h3, h5, h6, h7, i1, i4, i7, i8, j1, j10, j3, j4, j6, j8, j9", 
				"a1, a10, a2, a4, a5, a7, a9, b10, b2, b3, b6, b7, b8, c1, c10, c2, c4, c7, c8, d10, d2, d3, d4, e1, e2, e5, e6, e7, f2, f6, g1, g10, g2, g3, g8, g9, h5, h6, h7, h9, i10, i3, i8, i9, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a8, a9, b1, b10, b2, b5, b6, b9, c1, c3, c6, c7, c8, d3, d4, d6, d8, d9, e1, e4, e5, e7, e9, f2, f3, f4, f5, f6, f8, g2, g5, g6, h10, h6, i2, i7, j1, j10, j3, j4, j5, j7", 
				"a4, b1, b4, b7, c6, c9, d5, d6, d7, e1, e5, e6, e7, f10, f2, f8, f9, g10, g5, g6, i1, i7, i8, i9, j2, j7, j9", 
				"a1, a10, a3, a4, a8, a9, b1, b10, b2, b6, b7, b9, c10, c2, c6, c9, d10, d4, d7, d9, e4, e5, e6, e7, e8, e9, f1, f4, f5, f6, f9, g3, g4, g6, g7, g8, g9, h3, h4, h8, h9, i1, i10, i2, i3, i7, i8, j1, j10, j2, j4, j8, j9", 
				"a10, a2, b7, c1, c3, d10, d6, e2, e8, e9, g1, h5, i9, j1", 
				"a1, a10, a2, a8, b1, b10, b2, b3, b5, b6, b8, c1, c3, c4, c5, c7, c8, c9, d1, d2, d3, d4, d6, d8, d9, e2, e3, e7, e8, f1, f10, f3, f4, f5, f6, f9, g1, g4, g5, g9, h1, h10, h7, i1, i10, i2, i3, i4, i5, i6, i7, j3, j9", 
				"b9, c3, d6, g1, g10, h5, h8, j2", 
				"a10, a4, a5, a7, b2, b3, b4, b7, b8, b9, c6, d1, d10, d5, d6, d8, e10, e2, e4, e8, f2, f8, g10, g4, g8, h1, h2, h8, h9, i1, i2, i3, i7, j1, j2, j4, j6, j8", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j4, j5, j6, j7, j8, j9", 
				"a2, a4, a5, b10, b2, b3, b5, b8, b9, c1, c2, c5, c8, c9, d4, d5, d6, e10, e2, e3, e4, e6, f1, f3, f6, f8, f9, g1, g10, g2, g3, g5, g6, g7, g8, h4, h6, h7, h8, i1, i2, i3, i4, i7, i9, j1, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a5, a6, a7, a9, b1, b10, b2, b3, b4, b5, b6, b7, c1, c10, c2, c3, c4, c5, c6, c8, d1, d10, d2, d3, d4, d5, d6, d8, d9, e1, e10, e2, e3, e5, e7, e8, f1, f10, f3, f4, f5, f6, f7, f8, f9, g1, g10, g3, g4, g5, g6, g7, g8, g9, h3, h4, h6, h7, h9, i1, i2, i4, i5, i7, i8, i9, j10, j2, j3, j4, j5, j6, j8, j9", 
				"a10, a6, a8, b1, b10, b2, b3, b5, b6, b7, b8, c1, c3, c6, c8, c9, d1, d2, d3, d7, d8, e3, e4, e6, e7, e8, f1, f10, f3, f4, g3, g5, g6, g8, g9, h1, h2, h6, h8, i1, i10, i2, i5, i6, i7, i8, i9, j10, j2, j3, j5, j7, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b9, c1, c10, c2, c3, c4, c6, c8, d1, d10, d2, d3, d4, d5, d6, d7, d8, e1, e10, e2, e3, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a3, a4, a5, a6, b1, b10, b2, b5, b6, b7, b9, c1, c10, c4, c6, c7, c8, c9, d10, d2, d4, d5, d6, d7, d9, e6, e8, e9, f10, f4, f8, g1, g10, g3, g4, g7, g8, g9, h1, h3, h5, h8, i1, i10, i3, i8, i9, j1, j2, j3, j4, j5, j6, j7, j9", 
				"a10, a2, a4, a5, a6, a7, b1, b2, b3, b4, b7, b8, b9, c10, c2, c3, c6, c7, d1, d3, d4, d8, d9, e1, e10, e5, e6, e8, f1, f10, f3, f4, f5, f8, g10, g4, g8, h1, h10, h4, h6, h8, i10, i2, i3, i4, i5, i6, i9, j1, j10, j4, j5, j7", 
				"a3, b9, c10, c3, c6, c9, d1, d10, d8, f1, f10, f3, f5, g9, h4, h5, i1, i2, i3, i4, i5, i9, j4, j6, j7, j9", 
				"a5, a6, a9, b1, b3, c10, c3, c5, c6, c9, d10, d5, d8, d9, e1, e4, e5, e7, e9, f2, f4, f8, g1, g10, g3, g5, g8, i10, j1, j10, j2, j3, j4, j5, j6", 
				"a1, a2, a4, a7, a8, b2, b5, b6, b7, b8, c10, c2, c8, c9, d1, d4, d5, d7, e1, e2, e4, e9, f10, f4, f6, f7, g1, g10, g2, g3, g6, g7, h10, h2, h3, h4, h8, h9, i10, i4, i5, i7, i9, j1, j2, j5, j6, j7, j8, j9", 
				"a2, a6, a7, a9, b7, b9, c6, d1, d10, d2, d3, d4, d8, e1, e9, f1, f2, f3, f5, g10, g9, h7, i10, i2, i5, i6, i9, j1, j2, j4, j6", 
				"a2, a4, a5, a7, a8, b3, b5, b6, c1, c3, c7, c8, d7, d8, d9, e10, e4, e5, e6, e9, f1, f10, f5, f6, f8, g1, g2, g5, g6, h10, h2, h3, h4, h5, h7, h9, i2, i3, i4, i5, i6, i9, j2, j4, j8, j9", 
				"a1, a10, a2, a6, a8, b1, b2, b3, b4, b5, b6, b8, b9, c10, c3, c4, c5, c7, d10, d2, d3, d6, d7, d9, e6, e9, f2, f4, f5, f7, f8, g10, g6, g7, g9, h1, h3, h4, h5, h8, i10, i3, i4, i5, i6, i7, i8, i9, j5, j9", 
				"a10, a2, a3, a7, b10, b3, b4, b5, b7, c1, c10, c7, d10, d4, d6, d8, d9, e2, e8, e9, g1, g2, g4, g5, g9, h3, h5, i3, i8, j1, j3, j4, j8"
		}, new String[]{
				".00...000.", 
				".0...0.000", 
				".0.0.0..00", 
				"....00.0.0", 
				".00.0000.0", 
				"0.0..00000", 
				"00.0.0000.", 
				"00000.....", 
				"..0.0000.0", 
				"0.0000..0."
		}), is(true));


		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{
				"b1, b2, b3, b4, c1, c10, c3, c4, c6, c8, d2, d4, d7, d8, e1, e10, e3, e6, e7, e8, e9, f3, f4, f5, f6, g10, g2, g7, h10, h2, h3, h7, i1, i10, i2, i6, i7, j10, j3, j8", 
				"a2, a5, a6, a8, b10, b3, b5, b6, b7, b8, b9, c1, c2, c4, c5, c9, d10, d4, d6, d8, d9, e2, e6, e7, e9, f10, f2, f5, g1, g10, g4, g5, g6, g8, h1, h10, h2, h3, h7, h8, h9, i3, i5, i8, j1, j10, j3, j4, j7, j8", 
				"a10, a5, a6, a9, b1, b10, b2, b3, b6, b7, b9, c7, c8, d6, d7, d8, d9, e1, e10, e2, e4, e8, f3, f4, f5, f7, f8, f9, g10, g2, g7, g8, h10, h6, h9, i1, i3, i4, i5, i7, i8, i9, j1, j10, j2, j3, j5, j7, j9", 
				"a1, a10, a4, a6, a7, b1, b5, b7, b8, c1, c2, c5, c6, c7, d1, d3, d4, d5, d6, d8, d9, e1, e10, e2, e5, e6, f6, f7, f8, f9, g1, g10, g8, g9, h3, h4, h8, i10, i2, i3, i4, i8, i9, j1, j10, j2, j4, j7", 
				"a1, a10, a2, a5, a6, a7, a8, a9, b1, b2, b3, b4, b5, b7, b8, b9, c1, c2, c4, c5, c6, c7, c8, c9, d10, d2, d3, d4, d5, d9, e1, e10, e2, e3, e4, e5, e6, e9, f1, f10, f2, f3, f7, f8, g1, g10, g2, g3, g4, g5, g6, g7, h1, h10, h2, h5, h6, h7, h8, i1, i10, i2, i4, i5, i7, i9, j1, j10, j2, j4, j5, j6, j7, j8", 
				"a7, c5, c7, e9, f5, g2, g7, h10, h2, i2, i3, i5, i6", 
				"a10, a2, a4, a5, a6, a8, b10, b2, b3, b4, b5, b9, c1, c10, c3, c4, c5, c6, c9, d1, d10, d2, d3, d4, d6, d8, e10, e6, e7, e8, f2, f5, f6, g1, g4, g5, g7, g8, h1, h10, h2, h4, h6, h7, i10, i2, i3, i5, i6, i8, j1, j2, j4, j7, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a9, b1, b10, b2, b3, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c7, c9, d1, d2, d3, d4, d5, d7, d8, d9, e1, e10, e2, e3, e6, e7, e8, e9, f1, f10, f3, f5, f6, f7, f8, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h5, h6, h7, h8, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a5, a6, b1, b10, b3, b7, b8, b9, c1, c10, c2, c4, c6, c9, d10, d2, d4, d5, d6, d7, d9, e1, e10, e2, e5, e6, e8, e9, f1, f10, f2, f4, f5, f6, f7, f8, f9, g2, g5, g6, g7, g8, g9, h1, h10, h3, h5, h7, h8, h9, i10, i2, i3, i5, i6, i8, i9, j1, j2, j4, j5, j6, j8", 
				"a1, a10, a2, a4, a8, b1, b10, b3, b5, b7, b9, c1, c2, c3, c4, c9, d1, d2, d3, d4, d5, d9, e1, e10, e4, e6, e8, e9, f10, f4, f5, f8, f9, g10, g2, g3, g4, g5, g6, g8, g9, h10, h5, h6, h7, h8, h9, i1, i10, i4, i6, i7, j10, j2, j4, j5, j6, j8", 
				"a1, a2, a3, a4, a6, b1, b5, b6, b7, b9, c10, c3, c4, c5, c8, d10, d3, d5, d6, d9, e10, e2, e4, e7, e9, f10, f2, f4, f6, f8, f9, g10, g2, g4, g5, g9, h4, h5, h7, i1, i4, i6, i8, j7, j8", 
				"a10, a3, a4, a7, a8, b1, b4, b6, b7, b8, b9, c1, c2, c3, c5, c8, c9, d5, d6, d8, e2, e5, e6, e7, e9, f1, f10, f2, f4, f7, f8, f9, g1, g2, g4, g6, g7, h1, h5, h6, h7, h8, i1, i10, i2, i3, i6, i8, i9, j1, j10, j2, j3, j4, j5, j7, j8, j9", 
				"a1, a4, a5, a6, a9, b1, b10, b2, b3, b4, b5, c1, c10, c3, c5, c6, c7, c9, d1, d5, d7, d8, e2, e3, e6, e8, f4, f5, f6, f7, g5, g6, g9, h3, h6, h7, h9, i1, i2, i5, i7, i8, i9, j1, j3, j6", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b2, b3, b5, b6, b7, c1, c2, c3, c5, c6, c7, c9, d10, d2, d3, d4, d6, d7, e1, e3, e5, e6, e7, e8, e9, f1, f2, f7, g1, g10, g3, g4, g5, g6, g8, h3, h4, h6, h9, i10, i2, i4, i6, i7, j1, j2, j3, j4, j7, j9", 
				"a1, a10, a2, a4, a6, a7, b1, b4, b6, b9, c1, c4, c5, c6, c8, d1, d5, d7, d9, e6, e7, e8, e9, f10, f2, f3, f4, f8, g10, g2, g5, g6, g7, g9, h1, h2, h3, h4, h5, h7, h9, i1, i10, i3, i7, i8, i9, j2, j7", 
				"a2, a3, a4, a6, a7, b1, b10, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c9, d2, d3, d5, d8, e1, e2, e3, e4, e5, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f9, g2, g3, g5, g6, g7, g8, g9, h1, h10, h4, h5, h7, h8, i1, i10, i2, i3, i4, i5, i7, i8, j1, j10, j2, j3, j6, j7, j8", 
				"a1, a6, b10, b2, b3, b4, c10, c2, c3, c5, c6, c7, c8, d3, d6, d8, e5, e8, f2, f3, f6, f7, f9, g10, g3, g4, g5, h10, h5, h8, h9, i1, i10, i2, i3, i4, i7, i8, i9, j1, j10, j3, j4, j5, j6, j9", 
				"a1, a10, a2, a3, a4, a5, a8, a9, b1, b10, b2, b3, b4, b5, b7, b9, c10, c2, c3, c4, c6, c7, c8, d1, d3, d4, d5, d7, e2, e3, e9, f10, f5, f7, g10, g2, g7, g8, h1, h3, h4, h6, h7, h9, i1, i2, i3, i4, i5, i9, j2, j3, j4, j9", 
				"a1, a10, a2, a3, b1, b10, b2, b4, b6, b8, b9, c1, c2, c3, c4, c7, c8, c9, d1, d3, d9, e1, e2, e3, e6, e8, e9, f10, f5, g2, g3, g4, g5, g6, h1, h2, h4, h6, h7, i2, i3, i9, j1, j3, j4, j5, j6, j7, j8, j9", 
				"a2, a6, a8, b8, b9, c1, c2, c3, c4, c5, c7, d3, d8, e4, e6, f8, g10, g5, g6, h1, i10", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a3, a8, b1, b8, b9, d10, d6, e4, f3, f6, f7, g10, g2, g4, g7, h3, h5, h7, j8", 
				"a1, a2, a3, a8, b1, b2, b5, b6, b7, b8, b9, c6, c7, c8, d10, d3, d5, d7, e1, e3, e4, e8, e9, f1, f3, f5, g10, g5, g7, g8, h1, h10, h3, h5, h6, h7, i3, i6, j1, j2, j4, j5, j6, j8", 
				"a10, a3, a4, a8, b10, b5, b7, b9, c2, c4, c6, c7, c8, c9, d2, d4, d5, d6, d7, e10, e2, e3, e4, e5, e7, e9, f10, f2, f3, f5, f6, f8, f9, g10, g2, g8, h10, h2, h4, h9, i1, i2, i3, i6, i8, i9, j2, j3, j8", 
				"a1, a4, a5, a7, a9, b5, b7, c2, c4, c5, c6, c7, c8, c9, d10, d3, d6, d7, d8, d9, e1, e2, e3, e5, e7, e8, e9, f1, f2, f3, f4, f6, f9, g10, g2, g3, g6, g8, g9, h1, h3, h4, h6, h7, h8, i2, i6, i7, j3, j6, j8, j9", 
				"a1, a10, a8, b1, b10, b2, b4, b6, b8, c1, c10, c3, c5, c7, c8, d1, d3, d6, d7, d8, e3, e6, e9, f10, f4, f6, f8, g1, g10, g2, g4, g5, g7, g9, h1, h2, h4, h6, h7, h8, h9, i1, i10, i3, i4, i5, i7, i8, i9, j1, j3, j4, j7, j8", 
				"a10, a3, a4, a6, a7, a8, a9, b1, b3, b6, b7, b9, c1, c4, c5, c6, c7, c8, d2, d5, e1, e3, e5, e6, e8, f2, f4, f7, f8, f9, g6, g7, g8, h1, h10, h4, i1, i10, i2, i4, i7, j1, j2, j3, j4, j5, j6, j9", 
				"a1, a10, a3, a4, a7, a8, b1, b10, b2, b6, b7, b9, c1, c10, c2, c3, c5, c6, c7, c8, c9, d10, d2, d3, d5, d6, d7, d8, d9, e1, e10, e3, e4, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f7, f8, f9, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h8, h9, i1, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a4, a7, a9, b2, b6, b7, b8, b9, c2, c3, c4, c7, d2, d3, d4, d5, e1, e2, e3, e4, e5, e6, f2, f4, f5, f7, f9, g1, g10, g3, h10, h2, h3, h4, h5, h7, i1, i3, i4, i5, i7, j1, j3, j4, j6, j7", 
				"a2, a3, a4, a5, a6, a7, a8, b10, b2, b3, b4, c1, c2, c4, c6, c7, c9, d2, d6, d7, d8, e1, e10, e3, e4, e5, e6, e7, e9, f1, f2, f3, f4, g2, g3, g6, g7, g9, h3, h6, h8, i1, i10, i5, i6, j10, j2, j5, j6, j9", 
				"a1, a10, a2, a4, a6, a7, a8, a9, b1, b10, b2, b4, b7, b8, c10, c2, c4, c5, c6, c7, c8, c9, d2, d3, d4, d5, d6, d7, d8, e4, e6, e8, f1, f10, f3, f4, f5, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, h1, h10, h3, h5, h6, h7, h9, i3, i6, i9, j10, j2, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a2, a3, a4, b2, b7, b8, b9, c2, c5, c6, d3, d6, d7, e2, e5, f10, f2, f7, g10, g2, g3, g9, h1, h10, h2, h3, h7, i3, i4, i7, j3, j8, j9", 
				"a2, a4, a6, a8, b10, b3, b4, b6, b7, c1, c10, c7, d2, d3, d4, d5, e10, e2, e3, e4, e6, e7, e9, f1, f4, f5, g1, g10, g4, g5, g7, g8, g9, h1, h10, h2, h5, h6, i1, i10, i2, i4, i5, i6, i7, j1, j10, j2, j3, j4, j5, j6", 
				"a10, a2, a6, a7, b1, b2, b6, b8, c10, c2, c3, c9, d2, d3, d4, d7, d8, e1, e10, e6, e7, e8, f1, f2, f4, f6, f7, f9, g10, g3, g7, g8, g9, h5, h6, h7, h8, h9, i1, i4, i5, i6, i8, j1, j10, j2, j3, j5, j6, j8, j9", 
				"a1, a9, b1, b5, c1, c10, c2, c4, c5, c7, c8, c9, d10, d5, d6, e10, e5, e7, f10, f3, f4, f5, f9, g1, g10, g2, g3, g4, h1, h2, h3, h6, h7, i10, i4, i8, j1, j2, j4, j6, j7", 
				"a1, a4, a6, a7, a8, a9, b4, b6, b7, b9, c1, c10, c2, c5, c7, d2, d5, d6, d9, e1, e2, e3, e5, e6, e9, f1, f2, f4, f7, f9, g10, g2, g5, g7, g8, h1, h2, h7, h8, i1, i2, i3, i4, i5, i6, i7, i8, j1, j2, j4, j5, j6, j9", 
				"a1, a10, a3, a4, a7, a9, b2, b3, b4, b5, b6, b7, b8, b9, c1, c2, c3, c4, c5, c6, c7, c9, d2, d3, d4, d6, d9, e2, e3, e5, e7, e8, f3, f5, f8, g4, g7, h1, h10, h2, h3, h5, h6, h7, h8, h9, i2, i3, i4, i6, i7, i8, i9, j1, j3, j4, j6, j8, j9", 
				"c8, i4, j4", 
				"a10, a2, a3, a4, a9, b10, b3, b4, b5, b6, b7, b8, c2, c4, c5, c6, c7, c8, d1, d2, d3, d5, d6, d8, e10, e3, e4, e6, f2, f3, f7, f8, f9, g10, g3, g4, g6, g8, g9, h1, h3, h5, h7, i3, i5, i7, i8, j10, j3, j4, j5, j7, j9", 
				"a10, a4, a6, a7, b1, b9, c1, c3, c5, c6, c7, c8, d2, d3, d4, d9, e1, e6, f1, f4, f5, g1, g3, g6, g7, g9, h2, h4, h5, h6, h9, i10, i3, i5, i7, i9, j1, j3, j4, j5, j6, j7, j8", 
				"a1, a10, a3, a4, a5, a6, a7, a9, b1, b10, b2, b4, b5, b6, b8, b9, c1, c10, c2, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f8, g1, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, i1, i10, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j9", 
				"a1, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b6, b7, b9, c10, c2, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e5, e6, e8, e9, f1, f10, f2, f5, f6, f7, f8, f9, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h6, h7, h8, i1, i10, i4, i5, i8, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a2, a4, a5, a6, b1, b10, b3, b5, c2, c3, c4, c5, c6, c7, d1, d10, d3, d6, d8, e10, e4, e5, e6, e7, e8, e9, f1, f2, f4, f6, f7, f8, f9, g10, g3, g5, g6, g7, g8, g9, h10, h2, h5, h7, h9, i2, i3, i5, i6, i7, j10, j4, j5", 
				"a1, a2, a4, a6, a8, a9, b1, b3, b4, b5, b7, b8, b9, c1, c5, c6, c7, c9, d1, d10, d3, d4, d5, d6, d7, d9, e10, e3, e4, e5, e6, e9, f1, f10, f2, f3, f6, f9, g1, g2, g3, g6, h10, h5, h6, h7, i10, i2, i4, i5, i6, i9, j1, j2, j5, j6, j7, j9", 
				"c10", 
				"a1, a10, a3, a5, a6, b1, b10, b4, b9, c1, c8, d1, d2, d5, d6, d8, e1, e2, e3, e6, f10, f3, f5, f6, f7, f9, g10, g2, g3, g5, g6, g8, g9, h1, h2, h8, i2, i4, i6, i8, i9, j2, j6, j7, j9", 
				"a1, a4, a5, a8, b1, b8, c3, c5, c8, c9, d1, d3, d4, d6, d7, d9, e10, e2, e3, e9, f10, f2, f3, f4, f5, f6, f7, g1, g5, g6, g7, g9, h2, h3, h7, i10, i2, i5, i6, i7, i8, j3, j7, j9", 
				"a3, a5, a6, a8, b1, b5, b6, c3, d10, d7, d8, e2, f2, g4, h10, h5, i4, i6, j10, j2, j6", 
				"a4, a7, a9, b3, b6, b7, b9, c10, c3, c5, c7, c8, d1, d4, d6, d7, d8, e1, e10, e2, e3, e4, e5, e7, e9, f1, f10, f2, f6, g2, g4, g6, h1, h2, h6, h8, h9, i1, i10, i4, i9, j1, j10, j4, j6", 
				"a1, a2, a4, a8, b1, b10, b2, b3, b8, c1, c10, c3, c5, c6, c8, c9, d1, d10, d2, d4, d5, d8, d9, e2, e5, e7, e9, f2, f3, f4, f7, g10, g3, g4, g5, g6, g7, h1, h2, h3, h4, h9, i1, i10, i4, i6, i8, j10, j3, j8, j9", 
				"a1, a10, a4, a6, a9, b1, b2, b5, b6, b7, b9, c10, c3, c5, c7, c8, d1, d3, d4, d7, d8, e10, e2, e6, e7, e8, e9, f10, f5, f6, f8, f9, g2, g5, g6, g7, h2, h4, h9, i10, i3, i4, i5, i7, i8, j3, j6, j7", 
				"a10, a2, a3, a4, a6, a7, a9, b2, b4, b5, b6, b7, c1, c2, c3, c5, c7, c8, c9, d2, d3, d5, d9, e1, e10, e2, e4, e6, e8, e9, f1, f10, f2, f4, f7, f8, g1, g4, g6, g9, h3, h7, h8, h9, i1, i4, i5, j2, j3, j5, j6", 
				"a10, a5, a6, a8, b1, b2, b5, b8, b9, c1, c3, c5, c7, c8, d1, d10, d5, d7, d8, e2, e3, e4, e5, e7, e8, f1, f2, f6, f9, g10, g7, g8, g9, h1, h2, h5, h7, h8, i3, i5, i9, j1, j4, j5, j7, j8, j9", 
				"a1, a10, a2, a5, a7, b1, b3, b4, b7, b9, c10, c2, c4, c8, c9, d1, d6, d9, e1, e8, f10, f2, f5, g1, g10, g5, g6, g8, h10, h2, h5, h7, h9, i1, i10, i2, i4, i5, i6, i9", 
				"j2, j6", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i5, i6, i7, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a5, a6, a7, a8, a9, b1, b10, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, e1, e10, e2, e4, e6, e7, e8, e9, f1, f10, f2, f4, f5, f6, f7, f8, f9, g1, g10, g3, g4, g5, g6, g7, g8, g9, h10, h2, h3, h4, h5, h6, h7, h8, i1, i10, i3, i4, i5, i6, i8, i9, j1, j10, j2, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b5, b7, b8, c10, c2, c5, c8, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e4, e5, e6, e7, e8, e9, f1, f3, f4, f5, f6, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h6, h7, h9, i1, i10, i2, i3, i6, i8, i9, j1, j10, j2, j3, j4, j5, j7, j8, j9", 
				"a1, a10, a2, a4, a5, a7, a8, a9, b1, b2, b5, b7, b8, b9, c10, c3, c5, c6, c7, c8, d1, d10, d3, d4, d5, d6, d8, e10, e4, e5, e8, f10, f2, f4, f5, g1, g2, g3, g4, g6, g8, g9, h10, h3, h4, h5, h7, h8, i1, i10, i2, i3, i5, i7, i9, j1, j3, j5, j9", 
				"a6, b1, b2, b3, b4, b9, c1, c6, c8, c9, d1, d7, d8, d9, e10, e2, e4, e6, e7, e8, f10, f2, f4, f9, g10, g4, g6, g9, h3, h7, i3, i5, j1, j10, j8", 
				"a1, a10, a3, a4, b1, b7, b8, c10, d7, d8, e1, e10, e2, e3, e5, e8, e9, f1, f5, f6, f7, f9, g1, g10, g3, g4, g9, h10, h2, h3, h4, h5, h6, h7, h8, h9, i2, i3, i4, i6, i8, j1, j2, j7, j8, j9", 
				"a1, a10, a3, a4, a6, a7, a8, b10, b5, b6, b8, c10, c2, c3, c6, c8, d3, d5, d6, d7, d8, d9, e3, e5, f1, f8, g10, g6, g7, g8, h2, h5, h7, i2, i3, i5, i6, i7, i9, j10, j4, j5, j6, j8, j9", 
				"a1, a10, a2, a4, a5, a6, a7, a8, b1, b10, b4, b5, b6, b7, b8, b9, c10, c2, c3, c5, c7, c8, c9, d1, d2, d3, d5, d9, e1, e10, e4, e5, e7, e8, e9, f1, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g7, g8, h1, h10, h2, h3, h5, h6, h8, h9, i10, i2, i3, i4, i6, i7, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a4, a5, a6, b1, b3, c1, c4, c7, c8, d9, e2, e3, e9, f6, f8, g2, g3, g5, g6, g7, h1, h2, h6, i2, i4, i7, j2, j3, j4, j5, j9", 
				"a10, a4, a8, a9, b2, b3, b4, b7, b9, c1, c10, c2, c3, c4, c6, c7, d1, d3, d5, d6, d9, e1, e4, e5, e8, f10, f3, f4, f8, f9, g1, g5, h1, h10, h3, h5, h6, h7, h8, h9, i10, i2, i3, i4, i6, i7, i8, i9, j1, j2, j3, j4, j5, j6, j9", 
				"a1, a10, a4, a5, a7, a8, b1, b10, b2, b3, b4, b7, b8, c1, c10, c4, c7, c8, c9, d10, d2, d4, d5, d6, d7, d8, d9, e1, e10, e2, e4, e5, e6, e7, e8, e9, f1, f2, f5, f7, f8, f9, g10, g2, g4, g5, g6, g7, g8, g9, h4, h5, h7, h8, i1, i10, i2, i3, i4, i5, i8, i9, j1, j10, j2, j3, j4, j6, j7, j8, j9", 
				"a1, a3, a4, b4, b5, b9, c10, c5, c6, d2, d3, d6, e10, e5, e7, e8, f3, f7, f9, g1, g4, h1, h6, h8, i4, i7, j6", 
				"a10, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c6, c7, c8, c9, d1, d10, d4, d5, d6, d7, d9, e1, e10, e2, e3, e5, e6, e7, e8, f10, f2, f3, f4, f5, f6, f7, f9, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i10, i2, i4, i5, i6, i7, j1, j10, j2, j3, j4, j5, j6, j7, j9", 
				"a2, a5, a6, a7, b1, b2, b5, b6, b7, b8, b9, c2, c3, c4, c6, c7, c9, d1, d10, d2, d7, e1, e10, e2, e3, e4, e8, e9, f1, f2, f3, f4, f6, f7, f8, g1, g10, g3, g4, g5, h1, h8, h9, i1, i4, i5, j1, j3, j5, j6, j7, j8, j9", 
				"a10, a6, a7, b4, b5, b6, b7, b9, c5, c7, c8, d2, d8, e4, f1, f10, f2, f3, f4, f5, f6, f9, g3, g5, h1, h10, h7, h8, h9, i1, i2, i3, i5, i8, j6, j8", 
				"a1, a10, a2, a4, a5, a6, b10, b2, b3, b5, c1, c10, c3, c5, c6, c7, d1, d2, d3, d5, d6, d7, e10, e2, e5, e7, e8, f1, f2, f3, f7, f8, f9, g1, g10, g2, g3, g6, g7, g8, h2, h3, h5, h6, h7, h8, h9, i1, i10, i3, i4, i7, i9, j1, j10, j3, j4, j5, j6", 
				"a1, a2, a3, a5, a6, a9, b1, b10, b2, b3, b4, b8, b9, c1, c3, c4, c7, c8, c9, d2, d4, d5, d6, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f3, f6, f7, f8, g10, g2, g4, g6, g8, h1, h2, h4, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j10, j2, j4, j5, j6, j7, j8", 
				"a2, a3, a4, a7, a8, b10, b5, b6, b9, c10, c2, c6, d1, d2, d3, d4, d6, d7, d8, e3, e4, e6, e7, e9, f1, f10, f3, f4, f6, f7, g10, g2, g3, g6, g7, h1, h10, h3, h5, h7, i1, i2, i3, i4, i6, i8, j1, j2, j6, j8", 
				"a10, a3, a7, a8, b10, b2, b5, b6, c10, c2, c4, c7, c9, d4, d5, d7, d9, e10, e3, e6, f2, f3, f4, g1, g10, g4, g5, g7, g8, h1, h4, h6, h8, h9, i1, i2, i5, i6, i7, i9, j1, j6, j7, j8, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a4, a7, b1, b2, b5, b6, b8, c2, c6, c9, d10, d3, d6, d8, d9, e3, e7, e8, f1, f10, f2, f3, f7, f9, g1, g10, g5, g8, h1, h2, h3, h5, h6, h7, i1, i4, i7, i8, j1, j10, j3, j4, j6, j8, j9", 
				"a1, a10, a2, a4, a5, a7, a9, b10, b2, b3, b6, b7, b8, c1, c10, c2, c4, c7, c8, d10, d2, d3, d4, e1, e2, e5, e6, e7, f2, f6, g1, g10, g2, g3, g8, g9, h5, h6, h7, h9, i10, i3, i8, i9, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a10, a8, a9, b1, b10, b2, b5, b6, b9, c1, c3, c6, c7, c8, d3, d4, d6, d8, d9, e1, e4, e5, e7, e9, f2, f3, f4, f5, f6, f8, g2, g5, g6, h10, h6, i2, i7, j1, j10, j3, j4, j5, j7", 
				"a4, b1, b4, b7, c6, c9, d5, d6, d7, e1, e5, e6, e7, f10, f2, f8, f9, g10, g5, g6, i1, i7, i8, i9, j2, j7, j9", 
				"a1, a10, a3, a4, a8, a9, b1, b10, b2, b6, b7, b9, c10, c2, c6, c9, d10, d4, d7, d9, e4, e5, e6, e7, e8, e9, f1, f4, f5, f6, f9, g3, g4, g6, g7, g8, g9, h3, h4, h8, h9, i1, i10, i2, i3, i7, i8, j1, j10, j2, j4, j8, j9", 
				"a10, a2, b7, c1, c3, d10, d6, e2, e8, e9, g1, h5, i9, j1", 
				"a1, a10, a2, a8, b1, b10, b2, b3, b5, b6, b8, c1, c3, c4, c5, c7, c8, c9, d1, d2, d3, d4, d6, d8, d9, e2, e3, e7, e8, f1, f10, f3, f4, f5, f6, f9, g1, g4, g5, g9, h1, h10, h7, i1, i10, i2, i3, i4, i5, i6, i7, j3, j9", 
				"b9, c3, d6, g1, g10, h5, h8, j2", 
				"a10, a4, a5, a7, b2, b3, b4, b7, b8, b9, c6, d1, d10, d5, d6, d8, e10, e2, e4, e8, f2, f8, g10, g4, g8, h1, h2, h8, h9, i1, i2, i3, i7, j1, j2, j4, j6, j8", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b8, b9, c1, c10, c2, c3, c4, c5, c6, c7, c8, c9, d1, d10, d2, d3, d4, d5, d6, d7, d8, d9, e1, e10, e2, e3, e4, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f5, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g8, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i8, i9, j1, j10, j2, j4, j5, j6, j7, j8, j9", 
				"a2, a4, a5, b10, b2, b3, b5, b8, b9, c1, c2, c5, c8, c9, d4, d5, d6, e10, e2, e3, e4, e6, f1, f3, f6, f8, f9, g1, g10, g2, g3, g5, g6, g7, g8, h4, h6, h7, h8, i1, i2, i3, i4, i7, i9, j1, j3, j5, j6, j7, j8, j9", 
				"a1, a10, a2, a5, a6, a7, a9, b1, b10, b2, b3, b4, b5, b6, b7, c1, c10, c2, c3, c4, c5, c6, c8, d1, d10, d2, d3, d4, d5, d6, d8, d9, e1, e10, e2, e3, e5, e7, e8, f1, f10, f3, f4, f5, f6, f7, f8, f9, g1, g10, g3, g4, g5, g6, g7, g8, g9, h3, h4, h6, h7, h9, i1, i2, i4, i5, i7, i8, i9, j10, j2, j3, j4, j5, j6, j8, j9", 
				"a10, a6, a8, b1, b10, b2, b3, b5, b6, b7, b8, c1, c3, c6, c8, c9, d1, d2, d3, d7, d8, e3, e4, e6, e7, e8, f1, f10, f3, f4, g3, g5, g6, g8, g9, h1, h2, h6, h8, i1, i10, i2, i5, i6, i7, i8, i9, j10, j2, j3, j5, j7, j9", 
				"a1, a10, a2, a3, a4, a5, a6, a7, a8, a9, b1, b10, b2, b3, b4, b5, b6, b7, b9, c1, c10, c2, c3, c4, c6, c8, d1, d10, d2, d3, d4, d5, d6, d7, d8, e1, e10, e2, e3, e5, e6, e7, e8, e9, f1, f10, f2, f3, f4, f6, f7, f8, f9, g1, g10, g2, g3, g4, g5, g6, g7, g9, h1, h10, h2, h3, h4, h5, h6, h7, h8, h9, i1, i10, i2, i3, i4, i5, i6, i7, i9, j1, j10, j2, j3, j4, j5, j6, j7, j8, j9", 
				"a1, a3, a4, a5, a6, b1, b10, b2, b5, b6, b7, b9, c1, c10, c4, c6, c7, c8, c9, d10, d2, d4, d5, d6, d7, d9, e6, e8, e9, f10, f4, f8, g1, g10, g3, g4, g7, g8, g9, h1, h3, h5, h8, i1, i10, i3, i8, i9, j1, j2, j3, j4, j5, j6, j7, j9", 
				"a10, a2, a4, a5, a6, a7, b1, b2, b3, b4, b7, b8, b9, c10, c2, c3, c6, c7, d1, d3, d4, d8, d9, e1, e10, e5, e6, e8, f1, f10, f3, f4, f5, f8, g10, g4, g8, h1, h10, h4, h6, h8, i10, i2, i3, i4, i5, i6, i9, j1, j10, j4, j5, j7", 
				"a3, b9, c10, c3, c6, c9, d1, d10, d8, f1, f10, f3, f5, g9, h4, h5, i1, i2, i3, i4, i5, i9, j4, j6, j7, j9", 
				"a5, a6, a9, b1, b3, c10, c3, c5, c6, c9, d10, d5, d8, d9, e1, e4, e5, e7, e9, f2, f4, f8, g1, g10, g3, g5, g8, i10, j1, j10, j2, j3, j4, j5, j6", 
				"a1, a2, a4, a7, a8, b2, b5, b6, b7, b8, c10, c2, c8, c9, d1, d4, d5, d7, e1, e2, e4, e9, f10, f4, f6, f7, g1, g10, g2, g3, g6, g7, h10, h2, h3, h4, h8, h9, i10, i4, i5, i7, i9, j1, j2, j5, j6, j7, j8, j9", 
				"a2, a6, a7, a9, b7, b9, c6, d1, d10, d2, d3, d4, d8, e1, e9, f1, f2, f3, f5, g10, g9, h7, i10, i2, i5, i6, i9, j1, j2, j4, j6", 
				"a2, a4, a5, a7, a8, b3, b5, b6, c1, c3, c7, c8, d7, d8, d9, e10, e4, e5, e6, e9, f1, f10, f5, f6, f8, g1, g2, g5, g6, h10, h2, h3, h4, h5, h7, h9, i2, i3, i4, i5, i6, i9, j2, j4, j8, j9", 
				"a1, a10, a2, a6, a8, b1, b2, b3, b4, b5, b6, b8, b9, c10, c3, c4, c5, c7, d10, d2, d3, d6, d7, d9, e6, e9, f2, f4, f5, f7, f8, g10, g6, g7, g9, h1, h3, h4, h5, h8, i10, i3, i4, i5, i6, i7, i8, i9, j5, j9", 
				"a10, a2, a3, a7, b10, b3, b4, b5, b7, c1, c10, c7, d10, d4, d6, d8, d9, e2, e8, e9, g1, g2, g4, g5, g9, h3, h5, i3, i8, j1, j3, j4, j8"
		}, new String[]{
				".00...000.", 
				".0...0.000", 
				".0.0.0..00", 
				"....00.0.0", 
				".00.0000.0", 
				"0.0..00000", 
				"00.0.0000.", 
				"00000.....", 
				"..0.0000.0", 
				"0.0000..0."
		}), is(true));

		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{				
				"b2, c2", 
				"a3, b2", 
				"a3, b2, c2, a1, c1", 
				"c2, a1, c1"
		}, new String[]{
				"0..", 
				"..0", 
				"..."
		}), is(true));



		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{				
				"a1, b1, b3, d3", 
				"a1, b1, c2, d4", 
				"a1, b1, b3, d3",
				"b3, c2, c4", 
				"c2, c4, d3", 
				"b3, c2, d3, d4", 
				"a1, b1"
		}, new String[]{
				"...0", 
				"....", 
				"..0.", 
				"...."
		}), is(true));




		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{
				"a1, b1, b3, d3", 
				"a1, b1, c2, d4", 
				"a1, b1, b3, d3", 
				"b3, c2, c4", 
				"c2, c4, d3", 
				"b3, c2, d3, d4", 
				"a1, b1"
		}, new String[]{
				"...0", 
				"....", 
				"..0.", 
				"0..."
		}), is(false));


		assertThat(new SwitchingTumblersGaussian().switchingTumblers(new String[]{
				"a7, d5, e1, f7, g1", 
				"c3, d9, f8, g9, i8", 
				"a1, a3, a6, a7, a8, b10, b6, c1, c10, c3, c5, c9, d2, d3, d6, d7, e7, e8, f2, f3, f5, f7, f8, g1, g3, g7, g8, g9, h3, h7, i4, j3, j4, j9", 
				"c8, d3, f6, g8, i3, j10, j5", 
				"a1, a4, a6, b9, c6, c7, c9, d2, d6, d7, e8, f1, f10, f2, f3, f5, f7, g1, g7, g9, h3, h4, h7, h9, j10, j3, j4, j8", 
				"a7, a8, b5, c1, c10, c7, c9, d1, d2, d3, e10, e8, f1, f6, f9, g1, g8, h10, h2, h3, h5, i3, i4, j3, j9", 
				"b9, c3, c5, c7, c9, d2, e10, g7, i2, j9", 
				"a4, b5, b9, c5, c6, c7, c8, c9, d10, d2, d3, d7, d9, e10, f10, f3, f7, f8, f9, g7, g8, g9, h10, h3, h5, i4, i8, j1, j5, j8", 
				"a1, a3, a6, a7, a8, b10, c1, c10, c3, c5, c7, c9, d2, d3, d7, e8, f2, f3, f5, f7, f8, g1, g3, g4, g8, g9, h3, h7, i4, j3, j4, j9", 
				"b6, c7, d6, e7, g4, g7", 
				"d5, e8, f3, h2, h4, j3", 
				"a1, a4, a6, a8, b6, b9, c10, c6, c7, d3, d5, d7, e7, f1, f10, f5, f6, f7, f9, g1, g7, g8, h10, h2, h3, h4, h5, h7, i2, j10, j4, j9", 
				"a4, a7, b5, b6, b9, c3, c6, c9, d1, d2, d6, d9, e10, e7, f1, f10, f6, f8, g9, h3, i8, j10, j8", 
				"a7, e1, f1, f3, f7, g7, i3, i4", 
				"a3, a7, a8, b10, b9, c10, c8, d1, d10, d5, d6, d7, e1, e10, f1, f2, f3, f8, g1, g3, g9, h4, j1, j10, j5, j8", 
				"e10, f2, f6, g9, h9", 
				"a3, a4, a7, b10, b5, b6, c6, c7, d1, e1, e7, e8, f1, f10, f3, f6, f7, f8, f9, g1, g3, h10, h2, h3, h4, h5, h9, i2, i4, j10, j3, j8, j9", 
				"a3, a7, a8, b10, b5, b6, c1, c10, c3, c5, c8, c9, d10, d2, d7, e10, e7, e8, f3, f7, f8, g1, g3, g7, h2, h3, h9, i3, i4, j1, j10, j3, j5, j9", 
				"a8, b9, c10, d3, g4, g7, g8, h4, j8", 
				"a1, a3, a4, a6, a8, b10, b5, c1, c10, c5, c6, c7, c9, d1, d2, d3, d7, d9, e10, e8, f1, f10, f3, f5, f7, f9, g1, g3, g7, g8, g9, h10, h5, h7, h9, i2, i4, i8, j10, j3, j4, j8", 
				"a1, a4, a6, a7, b6, c3, c6, c7, c8, d1, d10, d3, d9, e7, f1, f10, f5, f8, f9, g8, g9, h10, h2, h3, h5, h7, i2, i3, i8, j1, j4, j5, j8, j9", 
				"a3, a4, a7, b10, b6, b9, c1, c6, c8, c9, d2, d3, d5, e7, f10, f2, f3, f8, g1, g3, g4, g7, g8, g9, i2, j5", 
				"a3, a7, b10, d6, f8, g3, h9, i4", 
				"a1, a6, b5, d10, d6, f5, h2, h7, j1, j4", 
				"c3, c5, f9, g7, h10, h5", 
				"a1, a3, a4, a6, a7, b10, c5, c6, c8, d3, d7, d9, f10, f3, f5, f7, g3, g7, g8, g9, h2, h3, h7, h9, i2, i8, j4, j5, j8, j9", 
				"d1, d10, d7, e1, g4, j1", 
				"a7, b5, c1, d1, f6, g4, h3, i2, j8", 
				"a4, c1, c3, c5, c6, f1, f10, j10, j9", 
				"a3, a4, a7, a8, b10, b6, c1, c10, c6, c8, c9, d2, d5, e7, f10, f2, f3, f8, g1, g3, g9, h4, i2, j5, j8"
		}, new String[]{
				".0...0.0..", 
				".......0..", 
				"......0.0.", 
				"..0...0.0.", 
				"....0000..", 
				"....00.0.0", 
				"..0..0....", 
				"..00......", 
				".00..0...0", 
				".........."
		}), is(false));



		System.out.println("all cases passed");

	}
	

	boolean switchingTumblersForSubmission(String[] t, String[] p) {

		BigInteger[] a=new BigInteger[t.length];
		for (int i = 0; i < t.length; i++) {
			a[i]=new BigInteger("0");
			for (String cell:t[i].split(", ")) {
				int c=cell.charAt(0)-'a';
				int r=Integer.parseInt(cell.substring(1))-1;
				int v=r*p.length+c;
				a[i]=a[i].setBit(v);
			}			
		}

		int in=0;
		for (int i = 100; i >=0; i--) 
			for (int j = in; j < a.length; j++) 
				if(a[j].testBit(i)){
					BigInteger temp=a[in];
					a[in]=a[j];
					a[j]=temp;

					for (int b = 0; b < a.length; b++) {
						if(b!=in && a[b].testBit(i))
							a[b]=a[b].xor(a[in]);
					}
					in++;
					
					break;
				}
		
		BigInteger k=BigInteger.ZERO;
		int c=0;
		for (int i = p.length-1; i >=0; i--) 
			for (int j = 0; j < p[i].length(); j++,c++) 
				if(p[i].charAt(j)=='0')
					k=k.setBit(c);
				
			
		for (int i = 100; i >=0; i--) 
			for (int j = 0; j < a.length; j++) 
				if(a[j].testBit(i) && k.testBit(i))
					k=k.xor(a[j]);
			
		return k.equals(BigInteger.ZERO)?true:false;
	}

}
