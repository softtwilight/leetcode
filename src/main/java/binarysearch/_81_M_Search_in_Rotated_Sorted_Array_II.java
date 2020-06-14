package binarysearch;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Author:   softtwilight
 * Date:     2020/06/15 0:13
 */
public class _81_M_Search_in_Rotated_Sorted_Array_II {
    private static final _81_M_Search_in_Rotated_Sorted_Array_II instance = new _81_M_Search_in_Rotated_Sorted_Array_II();

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 1};
        int target = 3;
        System.out.println(instance.search(nums, target));
    }

    /**
     * 0 ms	39.3 MB
     *
     * 好久没做binary-search 头晕啊
     * 这个实现太ugly了， 不忍直视。
     * 思路是先去掉首位相等的元素，因为相等的情况会让考虑左边还是右边时很麻烦
     *
     * 然后剔除掉首位相等的元素后，在用rotated_sort_array的解法
     */
    public boolean search(int[] nums, int target) {

        if (nums == null || nums.length == 0) return false;
        int lo = 0, hi = nums.length - 1;
        boolean atLeft = target > nums[0];
        if (target == nums[0]) return true;
        int temp = nums[0];
        boolean s = nums[0] == nums[hi];
        while (s && nums[lo] == temp) {
            lo++;
        }
        while (s && nums[hi] == temp) {
            hi--;
        }

        int begin = lo, end = hi;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int m;
            if (nums[mid] < nums[begin]&& atLeft) {
                m = Integer.MAX_VALUE;
            }
            else if (nums[mid] > nums[end] && !atLeft) {
                m = Integer.MIN_VALUE;
            } else {
                m = nums[mid];
            }
            if (m == target) {
                return true;
            }
            if (m < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo] == target;
    }
}
