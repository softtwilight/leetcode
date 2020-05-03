package banarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/05/03 22:22
 */
public class _315_Count_V2 {

    public static void main(String[] args) {
        int[] input = {5,2,6,1};
        _315_Count_V2 instance = new _315_Count_V2();
        System.out.println(instance.countSmaller(input));
    }

    /**
     * 我们用mergesort来实现
     * 4 ms	+ 41.8 MB
     *
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] res = new int[nums.length];
        int[] index = new int[res.length];
        for (int i = 0; i < res.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, index, 0, nums.length-1, res);
        List<Integer> list = new LinkedList<>();
        for (int i : res) {
            list.add(i);
        }
        return list;
    }

    private void mergeSort(int[] nums, int[] index, int lo, int hi, int[] res) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, index, lo, mid, res);
        mergeSort(nums, index, mid + 1, hi, res);
        merge(nums, index, lo, mid, mid + 1, hi, res);
    }

    private void merge(int[] nums, int[] index, int llo, int lhi, int rlo, int rhi, int[] res) {

        int t = llo;
        int length = lhi - llo + rhi - rlo + 2;
        int[] newNums = new int[length];
        int[] newIndex = new int[length];
        int i = 0;
        int count = 0;
        while (llo <= lhi || rlo <= rhi) {
            if (llo > lhi) {
                newNums[i] = nums[rlo];
                newIndex[i] = index[rlo];
                rlo++;
                count++;
            } else if (rlo > rhi) {
                newNums[i] = nums[llo];
                newIndex[i] = index[llo];
                res[index[llo]] += count;
                llo++;
            } else if (nums[llo] <= nums[rlo]) {
                newNums[i] = nums[llo];
                newIndex[i] = index[llo];
                res[index[llo]] += count;
                llo++;
            } else {
                newNums[i] = nums[rlo];
                newIndex[i] = index[rlo];
                rlo++;
                count++;
            }
            i++;
        }

        System.arraycopy(newNums, 0, nums, t, length);
        System.arraycopy(newIndex, 0, index, t, length);

    }
}
