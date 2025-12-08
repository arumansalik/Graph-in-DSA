package Problems;

import java.util.*;

public class CourseSchedule2UsingDFS {
    static List<Integer> order = new ArrayList<>();
    static boolean hasCycle = false;

    public static int[] findOrder(int numCourses, int[][] preRequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());


        for(int[] pre : preRequisites)
            adj.get(pre[1]).add(pre[0]);

        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            if(visited[i] == 0) {
                dfs(i, adj, visited);
            }
            if (hasCycle) return new int[0];
        }

        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    private static void dfs(int node, List<List<Integer>> adj, int[] visited) {
        if(hasCycle) return;

        visited[node] = 1;

        for (int nei: adj.get(node)) {
            if (visited[nei] == 0) dfs(nei, adj, visited);
            else if (visited[nei] == 1) {
                hasCycle = true;
                return;
            }
        }
        visited[node] = 2;
        order.add(node);
    }
    public static void main(String[] args) {
        int num = 4;
        int[][] prereq = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println(Arrays.toString(findOrder(num, prereq)));
    }
}
