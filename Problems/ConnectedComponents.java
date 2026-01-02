package Problems;

import java.util.*;

public class ConnectedComponents {

        // Union-Find (Disjoint Set)
        static class UnionFind {
            int[] parent;
            int[] rank;

            UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];

                // Initially, each node is its own parent
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // Find with path compression
            int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            // Union by rank
            boolean union(int x, int y) {
                int px = find(x);
                int py = find(y);

                if (px == py) return false; // already connected

                if (rank[px] < rank[py]) {
                    parent[px] = py;
                } else if (rank[px] > rank[py]) {
                    parent[py] = px;
                } else {
                    parent[py] = px;
                    rank[px]++;
                }
                return true;
            }
        }

        // Count connected components
        public static int countComponents(int V, int[][] edges) {
            UnionFind uf = new UnionFind(V);
            int components = V;

            for (int[] edge : edges) {
                if (uf.union(edge[0], edge[1])) {
                    components--;
                }
            }

            return components;
        }

        // MAIN METHOD — run locally
        public static void main(String[] args) {

            int V1 = 4;
            int[][] edges1 = {{0,1},{1,2}};
            System.out.println(countComponents(V1, edges1)); // Output: 2

            int V2 = 7;
            int[][] edges2 = {{0,1},{1,2},{2,3},{4,5}};
            System.out.println(countComponents(V2, edges2)); // Output: 3
        }
    }
