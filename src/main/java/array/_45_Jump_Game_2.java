package array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * You can assume that you can always reach the last index.
 *
 * Author:   softtwilight
 * Date:     2020/04/18 22:10
 */
public class _45_Jump_Game_2 {

    public static void main(String[] args) {
        int[] input = {2,3,1,1,4};
        _45_Jump_Game_2 instance = new _45_Jump_Game_2();
        System.out.println(instance.jump2(input));
    }

    /**
     * 贪心解法
     */
    public int jump2(int[] nums) {
        int curEnd = 0, furestJump = 0, jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > nums.length - 1) {
                return jumps + 1;
            }
            furestJump = Math.max(furestJump, i + nums[i]); //找到在目前的区间内，能跳得最远的点。作为下一个区间。
            if (i == curEnd) {
                curEnd = furestJump; // 新区间为最大区间
                jumps++; // 区间结束，需要条约一次
            }
        }
        return jumps;

    }
    /**
     * 这是最开始的解法，思路是最后开始回退，用数组记录每个点的jump数，
     * 在i点，往前找num[i]，然后找出前面num[i]的最小值，加上1，就是i点的步数
     * 对i来说，jump到这个最小值所在的点就是自己的最小步数。
     * 最后返回jumps[0]即可。
     *
     * 但是这个过程中是有很多多余的查找，因为是回溯，所以每一个点都会查找自己的范围内最小点，
     * 但是如果在这个点以前，有某个点的x + num[x] > 此点的这个值，那么这个点的查找就是多余的。
     *
     * 我们从头开始去跳。
     * 实际上i要跳的点j，是在 i ～ i + num[i] 范围内，j + num[j]上最大的那个。
     *
     */
    public int jump(int[] nums) {
        int[] jumps = new int[nums.length];
        jumps[nums.length - 1] = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            jumps[i] = smallest(jumps, i, nums[i]) + 1;
        }
        return jumps[0];
    }

    // 找到范围内最小的值
    int smallest(int[] jumps, int lo, int hi) {
        if (lo + hi >= jumps.length - 1) {
            return 0;
        }
        int result = jumps.length;
        for (int i = lo + 1; i <= lo + hi; i++) {
            result = Math.min(result, jumps[i]);
        }
        return result;
    }
}
