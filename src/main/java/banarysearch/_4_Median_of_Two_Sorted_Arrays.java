package banarysearch;

import static java.lang.Math.*;
import java.util.Arrays;
import java.util.Enumeration;

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
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
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
     * 不管和为奇数还是偶数，我们都需要找到坐标为merge[(n + m) / 2] 的值，
     * 也就是我们要找到一个数，在两个数组中共(n+m)/2个数小于它。我们先不考虑奇偶的情况
     *
     * 今天又尝试了一下，还是没能写出来，首先是思路不清晰，在脑子里没有一个清晰的解法，
     * 循环停在哪个点，代表的意义是什么，特别是在偶数的情况情况更加复杂一点，是需要算两个
     * 值的平均值。
     *
     *  参考了别人的思路，解了一版
     *  https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
     *
     *  最关键的还是思维的转变，想象中位数把数组分为左右两个部分，满足下列条件：
     *  1. 和为偶数时，左右长度相等。
     *  2. 和为奇数时，可以把中位数放左边（放右边也可以），这是左边的长度等于右边加一。
     *  3. 左边最大的数小于等于右边最小的数。这点又可以通过下面两点得到保证：
     *      a. A数组左边最大值小于（等于）B数组右边最小值。
     *      b. B数组左边最大值小于（等于）A数组右边最小值。
     *  4. 1 和 2 可以表达为在 i ，j的左边有(n + m + 1) / 2个元素。
     *  5. 停止搜素时基于i， j 计算leftMax 和 rightMin。
     *
     *  数组下标的要不要+-1， 判断条件要不要加等好，这些在没有分析清楚前是十分难判断的，
     *  最好是先画好图。
     *
     *  我最开始的范围选择是0 ～ A.length - 1, 这时候的变量代表的是数组下标，这样就需要考虑
     *  A 的最小值也不贡献rightMin 和 A的最大值也不贡献leftMax的情况，变得很复杂。
     *  这时候换一个抽象也很重要，0 ～ A.length, 变量是各个数组元素之间的空隙。
     *
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }
        int n = A.length, m = B.length;
        int sum = (n + m + 1) / 2;
        int lo = 0, hi = n, i = 0, j = 0;
        while (lo <= hi) {
            i = lo + (hi - lo) / 2;
            j = sum - i;
            if (i != 0 && j != m && A[i - 1] > B[j]) {
                hi = i - 1;
            } else if (j != 0 && i != n && B[j - 1] > A[i]) {
                lo = i + 1;
            } else {// i is perfect
                int leftMax = 0;
                if (i == 0) leftMax = B[j - 1];
                else if (j == 0)  leftMax = A[i - 1];
                else leftMax = Math.max(A[i - 1], B[j - 1]);
                if ((n + m) % 2 == 1) return leftMax;

                int rightMin = 0;
                if (i == n) rightMin = B[j];
                else if (j == m) rightMin = A[i];
                else rightMin = Math.min(A[i], B[j]);

                return (leftMax + rightMin) / 2.0;
            }
        }
        return 0;
    }

    public static double findMedianSortedArrays2(int[] A, int[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays2(B, A);
        }
        int m = A.length, n = B.length;
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }


}
