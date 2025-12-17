package ShortestPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Tuple {
    int dist, row, col;

    Tuple(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}

public class ShortestPathInGrid {
    public static int shortestPath(int[][] grid, int[] source, int[] destination) {
        int n = grid.length;
        int m = grid[0].length;

        if(grid[source[0]][source[1]] == 1) return -1;
        if (source[0] == destination[0] && source[1] == destination[1]) return 0;

        int[][] dist = new int[n][m];
        for (int[] row : dist)
            Arrays.fill(row, (int)1e9);

        Queue<Tuple> q = new LinkedList<>();
        dist[source[0]][source[1]] = 0;
        q.add(new Tuple(0, source[0], source[1]));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while(!q.isEmpty()) {
             Tuple curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dr[i];
                int nc = curr.col + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0 && curr.dist + 1 < dist[nr][nc]) {
                    dist[nr][nc] = curr.dist + 1;

                if(nr == destination[0] && nc == destination[1]) {
                    return curr.dist + 1;
                }

                q.add(new Tuple(curr.dist + 1, nr, nc));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        int[] source = {0, 1};
        int[] dest = {2, 2};

        System.out.println(shortestPath(grid, source, dest));
    }

}
