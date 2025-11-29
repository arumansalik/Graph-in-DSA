package Problems;

import java.util.*;

public class SurroundedRegions {
    public static void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            if(board[i][0] == 'O') dfs(board, i, 0);
            if(board[i][n-1] == 'O') dfs(board, i, n - 1);
        }

        for (int j =0; j < n; j++) {
            if(board[0][j] == 'O') dfs(board, 0, j);
            if(board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == 'S') board[i][j] = 'O';
            }
        }
    }

    public static void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;

        if(i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O') return;

        board[i][j] = 'S';

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
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
