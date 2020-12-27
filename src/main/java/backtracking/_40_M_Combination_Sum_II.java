package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/27 22:18
 */
public class _40_M_Combination_Sum_II {
    private static final _40_M_Combination_Sum_II instance = new _40_M_Combination_Sum_II();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * small change from #39
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
                helper(i + 1, candidates, target - candidates[i], list);
            }
            list.remove(list.size() - 1);
            while (i < candidates.length - 1 && candidates[i + 1] == candidates[i]) {
                i++;
            }
        }
    }
}
