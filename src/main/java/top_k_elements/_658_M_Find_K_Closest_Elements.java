package top_k_elements;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 *
 * Author:   softtwilight
 * Date:     2020/06/19 22:04
 */
public class _658_M_Find_K_Closest_Elements {
    private static final _658_M_Find_K_Closest_Elements instance = new _658_M_Find_K_Closest_Elements();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 3 ms	41.3 MB
     * 这题是做过的
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0, hi = arr.length - k;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int left = arr[mid];
            int right = arr[mid + k];
            if (left > x) {
                hi = mid;
            } else if (right < x) { // 这行其实和33行是可以合并的， 同样29和35也是的
                lo = mid + 1;
            } else if (x - left > right - x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(arr[lo + i]);
        }
        return result;
    }
}
