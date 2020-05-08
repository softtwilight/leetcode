package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 *
 * Author:   softtwilight
 * Date:     2020/05/08 22:31
 */
public class _658_M_Find_K_Closest_Elements {
    private static final _658_M_Find_K_Closest_Elements instance = new _658_M_Find_K_Closest_Elements();

    public static void main(String[] args) {
        int[] input = {1,1,2,2,2,2,2,3,3};
        System.out.println(instance.findClosestElements(input, 3, 3));
        System.out.println(instance.findClosestElements(input, 4, -1));
    }

    /**
     * 	3 ms  +	41.5 MB
     * 	二分查找最难的还是边界条件和判断条件。
     * 	虽然是两个坐标，但是因为存在对应关系，所以我们转换为对一个坐标进行二分。
     *
     *
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }

        int len = arr.length;
        int lo = 0;
        int hi = len - k;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // 最开始用绝对值计算的，通过了case但是在post里发现是错误
            // 怎么避免这个错误呢！最开始判断了arr[0] 和 arr[len - 1]与x的版本里
            // 用绝对值判断是可以的，因为确定了x 和 arr的位置关系。
            // 而这个简化的版本，因为 lo = 2， hi = 2时， 绝对值是一样的，导致左移了指针，得出错误答案
//            int diff =  Math.abs(arr[mid] - x) - Math.abs(arr[mid + k] - x);
//            if (diff <= 0) {
            // 相等的情况我们也移动右边界，因为左边是prefer的
            if (x - arr[mid] <= arr[mid + k] - x) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        List<Integer> result = new ArrayList<>(len);
        for (int i = lo; i < lo+k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

}
