package dfs;

import datastructure.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-iii/
 *
 * Author:   softtwilight
 * Date:     2020/06/08 21:36
 */
public class _437_E_Path_Sum_III {
    private static final _437_E_Path_Sum_III instance = new _437_E_Path_Sum_III();

    public static void main(String[] args) {
        TreeNode input = TreeNode.createByArray(new Integer[] {10,5,-3,3,2,null,11,3,-2,null,1,null});

        System.out.println(instance.pathSum(input, 8));
    }

    /**
     * 用list效率其实很低，需要遍历的加减sum值,
     * 最开始的思路其实是用map的，类似与求两数合为target的解法，
     * 用map比较麻烦的地方在于，怎么记录最近的值
     *
     * 比如node 1， 2， 3， 4 那么3， 4的合等于sum 4 - sum 2
     * 运用这个思路，我们只需要纪录卷积就可以了。而且不用回头修改sum值
     */
    public int pathSum(TreeNode root, int sum) {
        List<Integer> pathSum = new ArrayList<>();
        dfs(root, pathSum, sum);
        return result;
    }

    public int pathSum2(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return dfs2(root, preSum, sum, 0);
    }

    private int dfs2(TreeNode root, Map<Integer, Integer> preSum, int sum, int curSum) {
        if (root == null) return 0;
        int result = 0;
        curSum += root.val;
        if (preSum.containsKey(curSum - sum)) {
            result += preSum.get(curSum - sum);
        }

        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        result += dfs2(root.left, preSum, sum, curSum);
        result += dfs2(root.right, preSum, sum, curSum);

        preSum.put(curSum, preSum.get(curSum) - 1);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> pathSum, int sum) {
        if (root == null) return;
        pathSum.add(0);
        for (int i = 0; i < pathSum.size(); i++) {
            int newSum = root.val + pathSum.get(i);
            if (sum == newSum) {
                result++;
            }
            pathSum.set(i, newSum);
        }
        dfs(root.left, pathSum, sum);
        dfs(root.right, pathSum, sum);

        for (int i = 0; i < pathSum.size(); i++) {
            int newSum = pathSum.get(i) - root.val;
            pathSum.set(i, newSum);
        }
        pathSum.remove(pathSum.size() - 1);
    }
    int result = 0;
}
