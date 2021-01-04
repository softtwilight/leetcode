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




    public int smallestDistancePair2(int[] nums, int k) {
        //strategy: Binary Search + 2 Pointer + Prefix Sum
        Arrays.sort(nums);
        //low never works but high always works
        int low = -1, high = nums[nums.length - 1] - nums[0];
        while (true) {
            //reached boundary where low doesn't hold kth element but high just covers it
            if (low + 1 == high) {
                break;
            }
            int mid = (low + high) / 2;
            //if it works give to high but if it doesn't work give it to low
            if (possible(nums, mid, k)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }
    public boolean possible(int[] nums, int guess, int k) {
        //Two Pointer + Prefix Sum
        int count = 0, left = 0;
        //loop through all numbers
        for (int right = 0; right < nums.length; right++) {
            //continue to move left pointer until the difference is within our guess
            while (nums[right] - nums[left] > guess) {
                left++;
            }
            //this includes all differences at or below our guess so far
            count += right - left;
        }
        //see if we have more differences than k
        //this count is a sum of all differences at or below our guess
        return count >= k;
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
