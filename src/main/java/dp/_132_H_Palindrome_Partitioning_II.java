package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/25 23:40
 */
public class _132_H_Palindrome_Partitioning_II {
    private static final _132_H_Palindrome_Partitioning_II instance = new _132_H_Palindrome_Partitioning_II();

    public static void main(String[] args) {
        System.out.println(instance.minCut("abac"));
    }

    /**
     *
     */
    public int minCut(String s) {
        int[] memo = new int[s.length()];
        return dp(0, s, memo);
    }

    private int dp(int i, String s, int[] memo) {
        if (i == s.length() - 1) return 0;
        if (i == s.length()) return -1;
        if (memo[i] != 0) return memo[i] - 1;
        int result = memo.length;
        for (int hi = i; hi < s.length(); hi++) {
            if (isPalindrome(i, hi, s)) {
                result = Math.min(dp(hi + 1, s, memo) + 1, result);

            }
        }
        memo[i] = result + 1;
        return result;
    }

    boolean isPalindrome(int lo, int hi, String s) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}
