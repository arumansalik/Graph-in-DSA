package TopoLogicalSorting.Problems;

import java.util.*;

public class SafeStates {

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        List<List<Integer>> reverse = new ArrayList<>();
        for (int i = 0; i < n; i++) reverse.add(new ArrayList<>());

        int[] outdegree = new int[n];

        for (int i = 0; i < n; i++) {
            outdegree[i] = graph[i].length;
            for (int nei : graph[i]) {
                reverse.get(nei).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] safe = new boolean[n];

        for (int i = 0; i < n; i++)
            if (outdegree[i] == 0)
                q.offer(i);

        while (!q.isEmpty()) {
            int node = q.poll();
            safe[node] = true;

            for (int prev : reverse.get(node)) {
                outdegree[prev]--;
                if (outdegree[prev] == 0)
                    q.offer(prev);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (safe[i])
                ans.add(i);

        return ans;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1,2},
                {2,3},
                {5},
                {0},
                {5},
                {},
                {}
        };

        System.out.println(eventualSafeNodes(graph));
    }
}



