package array;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 *
 * Author:   softtwilight
 * Date:     2020/04/16 21:53
 */
public class _55_Jump_Game {

    public boolean canJump(int[] nums) {
        boolean[] cache = new boolean[nums.length];
        return canJump(nums, nums.length - 1, cache);
    }
    /**
     * 思路是从终点往回走，往前走k步，在到达的位置上如果值>=k, 则通过这个点可以到达终点。
     * 那么我们对这个点递归求解，如果子问题可到达，则返回true
     * 如果子问题不可达，也就是这个往前走的k点不可达，那么我们继续往前走。
     * 然后利用一个cache，记录走过的不可行的点，如果已经验证过不可行，返回false。
     * 这其实是一道动态规划题目，leetcode上还有人用贪心算法求解的，空间复杂度更低为1。
     *
     * 求解动态规划问题的一般思路
     * 1.Start with the recursive backtracking solution
     * 2.Optimize by using a memoization table (top-down[2] dynamic programming)
     * 3.Remove the need for recursion (bottom-up dynamic programming)
     * 4.Apply final tricks to reduce the time / memory complexity
     *
     */
    public boolean canJump(int[] nums, int hi, boolean[] cache) {
        if (hi == 0) { //到达起点
            return true;
        }
        for (int i = hi - 1; i>=0; i--) {
            if (cache[i]) {
                return false;
            }
            int leftToJump = hi - i;
            if (nums[i] >= leftToJump) {
                if (canJump(nums, i, cache)) {
                    return true;
                }
            }
            cache[i] = true;

        }
        return false;
    }


    enum Index {
        GOOD, BAD, UNKNOWN
    }

    public boolean canJump2(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            // 正数数组，nums[i] >= 1, 所以可以jump的范围是 i+1 到 furthestJump, 如果能跳到的点是GOOD的点
            // 那么i也是good点
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    /**
     * 我们可以观察到，如果i是一个GOOD的点，我们不需要之后i之后的点是否是好的，因为如果能到达i，
     * 那么也能到达最后一点，所以我们只需要记录一个最近的GOOD点就好了。
     *
     * 只记录最近的点还有一个好处，如果要判断到最近的点的距离
     */
    public boolean canJump3(int[] nums) {

        int lastPosition = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= nums.length) {
                lastPosition = i;
            }
        }
        return lastPosition == 0;
    }

}
