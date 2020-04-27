package banarysearch;

import java.util.Arrays;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * Author:   softtwilight
 * Date:     2020/04/27 22:10
 */
public class _4_Median_of_Two_Sorted_Arrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays1(nums1, nums2));
    }


    /**
     * 二分查找的思路是有的，可是考虑边界条件有点太复杂了，有点理不清楚。
     * 所以试着用归并排序的思路先做一个简单的，但是我们不需要merge排序所有的数组
     * 只需要前（m + n）/ 2 即可；O（（m+n）/2）的复杂度
     *
     * 9 ms	+ 40.8 MB
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int l = (n + m) / 2;
        int[] merge = new int[l + 1];

        int j =0, i = 0;
        while (i + j <= l) {
            if (i == n) {
                merge[i + j] = nums2[j];
                j++;
            } else if (j == m) {
                merge[i + j] = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                merge[i + j] = nums1[i];
                i++;
            } else if (nums1[i] >= nums2[j]) {
                merge[i + j] = nums2[j];
                j++;
            }
        }
        if ((n + m) % 2 == 1){
            return merge[(n + m) / 2];
        } else {
            return (merge[(n + m) / 2] + merge[(n + m) / 2 - 1]) / 2.0;
        }
    }

    /**
     *
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int n = nums1.length, m = nums2.length;
        int sum = (n + m ) / 2 - 1;
        int lo = 0, hi = n - 1;

        int j =0, i = 0;
        while (lo < hi) {
            i = lo + (hi - lo) / 2;
            j = sum - i;
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] > nums2[j]) {
                hi = i - 1;
            } else if (nums1[i] > nums2[j]){
                lo = i + 1;
            }
        }

        return (nums1[i] + nums2[j]) / 2;



    }




}
