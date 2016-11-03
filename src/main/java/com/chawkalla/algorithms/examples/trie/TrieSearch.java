package com.chawkalla.algorithms.examples.trie;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.chawkalla.algorithms.ds.Trie;

public class TrieSearch {

	public static void main(String[] args) {
		Trie test=new Trie();
		
		test.insert("bad");
		test.insert("baap");
		test.insert("bad");
		test.insert("cat");
		
		assertThat(test.search("bad"), is(true));
		assertThat(test.search("bat"), is(false));
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
		System.out.println("All test cases passed");
	}

}
