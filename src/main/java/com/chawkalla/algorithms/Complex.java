package com.chawkalla.algorithms;

public class Complex<T extends Number> {

	private T re;
	private T im;
	
	public Complex(T re, T im) {
		this.re = re;
		this.im = im;
	}

	public T getReal() {
		return re;
	}

	public T getImage() {
		return im;
	}

	public String toString() {
		return "(" + re + ", " + im + ")";
	}

	public double getModulus() {
		return Math.sqrt(Math.pow(re.doubleValue(), 2) +
				Math.pow(im.doubleValue(), 2));
	}
}
