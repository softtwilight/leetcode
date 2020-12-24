package dp;

import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/24 17:51
 *
 * https://leetcode.com/problems/triangle/
 */
public class _120_M_Triangle {
    private static final _120_M_Triangle instance = new _120_M_Triangle();

    public static void main(String[] args) {

        System.out.println(instance);
    }

    /**
     * recursive approach
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] memo = new int[triangle.size()][triangle.size()];
        return dp(0, 0, triangle, memo);
    }

    private int dp(int layer, int i, List<List<Integer>> triangle, int[][] memo) {
        if (layer == triangle.size()) return 0;
        if (memo[layer][i] != 0) return memo[layer][i];
        int result = triangle.get(layer).get(i);
        result += Math.min(dp(layer + 1, i, triangle, memo),
                dp(layer + 1, i + 1, triangle, memo));
        memo[layer][i] = result;
        return result;
    }
}
