package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathUnitWeights {
    public static int[] shortestPath(int V, int[][] edges, int src) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        while (!q.isEmpty()) {
            int node = q.poll();

            for(int nbr : adj.get(node)) {
                if(dist[node] + 1 < dist[nbr]) {
                    dist[nbr] = dist[node] + 1;
                    q.offer(nbr);
                }
            }
        }

        for(int i = 0; i < V; i++) {
            if(dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
                {0, 1},
                {0, 3},
                {1, 2},
                {3, 4}
        };

        int src = 0;

        int[] ans = shortestPath(V, edges, src);

        System.out.println("Shortest Path from node " + src + ":");
        System.out.println(Arrays.toString(ans));
    }
}
