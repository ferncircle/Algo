package com.chawkalla.algorithms.bean;

import java.util.HashMap;

public class SuffixNode {
	public boolean isLeaf=false;
	public HashMap<String, SuffixNode> edges=new HashMap<String, SuffixNode>();
}
