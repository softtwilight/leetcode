package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/21 23:24
 *
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class _1143_M_Longest_Common_Subsequence {
    private static final _1143_M_Longest_Common_Subsequence instance = new _1143_M_Longest_Common_Subsequence();

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ffggce";
        System.out.println(instance.longestCommonSubsequence(text1, text2));
    }

    /**
     * recursive approach,
     */
    public int longestCommonSubsequence(String text1, String text2) {

        int[][] memo = new int[text1.length()][text2.length()];
        // because the default value is 0, so if the result is 0, the computation will execute.
        // witch increases complexity to e theory, init as -1
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return dp(0, 0, text1.toCharArray(), text2.toCharArray(), memo);
    }

    private int dp(int i, int j, char[] text1, char[] text2, int[][] memo) {
        if (i == text1.length || j == text2.length) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int result;
        if (text1[i] == text2[j]) {
            result = 1 + dp(i + 1, j + 1, text1, text2, memo);
        } else {
            result = Math.max(dp(i + 1, j, text1, text2, memo),
                    dp(i, j + 1, text1, text2, memo));
        }
        memo[i][j] = result;
        return result;
    }
}
