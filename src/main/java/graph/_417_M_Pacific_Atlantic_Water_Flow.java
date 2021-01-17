package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2021/01/17 22:27
 *
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class _417_M_Pacific_Atlantic_Water_Flow {
    private static final _417_M_Pacific_Atlantic_Water_Flow instance = new _417_M_Pacific_Atlantic_Water_Flow();

    public static void main(String[] args) {

        int[][] m = {{3,3,3},{3,1,3},{0,2,4}};
        System.out.println(instance.pacificAtlantic(m));
    }

    /**
     * dfs to map all reachable form Pacific and Atlantic
     * then build the result
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] memo = new boolean[m][n];
        boolean[][] memo2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dps(i, 0, matrix, memo);
            dps(i, n - 1, matrix, memo2);
        }
        for (int j = 0; j < n; j++) {
            dps(0, j, matrix, memo);
            dps(m - 1, j, matrix, memo2);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] && memo2[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void dps(int i, int j, int[][] matrix, boolean[][] memo) {
        if (memo[i][j]) {
            return;
        }
        //
        memo[i][j] = true;
        for (int[] m : move) {
            int ni = i + m[0];
            int nj = j + m[1];
            if (ni >= 0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length) {
                if (matrix[i][j] <= matrix[ni][nj]) {
                    dps(ni,nj, matrix, memo);
                }
            }
        }
    }


}
