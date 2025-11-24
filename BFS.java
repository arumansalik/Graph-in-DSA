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

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);

        graph.get(1).add(0);
        graph.get(2).add(0);
        graph.get(3).add(1);
        graph.get(4).add(2);

        bfs(0, graph, n);
    }
}