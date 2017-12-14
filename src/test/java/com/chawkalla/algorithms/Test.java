package com.chawkalla.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static String encodeSearchParam(String param){
		if(param==null || param.length()==0) return param;
		String encoded=null;
		
		String key="";
		String value="";
		
		String[] kv=param.split(":");
		if(kv.length>1){
			key=kv[0];
			value=kv[1];
		}else
			value=kv[0];
		
		try {
			if(value.matches(".*\\s.*"))
				value=URLEncoder.encode("\""+value+"\"", "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		encoded=key+(kv.length>1?":":"")+value;
		
		return encoded;
	}
	
	public static void main(String[] args)throws Exception {
		
		
		assertThat(encodeSearchParam("navy birthday"), is("%22navy+birthday%22"));
		assertThat(encodeSearchParam("tags:navy birthday"), is("tags:%22navy+birthday%22"));
		System.out.println("all cases passed");
	}

}
