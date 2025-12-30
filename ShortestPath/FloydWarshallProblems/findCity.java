package ShortestPath.FloydWarshallProblems;

public class findCity {
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = (int)1e9;
        int[][] dist = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        for(int[] e: edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(dist[i][k] + dist[k][j] > dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minCount = n;
        int answerCity = -1;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if(i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if(count <= minCount) {
                minCount = count;
                answerCity = i;
            }
        }
        return answerCity;
    }
}
