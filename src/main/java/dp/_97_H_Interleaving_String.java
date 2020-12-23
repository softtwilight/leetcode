package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/23 23:17
 *
 * https://leetcode.com/problems/interleaving-string/
 */
public class _97_H_Interleaving_String {
    private static final _97_H_Interleaving_String instance = new _97_H_Interleaving_String();

    public static void main(String[] args) {
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
//        String s1 = "", s2 = "", s3 = "";
        System.out.println(instance.isInterleave(s1, s2, s3));
    }

    /**
     * recursive
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return dp(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), memo);
    }

    private boolean dp(char[] s1, int i, char[] s2, int j, char[] s3, int[][] memo) {

        int k = i + j;
        if (k >= s3.length) return true;
        boolean result = false;
        if (memo[i][j] != 0) return 1 == memo[i][j];
        if (i >= s1.length) {
            result = Arrays.equals(s2, j, s2.length, s3, k, s3.length);
        } else if (j >= s2.length) {
            result = Arrays.equals(s1, i, s1.length, s3, k, s3.length);
        } else {
            if (s2[j] != s3[k] && s1[i] != s3[k]) {
//                result = false;
            } else if (s2[j] == s3[k] && s1[i] != s3[k]) {
                result = dp(s1, i, s2, j + 1, s3, memo);
            } else if (s2[j] != s3[k] && s1[i] == s3[k]) {
                result = dp(s1, i + 1, s2, j, s3, memo);
            } else {
                result = dp(s1, i, s2, j + 1, s3, memo) || dp(s1, i + 1, s2, j , s3, memo);
            }
        }
        memo[i][j] = result ? 1 : -1;
        return result;

    }
}
