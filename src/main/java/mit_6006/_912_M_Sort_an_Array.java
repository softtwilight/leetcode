package mit_6006;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/10/24 22:42
 *
 * https://leetcode.com/problems/sort-an-array/
 *
 * 6006 第三课相关
 */
public class _912_M_Sort_an_Array {
    private static final _912_M_Sort_an_Array instance = new _912_M_Sort_an_Array();

    public static void main(String[] args) {
        int[] nums = {5,2,3,1};
        instance.insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * insert sort
     */
    public int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (nums[j] >= nums[j -1]) break;
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
        return nums;
    }


    /**
     * mergeSort
     */
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (hi - lo) / 2 + lo;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {

        // 这个地方可以创建一半大小的数组， 然后将左边拷贝到新数组， 然后直接merge到原来的数组
        // 可以让空间复杂度的系数变为0.5
        int[] merged = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= hi) {
            if (nums[i] < nums[j]) {
                merged[k] = nums[i++];
            } else {
                merged[k] = nums[j++];
            }
            k++;
        }
        while (i <= mid) {
            merged[k++] = nums[i++];
        }
        while (j <= hi) {
            merged[k++] = nums[j++];
        }
        System.arraycopy(merged, 0, nums, lo, merged.length);
    }


}
