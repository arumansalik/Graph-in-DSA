package MinimumSpanningTree;

import java.util.*;

public class KruskalsAlgorithm {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] size;

        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int node) {
            if(parent[node] == node) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if(rootU == rootV) return;

            if(size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
    }

    public static void kruskal(int V, List<Edge> edges) {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);

        int mstCost = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for(Edge edge : edges) {
            if(ds.find(edge.u) != ds.find(edge.v)) {
                ds.union(edge.u, edge.v);
                mstCost += edge.weight;
                mstEdges.add(edge);
            }
        }

        System.out.println("Edges in MST:");
        for(Edge e : mstEdges) {
            System.out.println(e.u + " -- " + e.v + " == " + e.weight);
        }
        System.out.println("Total MST Cost: " + mstCost);
    }

    public static void main(String[] args) {
        int V = 4;


        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskal(V, edges);
    }
}
