package com.chawkalla.algorithms.ds;


class UnionFind {
        private int count = 0;
        private int[] parent, rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        //1 <- 2 <-4, after find(4), 1 <- 4, 1 <- 2
        public int find(int p) {
        	while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return; //belong to same tree
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;//merge tree by changing the ROOT of p to rootQ
            }
            else {
                parent[rootQ] = rootP; //merge tree by changing the ROOT of q to rootP
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }