package banarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 *
 * Author:   softtwilight
 * Date:     2020/04/29 21:56
 */
public class _154_Find_Min_in_Rotated_Arr_2 {


    /**
     * 	1 ms  +	39.2 MB
     * 1ms只比15%的人快，我也是晕晕的。
     * 实际上比我快的解法在相等的时候用的是hi--的方法。
     *
     * 本来想区分nums[lo] == nums[hi], 如果不等，在相等的情况下
     * 我们还是可以将mid赋给hi，因为最小值还在左边。
     *
     * 而相等的情况下，我们用递归去求解lo～mid， 和 mid ～ hi两段。
     * 但是实际上这里总是会遍历都是相等的那部分。并没有比直接--有更好的performace
     * 在多线程条件下可能会有优势。
     *
     * 这里如果都相等，我们只能遍历完所有的点才能找到最小值。
     *
     * 换为hi--
     * 如果不递归，那么helper其实也不用了，直接写在一个方法里就可以。
     *
     */
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        return helper(nums, lo, hi);
    }

    public int helper(int[] nums, int lo, int hi) {
        boolean f = nums[lo] == nums[hi];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) {
                hi = mid;
            } else if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {  // nums[mid] = nums[hi]
                if (f) { //
//                    return Math.min(helper(nums, lo, mid - 1), helper(nums, mid + 1, hi));
                    hi--;
                } else {
                    hi = mid;
                }
            }
        }
        return nums[lo];
    }


}
