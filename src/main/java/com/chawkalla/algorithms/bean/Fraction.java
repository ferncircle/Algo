package com.chawkalla.algorithms.bean;

public class Fraction
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
		Fraction simple=simplify(value);
		if(simple==null)
			simple=this;
		
		return simple;
	}
	
	public static Fraction simplify(double d){
		int precision=1000;
		int range=200;
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

