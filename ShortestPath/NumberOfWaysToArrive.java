package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfWaysToArrive {
    static final int MOD = 1_000_000_007;

    public static int countPaths(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int[] r : roads) {
            adj.get(r[0]).add(new int[]{r[1], r[2]});
            adj.get(r[1]).add(new int[]{r[0], r[2]});
        }

        long[] dist = new long[n];
        long[] ways = new long[n];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        pq.offer(new long[]{0, 0});

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            long time = cur[0];
            int node = (int) cur[1];

            if(time > dist[node]) continue;

            for(int[] edge : adj.get(node)) {
                int next = edge[0];
                long wt = edge[1];

                long newDist = time + wt;

                if(newDist < dist[next]) {
                    dist[next] = newDist;
                    ways[next] = ways[node];
                    pq.offer(new long[]{newDist, next});
                } else if (newDist == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }
        return (int)(ways[n - 1] % MOD);
    }
    public static void main(String[] args) {

        int n = 7;
        int[][] roads = {
                {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3},
                {6, 3, 3}, {3, 5, 1}, {6, 5, 1},
                {2, 5, 1}, {0, 4, 5}, {4, 6, 2}
        };

        System.out.println("Number of shortest paths: " + countPaths(n, roads));
    }
}
