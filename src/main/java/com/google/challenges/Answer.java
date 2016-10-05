package com.google.challenges;

import java.util.ArrayList;
import java.util.Arrays;

public class Answer {

	public static int[] answer(int[][] m){		
		
		int mRowSize = m.length;
        int mColumnSize = m[0].length;
        if(mRowSize==0 || mColumnSize==0)
        	return new int[0];
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
        
        Fraction[][] r=matrixPower(o, 10); //multipy 10 times
		
        //create initial set starting matrix
		Fraction[] initial=new Fraction[mRowSize];
		initial[0]=new Fraction(1, 1);
		for(int i=1;i<mRowSize;i++){
			initial[i]=new Fraction(0, 1);
		}
		
		//multiply initial matrix to solution to get the final probabilities
		Fraction[][] sol=matrixMultiply(new Fraction[][]{initial}, r);
		for(Fraction[] d:sol){
			for(int i=0;i<d.length;i++){
				d[i]=d[i].simplify();
			}			
		}
		
		int[] denoms=new int[absorbStates.size()];
		
		for(int i=0;i<absorbStates.size();i++){
			denoms[i]=sol[0][absorbStates.get(i)].getDen();
		}
		int lcm=lcm(denoms);
		
			
		int[] result=new int[absorbStates.size()+1];
		result[result.length-1]=lcm;
		for(int i=0;i<absorbStates.size();i++){
			Fraction f=sol[0][absorbStates.get(i)];
			
			int numerator=0;
			if(f.getDen()!=0){
				numerator=(lcm/f.getDen())*f.getNum();
			}
			result[i]=numerator;
		}
		return result;
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
			int precision=1000;
			int range=10000;
			int number=(int)(d*precision);
			for(int i=1;i<range;i++){
				for(int j=i;j<range;j++){
					double numerator=i;
					double denominator=j;
					int val=(int)((numerator/denominator)*precision);
					if(number==val)
						return new Fraction(i, j);
				}
			}
			return null;
		}
		public static Fraction reduce(int n, int d){
			int gcdNum = gcd(n,d);	
			if(gcdNum!=0){
				d = d / gcdNum; 
				n = n / gcdNum; 
			}
			return new Fraction(n,d);	
		}
		
		public Fraction add(Fraction b){
			int numerator = (this.num * b.den) + (b.num * this.den); 
			int	denominator = this.den * b.den; 

			return reduce(numerator,denominator);

		}
		public Fraction subtract(Fraction b){
			int numerator = (this.num * b.den) - (b.num * this.den);
			int denominator = this.den * b.den;

			return reduce(numerator,denominator);
		}
		public Fraction multiply(Fraction b){
			int num1 = this.num * b.num; 
			int	num2 = this.den * b.den;

			return reduce(num1,num2);
		}
		public Fraction divide(Fraction b){ 		
			int num1 = this.num * b.den; 
			int num2 = this.den * b.num;

			return reduce(num1, num2);
		}
		public String toString()  
		{			
			return(num + "/" + den); 
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
		           {0,1,1,0,0,1},  
		           {4,0,0,3,2,0}, 
		           {0,0,0,0,0,0}, 
		           {0,0,0,0,0,0}, 
		           {0,0,0,0,0,0}, 
		           {0,0,0,0,0,0},
		         };
		System.out.println(Arrays.toString(answer(b)));
		/*System.out.println();
		System.out.println(Fraction.gcd(13122, Fraction.gcd(39366, 59049)));
		System.out.println(Fraction.gcd(625,1000));*/
		int[][] c={{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
		
		System.out.println(Arrays.toString(answer(c)));
		
		System.out.println(Arrays.toString(answer(new int[][]{
					{0,1,1,1,1,1},  
		        	{0,0,0,0,0,0}, 
		        	{0,0,0,0,0,0}, 
		        	{0,0,0,0,0,0}, 
		        	{0,0,0,0,0,0}, 
		        	{0,0,0,0,0,0},
		})));
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
