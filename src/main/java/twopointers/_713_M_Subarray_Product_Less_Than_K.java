package twopointers;

/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * Author:   softtwilight
 * Date:     2020/05/17 22:32
 */
public class _713_M_Subarray_Product_Less_Than_K {
    private static final _713_M_Subarray_Product_Less_Than_K instance = new _713_M_Subarray_Product_Less_Than_K();

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 1;
        System.out.println(instance.numSubarrayProductLessThanK(nums, k));
    }

    /**
     * 用滑动窗口的解法
     * 实际上关键的是 result += hi - lo + 1;这一步
     * 因为新增的解法就是窗口的长度。
     * 比如说窗口中现在的数是 a b c d
     * 那么加入e， 新增的集合是5个  （e, ed, edc, edcb, edcba)依次乘1。
     * 不用考虑窗口收缩与否。
     * 实际上很多时候都要寻找这种不变量， invariant. 这个是让解法更清晰更简单的方式。
     * 找到共同点， 然后抽象，而不是用if else 去cover分支。
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k <= 1) return 0;
        int lo = 0, hi = 0;
        int prod = 1;
        int result = 0;
        while (hi < nums.length) {
            prod *= nums[hi];
            while (prod >= k && lo <= hi) {
                prod /= nums[lo];
                lo++;
            }
            result += hi - lo + 1;
            hi++;
        }
        return result;
    }
}
