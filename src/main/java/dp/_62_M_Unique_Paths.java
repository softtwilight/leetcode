package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/19 19:58
 *
 * https://leetcode.com/problems/unique-paths/
 */
public class _62_M_Unique_Paths {
    private static final _62_M_Unique_Paths instance = new _62_M_Unique_Paths();

    public static void main(String[] args) {
        System.out.println(instance.uniquePaths(51, 9));
        System.out.println(instance.uniquePathsByDP(51, 9));
    }

    /**
     * 不用DP解的， 这是个排列问题
     *
     * 一共需要移动 m + n - 2 次， 然后其中 n - 1 次右移， 问有多少种移动方法
     * (m + n - 2)!/(m - 1! *n - 1!)
     *
     * 需要注意的是结果是int， 如果算阶乘  小数从高到底， 那么可能不整除， 从低到高， 可能超过int的范围
     * 这里用long 保存结果， 然后小数从低到高
     */
    public int uniquePaths(int m, int n) {
        if (m < n) return uniquePaths(n, m);
        int sum = m + n - 2;
        int i = n - 1;
        long result = 1;
        while (i > 0) {
            result *= sum;
            result /= (n - i);
            sum--;
            i--;
        }
        return (int) result;
    }

    /**
     * solved by top-down dp
     */
    public int uniquePathsByDP(int m, int n) {
        int[][] memo = new int[m][n];
        return helper(m - 1, n - 1, memo);
    }

    private int helper(int m, int n, int[][] memo) {
        if (m == 0 || n == 0) return 1;
        if (memo[m][n] != 0) return memo[m][n];
        int result = helper(m - 1, n, memo) + helper(m, n - 1, memo);
        memo[m][n] = result;
        return result;
    }
}
