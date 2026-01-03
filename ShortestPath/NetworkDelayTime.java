package ShortestPath;

import java.util.Arrays;

public class NetworkDelayTime {

    public static int netWorkDelay(int[][] times, int n, int k) {

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Bellman-Ford: relax edges n-1 times
        for (int i = 1; i <= n - 1; i++) {
            boolean updated = false;

            for (int[] edge : times) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    updated = true;
                }
            }

            // Optimization: stop early if no updates
            if (!updated) break;
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[][] times = {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        };

        System.out.println(netWorkDelay(times, 4, 2)); // Output: 2
    }
}
