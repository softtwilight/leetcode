package top_k_elements;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Author:   softtwilight
 * Date:     2020/06/16 21:35
 */
public class _215_M_Kth_Largest_Element_in_an_Array {
    private static final _215_M_Kth_Largest_Element_in_an_Array instance = new _215_M_Kth_Largest_Element_in_an_Array();

    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(instance.findKthLargest3(nums, 1));
    }

    /**
     *
     */
    public int findKthLargest3(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int pivotIndex = quickSort(nums, lo, hi);
            if (pivotIndex == k) return nums[k];
            if (pivotIndex > k) {
                hi = pivotIndex - 1;
            } else {
                lo = pivotIndex + 1;
            }
        }
        return -1;

    }

    private int quickSort(int[] nums, int lo, int hi) {
        int pivot = lo;
        lo++;
        hi--;
        while (lo < hi) {
            while (lo < hi && nums[lo] < nums[pivot]) {
                lo++;
            }
            while (lo < hi && nums[hi] > nums[pivot]) {
                hi--;
            }
            swap(nums, lo, hi);
        }
        swap(nums, pivot, hi - 1);
        return hi - 1;
    }

    void  swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 3 ms	+ 39.7 MB
     * 利用堆， 依次取出k个元素， 我们判断k 和 数组一般的长度，决定用最大堆还是最小堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue;
        if (k > nums.length / 2 ) {
            priorityQueue = new PriorityQueue<>();
            k = nums.length + 1 - k;
        } else {
            priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        }
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(nums[i]);
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = priorityQueue.poll();
        }
        return result;
    }


    /**
     * 4 ms	+ 39.7 MB
     * 更简单的priority queue 版本 可以选择像上面那样， 把空间复杂度控制在Min （k， n / 2）
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
}
