package dp;

/**
 * Author:   softtwilight
 * Date:     2021/01/09 21:45
 *
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class _1155_M_Number_of_Dice_Rolls_With_Target_Sum {
    private static final _1155_M_Number_of_Dice_Rolls_With_Target_Sum instance = new _1155_M_Number_of_Dice_Rolls_With_Target_Sum();

    public static void main(String[] args) {
        System.out.println(instance.numRollsToTarget(30, 30, 500));
        System.out.println(instance.numRollsToTarget(2, 6, 12));
    }

    /**
     * dp for recursive
     */
    public int numRollsToTarget(int d, int f, int target) {
        int[][] memo = new int[d + 1][target + 1];
        return dp(d, f, target, memo);
    }

    private static final int MASK = 1000000007;

    private int dp(int d, int f, int target, int[][] memo) {
        if (target == 0 && d == 0) return 1;

        // optimize the return
        if (d < 0 || target < 0 || target < d || target > f * d){
            return 0;
        }
        if (memo[d][target] != 0) return memo[d][target] - 1;
        int result = 0;

        for (int i = 1; i <= f; i++) {
            result += dp(d - 1, f, target - i, memo);
            result %= MASK;
        }
        memo[d][target] = result + 1;
        return memo[d][target] - 1;
    }
}
