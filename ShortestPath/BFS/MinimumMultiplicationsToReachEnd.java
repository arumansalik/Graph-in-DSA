package ShortestPath.BFS;

import java.util.*;

public class MinimumMultiplicationsToReachEnd {
    public static int minimumMultiplication(int[] arr, int start, int end) {
        if(start == end) return 0;

        int MOD = 100000;

        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[MOD];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        q.offer(start);

        while (!q.isEmpty()) {
            int node = q.poll();

            for(int num : arr) {
                int next = (num * node) % MOD;

                if(dist[node] + 1 < dist[next]) {
                    dist[next] = dist[node] + 1;
                }

                if(next == end) {
                    return dist[next];
                }

                q.offer(next);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 7};
        int start = 3, end = 30;

        System.out.println(minimumMultiplication(arr, start, end));
    }
}
