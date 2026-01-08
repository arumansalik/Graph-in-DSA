package Problems;

import java.util.*;

public class NumberOfEnclaves {

    public static int numEnclaves(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Add all boundary land cells
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) queue.offer(new int[]{i, 0});
            if (grid[i][n - 1] == 1) queue.offer(new int[]{i, n - 1});
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) queue.offer(new int[]{0, j});
            if (grid[m - 1][j] == 1) queue.offer(new int[]{m - 1, j});
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // Step 2: BFS to remove land connected to boundary
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0)
                continue;

            grid[r][c] = 0; // mark as water

            for (int[] d : dirs) {
                queue.offer(new int[]{r + d[0], c + d[1]});
            }
        }

        // Step 3: Count remaining land cells (enclaves)
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        System.out.println("Number of Enclaves = " + numEnclaves(grid));
    }
}
