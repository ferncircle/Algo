package com.google.challenges;

import java.util.ArrayList;
import java.util.Arrays;

public class Answer33Fraction {

	public static int[] answer(int[][] m){		
		
		if(m==null || m[0]==null)
			throw new IllegalStateException();
		int mRowSize = m.length;
		int mColumnSize = m[0].length;

		if(mRowSize==0 || mColumnSize==0 || mRowSize>10 || mColumnSize>10)
			throw new IllegalStateException();
		
		if(mRowSize!=mColumnSize)
			throw new IllegalStateException();
		
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
		
		//negative value
		for(int i=0;i<mRowSize;i++){
			for(int k=0;k<mColumnSize;k++){
				if(m[i][k]<0)
					throw new IllegalStateException();
			}
		}
		
		if(mRowSize==1 || mColumnSize==1){
			if(m[0][0]!=0)
				throw new IllegalStateException();
			else{
				return new int[]{1,1};
			}

		}
		
		/*if(Arrays.deepEquals(m, new int[][]{
				{0,1,0,0,0,1},  
				{4,0,0,3,2,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0},
		})){
			return new int[]{};
		}*/
		
        ArrayList<Integer> absorbStates=new ArrayList<Integer>();
        Fraction[][] o=createFractionMatrix(mRowSize, mColumnSize);
        for(int i=0;i<mRowSize;i++){
        	int total=0;
        	for(int k=0;k<mColumnSize;k++){
        		total=total+m[i][k];        		
        	}
        	if(total>0){
        		for(int j=0;j<mColumnSize;j++){        			
            		o[i][j]=new Fraction(m[i][j], total); //set to probability in fraction
            	}
        	}else if(total==0){
        		o[i][i]=new Fraction(1, 1);
        		absorbStates.add(i);
        	}        	
        }
        
        Fraction[][] r=matrixPower(o, 100); //multipy 10 times
		
        //create initial set starting matrix
		Fraction[] initial=new Fraction[mRowSize];
		initial[0]=new Fraction(1, 1);
		for(int i=1;i<mRowSize;i++){
			initial[i]=new Fraction(0, 1);
		}
		
		//multiply initial matrix to solution to get the final probabilities
		Fraction[][] sol=matrixMultiply(new Fraction[][]{initial}, r);
		
		Fraction[] solution=sol[0];				
		
		int[] result=null;
		int[] denoms=new int[absorbStates.size()];

		Fraction[] absorbFractions=new Fraction[absorbStates.size()];
		for(int i=0;i<absorbStates.size();i++){
			absorbFractions[i]=solution[absorbStates.get(i)].simplify();
		}
		for(int i=0;i<absorbFractions.length;i++){
			denoms[i]=(int)absorbFractions[i].getDen();
		}
		int lcm=lcm(denoms);


		result=new int[absorbFractions.length+1];
		result[result.length-1]=lcm;
		for(int i=0;i<absorbFractions.length;i++){
			Fraction f=absorbFractions[i];

			int numerator=0;
			if(f.getDen()!=0){
				numerator=(int)((lcm/f.getDen())*f.getNum());
			}
			result[i]=numerator;
		}
		
		return result;
	}
	
	public static int lcm(int a, int b)
	{
		int gcd=(int)Fraction.gcd(a, b);
		if(gcd!=0)
			return a * (b / gcd);
		else return 0;
	}
	
	public static int lcm(int[] input)
	{
		if(input.length==0)
			return 0;
		int result = input[0];
	    for(int i = 1; i < input.length; i++) 
	    	result = lcm(result, input[i]);
	    return result;
	}
	
	public static Fraction[][] matrixPower(Fraction[][] a, int power){
		Fraction[][] m=null;
		if(power<=1)
			return a;
		Fraction[][] r=matrixPower(a, power/2);
		m=matrixMultiply(r, r);
		if(power%2!=0){ 
			m=matrixMultiply(m, a);
		}
		return m;
	}
	
	
	public static Fraction[][] matrixMultiply(Fraction[][] m, Fraction[][] n){		
		int mRowSize = m.length;
        int mColumnSize = m[0].length;
        int nColumnSize = n[0].length;

        Fraction[][] r = createFractionMatrix(mRowSize, nColumnSize);
       
        for (int i = 0; i < mRowSize; i++) { 
            for (int j = 0; j < nColumnSize; j++) {
                for (int k = 0; k < mColumnSize; k++) {
                    r[i][j] = r[i][j].add(m[i][k].multiply(n[k][j]));
                }
            }
        }		
		return r;
	}
	
	public static Fraction[][] createFractionMatrix(int rows, int cols){
		Fraction[][] r=new Fraction[rows][cols];
		for (int i = 0; i < rows; i++) { 
            for (int j = 0; j < cols; j++) {               
                    r[i][j] = new Fraction(0, 1);               
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
		private double num;  
		private double den;  

		public Fraction(double n, double d){
			if(d != 0)
			{
				num = n;
				den = d;
			}	
		}
		public static double gcd(double a, double b) {
			if (b==0) return a;
			return gcd(b,a%b);
		}
		
		public Fraction simplify(){
			double numer=num;
			double denom=den;
			double value=numer/denom;
			Fraction simple=null;
			if(value>0){
				simple=simplify(value);
				
			}
			if(simple==null)
				simple=this;
			
			return simple;
		}
		
		public static Fraction simplify(double d){
			int range=10000;
			int roundPrecision=4;
			if(d<0.009){
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
					if(roundedVal<rounded)
						break;
				}
			}
			return null;
		}
		public static Fraction reduce(double n, double d){
			double gcdNum = gcd(n,d);	
			if(gcdNum!=0){
				d = d / gcdNum; 
				n = n / gcdNum; 
			}
			return new Fraction(n,d);	
		}
		
		public Fraction add(Fraction b){
			double numerator = (this.num * b.den) + (b.num * this.den); 
			double denominator = this.den * b.den; 

			return reduce(numerator,denominator);

		}
		public Fraction multiply(Fraction b){
			double num1 = this.num * b.num; 
			double num2 = this.den * b.den;

			return reduce(num1,num2);
		}
		public String toString()  
		{			
			return(num + "/" + den); 
		}
		public double getNum() {
			return num;
		}
		public void setNum(double num) {
			this.num = num;
		}
		public double getDen() {
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
				{0,0,0,0,2,0}, 
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
				{0, 0, 0, 0, 0}
				};

		System.out.println(Arrays.toString(answer(c)));
		System.out.println();
		int[][] d={
				{0, 0, 1, 0},
				{2, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}

		};
		System.out.println(Arrays.toString(answer(d)));
		System.out.println();
		int[][] e={
				/*{0,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0}, 
				{1,0,0,0,0,0,0,0,0,0},*/
				{0,12,150},
				{0,0,0},
				{1,0,0}
				
		};
		System.out.println(Arrays.toString(answer(e)));
		
		/*System.out.println("array equals?"+Arrays.deepEquals(d, new int[][]{
				{0, 0, 1, 0},
				{2, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}

		}));*/
		
		/*System.out.println();
		System.out.println(Fraction.gcd(13122, Fraction.gcd(39366, 59049)));
		System.out.println(Fraction.gcd(625,1000));*/
		
		//System.out.println(lcm(0, 0));
		/*
		
		Fraction f1=new Fraction(3, 14);
		Fraction f2=new Fraction(1, 7);
		
		System.out.println(f1.add(f2).toString());
		*/
		/*System.out.println(Fraction.simplify(0.214));
		System.out.println(Fraction.simplify(0.642));*/
		//System.out.println(Fraction.reduce(8431, 13122));
		
		//System.out.println(lcm(new int[]{7,14,14}));
	}

}
