import java.util.*;

public class BFS {
    public static void bfs(int start, List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();


        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.println(node + " ");

            for (int neighbour : graph.get(node)) {
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
    }
}