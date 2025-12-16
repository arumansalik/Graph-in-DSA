package ShortestPath;

import java.util.*;

// Edge class to store destination and weight
class Edge {
    int v;
    int wt;

    Edge(int v, int wt) {
        this.v = v;
        this.wt = wt;
    }
}

public class PrintShortestPathDijkstra {

    // Dijkstra method
    public static List<Integer> shortestPath(int n, List<List<Edge>> adj) {

        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Min-heap: (distance, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

        dist[0] = 0;
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];

            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.v;
                int wt = edge.wt;

                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    parent[v] = u;              // ⭐ parent update
                    pq.add(new int[]{dist[v], v});
                }
            }
        }

        // If destination unreachable
        if (dist[n - 1] == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int node = n - 1;
        while (node != -1) {
            path.add(node);
            node = parent[node];
        }

        Collections.reverse(path);
        return path;
    }

    // MAIN FUNCTION
    public static void main(String[] args) {
        int n = 6;

        // Adjacency list
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // Add edges (undirected)
        addEdge(adj, 0, 1, 2);
        addEdge(adj, 0, 4, 1);
        addEdge(adj, 1, 2, 3);
        addEdge(adj, 4, 2, 2);
        addEdge(adj, 4, 5, 4);
        addEdge(adj, 5, 3, 1);
        addEdge(adj, 2, 3, 6);

        List<Integer> path = shortestPath(n, adj);

        if (path.isEmpty()) {
            System.out.println("No path exists");
        } else {
            System.out.println("Shortest Path:");
            for (int node : path) {
                System.out.print(node + " ");
            }
        }
    }

    // Helper to add undirected edges
    static void addEdge(List<List<Edge>> adj, int u, int v, int wt) {
        adj.get(u).add(new Edge(v, wt));
        adj.get(v).add(new Edge(u, wt));
    }
}
