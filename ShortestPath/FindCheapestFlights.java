package ShortestPath;

import java.util.*;

public class FindCheapestFlights {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // Step 1: Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] f : flights) {
            adj.get(f[0]).add(new int[]{f[1], f[2]});
        }

        // Step 2: BFS queue -> {city, cost_so_far, stops_used}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0, 0});

        // Step 3: Distance array (min cost to reach each city)
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 4: BFS traversal
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int city = curr[0];
            int cost = curr[1];
            int stops = curr[2];

            // If stops exceed k, skip
            if (stops > k) continue;

            // Explore neighbors
            for (int[] next : adj.get(city)) {
                int nextCity = next[0];
                int price = next[1];

                // Relax edge if cheaper path found
                if (cost + price < dist[nextCity]) {
                    dist[nextCity] = cost + price;
                    queue.offer(new int[]{nextCity, cost + price, stops + 1});
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

public class Main {
    public static void main(String[] args) {

        int n = 4;
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };

        int src = 0, dst = 3, k = 1;

        FindCheapestFlights sol = new FindCheapestFlights();
        int result = sol.findCheapestPrice(n, flights, src, dst, k);

        System.out.println("Cheapest Price: " + result);
    }
}
