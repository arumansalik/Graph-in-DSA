package ShortestPath;

public class FloydWarshall {
    static final int INF = (int)1e9;

    public static void floydWarshall(int[][] dist, int V) {

        for(int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if(dist[i][i] < 0) {
                System.out.println("Negative cycle detected");
                return;
            }
        }
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] dist = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };

        floydWarshall(dist, V);

        System.out.println("All-pairs shortest paths:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(
                        (dist[i][j] == INF ? "INF" : dist[i][j]) + " "
                );
            }
            System.out.println();
        }
    }
}
