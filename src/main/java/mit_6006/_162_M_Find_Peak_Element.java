package mit_6006;

/**
 * Author:   softtwilight
 * Date:     2020/10/22 21:33
 *
 * https://leetcode.com/problems/find-peak-element/
 *
 * 6006 第一课相关
 */
public class _162_M_Find_Peak_Element {
    private static final _162_M_Find_Peak_Element instance = new _162_M_Find_Peak_Element();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * one dimension array's peak.
     * 关键是理解问题，find a peak， 这个是可以转化为二分查找的。
     * 我们找中间值，比较mid 和 mid - 1， mid + 1的大小。
     * when nums[mid] < nums[mid-1] 时， 我们知道左边一定存在peak。
     *  1. mid-1 -> 0 一直递增， 那么nums[0] 是peak
     *  2. 不递增， 那么变小的那个数i， nums[i+1]是peak
     * same as 左边。
     */
    public int findPeakElement(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int lo, int hi) {
        if (hi <= lo) {
            return lo;
        }
        int mid = (hi - lo) / 2 + lo;
        // 这里mid 是可能等于lo的， 所以lo - 1可能 = -1， 判定边界情况。
        // 但是mid 一定是小于hi的， 所以hi的情况不用判定
        if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
            return helper(nums, lo, mid - 1);
        } else if (nums[mid] < nums[mid + 1]) {
            return helper(nums, mid + 1, hi);
        } else {
            return mid;
        }

    }
}
