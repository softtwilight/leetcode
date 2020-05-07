package binarysearch;

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
     * 思路是这样的，我们假设左右两边都是已经排好序的数组，这时候我们merge两个数组
     * 如果新数组来自left，则count[index of 该点的值] += 此前merge的右边数组的个数。
     * 为什么呢，因为右边位于left的右边（显而易见），同时先merge的数小于left现在所处的值。
     * 刚好是题目要求的条件。
     * 所以我们一次merge，就能确定一半（left）的count数，递归之后就能求解所有的left了。
     * （对右边来说，本身是生序的，所以右边不存在比自己小的数，同时自己又不存在右边，故不需要 +count）
     * （但右边的数组在子递归中是可以有部分作为左边的）
     * 5 | 2 | 6 | 1
     * 0 | 1 | 2 | 3
     * 这里有一个麻烦的地方是，需要记录merge前数组数量和index的关系
     * 可以理解为一个对象，包含value 和 index， 根据value对该对象进行merge排序，
     * 然后根据index 来确定count[]的下标。
     *
     * leetcode上有直接排序index， 而不排序nums的， 我认为同时排序可能会更直观一点，不影响复杂度。
     *
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
        int count = 0; //记录merge来自右边数组的个数。
        while (llo <= lhi || rlo <= rhi) {
            if (llo > lhi) {
                newNums[i] = nums[rlo];
                newIndex[i] = index[rlo];
                rlo++;
                count++;
            } else if (rlo > rhi) {
                newNums[i] = nums[llo];
                newIndex[i] = index[llo];
                // 我们知道index数组包含着原数组的index，也就是result数组的index
                // 在原来的结果上加上此次merge来自右边数组的个数。
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
        //将merge的数组copy进原数组。
        System.arraycopy(newNums, 0, nums, t, length);
        System.arraycopy(newIndex, 0, index, t, length);
    }
}
