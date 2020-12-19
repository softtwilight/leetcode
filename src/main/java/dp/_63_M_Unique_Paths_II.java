package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/19 22:28
 */
public class _63_M_Unique_Paths_II {
    private static final _63_M_Unique_Paths_II instance = new _63_M_Unique_Paths_II();

    public static void main(String[] args) {
//        int[][] input = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] input = {{0,1},{0,0}};
        System.out.println(instance.uniquePathsWithObstacles2(input));
    }

    /**
     * 用recursive来解的DP
     *
     * 实际上这里， obstacle的点剔除之后建立一个新数组， 就完全是62的问题了。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        memo[m - 1][n - 1] = 1;
        return helper(0, 0, obstacleGrid, memo);
    }

    private int helper(int m, int n, int[][] obstacleGrid, int[][] memo) {
        if (m == obstacleGrid.length || n == obstacleGrid[0].length) return 0;
        if (obstacleGrid[m][n] == 1) return 0;
        if (memo[m][n] != 0) return memo[m][n];
        int result = helper(m + 1, n, obstacleGrid, memo) + helper(m, n + 1, obstacleGrid, memo);
        memo[m][n] = result;
        return result;
    }


    /**
     * the path of i j = P(i-1, j) + P (i, j-1)
     * 遍历第一行和第一列， 构建init value
     * 如果是障碍， #path = 0
     *
     * 可以利用原来的数组，来bottom - up DP solution
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}
