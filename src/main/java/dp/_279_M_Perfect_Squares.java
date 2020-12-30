package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/30 21:22
 */
public class _279_M_Perfect_Squares {
    private static final _279_M_Perfect_Squares instance = new _279_M_Perfect_Squares();

    public static void main(String[] args) {
        System.out.println(instance.numSquares(4));
    }

    /**
     *
     */
    public int numSquares(int n) {
        int[] memo = new int[n + 1];
        return dp(n, memo);
    }

    private int dp(int n, int[] memo) {
        if (n == 0) return 0;
        if (memo[n] != 0) return memo[n];
        int result = n;
        for (int i = 1; i * i <= n; i++) {
            result = Math.min(result, dp(n - i * i, memo) + 1);
        }
        memo[n] = result;
        return result;
    }
}
