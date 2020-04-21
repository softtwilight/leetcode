package array;

import java.util.Arrays;

/**
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 *
 * Author:   softtwilight
 * Date:     2020/04/21 22:48
 */
public class _34_Search_Range_In_SortedArr {

    public static void main(String[] args) {
        _34_Search_Range_In_SortedArr instance = new _34_Search_Range_In_SortedArr();
        int[] input1 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(instance.searchRange(input1, 8)));
        System.out.println(Arrays.toString(instance.searchRange(input1, 6)));

    }



    /**
     * runtime complexity must be in the order of O(log n).
     * 所以这里在找边界的时候就不同依次移动，依然使用二分查找.
     * use 0 ms	+ 42.5 MB
     *
     * 这里的关键就是相等的情况也要继续二分查找，查找的终点是触及边界
     * 或者左边边界前一个数小于target， 右边边界后一个数大于target。
     *
     */
    public int[] searchRange(int[] nums, int target) {

        int left = search(nums, 0, nums.length - 1, target, true);
        if (left == -1) {
            return new int[] {-1, -1};
        }
        int right = search(nums, 0, nums.length - 1, target, false);
        return new int[] {left, right};
    }

    public int search(int[] nums, int lo, int hi, int target, boolean isLeft) {

        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] < target) {
            return search(nums, mid + 1, hi, target, isLeft);
        }
        if (nums[mid] > target) {
            return search(nums, lo, mid - 1, target, isLeft);
        }

        if (isLeft) {
            if (mid == lo || nums[mid - 1] < target) {
                return mid;
            }
            return search(nums, lo, mid - 1, target, true);
        } else {
            if (mid == hi || nums[mid + 1] > target) {
                return mid;
            }
            return search(nums, mid + 1, hi, target, false);
        }


    }

}
