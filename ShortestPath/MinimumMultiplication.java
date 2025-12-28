package ShortestPath;

import java.util.*;

public class MinimumMultiplication {
    public static int MinMultiplication(int[] arr, int start, int end) {
        if(start ==  end) return 0;

        int MOD = 100000;

        int[] dist = new int[MOD];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, start});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int steps = curr[0];
            int node = curr[1];

            if(node == end) return steps;

            if(steps > dist[node]) continue;

            for(int num : arr) {
                int next = (node * num) % MOD;

                if(steps + 1 < dist[next]) {
                    dist[next] = steps + 1;
                    pq.offer(new int[]{steps + 1, next});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 7};
        int start = 3, end = 30;

        System.out.println(MinMultiplication(arr, start, end));

    }
}
