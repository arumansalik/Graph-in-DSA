package Problems;

import java.util.*;

public class NumberOfEnclavesBFS {
    public static int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            if(grid[i][0] == 1) {
                q.add(new int[]{i, 0});
                grid[i][0] = 0;
            }
            if(grid[i][n - 1] == 1) {
                q.add(new int[]{i, n - 1});
                grid[i][n - 1] = 0;
            }
        }

        for(int j = 0; j < n; j++) {
            if(grid[0][j] == 1) {
                q.add(new int[]{0, j});
                grid[0][j] = 0;
            }
            if(grid[m - 1][j] == 1) {
                q.add(new int[]{m - 1, j});
                grid[m - 1][j] = 0;
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, 1}};


        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int x = cell[0], y = cell[1];

            for(int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && grid[nx][ny] == 1) {
                    grid[nx][ny] = 0;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1)
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

        System.out.println("Number of Enclaves: " + numEnclaves(grid));

    }
}
