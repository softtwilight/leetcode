package dfs;

import datastructure.TreeNode;

/**
 * Author:   softtwilight
 * Date:     2020/12/16 21:47
 *
 * https://leetcode.com/problems/symmetric-tree/
 */
public class _101_E_Symmetric_Tree {
    private static final _101_E_Symmetric_Tree instance = new _101_E_Symmetric_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     *
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return false;
        if (left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
