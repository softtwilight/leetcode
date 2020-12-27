package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/27 21:31
 *
 * https://leetcode.com/problems/combination-sum/
 */
public class _39_M_Combination_Sum {
    private static final _39_M_Combination_Sum instance = new _39_M_Combination_Sum();

    public static void main(String[] args) {
        int[] inputs = {2,3,5};
        System.out.println(instance.combinationSum(inputs, 8));
    }

    /**
     * backtracking
     *
     * a easy way to brute force ?
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        helper(0, candidates, target, list);
        return result;
    }


    List<List<Integer>> result = new ArrayList<>();

    private void helper(int lo, int[] candidates, int target, List<Integer> list) {
        for (int i = lo; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            list.add(candidates[i]);
            if (candidates[i] == target) {
                result.add(new ArrayList<>(list));
            } else {
                helper(i, candidates, target - candidates[i], list);
            }
            list.remove(list.size() - 1);
        }
    }
}
