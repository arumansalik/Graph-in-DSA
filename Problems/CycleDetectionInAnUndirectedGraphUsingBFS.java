package Problems;

import java.util.*;

public class CycleDetectionInAnUndirectedGraphUsingBFS {

    static boolean detectCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCheck(i, -1, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfsCheck(int node, int parent,
                                    List<List<Integer>> adj,
                                    boolean[] visited) {

        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfsCheck(neighbor, node, adj, visited)) {
                    return true;
                }
            }
            // If visited and not parent → cycle
            else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(0);
        adj.get(0).add(3);

        System.out.println("Cycle Present: " + detectCycle(V, adj));
    }
}
