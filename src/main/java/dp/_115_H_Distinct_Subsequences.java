package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/24 17:25
 *
 * https://leetcode.com/problems/distinct-subsequences/
 */
public class _115_H_Distinct_Subsequences {
    private static final _115_H_Distinct_Subsequences instance = new _115_H_Distinct_Subsequences();

    public static void main(String[] args) {
//        String s = "rabbbit", t = "rabbit";
        String s = "babgbag", t = "bag";
        System.out.println(instance.numDistinct(s, t));
    }

    /**
     * recursive approach the #sub problem is |s|*|t|
     * the time of subproblem is O(1)
     * time total time is O(|s|*|t|)
     *
     * The default value of int array is 0, the dp result maybe 0,
     * so we add one to every result to different memo hits from default value.
     * No further initialize.
     */
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        return dp(s.toCharArray(), 0, t.toCharArray(), 0, memo);
    }

    private int dp(char[] s, int i, char[] t, int j, int[][] memo) {
        if (j == t.length) return 1;
        if (i == s.length) return 0;
        if (memo[i][j] != 0) return memo[i][j] -1;
        int result;
        if (s[i] == t[j]) {
            result = dp(s, i + 1, t, j, memo) + dp(s, i + 1, t, j + 1, memo);
        } else {
            result = dp(s, i + 1, t, j, memo);
        }
        memo[i][j] = result + 1;
        return result;
    }
}
