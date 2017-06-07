package com.chawkalla.algorithms.examples.bits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Bits {

	public static void main(String[] args) {
		System.out.println(convertSign(10));
		//System.out.println(addNumbers(10, 3));

		int number=64;
		System.out.println("-4="+Integer.toBinaryString(-4) +" and 4="+Integer.toBinaryString(4));
		System.out.println("Set 3rd bit for "+Integer.toBinaryString(number)
		+" " +Integer.toBinaryString((number|1<<3)));
		System.out.println("clear 3rd bit for "+Integer.toBinaryString(number)
		+" " +Integer.toBinaryString(number&(~(1<<3))));
		System.out.println("check power of 2?"+(((number&(number-1))==0)?"true":"false"));
		System.out.println("Get power of 2^3="+(1<<3));

		System.out.println("all 1s:"+Integer.toBinaryString(~0));
		System.out.println("all 1s - 111="+Integer.toBinaryString(~0-7));
		System.out.println("set first i bits:"+Integer.toBinaryString((1<<5)-1));

		System.out.println("set first i to j bits:"+Integer.toBinaryString(((1<<8)-1)^((1<<5)-1)));

		countOnes(19);
		System.out.println("binary to integer 1000="+Integer.parseInt("1000", 2));

		System.out.println("Get ith bit="+Bits.getIthBit(5, 4));

		System.out.println("binary representation of 5="+"0b101"+"="+0b101);
		String a="10101";
		System.out.println(String.format("Getting number of the form %s = ((1<<%d))/3 = %s", a,a.length()+1, 
				Integer.toBinaryString(((1<<a.length()+1))/3)));

		System.out.println(String.format("Count longest consecutive ones for 15 n & (n<<1)= %d", 
				maxConsecutiveOnes(15)));

		System.out.println(String.format("Get lowest set bit n & ~(n-1)= 110 & 010 =10(2) =%d "
				+ "or use Integer.lowestSetBit(n)", 6 & ~(5)));

		System.out.println(String.format("Get higest set bit n= %d"
				+ " or use Integer.highestSetBit(n)", getHighestSetBit(9)));
		
		System.out.println("bitwise range AND:  0b1101 to 0b1111="+rangeBitwiseAnd(0b1101, 0b1111));
		System.out.println("All test cases passed");
	}

	public static int convertSign(int number){
		System.out.println(Integer.toBinaryString(number)+"   ~number="+Integer.toBinaryString(~number)+ " ~number+1="+Integer.toBinaryString(~number+1));
		int other=~number+1;

		return other;
	}

	public static int countOnes(int number){
		int count=0;
		int temp=number;
		while(temp!=0){
			temp=temp&temp-1;
			count++;
		}
		return count;
	}

	public static int maxConsecutiveOnes(int x)
	{

		int count = 0;

		// Count the number of iterations to
		// reach x = 0.
		while (x!=0)
		{
			// This operation reduces length
			// of every sequence of 1s by one.
			x = (x & (x << 1));

			count++;
		}

		return count;
	}

	public static int addNumbers(int a, int b){
		if(b<=0)
			return a;
		int sum=a^b;
		int carry=a&b;
		System.out.println("sum="+Integer.toBinaryString(sum)+ " carry="+Integer.toBinaryString(carry));

		return addNumbers(sum, carry);
	}

	public static char getIthBit(int n, int i){
		return (((n >> i) & 1)==1)?'1':'0';
	}
	
	public static int OneToNOr(int n){
		
		return n==0?0:1<<getHighestSetBit(n)-1;
	}
	
	public static int OneToNXOr(int a){
		/*0000 <- 0  [a]
		0001 <- 1  [1]
		0010 <- 3  [a+1]
		0011 <- 0  [0]
		0100 <- 4  [a]
		0101 <- 1  [1]
		0110 <- 7  [a+1]
		0111 <- 0  [0]*/
		int[] r=new int[]{a,1,0,a+1};
		return r[a%4];
	}
	
	/**
	 * for 1101 --> 1111 the answer is 1100
	 * 
	 * We just need to remove extra last 1's from n until m=n
	 * 
	 */
	public static int rangeBitwiseAnd(int m, int n) {
		
		while(m<n) n = n & (n-1);
        return n;
       /* if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;*/
    }

	public static int getHighestSetBit(int n){
		//bit smearing
		//first set all bits to the highest one 
		n |=(n >> 16);
		n |=(n >> 8);	
		n |=(n >> 4);	
		n |=(n >> 2);	
		n |=(n >> 1);	
		//now just right shift and exOr 1111 ^ 0111
		return n ^ (n >> 1);

	}
	
	public static int nextPowerOf2(int num){
        if(num ==0){
            return 1;
        }
        if(num > 0 && (num & (num-1)) == 0){
            return num;
        }
        while((num & (num-1)) > 0){
            num = num & (num-1);
        }
        return num<<1;
    }

}
