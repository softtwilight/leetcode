package twopointers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 *
 * Author:   softtwilight
 * Date:     2020/05/15 23:39
 */
public class _15_M_3Sum {
    private static final _15_M_3Sum instance = new _15_M_3Sum();

    public static void main(String[] args) {
        System.out.println(instance.threeSum(new int[] {0, 0}));

    }


    /**
     * 22 ms  +	42.9 MB
     * 求和问题，用双指针做的，需要排序先。用HashMap表现应该会更好。
     *
     * 遍历排序后的数组，然后用首位两个指针找和为-nums[i]的数；
     * 思路简单，但是边界条件和不重复的要求是最麻烦的。
     * 需要小心的判定范围和移动指针，去掉重复的元素。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            while (i > 0  && i < nums.length && nums[i - 1] == nums[i]) {
                i++;
            }
            if (i >= nums.length || nums[i] > 0) break;
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[hi] < 0) break;
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                }
                if (sum <= 0) {
                    while (lo < hi && nums[lo + 1] == nums[lo]) {
                        lo++;
                    }
                    lo++;
                }

                if (sum >= 0){
                    while (lo < hi && nums[hi - 1] == nums[hi]) {
                        hi--;
                    }
                    hi--;
                }
            }
        }
        return result;
    }
}
