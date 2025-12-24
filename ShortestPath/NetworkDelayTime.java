package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    public static int netWorkDelay(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for(int[] time: times) {
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        pq.offer(new int[]{0, k});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0];
            int node = cur[1];

            if(time > dist[node]) continue;

            for (int[] nei: adj.get(node)) {
                int next = nei[0];
                int wt = nei[1];

                if(time + wt < dist[next]) {
                    dist[next] = time + wt;
                    pq.offer(new int[]{dist[next], next});
                }
            }
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == Integer.MAX_VALUE) return -1;
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

        System.out.println(netWorkDelay(times, 4, 2));
    }
}
