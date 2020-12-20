package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/20 21:01
 *
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class _64_M_Minimum_Path_Sum {
    private static final _64_M_Minimum_Path_Sum instance = new _64_M_Minimum_Path_Sum();

    public static void main(String[] args) {
        int[][] input = {{1,2,3},{4,5,6}};
        System.out.println(instance.minPathSum(input));
    }

    /**
     *
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        return helper(0, 0, grid, memo);
    }

    private int helper(int i, int j, int[][] grid, int[][] memo) {
        if (i >= grid.length || j >= grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        if (memo[i][j] != 0) return memo[i][j];
        int result = Math.min(helper(i + 1, j, grid, memo), helper(i, j + 1, grid, memo)) + grid[i][j];
        memo[i][j] = result;
        return result;
    }

}
