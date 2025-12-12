package TopoLogicalSorting.Problems;

import java.lang.reflect.Array;
import java.util.*;

class Pair {
    int v, wt;
    Pair(int v, int wt) {
        this.v = v;
        this. wt = wt;
    }
}

public class DAGShortestPath {
    public static int[] shortestPath(int N, ArrayList<ArrayList<Pair>> adj, int src) {
        boolean[] visited = new boolean[N];
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < N; i++) {
            if(!visited[i]) topoSort(i, visited, st, adj);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;


        while(!st.isEmpty()) {
            int u = st.pop();

            if(dist[u] != Integer.MAX_VALUE) {
                for(Pair p : adj.get(u)) {
                    int v = p.v;
                    int wt = p.wt;

                    if(dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }
        return dist;
    }

    static void topoSort(int Node, boolean[] visited, Stack<Integer> st, ArrayList<ArrayList<Pair>> adj) {
        visited[Node] = true;

        for(Pair p : adj.get(Node)) {
            if(!visited[p.v]) {
                topoSort(p.v, visited, st, adj);
            }
        }

        st.push(Node);
    }

    public static void main(String[] args) {
        int N = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < N; i++) adj.add(new ArrayList<>());

        adj.get(0).add(new Pair(1, 2));
        adj.get(0).add(new Pair(4, 1));
        adj.get(1).add(new Pair(2, 3));
        adj.get(4).add(new Pair(2, 2));
        adj.get(2).add(new Pair(3, 6));

        int[] res = shortestPath(N, adj, 0);

        System.out.println("Shortest distances from Node 0: ");
        System.out.println(Arrays.toString(res));
    }
}
