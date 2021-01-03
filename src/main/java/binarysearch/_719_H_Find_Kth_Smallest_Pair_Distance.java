package binarysearch;

import java.util.Arrays;
import java.util.Random;

/**
 * Author:   softtwilight
 * Date:     2021/01/03 21:14
 *
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 */
public class _719_H_Find_Kth_Smallest_Pair_Distance {
    private static final _719_H_Find_Kth_Smallest_Pair_Distance instance = new _719_H_Find_Kth_Smallest_Pair_Distance();

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5};
        System.out.println(instance.smallestDistancePair(nums, 3));
    }

    /**
     * Memory Limit Exceeded
     *
     * 先求所有结合， 然后在找k-th最小， （利用快排的思想）
     * 但是内存占用太大了， 没过test
     */
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        int[] distance = new int[n * (n - 1) / 2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dis = Math.abs(nums[j] - nums[i]);
                distance[index++] = dis;
            }
        }
        return findKthSmallest(distance, k - 1);
    }

    public int findKthSmallest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        Random ra = new Random();
        while (lo <= hi) {
            int pivot = lo + ra.nextInt(hi - lo + 1);
            int pivotIndex = partition(nums, lo, hi, pivot);
            if (pivotIndex == k) break;
            if (pivotIndex > k) {
                hi = pivotIndex - 1;
            } else {
                lo = pivotIndex + 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int lo, int hi, int pivot) {
        swap(nums, pivot, hi);
        int storeIndex = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < nums[hi]) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, hi);
        return storeIndex;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
