import java.util.*;

public class NumberOfDistinctIslands {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static int numberOfDistinctIslands(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Set<List<String>> shapes = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    List<String> shape = new ArrayList<>();
                    dfs(grid, visited, r, c, r, c, shape);
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }

    private static void dfs(int[][] grid, boolean[][] visited,
                            int r, int c, int baseR, int baseC,
                            List<String> shape) {

        visited[r][c] = true;
        shape.add((r - baseR) + "," + (c - baseC));

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length
                    && grid[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(grid, visited, nr, nc, baseR, baseC, shape);
            }
        }
    }

    // Test locally
    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0},
                {1,0,0,0},
                {0,0,1,1},
                {0,0,1,0}
        };

        System.out.println(numberOfDistinctIslands(grid)); // Output: 1
    }
}
