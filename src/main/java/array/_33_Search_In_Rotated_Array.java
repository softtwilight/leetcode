package array;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: num = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: num = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Author:   softtwilight
 * Date:     2020/04/15 20:23
 */
public class _33_Search_In_Rotated_Array {

    public static void main(String[] args) {
        int[] numbs = {1,3};
        int target = 3;
        _33_Search_In_Rotated_Array instance = new _33_Search_In_Rotated_Array();
        System.out.println(instance.search(numbs, target));
    }

    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    /**
     * 此处用递归，也可以用while循环，改变两个指针。
     * 思路是通过判断target在最小点的左边还是右边，决定向左还是向右搜索。
     * leetcode上还有一种解法，先找到最小点，然后用标准二分查找。这种解法在复杂度上应该是更复杂的，最坏情况会两次查找整个数组。
     * 但是问题分解后非常好理解。
     * 以下是最小点的寻找方法：
     * public int findMinIdx(int[] nums) {
     *     int start = 0, end = nums.length - 1;
     *     while (start < end) {
     *         int mid = start + (end -  start) / 2;
     *         if (nums[mid] > nums[end]) start = mid + 1;
     *         else end = mid;
     *     }
     * 	return start;
     * }
     */
    public int search(int[]nums, int target, int lo, int hi) {
        if (lo > hi) return -1;
        boolean onLeft = target >= nums[0]; // 判断target是否在最小点的左边
        boolean onRight = target <= nums[nums.length - 1];// 判断target是否在最小点的右边
        boolean isRotated = nums[0] >= nums[nums.length - 1];//存在pivot为0的情况，也就是整个数组还是生序的

        if (!(onLeft || onRight)) {
            return  -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            if (nums[mid] <= nums[nums.length - 1] && onLeft && isRotated) { //小于目标值，mid在右边，target在左边，向左搜索。
                return search(nums, target, lo,  mid - 1);
            }
            return search(nums, target, mid + 1, hi);
        }else {
            if (nums[mid] >= nums[0] && onRight && isRotated) {//大于目标值，mid在左边，target在右边，向右搜索。
                return search(nums, target, mid + 1, hi);
            }
            return search(nums, target, lo,  mid - 1);
        }

    }
}
