package MinimumSpanningTree;

import java.util.*;

public class PrimsAlgorithm {
    static class Pair {
        int node;
        int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static int primsMST(int V, List<List<Pair>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        boolean[] visited = new boolean[V];
        int mstCost = 0;

        pq.offer(new Pair(0, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int wt = curr.weight;

            if(visited[node]) continue;

            visited[node] = true;
            mstCost += wt;

            for(Pair neigh : adj.get(node)) {
                if(!visited[neigh.node]) {
                    pq.offer(neigh);
                }
            }
        }
        return mstCost;
    }

    public static void main(String[] args) {
        int V = 3;
        List<List<Pair>> adj = new ArrayList<>();

        for(int  i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 2));
        adj.get(1).add(new Pair(0, 2));

        adj.get(0).add(new Pair(2, 3));
        adj.get(2).add(new Pair(0, 3));

        adj.get(1).add(new Pair(2, 1));
        adj.get(2).add(new Pair(1, 1));

        System.out.println("MST cost: " + primsMST(V, adj));
    }
}
