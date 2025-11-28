package Problems;

import java.util.*;

public class CycleDetectionInAnUndirectedGraphUsingBFS {
    static boolean detectCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if(!visited[i]) {
                if(bfsCheck(i, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean bfsCheck(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, -1});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int parent = curr[1];

            for(int neighbor: adj.get(node)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(new int[]{neighbor, node});
                } else if (neighbor != parent) {
                    return true;
                }
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
