package com.google.challenges;

import java.util.ArrayList;
import java.util.Arrays;

public class Answer33Double {

	public static int[] answer(int[][] m){

		if(m==null)
			return null;
		int mRowSize = m.length;
		int mColumnSize = m[0].length;

		if(mRowSize==0 || mColumnSize==0 || mRowSize>10 || mColumnSize>10)
			return new int[]{};
		if(mRowSize==1 || mColumnSize==1){
			if(m[0][0]!=0)
				return new int[]{};
			else{
				return new int[]{1,1};
			}

		}
		
		for(int i=0;i<mRowSize;i++){
			for(int j=0;j<mColumnSize;j++){
				if(m[i][j]==Integer.MAX_VALUE)
					throw new IllegalStateException();
			}
		}
		
		int t=0;
		for(int j=0;j<mColumnSize;j++){
			t=t+m[0][j];
		}
		if(t==0)
			throw new IllegalStateException();


		ArrayList<Integer> absorbStates=new ArrayList<Integer>();
		double[][] o=new double[mRowSize][mColumnSize];
		for(int i=0;i<mRowSize;i++){
			double total=0;
			for(int k=0;k<mColumnSize;k++){
				total=total+m[i][k];
			}
			if(total>0){
				for(int j=0;j<mColumnSize;j++){
					double val=m[i][j]/total;
					o[i][j]=val;
				}
			}else if(total==0){
				o[i][i]=1;
				absorbStates.add(i);
			}

		}
		double[][] r=matrixPower(o, 200);

		double[] initial=new double[mRowSize];
		initial[0]=1.0;

		double[][] sol=matrixMultiply(new double[][]{initial}, r);

		double[] solution=sol[0];


		int[] result=null;
		int[] denoms=new int[absorbStates.size()];
		if(solution!=null && solution.length>0){
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
		}
		return result;
	}

	public static int[][] pad(int[][] m){
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
		int result = input[0];
		for(int i = 1; i < input.length; i++) 
			result = lcm(result, input[i]);
		return result;
	}

	public static Fraction simplify(double d){
		int range=10000;
		int roundPrecision=4;
		if(d<0.01){
			return new Fraction(0, 1);
		}
		double rounded=round(d, roundPrecision);
		for(int i=1;i<range;i++){
			for(int j=i;j<range;j++){
				double numerator=i;
				double denominator=j;
				double roundedVal=round((numerator/denominator), roundPrecision);
				if(rounded==roundedVal)
					return new Fraction(i, j);
			}
		}
		return null;
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

					r[i][j]=round(r[i][j], 6);
				}
			}
		}		
		return r;
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

		/*double[][] a={	{1,		0,		0,		0},
						{0.5,	0,		0.5,	0},
						{0,		0.5,	0,		0.5},
						{0,		0,		0,		1}

					};

		System.out.println(Arrays.toString(answer(a)));*/

		/*double[][] b={
		   {0,0.5,0,0,0,0.5},  
		   {0.44,0,0,0.33,0.22,0},  
		   {0,0,1,0,0,0},  
		   {0,0,0,1,0,0},  
		   {0,0,0,0,1,0},  
		   {0,0,0,0,0,1}, 
		};*/

		int[][] b={
				{0,199,0,0,0,150},  
				{4,0,0,3,2,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0},
		};
		//System.out.println(Arrays.toString(answer(b)));
		System.out.println();
		int[][] c={{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

		//System.out.println(Arrays.toString(answer(c)));
		System.out.println();
		int[][] d={
				{0, 0, 1, 0},
				{2, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}

		};
		//System.out.println(Arrays.toString(answer(d)));
		System.out.println();
		int[][] e={
				{8,1,1,1,1,1,199,1,1,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0},

		};
		System.out.println(Arrays.toString(answer(e)));


	}

}
