package ShortestPath.BellmanFord;

import java.util.*;

class Solution {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static int[] bellmanFord(int N, List<Edge> edge, int src) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < N - 1; i++) {
            for(Edge e : edge) {
                if(dist[e.u] != 1e8 && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        //“Can any distance still be reduced after N−1 iterations?”
        for(Edge e: edge) {
            if(dist[e.u] != 1e8 && dist[e.u] + e.w < dist[e.v]) {
                System.out.println("Negative Cycles detected");
                return new int[]{-1};
            }
        }
        return dist;
    }
}

public class BellmanFordDemo {
    public static void main(String[] args) {
        int V = 5;
        List<Solution.Edge> edges = new ArrayList<>();

        edges.add(new Solution.Edge(0, 1, -1));
        edges.add(new Solution.Edge(0, 2, 4));
        edges.add(new Solution.Edge(1, 2, 3));
        edges.add(new Solution.Edge(1, 3, 2));
        edges.add(new Solution.Edge(1, 4, 2));
        edges.add(new Solution.Edge(3, 2, 5));
        edges.add(new Solution.Edge(3, 1, 1));
        edges.add(new Solution.Edge(4, 3, -3));

        int[] dist = Solution.bellmanFord(V, edges, 0);

        System.out.println("Shortest distances from source: ");
        for(int d : dist) {
            System.out.print(d + " ");
        }
    }
}
