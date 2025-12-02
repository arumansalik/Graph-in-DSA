package Problems;

import java.util.*;

public class IsGraphBipartite {
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int i = 0; i < n; i++) {
            if(color[i] == -1) {
                if(!bfsCheck(i, graph, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean bfsCheck(int start, int[][] graph, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int neighbor : graph[node]) {
                if(color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node];
                    q.offer(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] graph1 = {{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] graph2 = {{1,3},{0,2},{1,3},{0,2}};

        System.out.println(isBipartite(graph1)); // false
        System.out.println(isBipartite(graph2)); // true
    }
}
