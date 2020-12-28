package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/28 22:09
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class _188_M_Best_Time_to_Buy_and_Sell_Stock_IV {
    private static final _188_M_Best_Time_to_Buy_and_Sell_Stock_IV instance = new _188_M_Best_Time_to_Buy_and_Sell_Stock_IV();

    public static void main(String[] args) {
        int[] inputs = {3,2,6,5,0,3};
        System.out.println(instance.maxProfit(3, inputs));
    }

    /**
     * n^2 * k 的复杂度， 很容易想， 但是太慢了
     */
    public int maxProfit(int k, int[] prices) {
        int[][] memo = new int[k + 1][prices.length];
        return dp(k, 0, prices, memo);
    }

    private int dp(int k, int i, int[] prices, int[][] memo) {
        if (k == 0) return 0;
        if (i >= prices.length - 1) return 0;
        if (memo[k][i] != 0) return memo[k][i] - 1;
        // if buy
        int buy = 0;
        for (int j = i + 1; j < prices.length; j++) {
            // sell at j
            if (prices[j] > prices[i]) {
                buy = Math.max(buy, dp(k - 1, j, prices, memo) + prices[j] - prices[i]);
            }
        }
        int noBuy = dp(k, i + 1, prices, memo);
        memo[k][i] =  Math.max(buy, noBuy) + 1;
        return memo[k][i] - 1;
    }

}
