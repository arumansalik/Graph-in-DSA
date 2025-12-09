package TopoLogicalSorting;

import java.util.*;

public class KahnsTopoSort {
    public static List<Integer> topoSort(int numNodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numNodes];

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numNodes; i++) {
            if(indegree[i] == 0)
                q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();

        while(!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);

            for(int neighbour: graph.get(node)) {
                indegree[neighbour]--;
                if(indegree[neighbour] == 0)
                    q.offer(neighbour);
            }
        }

        if (topo.size() != numNodes) {
            System.out.println("Cycle detected - No Topological Order");
            return new ArrayList<>();
        }

        return topo;
    }

    public static void main(String[] args) {
        int numNodes = 6;
        int[][] edges = {
                {5, 2},
                {5, 0},
                {4, 0},
                {4, 1},
                {2, 3},
                {3, 1}
        };

        List<Integer> result = topoSort(numNodes, edges);
        System.out.println("Topological Sort (Kahn's  Algorithm): " + result);
    }
}
