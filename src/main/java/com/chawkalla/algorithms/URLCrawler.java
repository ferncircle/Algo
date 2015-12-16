package com.chawkalla.algorithms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

public class URLCrawler {

	private HttpClient  httpClient;
	private ClientConnectionManager clientConnectionManager;

	public URLCrawler() {
		super();
		init();
	}

	public void init() {
		if (httpClient == null) {
			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(10000));

			if (clientConnectionManager == null) {
				SchemeRegistry schemeRegistry = new SchemeRegistry();
				schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
				clientConnectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
			}
			DefaultHttpClient  newHttpClient = new DefaultHttpClient(clientConnectionManager, params);

			newHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
			httpClient = newHttpClient;
		}
	}

	public List<String> searchContentInUrl(String url, String pattern){
		List<String> jsFiles=new ArrayList<String>();
		HttpGet method = new HttpGet(url);
		HttpResponse response = null;
		try {
			String userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)";
			httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, userAgent);
			// Execute the method.
			response = httpClient.execute(method);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("status code ="+statusCode);

			// Read the response body.
			if (response.getEntity() != null) {
				InputStream responseStream= response.getEntity().getContent();  
				BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));
				String line;
				Pattern p = Pattern.compile(pattern);
				while ((line = in.readLine()) != null) {
					//System.out.println(line);

					Matcher m = p.matcher(line);

					if (m.find()) {
						//System.out.println(m.group(1));
						
						System.out.println(m.group(1));
						jsFiles.add(m.group(1));
					}
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return jsFiles;
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		URLCrawler cr=new URLCrawler();
		String url="http://qa.military.com/military-transition/checklist-active-duty-separating.html";
		String pattern="(http://.*\\.js)[\"\']";
		String searchString="(login_widgets)";
		List<String> jsFiles=cr.searchContentInUrl(url, pattern);
		if(jsFiles!=null)
			for(String js:jsFiles){
				System.out.println(js);
				try {
					List<String> lines=cr.searchContentInUrl(js, searchString);
					if(lines!=null)
						for(String line:lines){
							System.out.println("Found:          "+js);
						}
				} catch (Exception e) {
					
				}

			}

	}

}
