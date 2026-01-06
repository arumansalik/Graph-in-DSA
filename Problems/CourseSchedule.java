package Problems;

import java.util.*;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        // Step 1: Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Build indegree array
        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }

        // Step 3: Queue for courses with 0 indegree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: BFS
        int completed = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completed++;

            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // Step 5: Check if all courses are completed
        return completed == numCourses;
    }

    // ---------------- MAIN FUNCTION ----------------
    public static void main(String[] args) {

        // Example 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Can finish courses (Example 1): "
                + canFinish(numCourses1, prerequisites1)); // true

        // Example 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish courses (Example 2): "
                + canFinish(numCourses2, prerequisites2)); // false

        // Example 3
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println("Can finish courses (Example 3): "
                + canFinish(numCourses3, prerequisites3)); // true
    }
}
