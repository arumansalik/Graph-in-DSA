package TopoLogicalSorting.Problems;

import java.util.*;


public class CourseSchedule2 {
    public static int[] findOrder(int numCourses, int[][] preRequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for(int[] pre: preRequisites) {
            int a = pre[0];
            int b = pre[1];
            graph.get(b).add(a);
            indegree[a]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0)
                q.offer(i);
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while(!q.isEmpty()) {
            int course = q.poll();
            order[idx++] = course;

            for(int next : graph.get(course)) {
                indegree[next]--;
                if(indegree[next] == 0)
                    q.offer(next);
            }
        }
        return idx == numCourses ? order : new int[0];
    }
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        };

        int[] result = findOrder(numCourses, prerequisites);
        System.out.println("Course Order: " + Arrays.toString(result));
    }
}
