package Problems;

import java.util.*;

public class SurroundedRegions {

    public static void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Add boundary 'O's to queue
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') queue.offer(new int[]{i, 0});
            if (board[i][n - 1] == 'O') queue.offer(new int[]{i, n - 1});
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') queue.offer(new int[]{0, j});
            if (board[m - 1][j] == 'O') queue.offer(new int[]{m - 1, j});
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // Step 2: BFS to mark safe regions
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'O')
                continue;

            board[r][c] = 'S'; // mark safe

            for (int[] d : dirs) {
                queue.offer(new int[]{r + d[0], c + d[1]});
            }
        }

        // Step 3: Flip surrounded regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'S') board[i][j] = 'O';
            }
        }
    }

    public static void main(String[] args) {

        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };

        solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
