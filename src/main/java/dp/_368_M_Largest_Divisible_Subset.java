package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2021/01/02 22:09
 */
public class _368_M_Largest_Divisible_Subset {
    private static final _368_M_Largest_Divisible_Subset instance = new _368_M_Largest_Divisible_Subset();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 8, 9, 15, 21, 27, 16, 81, 243};
        System.out.println(instance.largestDivisibleSubset(nums));
    }

    /**
     * bottom up dp
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<Integer>[] memo = new List[nums.length];
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >=0; i--) {
            List<Integer> sub = new ArrayList<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    if (memo[j].size() > sub.size()) {
                        sub = memo[j];
                    }
                }
            }
            sub  = new ArrayList<>(sub);
            sub.add(nums[i]);
            memo[i] = sub;
            if (sub.size() > result.size()) {
                result = sub;
            }
        }
        return result;
    }



}
