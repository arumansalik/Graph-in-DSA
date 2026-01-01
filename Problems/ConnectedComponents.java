package Problems;

import java.util.*;

public class ConnectedComponents {

    public static int countComponents(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, graph, visited);
            }
        }

        return components;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    public static void main(String[] args) {

        int V1 = 4;
        int[][] edges1 = {{0,1},{1,2}};
        System.out.println(countComponents(V1, edges1)); // Output: 2

        int V2 = 7;
        int[][] edges2 = {{0,1},{1,2},{2,3},{4,5}};
        System.out.println(countComponents(V2, edges2)); // Output: 3
    }
}
