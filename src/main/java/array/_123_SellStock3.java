package array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Hard
 * @author softtwilight 2020/01/31
 *
 */
public class _123_SellStock3 {

    /**
     * Runtime: 0 ms, faster than 100.00%
     * Memory Usage: 42.9 MB, less than 7.32%
     */
    public int maxProfit(int[] prices) {
        int[] cc = maxRange(0, prices.length, prices, false);
        int intervalMax = cc[2];
        if (intervalMax == 0) return 0;

        // find the max sell income of the part left the the best buy point.
        int leftMax = maxRange(0, cc[0], prices, false)[2];

        // find the max sell income of the part right the the best sell point.
        int rightMax = maxRange(cc[1], prices.length, prices, false)[2];

        // find the max income if split the best sell to two part
        int maxPriceReduction = maxRange(cc[0], cc[1], prices, true)[2];

        // use the max profit if the secend transaction is need.
        int sub = Math.max(leftMax, Math.max(rightMax, maxPriceReduction));
        return intervalMax + sub;

    }

    /**
     * find the best sell chance.
     * return {buyPoint, sellPoint, bestIncome} in array.
     */
    public int[] maxRange(int from, int to, int[] prices, boolean reverse) {
        if (from == to) {
            return new int[]{0, 0, 0};
        }
        int result = 0;
        int last = from;

        int l = from, r = to;
        for (int i = from; i < to; i++) {
            int c = prices[i] - prices[last];
            if (reverse) {
                c =  -c;
            }
            if (c > 0) {
                if (c > result) {
                    result = c;
                    l = last;
                    r = i;
                }
            } else {
                last = i;
            }
        }

        int[] combine = new int[3];
        combine[0] = l;
        combine[1] = r;
        combine[2] = result;
        return combine;

    }
}
