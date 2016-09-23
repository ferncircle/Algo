package com.chawkalla.algorithms;

public class Combination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(NChooseK(26, 2));
	}
	

	//http://sites.google.com/site/spaceofjameschen/annnocements/printtheindexofawordwithlettersinascendingorder--microsoft
	public static int  NChooseK(int n, int k)
	{
	    if (n == k || k == 0) return 1;

	    return NChooseK(n - 1, k) + NChooseK(n - 1, k - 1);
	}
	

}
