/**
 * 
 */
package com.chawkalla.algorithms.examples.codefights.tour;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author SFargose
 *
 */
public class Domains {

	String[] subdomains(String[] domains) {

	    Trie trie=new Trie();
	    for(String domain:domains){
	        trie.insert(domain.split("\\."));
	    }
	    
	    List<String> list=new ArrayList<String>();
	    print(trie.root,list,1,"");
	    
	    String[] res=list.toArray(new String[list.size()]);
	    return res;
	}

	public void print(Node n, List<String> list, int l, String prev){
	    if(n==null) return;
	    StringBuffer buf=new StringBuffer();
	    for(int i=0;i<2*l;i++)
	        buf.append("-");
	    for(Map.Entry<String,Node> e:n.map.entrySet()){
	        String domain=e.getKey()+(prev.length()==0?"":".")+prev;
	        list.add(buf.toString()+domain);
	        print(e.getValue(), list, l+1, domain);
	    }
	}


	public class Trie{
	    Node root=new Node();
	    
	    public void insert(String[] domains){
	        Node cur=root;
	        for(int i=domains.length-1;i>=0;i--){
	            String d=domains[i];
	            cur=cur.add(d);
	        }
	        cur.eof=true;
	    }
	}

	public class Node{
	    boolean eof=false;
	    TreeMap<String, Node> map=new TreeMap<String, Node>();
	    
	    public Node add(String d){
	        map.compute(d,(k,v)->v==null?new Node():v);
	        return map.get(d);
	    }
	}



	public static void main(String[] args) {

		assertThat(new Domains().subdomains(new String[]{"com", 
				"en.wikipedia.org", 
				"ru.wikipedia.org", 
				"wikipedia.org", 
				"hy.wikipedia.org", 
				"org", 
				"google.com", 
				"whois.pir.org", 
				"yahoo.com", 
				"yandex.ru", 
				"codefights.com", 
		"ru"}), is(new String[]{
				"--com", 
				"----codefights.com", 
				"----google.com", 
				"----yahoo.com", 
				"--org", 
				"----pir.org", 
				"------whois.pir.org", 
				"----wikipedia.org", 
				"------en.wikipedia.org", 
				"------hy.wikipedia.org", 
				"------ru.wikipedia.org", 
				"--ru", 
				"----yandex.ru"
		}));
		System.out.println("all cases passed");
	}

}
