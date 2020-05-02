package banarysearch;

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
                continue;
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
     * 实际上我们反向找，每移动一个步，就将nums[i]放入一个有序的map，value是i，
     * 这样只用向前找到仅仅小于当前值的坐标j，然后遍历到该坐标， count[j]
     * 加上i ~ j过程中小于num[i]的数。
     * 如果在向前找的过程中，有等于的坐标k，我们返回count[k] + 小于的数。
     *
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
