package binarysearch;

import java.util.*;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Author:   softtwilight
 * Date:     2020/05/02 21:40
 */
public class _315_Count_of_Smaller_Numbers_After_Self {

    public static void main(String[] args) {
        int[] input = {5,2,6,1};
        _315_Count_of_Smaller_Numbers_After_Self instance = new _315_Count_of_Smaller_Numbers_After_Self();
        System.out.println(instance.countSmaller3(input));
    }


    /**
     * 我们试试用二分查找的方法来。
     * 最开始的想法是这样的，没遍历一个数，就对之后的数组排序，
     * 然后利用二分查找，找到小于当前值的坐标，然后就通过下标找到个数了。
     * 复杂度是Sum（i 属于0 ~ n） （i * log i + log i） n^2 * log(n)的复杂度。
     * 参考 https://math.stackexchange.com/questions/1465987/sum-of-logs-in-the-form-of-1-log1-2-log2-ldots-n-log-n
     *
     * 其实这里很多排序是多余的。
     * 然后我们发现可以从尾部开始遍历，每遍历一个元素，就对其排序。
     * 如果每次遍历的排序是一个插入排序，这样复杂度变成了 n * （ n + log（n））
     * 实际上我们在搜索的过程中已经找到元素要插入的位置了，不知道能不能把排序单个元素的复杂度降下来。
     * 先试一下吧，感觉这个复杂还是很高。
     *
     * 461 ms	45.2 MB
     * 比第一个解法的耗时还要多。是对数组排序太耗时了，让复杂度无法降下来。
     *
     * 优化的思路在排序上，leetcode上看到有用mergesort实现的
     *
     */
    public List<Integer> countSmaller3(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int[] counts = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            int j = lower(nums, i);
            counts[i] = j - i;
            // 排序数组
            for (int k = i; k <= j - 1; k++) {
                int temp = nums[k + 1];
                nums[k + 1] = nums[k];
                nums[k] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            result.add(counts[i]);
        }
        return result;
    }

    private int lower(int[] nums, int i) {
        int target = nums[i];
        int lo = i + 1;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo - 1;
    }


    /**
     * 我们反向找，每移动一个步，就将nums[i]放入一个有序的map，value是i，
     * 这样只用向前找到仅仅小于当前值的坐标j，然后遍历到该坐标， count[j]
     * 加上i ~ j过程中小于num[i]的数。
     * 如果在向前找的过程中，有等于的坐标k，我们返回count[k] + 小于的数。
     *
     * 实现得很复杂了，但是思路还算清晰，一次就pass了。
     *  291 ms + 46.9 MB
     *
     *  这里搜索和插入tree map的复杂度是 log n， 然后遍历的复杂度不好计算。
     *  总的复杂度是介于（n * log n 和 n ^ 2 之间的）
     *  然后我好奇提交了一下最暴力版本，不出意外的Time Limit Exceeded
     *
     *  这个表现应该还是思路有问题了。
     *
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        int[] counts = new int[nums.length];
        //key为nums[i]，数组第一位记录上一个nums[i]的坐标， 数组第二位记录nums[i]此前出现的个数
        TreeMap<Integer, int[]> indexMemo = new TreeMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            Map.Entry<Integer, int[]> lowerEntry = indexMemo.lowerEntry(nums[i]);
            int[] sameEntry = indexMemo.get(nums[i]);
            if (sameEntry == null) {
                indexMemo.put(nums[i], new int[] {i, 1});
            } else {
                indexMemo.replace(nums[i], new int[] {i, sameEntry[1] + 1});
            }
            if (lowerEntry == null) {
                counts[i] = 0;
            } else {
                int count = 0;
                int[] value = lowerEntry.getValue();
                int k;
                for (k = i + 1; k < value[0]; k++) {
                    if (nums[k] == nums[i]) {
                        counts[i] = count + counts[k];
                        break;
                    } else if (nums[k] < nums[i]) {
                        count++;
                    }
                }
                if (k == value[0]) {
                    count += counts[value[0]] + value[1];
                    counts[i] = count;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            result.add(counts[i]);
        }
        return result;
    }

    /**
     * 用暴力的解法很简单，O（n^2)的复杂度，没有提交。
     * 这道题目里重复计算的部分并没有很简单的显示出来。
     */
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }
}
