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
        System.out.println(instance.uniquePathsWithObstacles(input));
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
}
