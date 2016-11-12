package com.chawkalla.algorithms.examples.trie;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import com.chawkalla.algorithms.ds.Trie;

public class TrieSearch {

	public static void main(String[] args) {
		Trie test=new Trie();
		List<String> list=Arrays.asList("bad","baap","bat","cat");
		for (int i = 0; i < list.size(); i++) {
			test.insert(list.get(i));
		}
		
		assertThat(test.search("bad"), is(true));
		assertThat(test.search("bat"), is(true));
		assertThat(test.search("cat"), is(true));
		assertThat(test.startsWith("cat"), is(true));
		assertThat(test.startsWith("ca"), is(true));
		assertThat(test.startsWith("bh"), is(false));

		assertThat(test.searchRegex("bad"), is(true));
		assertThat(test.searchRegex("ba"), is(false));
		assertThat(test.searchRegex("ba.."), is(true));
		assertThat(test.searchRegex(".at"), is(true));
		assertThat(test.searchRegex("b.d"), is(true));
		assertThat(test.searchRegex("c.d"), is(false));
		assertThat(test.searchRegex(".."), is(false));
		assertThat(test.searchRegex("..."), is(true));
		assertThat(test.searchRegex("c"), is(false));
		
		
		test=new Trie();	
		for (int i = 0; i < list.size(); i++) {
			test.insert(list.get(i),i);
		}
		assertThat(test.startsWithWords("b"), is(Arrays.asList("aap:1","ad:0","at:2")));
		System.out.println("All test cases passed");
	}

}
