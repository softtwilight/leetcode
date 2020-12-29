package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/29 21:27
 */
public class _221_M_Maximal_Square {
    private static final _221_M_Maximal_Square instance = new _221_M_Maximal_Square();

    public static void main(String[] args) {
        char[][] input = {{'0', '1'}, {'1', '0'}};
        System.out.println(instance.maximalSquare(input));
    }

    /**
     *
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 多一列，多一行， 不用initialize了
        int[][] memo = new int[m + 1][n + 1];
        int result = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    // System.out.println("aa");
                    memo[i][j] = 1 + Math.min(memo[i+1][j+1], Math.min(memo[i + 1][j], memo[i][j + 1]));
                    result = Math.max(memo[i][j], result);
                }
            }
        }
        return  result * result;
    }
}
