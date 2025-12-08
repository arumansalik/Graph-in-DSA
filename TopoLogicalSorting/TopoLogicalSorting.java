package TopoLogicalSorting;

import java.util.*;

public class TopoLogicalSorting {
    public static List<Integer> topologicalSort(int numNodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        int[] state = new int[numNodes];
        List<Integer> topo = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            if(state[i] == 0) {
                if(!dfs(i, graph, state, topo)) {
                    System.out.println("Graph contains a cycle");
                    return new ArrayList<>();
                }
            }
        }
        Collections.reverse(topo);
        return topo;
    }

    public static boolean dfs(int node, List<List<Integer>> graph, int[] state, List<Integer> topo) {
        if(state[node] == 1) return false;
        if(state[node] == 2) return true;

        state[node] = 1;

        for(int neighbour: graph.get(node)) {
            if(!dfs(neighbour, graph, state, topo)) {
                return false;
            }
        }

        state[node] = 2;
        topo.add(node);
        return true;
    }
    public static void main(String[] args) {
        int numNodes = 6;

        // Example graph edges
        // 5 → 2, 5 → 0, 4 → 0, 4 → 1, 2 → 3, 3 → 1
        int[][] edges = {
                {5, 2},
                {5, 0},
                {4, 0},
                {4, 1},
                {2, 3},
                {3, 1}
        };

        List<Integer> result = topologicalSort(numNodes, edges);

        System.out.println("Topological Order: " + result);
    }

}
