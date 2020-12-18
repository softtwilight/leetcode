package mit_6006;

/**
 * Author:   softtwilight
 * Date:     2020/12/18 21:45
 *
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class _329_H_Longest_Increasing_Path_in_a_Matrix {
    private static final _329_H_Longest_Increasing_Path_in_a_Matrix instance = new _329_H_Longest_Increasing_Path_in_a_Matrix();

    public static void main(String[] args) {
        int[][] input = {
                {3,4,5},
                {3,2,6},
                {2,2,1}
        };
        System.out.println(instance.longestIncreasingPath(input));
    }

    /**
     * The first version timeout. We can remember the path length already passed.
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] cache = new int[row][col];
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result = Math.max(result, dfs(i, j, matrix, cache));
            }
        }
        return result;

    }

    public final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int i, int j, int[][] matrix, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int result = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (indexOk(x, y, matrix)) {
                if (matrix[x][y] > matrix[i][j]) {
                    result = Math.max(result, dfs(x, y, matrix, cache));
                }
            }
        }
        cache[i][j] = result + 1;
        return result + 1;
    }

    private boolean indexOk(int i, int j, int[][] matrix) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return false;
        }
        return true;
    }
}