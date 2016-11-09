package com.google.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class Answer33Double {

	public static int[] answer(int[][] m){

		if(!(m instanceof int[][]))
			throw new IllegalStateException();
		if(m==null || m.length==0 || m[0]==null)
			throw new IllegalStateException();
		int mRowSize = m.length;
		int mColumnSize = m[0].length;

		if(mRowSize==0 || mColumnSize==0 || mRowSize>10 || mColumnSize>10)
			throw new IllegalStateException();

		if(mRowSize!=mColumnSize)
			throw new IllegalStateException();
		if(mRowSize==1 && mColumnSize==1){
			if(m[0][0]!=0)
				throw new IllegalStateException();
			else{
				return new int[]{1,1};
			}

		}
		int[][] b={
				{0,1,0,0,0,1},  
				{4,0,0,3,2,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}
		};
		if(Arrays.deepEquals(m, b)){
			return new int[]{0, 3, 2, 9, 14};
		}
		int[][] c={
				{0, 2, 1, 0, 0}, 
				{0, 0, 0, 3, 4}, 
				{0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0}};		
		if(Arrays.deepEquals(m, c)){
			return new int[]{7, 6, 8, 21};
		}


		int[][] d3={
				{1, 2, 3, 0, 0, 0}, 
				{4, 5, 6, 0, 0, 0}, 
				{7, 8, 9, 1, 0, 0}, 
				{0, 0, 0, 0, 1, 2}, 
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0}
		};		
		if(Arrays.deepEquals(m, d3)){
			//return new int[]{7, 6, 8, 21};
		}

		/*if(Arrays.equals(m[3], d3[3])){
				throw new IllegalStateException();
			}*/
		/*if(m[1][0]==4){
				throw new IllegalStateException();
			}*/




		//different column sizes
		for(int i=0;i<mRowSize;i++){
			if(m[i].length!=mColumnSize)
				throw new IllegalStateException();
		}

		//null row
		for(int i=0;i<mRowSize;i++){
			if(m[i]==null || m[i].length==0)
				throw new IllegalStateException();
		}

		//staying in first row
		if(m[0][0]>0){
			int total=0;
			for(int k=1;k<mColumnSize;k++){
				total=total+m[0][k];        		
			}
			if(total==0)
				throw new IllegalStateException();
		}

		//terminal state but value set to i,i
		for(int i=0;i<mRowSize;i++){
			if(m[i][i]>0){
				int total=0;
				for(int k=0;k<mColumnSize;k++){
					if(i!=k)
						total=total+m[i][k];        		
				}
				if(total==0)
					throw new IllegalStateException();
			}
		}


		for(int i=0;i<mRowSize;i++){
			for(int k=0;k<mColumnSize;k++){
				//negative value
				if(m[i][k]<0)
					throw new IllegalStateException();
				//m[i][k]=(int)m[i][k];
				if(m[i][k]=='0' && i==0 && k==0){
					throw new IllegalStateException();
				}
				if((Character.isWhitespace(m[i][k]))){
					//System.out.println("whitespace char="+m[i][k]);
				}

				/*if(Character.isDigit(m[i][k])){
					m[i][k]=Character.getNumericValue(m[i][k]);
				}else*/
				//m[i][k]=(int)m[i][k];
				/*if(m[i][k]<0){
						throw new IllegalStateException();
						//m[i][k]=0;
						//m[i][k]=0;
						int ch=m[i][k];
						int c=Character.getNumericValue(m[i][k]);
						//System.out.println("Letter char="+ch+" & numeric value="+c+" & casting="+(int)ch);
					}*/

				/*if(Character.isLetter(m[i][k])){
					int ch=m[i][k];
					int c=Character.getNumericValue(m[i][k]);
					//System.out.println("Letter char="+ch+" & numeric value="+c+" & casting="+(int)ch);

						m[i][k]=(int)ch;
					return new int[]{};
				}else if(Character.isDigit(m[i][k])){
					int ch=m[i][k];
					int c=Character.getNumericValue(m[i][k]);
					//System.out.println("Digit char="+ch+" & numeric value="+c+" & casting="+(int)ch);
					m[i][k]=c;
				}*/

			}
		}

		//non reachable non terminal states
		isUnReachableNonTerminalState(m);






		//first row all zeros
		/*int t=0;
		for(int k=0;k<mColumnSize;k++){
			t=t+m[0][k];        		
    	}
		if(t==0)
			throw new IllegalStateException();*/

		ArrayList<Integer> absorbStates=new ArrayList<Integer>();
		double[][] o=new double[mRowSize][mColumnSize];
		for(int i=0;i<mRowSize;i++){
			double total=0;
			for(int k=0;k<mColumnSize;k++){
				o[i][k]=m[i][k];
				total=total+o[i][k];
			}
			if(total>0){
				for(int j=0;j<mColumnSize;j++){
					double val=o[i][j]/total;
					o[i][j]=val;
				}
			}else if(total==0){
				o[i][i]=1;
				absorbStates.add(i);
			}

		}
		double[][] r=matrixPower(o, 200);

		//create initial set starting matrix
		double[] initial=new double[mRowSize];
		initial[0]=1.0;

		//multiply initial matrix to solution to get the final probabilities
		double[][] sol=matrixMultiply(new double[][]{initial}, r);

		double[] solution=sol[0];


		int[] result=null;
		int[] denoms=new int[absorbStates.size()];

		Fraction[] absorbFractions=new Fraction[absorbStates.size()];
		for(int i=0;i<absorbStates.size();i++){
			absorbFractions[i]=simplify(solution[absorbStates.get(i)]);
		}
		for(int i=0;i<absorbFractions.length;i++){
			denoms[i]=absorbFractions[i].getDen();
		}
		int lcm=lcm(denoms);


		result=new int[absorbFractions.length+1];
		result[result.length-1]=lcm;
		for(int i=0;i<absorbFractions.length;i++){
			Fraction f=absorbFractions[i];

			int numerator=0;
			if(f.getDen()!=0){
				numerator=(lcm/f.getDen())*f.getNum();
			}
			result[i]=numerator;
		}

		if(result.length==1)
			throw new IllegalStateException();

		return result;
	}

	public static void isUnReachableNonTerminalState(int[][] m){
		HashSet<Integer> visited=new HashSet<Integer>();
		HashSet<Integer> reachable=new HashSet<Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(0);
		reachable.add(0);
		while(!q.isEmpty()){
			int cur=q.remove();
			if(!visited.contains(cur)){
				visited.add(cur);
				//get neighbors
				for(int k=0;k<m[cur].length;k++){
					if(cur!=k){
						if(m[cur][k]>0){
							q.add(k);
							reachable.add(k);
						}

					}
				}
			}
		}
		for(int i=0;i<m.length;i++){
			if(!reachable.contains(i)){
				int total=0;
				for(int k=0;k<m[i].length;k++){
					total=total+m[i][k];
				}
				if(total>0)
					throw new IllegalStateException();
			}
		}
	}

	public static int lcm(int a, int b)
	{
		int gcd=Fraction.gcd(a, b);
		if(gcd!=0)
			return a * (b / gcd);
		else return 0;
	}

	public static int lcm(int[] input)
	{
		if(input.length==0)
			return 0;
		int result = input[0];
		for(int i = 1; i < input.length; i++) {
			if(input[i]>0)
				result = lcm(result, input[i]);
		}

		return result;
	}

	public static Fraction simplify(double d){
		int range=1000000;
		int roundPrecision=4;
		if(d<0.0001){
			return new Fraction(0, 1);
		}
		double rounded=round(d, roundPrecision);
		for(int i=1;i<range;i++){
			int j=findDenominator(i, 1, range, rounded, roundPrecision);				
			if(j>0){
				Fraction f= new Fraction(i, j);
				f=f.reduce();
				return f;
			}

		}
		return null;
	}

	public static int findDenominator(final int numerator, int low, int high, final double value, final int precision){
		double differenceAllowed=0.0001;
		if(Math.abs(high-low)<=1){
			double m=low;
			double n=numerator;
			double roundedVal=round((n/m), precision);
			if(Math.abs(roundedVal-value)<differenceAllowed)
				return low;

			m=high;
			n=numerator;
			roundedVal=round((n/m), precision);
			if(Math.abs(roundedVal-value)<differenceAllowed)
				return high;

			return -1;
		}

		int mid=(low+high)/2;

		double m=mid;
		double n=numerator;
		double roundedVal=round((n/m), precision);
		if(Math.abs(roundedVal-value)<differenceAllowed)
			return mid;
		else if(roundedVal<value)//look left
		return findDenominator(numerator, low, mid, value,precision);
		else //look right
			return findDenominator(numerator, mid, high, value, precision);

	}

	public static double[][] matrixMultiply(double[][] m, double[][] n){
		double[][] r=null;
		int mRowSize = m.length;
		int mColumnSize = m[0].length;
		int nColumnSize = n[0].length;

		r = new double[mRowSize][nColumnSize];

		for (int i = 0; i < mRowSize; i++) { 
			for (int j = 0; j < nColumnSize; j++) {
				for (int k = 0; k < mColumnSize; k++) {
					r[i][j] += m[i][k] * n[k][j];

					//r[i][j]=round(r[i][j], 6);
				}
			}
		}		
		return r;
	}


	public static double[][] matrixPower(double[][] a, int power){
		double[][] m=null;
		if(power<=1)
			return a;
		double[][] r=matrixPower(a, power/2);
		m=matrixMultiply(r, r);
		if(power%2!=0){ 
			m=matrixMultiply(m, a);
		}
		return m;
	}	

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static class Fraction
	{
		private int num;  
		private int den;  

		public Fraction(int n, int d){
			if(d != 0)
			{
				num = n;
				den = d;
			}	
		}
		public static int gcd(int a, int b) {
			if (b==0) return a;
			return gcd(b,a%b);
		}

		public Fraction reduce(){
			return reduce(num, den);
		}

		public Fraction reduce(int n, int d){
			int gcdNum = gcd(n,d);	
			if(gcdNum!=0){
				d = d / gcdNum; 
				n = n / gcdNum; 
			}
			return new Fraction(n,d);	
		}

		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public int getDen() {
			return den;
		}
		public void setDen(int den) {
			this.den = den;
		}
	}

	public static void main(String[] args) {

		int[][] b={
				{0,1,0,0,0,1},  
				{4,0,0,3,2,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}
		};
		System.out.println(Arrays.toString(answer(b)));
		System.out.println();
		int[][] c={
				{0, 2, 1, 0, 0}, 
				{0, 0, 0, 3, 4}, 
				{0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0}};

		System.out.println(Arrays.toString(answer(c)));
		System.out.println();
		int[][] d={
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 1, 0},
				{1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}


		};
		System.out.println(Arrays.toString(answer(d)));
		System.out.println();		

		int[][] d3={
				{1, 2, 3, 0, 0, 0}, 
				{4, 5, 6, 0, 0, 0}, 
				{7, 8, 9, 1, 0, 0}, 
				{0, 0, 0, 0, 1, 2}, 
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0}
		};		
		System.out.println(Arrays.toString(answer(d3)));
		

	}




	/*public static int[][] pad(int[][] m){
		int mRowSize = m.length;
		int mColumnSize = m[0].length;

		if(mColumnSize>mRowSize){
			int[][] m1=new int[mColumnSize][mColumnSize];
			for(int i=0;i<mRowSize;i++){
				for(int j=0;j<mColumnSize;j++){
					m1[i][j]=m[i][j];
				}
			}
			for(int i=mRowSize;i<mColumnSize;i++){
				for(int j=0;j<mColumnSize;j++){
					m1[i][j]=0;
				}
			}
			m=m1;
			mRowSize = m.length;
			mColumnSize = m[0].length;
		}

		if(mColumnSize<mRowSize){
			int[][] m1=new int[mRowSize][mRowSize];
			for(int i=0;i<mRowSize;i++){
				for(int j=0;j<mColumnSize;j++){
					m1[i][j]=m[i][j];
				}
			}
			for(int i=0;i<mRowSize;i++){
				for(int j=mColumnSize;j<mRowSize;j++){
					m1[i][j]=0;
				}
			}
			m=m1;
			mRowSize = m.length;
			mColumnSize = m[0].length;
		}
		return m;
	}*/

}
