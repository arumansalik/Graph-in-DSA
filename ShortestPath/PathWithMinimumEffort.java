package ShortestPath;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {
    static class Cell {
        int effort, row, col;

        Cell(int e, int r, int c) {
            effort = e;
            row = r;
            col = c;
        }
    }


    public static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];
        for(int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.effort - b.effort);

        dist[0][0] = 0;
        pq.offer(new Cell(0, 0, 0));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while(!pq.isEmpty()) {
            Cell curr = pq.poll();
            int r = curr.row;
            int c = curr.col;
            int effort = curr.effort;

            if(r == n - 1 && c == m - 1) {
                return effort;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= 0 && nc >= 0 && nr < n && nc < m) {
                    int newEffort = Math.max(effort, Math.abs(heights[r][c] - heights[nr][nc]));

                    if(newEffort < dist[nr][nc]) {
                        dist[nr][nc] = newEffort;
                        pq.offer(new Cell(newEffort, nr, nc));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        int result = minimumEffortPath(heights);
        System.out.println("Minimum Effort Path = " + result);
    }
}
