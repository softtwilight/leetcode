package dp;

import datastructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:   softtwilight
 * Date:     2021/01/20 23:18
 */
public class _968_H_Binary_Tree_Cameras {
    private static final _968_H_Binary_Tree_Cameras instance = new _968_H_Binary_Tree_Cameras();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     *  try to use dp to solve the problem, it's pretty hard to debug
     *  the middle case didn't pass. paste a greedy solution, bottom-up,
     *  and try to use as less camera as possible
     */
    public int minCameraCover(TreeNode root) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(root, 0, memo);
    }

    private int dfs(TreeNode root, int i, Map<Integer, Integer> memo) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && i == 0) return 1;
        if (memo.containsKey(root.hashCode() + i)) return memo.get(root.hashCode() + i);
        int result = Integer.MAX_VALUE;
        if (i == -1) {
            result = 1 + dfs(root.left, 1, memo) + dfs(root.left, 1, memo);
        } else if (i == 0) {
            if (root.left != null)
                result = dfs(root.left, -1, memo) + dfs(root.right, 0, memo);
            if (root.right != null)
                result = Math.min(result, dfs(root.left, 0, memo) + dfs(root.right, -1, memo));
            result = Math.min(result, 1 + dfs(root.left, 1, memo) + dfs(root.right, 1, memo));
        } else if (i == 1) {
            result = dfs(root.left, 0, memo) + dfs(root.right, 0, memo);
        }
        memo.put(root.hashCode() + i, result);
        return result;
    }



    int res = 0;
    public int minCameraCover2(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }
}
