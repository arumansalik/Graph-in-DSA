package Problems;

public class FloodFillDFS {
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int original = image[sr][sc];
        if (original == newColor) return image;

        dfs(image, sr, sc, original, newColor);
        return image;
    }

    private static void dfs(int[][] image, int sr, int sc, int original, int newColor) {
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) return;
        if(image[sr][sc] != original) return;

        image[sr][sc] = newColor;

        dfs(image, sr - 1, sc, original, newColor);
        dfs(image, sr + 1, sc, original, newColor);
        dfs(image, sr, sc - 1, original, newColor);
        dfs(image, sr, sc + 1, original, newColor);
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        floodFill(image, 1, 1, 2);

        System.out.println("After flood fill:");
        for (int[] row : image) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
