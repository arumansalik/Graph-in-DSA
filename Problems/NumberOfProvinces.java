package Problems;

public class NumberOfProvinces {
    public static int findCircleNum(int[][] connected) {
        int n = connected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for(int  i = 0; i < n; i++) {
            if(!visited[i]) {
                provinces++;
                dfs(i, connected, visited);
            }
        }
        return provinces;
    }

    private static void dfs(int i, int[][] connected, boolean[] visited) {
        visited[i] = true;

        for (int j = 0; j < connected.length ; j++) {
            if(connected[i][j] == 1 && !visited[j]) {
                dfs(j, connected, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] connected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        System.out.println("Number of provinces: " + findCircleNum(connected));
    }
}
