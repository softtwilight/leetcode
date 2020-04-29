package banarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 *
 * Author:   softtwilight
 * Date:     2020/04/29 21:56
 */
public class _153_Find_Min_in_Rotated_Arr {

    public static void main(String[] args) {
        int[] input = {2,1};
        System.out.println(findMin(input));
    }


    /**
     * 这个二分查找的关键是判断跟第一个值的关系来判断向左还是向右所以边界。
     * ps （第一次提交{2, 1}的case错了，因为只判断了 > nums[0]和小于的情况，漏掉=的情况
     *      nums[0]是一个很特殊的点，要全量考虑）
     *
     * leetcode 上看到一个更简洁一点的版本，考虑的边界条件不一样。
     * 0 ms	+ 38.9 MB
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = nums.length - 1;
        int result = nums[0];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            result = Math.min(result, nums[mid]);
            if (nums[mid] >= nums[0]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }
    /**
     * 1. 循环条件是left < right, 所以mid < right;
     * 2. 所以nums[mid] != nums[right]
     * 3. 左移的时候right = mid， 因为1保证了可以缩小范围。
     * 4. 右移 left = mid + 1
     * 5. 循环退出条件是left = right = 最低点坐标
     *      因为二分查找保证最后一次移动的步数是1，从最接近最小值的地方移动到最小值。
     */
    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }


}
