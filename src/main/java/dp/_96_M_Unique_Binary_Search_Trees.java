package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/22 22:44
 */
public class _96_M_Unique_Binary_Search_Trees {
    private static final _96_M_Unique_Binary_Search_Trees instance = new _96_M_Unique_Binary_Search_Trees();

    public static void main(String[] args) {
        System.out.println(instance.numTrees(4));
    }

    /**
     * recursive
     *
     * choose the root, then compute #left tree * #right tree, sum up;
     * the subtree will save the result for later use.
     */
    public int numTrees(int n) {
        int[] memo = new int[n + 1];
        return dp(n, memo);
    }

    private int dp(int n, int[] memo) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (memo[n] != 0) return memo[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            int left = i - 0;
            int right = n - 1 - i;
            result += dp(left, memo) * dp(right, memo);
        }
        memo[n] = result;
        return result;
    }

}
