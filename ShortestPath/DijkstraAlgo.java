package ShortestPath;

import java.util.*;

public class DijkstraAlgo {
    public static int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for(int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;


        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int curDist = top[1];

            if(curDist > dist[node]) continue;

            for(int[] nbr : adj.get(node)) {
                int neighbour = nbr[0];
                int weight = nbr[1];

                if(curDist + weight < dist[neighbour]) {
                    dist[neighbour] = curDist + weight;
                    pq.offer(new int[]{neighbour, dist[neighbour]});
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {

        int[][] edges = {
                {0, 1, 1},
                {0, 2, 5},
                {0, 3, 2},
                {1, 3, 3},
                {2, 3, 1}
        };

        int V = 4, src = 0;

        int[] dist = dijkstra(V, edges, src);

        System.out.println("Shortest distances from source " + src);
        System.out.println(Arrays.toString(dist));
    }
}
