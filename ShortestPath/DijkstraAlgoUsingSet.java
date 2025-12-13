package ShortestPath;

import java.util.*;

public class DijkstraAlgoUsingSet {
    static class Pair {
        int dist, node;;
        Pair(int d, int n) {
            dist = d;
            node = n;
        }
    }

    public static int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for(int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        TreeSet<Pair> set = new TreeSet<>(
                (a, b) -> a.dist != b.dist ? a.dist - b.dist : a.node - b.node
        );

        set.add(new Pair(0, src));

        while (!set.isEmpty()) {
            Pair cur = set.pollFirst();
            int node = cur.node;

            for(int[] nbr : adj.get(node)) {
                int v = nbr[0], wt = nbr[1];

                if(dist[node] + wt < dist[v]) {
                    if(dist[v] != Integer.MAX_VALUE) {
                        set.remove(new Pair(dist[v], v));
                    }
                    dist[v] = dist[node] + wt;
                    set.add(new Pair(dist[v], v));
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        int[][] edges = {
                {0,1,1},
                {0,2,5},
                {0,3,2},
                {1,3,3},
                {2,3,1}
        };

        int[] res = dijkstra(4, edges, 0);
        System.out.println(Arrays.toString(res));
    }
}
