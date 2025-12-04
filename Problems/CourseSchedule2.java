package Problems;

import java.util.*;

public class CourseSchedule2 {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            adj.get(b).add(a);
            indegree[a]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        int[] result = new int[numCourses];
        int index = 0;

        while (!q.isEmpty()) {
            int course = q.poll();
            result[index++] = course;

            for (int neighbor : adj.get(course)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }

        if (index == numCourses) return result;
        return new int[0];
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }
}
