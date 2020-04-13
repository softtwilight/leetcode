package array;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c,
 * and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * Author:   softtwilight
 * Date:     2020/04/13 15:27
 */
public class _18_4sum {
    public static void main(String[] args) {

    }


    /**
     * 先考虑两数求和的问题，未排序的情况下，暴力求解为O（n^2）的复杂度
     * 如果我们先将数组排序（n * log（n））的复杂度，然后可以用两个指针，从两端开始遍历
     * 就只需要遍历一次排序后的数组，order的信息，意味着我们可以提前结束搜索，而且大小的关系
     * 可以帮组我们对下标进行操作。所以复杂度为n * log（n） + n 还是n*log（n）。
     *
     * 当问题变成多个数进行求目标和的时候，让其中两个变量利用上面两数求和的方法来求解，其余变量遍历。
     * 还有别的方法吗，比方说剩余一个变量，然后2分查找。n * log（n） > n; 即使最后理论上复杂度是一样的，
     * 但是为什么呢，这是数学上的结论，有什么直观的地方吗，
     * 二分查找总是在中点开始查找，相当于对要找的值一无所知，而双指针则是每查找一次，范围越来越小
     * 相当于学习了查找过程，一个方向是变大，一个方向是变小，总有一种方法逼近target。咸了加水，淡了加盐。
     *
     * 还有一种方法，就是把值放入hashMap，如果存在（target - nums[i]）则addToList,如果不存在，就将
     * num[i] put入map。时间复杂度为O（n），引入了额外的空间复杂度O（n）
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length -2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {

                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            break;
                        } else if (sum > target) {
                            break;
                        }

                    }
                    while (k < nums.length - 1 && nums[k] == nums[k + 1]){
                        k++;
                    }
                }

                while (j < nums.length - 1 && nums[j] == nums[j + 1]){
                    j++;
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]){
                i++;
            }
        }
        return result;
    }



    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);

        //用来估算最值，减少循环
        int max = nums[nums.length - 1];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length-3;i++) {

            //去重，除去初始值
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            //max
            if (nums[i] + 3 * max < target) {
                continue;
            }

            //min
            if( nums[i] * 4 > target ) {
                break;
            }

            for (int j = i + 1; j < nums.length-2; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                if( nums[i] + nums[j] + 2 * max < target )
                    continue;
                if( nums[i] + nums[j] * 3 > target ) {
                    break;
                }

                int lo = j+1;
                int hi = nums.length-1;
                while (lo < hi) {
                    int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        lo++;
                        hi--;

                        //去重
                        while (lo < hi && nums[lo] == nums[lo-1]) {
                            lo++;
                        }
                        while (hi > lo && nums[hi] == nums[hi+1]) {
                            hi--;
                        }
                    } else if (sum > target) {
                        hi--;
                    } else {
                        lo++;
                    }
                }
            }
        }
        return list;
    }


}
