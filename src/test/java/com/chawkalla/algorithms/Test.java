package com.chawkalla.algorithms;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class Test {

	
	public static void main(String[] args) {
		SoftReference<HashMap<String, String>> map=
				new SoftReference<HashMap<String,String>>(new HashMap<String, String>());
		
	}

}
