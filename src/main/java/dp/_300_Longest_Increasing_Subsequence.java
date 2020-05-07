package dp;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * Author:   softtwilight
 * Date:     2020/05/06 21:57
 */
public class _300_Longest_Increasing_Subsequence {

    private static final _300_Longest_Increasing_Subsequence instance = new _300_Longest_Increasing_Subsequence();
    public static void main(String[] args) {
        int[] input = {10,9,2,5,3,4};
        System.out.println(instance.lengthOfLIS(input));
    }


    /**
     * 暴力的解法
     *
     * 递归
     * 对于一点来说，存在两种情况，取这个点或者不取这个点。
     * 然后所以存在2^n这么多情况，我们可以遍历所有这些列，然后计算是递增序列里的最长值。
     * （如果是小于前一个值，那我们修剪掉取这个值的分支）
     * 但是整体的复杂度还是O(2^n)
     */
    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        int nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }


    /**
     * 递归版本
     */
    public int lengthOfLIS2(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS2(nums, -1, 0, memo);
    }
    public int lengthofLIS2(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS2(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS2(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }

    /**
     * memo[i]的含义是以nums[i]结尾的数列包含的最长递增数列的长度。
     * 这个是动态规划中特别重要的状态（state），也就是子问题的解。
     *
     * 然后我们可以遍历（0 ～ i），如果nums[i] > nums[j], 那么在j的基础上又加上了1,
     * 然后我们求这个遍历过程中的最大值，放入memo。
     *
     * j的值是独立与i的值， 而i依赖j的值。
     *
     * 这样我们就从前往后构造了解。
     * 最后的result就是 memo中的最大值。
     *
     */
    public int lengthOfLIS3(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
            result = Math.max(result, memo[i]);
        }
        return result;
    }

}
