package ShortestPath;

import java.util.*;

public class CheapestFlight {
    public static int cheapestFlightWithinKStops(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n ; i++) adj.add(new ArrayList<>());

        for(int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, src, 0});

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int stops = cur[0];
            int node = cur[1];
            int cost = cur[2];

            if(stops > k) continue;

            for(int[] next : adj.get(node)) {
                int nextNode = next[0];
                int price = next[1];

                if(cost + price < dist[nextNode]) {
                    dist[nextNode] = cost + price;
                    q.offer(new int[]{stops + 1, nextNode, cost + price});
                }
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        int n = 4, src = 0, dst = 3, K = 1;
        int[][] flights = {
                {0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}
        };

        System.out.println(cheapestFlightWithinKStops(n, flights, src, dst, K));
    }
}
