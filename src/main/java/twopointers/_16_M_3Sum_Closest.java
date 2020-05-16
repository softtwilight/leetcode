package twopointers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 *
 * Author:   softtwilight
 * Date:     2020/05/16 21:58
 */
public class _16_M_3Sum_Closest {
    private static final _16_M_3Sum_Closest instance = new _16_M_3Sum_Closest();

    public static void main(String[] args) {
        int[] nums = {-1, 2, 3};
        int target = 1;
        System.out.println(instance.threeSumClosest(nums, target));
    }


    /**
     * 3 ms	+ 39.2 MB
     *
     * two pointer, 感觉比3sum 还要简单一点，因为不用考虑去重
     *
     * 一个小优化是，如果第一个数的值大于target， 而且之前有保存结果，提前break。
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > target && diff != Integer.MAX_VALUE) break;
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[hi] < target && diff != Integer.MAX_VALUE) break;
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target) {
                    return sum;
                }
                if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
                if (diff > Math.abs(target - sum)) {
                    diff = Math.abs(target - sum);
                    result = sum;
                }

            }
        }
        return result;
    }
}
